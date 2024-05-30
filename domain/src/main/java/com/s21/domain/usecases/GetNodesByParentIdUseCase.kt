package com.s21.domain.usecases


import com.s21.domain.model.Node
import com.s21.domain.model.NodeId
import com.s21.domain.repository.NodeRepository

class GetNodesByParentIdUseCase(private val nodeRepository : NodeRepository) {
    suspend fun execute(parentId : NodeId) : List<Node>{
        return nodeRepository.getNodesByParentId(parentId = parentId)
    }
}