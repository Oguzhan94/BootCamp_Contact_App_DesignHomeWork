package com.oguzhan.contactapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.oguzhan.contactapp.presentation.navigation.Screen.AddContact
import com.oguzhan.contactapp.presentation.navigation.Screen.Detail
import com.oguzhan.contactapp.presentation.navigation.Screen.Home
import com.oguzhan.contactapp.presentation.navigation.Screen.Settings
import com.oguzhan.contactapp.presentation.screens.addContact.AddContactScreen
import com.oguzhan.contactapp.presentation.screens.detail.DetailScreen
import com.oguzhan.contactapp.presentation.screens.home.HomeScreen
import com.oguzhan.contactapp.presentation.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(
    startDestination: Screen,
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Home> {
            HomeScreen(
                navController = navController,
                onNavigateToDetail = { navController.navigate(Detail(it)) },
                onNavigateToAddContact = { isEditing, id ->
                    navController.navigate(
                        AddContact(
                            isEditing,
                            id
                        )
                    )
                }
            )
        }
        composable<Detail> {
            val route: Detail = it.toRoute()
            DetailScreen(
                navController = navController, contactID = route.contactID
            )
        }
        composable<Settings> {
            SettingsScreen()
        }

        composable<AddContact> {
            val route: AddContact = it.toRoute()
            AddContactScreen(navController = navController, isEdit = route.isEditing, id = route.id)
        }
    }
}