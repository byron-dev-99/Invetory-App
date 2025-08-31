package com.example.roominventory.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roominventory.InventoryApplication
import com.example.roominventory.ui.viewmodels.HomeViewModel
import com.example.roominventory.ui.viewmodels.ItemDetailsViewModel
import com.example.roominventory.ui.viewmodels.ItemEntryViewModel
import com.example.roominventory.ui.viewmodels.ItemUpdateViewModel

object AppViewModelProvider {

    val factory = viewModelFactory {
        initializer {
            HomeViewModel(
                inventoryApplication().appContainer.itemsRepository
            )
        }
        initializer {
            ItemEntryViewModel(
                inventoryApplication().appContainer.itemsRepository
            )

        }
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().appContainer.itemsRepository
            )
        }
        initializer {
            ItemUpdateViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().appContainer.itemsRepository
            )
        }
    }

}


fun CreationExtras.inventoryApplication(): InventoryApplication {
    return (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
}