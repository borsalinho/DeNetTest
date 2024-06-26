package com.DeNet.di

import com.s21.domain.repository.NodeRepository
import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.DeleteNodeUseCase
import com.s21.domain.usecases.GetNodeByIdUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetNodeUseCases
import com.s21.domain.usecases.GetRootNodeUseCase
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
    fun provideGetRootNodesUseCases(nodeRepository : NodeRepository) : GetNodeUseCases {
        return GetNodeUseCases(nodeRepository = nodeRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNodeUseCase(nodeRepository : NodeRepository) : DeleteNodeUseCase {
        return DeleteNodeUseCase(nodeRepository = nodeRepository)
    }

    @Provides
    @Singleton
    fun provideGetNodeByIdUseCase(nodeRepository : NodeRepository) : GetNodeByIdUseCase {
        return GetNodeByIdUseCase(nodeRepository = nodeRepository)
    }

    @Provides
    @Singleton
    fun provideGetRootNodeUseCase(nodeRepository : NodeRepository) : GetRootNodeUseCase {
        return GetRootNodeUseCase(nodeRepository = nodeRepository)
    }


}