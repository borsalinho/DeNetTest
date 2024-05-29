package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.s21.data.storage.model.NodeEntity
import com.s21.data.storage.model.NodeWithChildren

@Dao
interface NodeDao {
    @Transaction
    @Query("SELECT * FROM nodes WHERE id = :id")
    suspend fun getNodeWithChildren(id: String): NodeWithChildren?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node: NodeEntity)

    @Delete
    suspend fun deleteNode(node: NodeEntity)
}