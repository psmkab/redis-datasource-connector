package com.api.service

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
    private val redisMessageSubscriber: RedisMessageSubscriber
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

        key.ifNotNull {
            return@ifNotNull redisOperationHandler.read(key = key)
                .map {
                    log.info("Read data from other persistent layer by key: $key")
                    if(it.isNullOrEmpty()) false
                    else it
                }
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
                .map {
                    if(it) true
                    else false
                }
        }
    }
}