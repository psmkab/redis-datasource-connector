package com.api.data

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@RedisHash("Request")
data class Request (
    val key: String,
    val value: String
) : Serializable