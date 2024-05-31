package com.s21.data.implementation

import com.s21.data.extensions.toEntity
import com.s21.data.extensions.toNode
import com.s21.data.storage.dao.NodeDao
import com.s21.domain.model.Node
import com.s21.domain.model.NodeId
import com.s21.domain.repository.NodeRepository

class NodeRepositoryImpl(private val nodeDao: NodeDao) : NodeRepository {

    override suspend fun addNode(node: Node) {
        nodeDao.insertNode(node = node.toEntity())
    }
    override suspend fun getRootNode(): Node {
        return nodeDao.getRootNode().toNode() }

    override suspend fun getNodesByParentId(parentId: NodeId): List<Node> {
        return nodeDao.getNodesByParentId(parentId = parentId.id).map { it.toNode() }
    }

    override suspend fun getNodeById(nodeId: NodeId): Node {
        return nodeDao.getNodeById(id = nodeId.id).toNode()
    }

    override suspend fun deleteNode(node: Node) {
        nodeDao.deleteNode(node = node.toEntity())
    }
}

