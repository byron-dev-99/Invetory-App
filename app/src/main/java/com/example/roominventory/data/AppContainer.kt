package com.example.roominventory.data

import android.content.Context

interface AppContainer {

    val itemsRepository: ItemsRepository

}


class AppContainerImpl(private val context: Context) : AppContainer {

    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())

    }

}