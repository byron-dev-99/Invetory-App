package com.example.roominventory.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(
    private val dao: ItemDao
) : ItemsRepository {

    override fun getAllItemsStream(): Flow<List<Item>> {
        return dao.getAllItems()
    }

    override fun getItemStream(id: Int): Flow<Item?> {
        return dao.getItem(id)
    }

    override suspend fun insertItem(item: Item) {
        dao.insert(item)
    }

    override suspend fun updateItem(item: Item) {
        dao.update(item)
    }

    override suspend fun deleteItem(id: Int) {
        dao.delete(id)
    }

}