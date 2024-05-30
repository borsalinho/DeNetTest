package com.DeNet.di

import android.content.Context
import com.DeNet.presentation.viewmodel.MyViewModel
import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetRootNodeUseCases

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
        addNodeUseCase : AddNodeUseCase,
        getNodesByParentIdUseCase : GetNodesByParentIdUseCase,
        getRootNodeUseCases : GetRootNodeUseCases

    ) : MyViewModel{
        return MyViewModel(
            addNodeUseCase = addNodeUseCase,
            getNodesByParentIdUseCase = getNodesByParentIdUseCase,
            getRootNodeUseCases = getRootNodeUseCases
        )
    }
}