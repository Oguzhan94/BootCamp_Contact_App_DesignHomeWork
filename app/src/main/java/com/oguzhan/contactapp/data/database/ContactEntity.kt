package com.oguzhan.contactapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val image: String? = null
)
