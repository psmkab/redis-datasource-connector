package com.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@Configuration
class RedisConfig {
    @Value("\${spring.redis.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    private var redisPort: Int? = 6379

    @Bean
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
}