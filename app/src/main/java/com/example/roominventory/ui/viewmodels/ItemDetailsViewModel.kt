package com.example.roominventory.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roominventory.data.ItemsRepository
import com.example.roominventory.ui.screens.item.ItemDetailDestination
import com.example.roominventory.ui.states.ItemDetailsUiState
import com.example.roominventory.utils.toItem
import com.example.roominventory.utils.toItemDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailDestination.ItemID])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(outOfStock = it.quantity <= 0, itemDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    /**
     * Reduces the item quantity by one and update the [ItemsRepository]'s data source.
     */
    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.itemDetails.toItem()
            if (currentItem.quantity > 0) {
                itemsRepository.updateItem(currentItem.copy(quantity = currentItem.quantity - 1))
            }
        }
    }

    /**
     * Deletes the item from the [ItemsRepository]'s data source.
     */
    suspend fun deleteItem() {
        try {

            itemsRepository.deleteItem(uiState.value.itemDetails.id)
            Log.i("ItemDetailsViewModel", uiState.value.itemDetails.toString())
        }
        catch (e: Exception){
            Log.i("Error in ItemDetailsViewModel", e.toString())

        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}