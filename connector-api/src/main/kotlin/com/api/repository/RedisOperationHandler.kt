package com.api.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository
import utils.logger

/**
 * NAME : sungmin park
 * DATE : 2019-09-16
 */

@Repository
class RedisOperationHandler @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    companion object {
        val log = logger<RedisOperationHandler>()
    }

    // todo -> lateinit 깔끔하게 바꿔야 함 혹은 lazy loading으로.
    private lateinit var opsForValue: ValueOperations<String, Any>

    init {
        val opsForValue = redisTemplate.opsForValue()
    }

    fun read(key: String) : String {
        return opsForValue.get(key).toString()
    }

    // todo ("logging은 서비스 레이어에서 처리하는 것이 좋아보임.")
    fun write(key: String, value: String) : Boolean {
        return try {
            opsForValue.set(key, value)
            true
        } catch (e: Exception) {
            log.error("Cannot write data to redis. key: $key value: $value")
            false
        }
    }
}