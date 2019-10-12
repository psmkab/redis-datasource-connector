package com.api.service

import com.api.data.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.Topic
import org.springframework.stereotype.Component

/**
 * NAME : sungmin park
 * DATE : 2019-10-12
 */

@Component
class RedisMessagePublisher @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, String>,
    private val topic: Topic
) {
    fun sendMessage(key: String, value: String) {
        redisTemplate.convertAndSend(
            topic.topic,
            Request(key = key, value = value)
        )
    }
}