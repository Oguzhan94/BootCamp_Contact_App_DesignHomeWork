package com.oguzhan.contactapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val image : String,
    val name : String,
    val surname : String,
    val email : String,
    val phoneNumber : String
)
