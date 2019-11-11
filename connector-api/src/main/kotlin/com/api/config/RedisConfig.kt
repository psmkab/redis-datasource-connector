package com.api.config

import com.api.service.RedisMessageSubscriber
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.Topic
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@Configuration
class RedisConfig {
    companion object {
        private const val topic = "REDIS_TOPIC"
    }

    @Value("\${spring.redis.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    private var redisPort: Int? = 6379

    fun redisConnectionFactory() : RedisConnectionFactory {
        return JedisConnectionFactory(RedisStandaloneConfiguration(redisHost, redisPort!!))
    }

    @Bean
    @ConditionalOnBean(type = ["redisConnectionFactory"])
    fun redisTemplate() : RedisTemplate<String, String> {
        return RedisTemplate<String, String>().apply {
            setConnectionFactory(redisConnectionFactory())
            keySerializer = StringRedisSerializer()
        }
    }

    @Bean
    fun topic() = ChannelTopic(topic)

    @Bean
    @ConditionalOnBean(type = ["redisConnectionFactory", "eventListener", "topic"])
    fun redisMessageListenerContainer(redisConnectionFactory: RedisConnectionFactory,
                                      eventListener: MessageListenerAdapter,
                                      topic: Topic) : RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            this.setConnectionFactory(redisConnectionFactory)
            this.addMessageListener(eventListener, topic)
        }
    }

    @Bean
    @ConditionalOnBean(type = ["redisMessageListenerContainer"])
    fun eventListener() : MessageListenerAdapter {
        return MessageListenerAdapter(RedisMessageSubscriber())
    }

}