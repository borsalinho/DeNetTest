package com.s21.domain.repository

import com.s21.domain.model.Node
import com.s21.domain.model.NodeId

interface NodeRepository {
    suspend fun addNode(parent: Node)
    suspend fun getRootNode(): Node
    suspend fun getNodesByParentId(parentId: NodeId): List<Node>

}