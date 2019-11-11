package com.api.service

import com.api.repository.BackupStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import utils.logger.logger

/**
 * NAME : sungmin park
 * DATE : 2019-10-12
 */

@Component
class RedisMessageSubscriber @Autowired constructor(
    private val backupStorageService: List<BackupStorageService<*>>
) : MessageListener {
    companion object {
        val log = logger<RedisMessageSubscriber>()
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        log.info("Get message, ${message.body.toString()}")

        // TODO : HOW TO FIX DATA..
//        backupStorageService
//            .map { it.write(message.body.toString()) }
    }
}