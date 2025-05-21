package com.dantrap.inspectapp

import android.app.Application
import com.dantrap.inspectapp.di.AppContainer

class App : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
