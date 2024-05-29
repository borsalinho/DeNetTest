package com.DeNet.app

import android.app.Application
import com.DeNet.di.AppComponent
import com.DeNet.di.AppModule
import com.DeNet.di.DaggerAppComponent
import com.DeNet.di.DataModule
import com.DeNet.di.DomainModule

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .build()

    }
}