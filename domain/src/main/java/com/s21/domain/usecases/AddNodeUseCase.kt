package com.s21.domain.usecases

import com.s21.domain.model.Node
import com.s21.domain.repository.NodeRepository

class AddNodeUseCase(private val nodeRepository : NodeRepository) {
    suspend fun execute(parent: Node){
        return nodeRepository.addNode(parent = parent)
    }
}