package com.s21.data.implementation

import com.s21.domain.model.Node
import com.s21.domain.repository.NodeRepository

class NodeRepositoryImpl() : NodeRepository {

    override suspend fun addNode(parent: Node) {
        TODO("Not yet implemented")
    }

    override suspend fun getNode(nodeId: String): Node? {
        TODO("Not yet implemented")
    }

    override suspend fun removeNode(node: Node) {
        TODO("Not yet implemented")
    }
}