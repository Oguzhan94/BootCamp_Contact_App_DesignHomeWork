package com.oguzhan.contactapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.oguzhan.contactapp.model.Contact
import com.oguzhan.contactapp.navigation.Screen.AddContact
import com.oguzhan.contactapp.navigation.Screen.Home
import com.oguzhan.contactapp.navigation.Screen.Detail
import com.oguzhan.contactapp.navigation.Screen.Settings
import com.oguzhan.contactapp.ui.addContact.AddContactScreen
import com.oguzhan.contactapp.ui.detail.DetailScreen
import com.oguzhan.contactapp.ui.home.HomeScreen
import com.oguzhan.contactapp.ui.settings.SettingsScreen

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
            HomeScreen(navController = navController){
                navController.navigate(Detail(it))
            }
        }
        composable<Detail> {
            val route: Detail = it.toRoute()
            DetailScreen(
                navController = navController,contactID = route.contactID)
        }
        composable<Settings> {
            SettingsScreen()
        }

        composable<AddContact> {
            AddContactScreen(navController = navController)
        }
    }
}