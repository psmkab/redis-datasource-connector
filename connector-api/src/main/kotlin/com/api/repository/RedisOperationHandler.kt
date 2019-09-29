package com.api.repository

import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository
import utils.logger.logger

/**
 * NAME : sungmin park
 * DATE : 2019-09-16
 */

@Repository
class RedisOperationHandler @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, String>
) {
    companion object {
        val log = logger<RedisOperationHandler>()
    }

    // todo -> lateinit 깔끔하게 바꿔야 함 혹은 lazy loading으로.
    private lateinit var opsForValue: ValueOperations<String, String>

    init {
        val opsForValue = redisTemplate.opsForValue()
    }

    /**
     * if no data in redis by key, it's not error, just assume failed case.
     */
    fun read(key: String) : Single<String?> {
        return Single.fromCallable {
            opsForValue.get(key)
        }
        .onErrorReturnItem(null)
    }

    /**
     * if write to redis is success, return true, else return false
     */
    fun write(key: String, value: String) : Single<Boolean> {
        return Single.fromCallable {
            opsForValue.set(key, value)
            true
        }
        .onErrorReturnItem(false)
    }

}