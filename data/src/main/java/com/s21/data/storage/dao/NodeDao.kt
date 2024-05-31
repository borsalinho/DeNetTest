package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.s21.data.storage.model.NodeEntity


@Dao
interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node: NodeEntity)

    @Query("SELECT * FROM nodes WHERE parentId IS NULL") // пусть пока будет так
    suspend fun getRootNode(): NodeEntity

    @Query("SELECT * FROM nodes WHERE parentId = :parentId")
    suspend fun getNodesByParentId(parentId: Long): List<NodeEntity>

    @Query("SELECT * FROM nodes WHERE id = :id")
    suspend fun getNodeById(id: Long) : NodeEntity

    @Delete
    suspend fun deleteNode(node: NodeEntity)
}