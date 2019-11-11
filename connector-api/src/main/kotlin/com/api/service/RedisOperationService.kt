package com.api.service

import com.api.repository.BackupStorageService
import com.api.repository.RedisOperationHandler
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utils.logger.logger
import utils.validation.ifNotNull

/**
 * NAME : sungmin park
 * DATE : 2019-09-17
 */

@Service
class RedisOperationService @Autowired constructor(
    private val redisOperationHandler: RedisOperationHandler,
    private val backupStorageService: List<BackupStorageService<*>>,
    private val redisMessagePublisher: RedisMessagePublisher
) : ReadService<String, Single<String?>>, WriteService<String, String, Single<Boolean>> {

    companion object {
        val log = logger<RedisOperationService>()!!
    }

    /**
     * if read from redis is succeed, return value.
     * if read from redis is failed, read from other persistent layers which responded fastest.
     */
    override fun read(key: String): Single<String?> {
        log.info("Read data from redis by key:$key")

        return key ifNotNull {
            return@ifNotNull redisOperationHandler.read(key = key)
                .map {
                    if(it != null) it else getValueFromBackupStorage(key = key)
                }
                .onErrorReturn {
                    log.error("Cannot find data in redis w/ persistent layer by key: $key")
                    null
                }
        }
    }

    private fun getValueFromBackupStorage(key: String): String {
        return backupStorageService
            .parallelStream()
            .map { it.get(key) }
            .filter { it != null }
            .map { it.toString() }
            .findAny()
            .orElseThrow {
                RuntimeException("Cannot find any value by key: $key")
            }
    }

    /**
     * if write to redis is success, send event for writing other persistent layers by asynchronous.
     * if write to redis is failed, do not send event for preventing data in-consistency.
     */
    override fun write(key: String, value: String): Single<Boolean> {
        log.info("Write data to redis by key:$key value:$value")

        return key.ifNotNull {
            return@ifNotNull redisOperationHandler.write(key = key, value = value)
                .flatMap {
                    if(it) relayEventToPersistentLayer(key = key, value = value) else Single.just(false)
                }
                .onErrorReturn {
                    log.error("Cannot update persistent layer by key: $key, value: $value")
                    false
                }
        }
    }

    // TODO : MAKE THIS FUNCTION TO ASYNC
    private fun relayEventToPersistentLayer(key: String, value: String): Single<Boolean> {
        return Single.fromCallable {
            redisMessagePublisher.sendMessage(key, value)
            true
        }
        .onErrorReturn {
            log.error("Cannot relay event to persistent layer by key: $key, value: $value")
            false
        }

    }
}