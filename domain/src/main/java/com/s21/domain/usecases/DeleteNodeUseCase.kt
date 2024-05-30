package com.s21.domain.usecases

import com.s21.domain.model.Node
import com.s21.domain.repository.NodeRepository

class DeleteNodeUseCase(private val nodeRepository : NodeRepository) {
    suspend fun execute(node: Node){
        return nodeRepository.deleteNode(node = node)
    }
}