package com.s21.domain.usecases

import com.s21.domain.model.Node
import com.s21.domain.model.NodeId
import com.s21.domain.repository.NodeRepository

class GetNodeByIdUseCase(private val nodeRepository : NodeRepository) {
    suspend fun execute(nodeId: NodeId) : Node{
        return nodeRepository.getNodeById(nodeId = nodeId)
    }
}

