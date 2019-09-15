package com.api.controller

import com.api.service.OperationService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@RequestMapping("/api/v1")
@RestController
class ReadController @Autowired constructor(
    private val operationService: OperationService
) {
    @ApiOperation(value = "get com.api.data from redis if exist. if not get com.api.data from other com.api.data sources that responded fastest. ")
    @GetMapping("/read/{key}")
    fun read(@PathVariable("key") key: String) : String {
        return operationService.read(key)
    }
}