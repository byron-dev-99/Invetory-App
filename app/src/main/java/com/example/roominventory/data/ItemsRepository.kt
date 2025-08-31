package com.example.roominventory.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllItemsStream() : Flow<List<Item>>

    fun getItemStream(id : Int) : Flow<Item?>

    suspend fun insertItem(item : Item)

    suspend fun updateItem(item : Item)

    suspend fun deleteItem(id: Int)


}