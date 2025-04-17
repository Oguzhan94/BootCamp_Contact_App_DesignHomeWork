package com.oguzhan.contactapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(val contactID: Int) : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data class AddContact(val isEditing: Boolean, val id: Int? = null) : Screen
}