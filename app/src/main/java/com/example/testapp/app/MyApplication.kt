package com.example.testapp.app

import android.app.Application
import com.example.testapp.network.RemoteDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class  MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RemoteDataSource.init()
    }
}