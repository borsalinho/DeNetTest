package com.s21.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s21.data.storage.dao.NodeDao
import com.s21.data.storage.model.NodeEntity

@Database(entities = [NodeEntity::class], version = 1)
abstract class NodeDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
}