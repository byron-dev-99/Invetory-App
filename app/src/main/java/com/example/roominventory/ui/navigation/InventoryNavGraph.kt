package com.example.roominventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roominventory.ui.screens.home.HomeScreen
import com.example.roominventory.ui.screens.home.destinationHome
import com.example.roominventory.ui.screens.item.ItemDetailDestination
import com.example.roominventory.ui.screens.item.ItemDetailScreen
import com.example.roominventory.ui.screens.item.ItemEntryDestination
import com.example.roominventory.ui.screens.item.ItemEntryScreen
import com.example.roominventory.ui.screens.item.ItemUpDateDestination
import com.example.roominventory.ui.screens.item.ItemUpdateScreen


@Composable
fun InventoryNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier

) {

    NavHost(
        navController = navHostController,
        startDestination = destinationHome.route,
        modifier = modifier
    ) {
        composable(route = destinationHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navHostController.navigate(ItemEntryDestination.route)
                },
                navigateToItemUpdate = {
                    navHostController.navigate("${ItemDetailDestination.route}/${it}")
                }
            )
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = {
                    navHostController.popBackStack()
                },
                onNavigateUp = {
                    navHostController.navigateUp()
                },
            )
        }

        composable(
            route = ItemDetailDestination.routeWithArgs, arguments =

                listOf(
                    navArgument(ItemDetailDestination.ItemID) { type = NavType.IntType }
                )
        ) { navBackStackEntry ->


            ItemDetailScreen(
                navigationBack = {
                    navHostController.navigateUp()
                },
                navigationToEDitItem = {
                    navHostController.navigate("${ItemUpDateDestination.route}/$it")
                }
            )

        }
        composable(
            route = ItemUpDateDestination.routeWithArgs,
            arguments = listOf(
                navArgument(ItemUpDateDestination.itemID) { type = NavType.IntType }
            )

        ) {
            ItemUpdateScreen(
                navigateBack = {
                    navHostController.popBackStack()
                },
                onNavigateUp = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}