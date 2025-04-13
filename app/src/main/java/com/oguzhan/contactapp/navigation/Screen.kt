package com.oguzhan.contactapp.navigation

import com.oguzhan.contactapp.model.Contact
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(val contactID: Int) : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object AddContact : Screen
}