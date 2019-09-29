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
    private val redisOperationHandler: RedisOperationHandler
) : OperationService<String, String> {

    companion object {
        val log = logger<RedisOperationService>()!!
    }

    // todo("exception handling")
    override fun read(key: String): Single<Boolean> {
        log.info("Read data from redis by key:$key")

        key.ifNotNull {
            redisOperationHandler.read(key = key)
        }
    }

    // todo("exception handling")
    override fun write(key: String, value: String): Single<Boolean> {
        log.info("Write data to redis by key:$key value:$value")

        key.ifNotNull {
            redisOperationHandler.write(key = key, value = value)
        }
    }
}