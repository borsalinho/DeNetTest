package com.s21.domain.repository

import com.s21.domain.model.Node

interface NodeRepository {
    suspend fun addNode(parent: Node)
    suspend fun removeNode(node: Node)
    suspend fun getNode(nodeId: String): Node?
}