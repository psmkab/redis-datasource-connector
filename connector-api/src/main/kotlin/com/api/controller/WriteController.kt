package com.api.controller

import com.api.data.Request
import com.api.service.RedisOperationService
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RestController

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@RequestMapping("/api/v1")
@RestController
class WriteController @Autowired constructor(
    private val redisOperationService: RedisOperationService
) {
    @RequestMapping("write", method = [ PUT, POST ])
    fun write(@RequestBody body: Request) : Single<Boolean> {
        return redisOperationService.write(
            key = body.key,
            value = body.value
        )
    }
}