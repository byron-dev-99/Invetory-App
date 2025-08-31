package com.example.roominventory

import android.app.Application
import com.example.roominventory.data.AppContainer
import com.example.roominventory.data.AppContainerImpl

class InventoryApplication : Application() {

    lateinit var appContainer: AppContainer


    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }

}