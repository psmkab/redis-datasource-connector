package com.api.repository

import io.reactivex.Completable

interface BackupStorageService<out V> {
    fun get(key: String) : V
    fun write(key: String, value: String): Completable
}