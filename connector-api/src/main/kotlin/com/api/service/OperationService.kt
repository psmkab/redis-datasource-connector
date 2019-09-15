package com.api.service

import com.api.repository.RedisOperationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utils.logger

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@Service
class OperationService @Autowired constructor(
    private val operationHandler: RedisOperationHandler
) {
    companion object {
        val log = logger<OperationService>()
    }

    fun read(key: String) : String {
        return operationHandler.read(key)
    }

    fun write(body: String) : String {
//        return operationHandler.write(key = )
        return "success"
    }
}