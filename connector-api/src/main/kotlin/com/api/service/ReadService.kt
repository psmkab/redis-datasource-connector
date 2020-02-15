package com.api.service

/**
 * NAME : sungmin park
 * DATE : 2019-10-12
 */

interface ReadService<in K, out V> {
    fun read(key: K) : V
}