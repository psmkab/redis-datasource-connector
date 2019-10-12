package com.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

/**
 * NAME : sungmin park
 * DATE : 2019-10-12
 */

@Component
class RedisMessageSubscriber @Autowired constructor(
    // todo : add all persistent layers
) : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {

    }
}