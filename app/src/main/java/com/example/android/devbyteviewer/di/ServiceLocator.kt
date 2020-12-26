package com.example.android.devbyteviewer.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.database.getDatabase
import com.example.android.devbyteviewer.repository.VideosRepository
import com.example.android.devbyteviewer.viewmodels.DevByteViewModel

object ServiceLocator {

    fun provideDatabase(context: Context): VideosDatabase = getDatabase(context)

    private fun createVideosRepository(database: VideosDatabase): VideosRepository {
        return VideosRepository(database)
    }

    fun provideDevByteViewModel(app: Application): ViewModelProvider.Factory {
        val database = provideDatabase(app)
        val repository = createVideosRepository(database)
        return DevByteViewModel.Factory(app, repository)
    }

}