package com.example.roominventory.ui.states

import com.example.roominventory.data.Item

data class HomeUiState(
    val itemsList : List<Item> = listOf()
)
