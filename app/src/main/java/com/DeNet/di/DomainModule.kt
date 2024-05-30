package com.DeNet.di

import com.s21.domain.repository.NodeRepository
import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetRootNodeUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule{

    @Provides
    @Singleton
    fun provideAddNodeUseCase(nodeRepository : NodeRepository) : AddNodeUseCase {
        return AddNodeUseCase(nodeRepository = nodeRepository)
    }


    @Provides
    @Singleton
    fun provideGetNodesByParentIdUseCase(nodeRepository : NodeRepository) : GetNodesByParentIdUseCase {
        return GetNodesByParentIdUseCase(nodeRepository = nodeRepository)
    }

    @Provides
    @Singleton
    fun provideGetRootNodesUseCases(nodeRepository : NodeRepository) : GetRootNodeUseCases {
        return GetRootNodeUseCases(nodeRepository = nodeRepository)
    }



}