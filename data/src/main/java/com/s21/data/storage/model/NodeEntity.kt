package com.s21.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.s21.domain.model.Node

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val parentId: Long?
)
