package com.DeNet.di

import android.content.Context
import androidx.room.Room
import com.s21.data.implementation.NodeRepositoryImpl
import com.s21.data.storage.dao.NodeDao
import com.s21.data.storage.database.NodeDatabase
import com.s21.domain.repository.NodeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(сontext: Context): NodeDatabase {
        return Room.databaseBuilder(
            сontext,
            NodeDatabase::class.java,
            "nodes-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTranslationDao(appDatabase: NodeDatabase): NodeDao {
        return appDatabase.nodeDao()
    }

    @Singleton
    @Provides
    fun provideNodeRepositoryImpl(
        nodeDao: NodeDao
    ) : NodeRepository {
        return NodeRepositoryImpl(
            nodeDao = nodeDao
        )
    }
}