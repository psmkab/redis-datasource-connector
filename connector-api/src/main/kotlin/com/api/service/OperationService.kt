package com.api.service

import io.reactivex.Single

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */


/**
 * @see not for ServiceLoader usage. just specify action.
 */
interface OperationService<K, V> {
    fun read(key: K) : Single<Boolean>
    fun write(key: K, value: V) : Single<Boolean>
}