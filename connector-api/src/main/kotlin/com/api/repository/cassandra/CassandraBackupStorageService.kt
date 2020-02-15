package com.api.repository.cassandra

import com.api.repository.BackupStorageService
import io.reactivex.Completable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CassandraBackupStorageService @Autowired constructor(

) : BackupStorageService<String> {
    override fun get(key: String): String {
        return "c*"
    }

    override fun write(key: String, value: String): Completable {
        return Completable.complete()
    }
}