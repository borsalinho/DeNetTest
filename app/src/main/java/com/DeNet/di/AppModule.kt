package com.DeNet.di

import android.content.Context
import com.DeNet.presentation.preferencemanager.PreferenceManager
import com.DeNet.presentation.viewmodel.MyViewModel
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
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideMyViewModel(
        context: Context,
        addNodeUseCase : AddNodeUseCase,
        getNodesByParentIdUseCase : GetNodesByParentIdUseCase,
        getRootNodeUseCases : GetNodeUseCases,
        deleteNodeUseCase : DeleteNodeUseCase,
        getNodeByIdUseCase : GetNodeByIdUseCase,
        preferenceManager : PreferenceManager,
        getRootNodeUseCase : GetRootNodeUseCase
    ) : MyViewModel{
        return MyViewModel(
            context = context,
            addNodeUseCase = addNodeUseCase,
            getNodesByParentIdUseCase = getNodesByParentIdUseCase,
            getNodeUseCases = getRootNodeUseCases,
            deleteNodeUseCase = deleteNodeUseCase,
            getNodeByIdUseCase = getNodeByIdUseCase,
            preferenceManager = preferenceManager,
            getRootNodeUseCase = getRootNodeUseCase
        )
    }

    @Provides
    @Singleton
    fun providePreferenceManager(context : Context): PreferenceManager {
        return PreferenceManager(context = context)
    }
}