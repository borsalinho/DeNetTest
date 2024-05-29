package com.s21.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val parentId: String?
)
