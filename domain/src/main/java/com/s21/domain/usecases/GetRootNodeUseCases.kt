package com.s21.domain.usecases

import com.s21.domain.model.Node
import com.s21.domain.repository.NodeRepository

class GetRootNodeUseCases(private val nodeRepository : NodeRepository) {
    suspend fun execute() : Node{
        return nodeRepository.getRootNode()
    }
}