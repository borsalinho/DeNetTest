package com.DeNet.di

import com.DeNet.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

}