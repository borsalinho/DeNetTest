package com.s21.data.implementation

import com.s21.data.extensions.toEntity
import com.s21.data.extensions.toNode
import com.s21.data.storage.dao.NodeDao
import com.s21.domain.model.Node
import com.s21.domain.model.NodeId
import com.s21.domain.repository.NodeRepository

class NodeRepositoryImpl(private val nodeDao: NodeDao) : NodeRepository {

    override suspend fun addNode(node: Node) {
        nodeDao.insertNode(node.toEntity())
    }
    override suspend fun getRootNode(): Node {
        return nodeDao.getRootNode().toNode() }

    override suspend fun getNodesByParentId(parentId: NodeId): List<Node> {
        return nodeDao.getNodesByParentId(parentId.id).map { it.toNode() }
    }

}

