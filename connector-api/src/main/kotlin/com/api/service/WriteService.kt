package com.api.service

/**
 * NAME : sungmin park
 * DATE : 2019-10-12
 */

interface WriteService<in K, in V, out R> {
    fun write(key: K, value: V) : R
}