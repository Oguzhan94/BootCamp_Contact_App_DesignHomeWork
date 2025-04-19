package com.oguzhan.contactapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Insert
    suspend fun addContact(contact: ContactEntity)

    @Query("SELECT * FROM ContactEntity")
    suspend fun getAllContacts(): List<ContactEntity>

    @Query("DELETE FROM ContactEntity WHERE id = :id")
    suspend fun deleteContact(id: Int)

    @Update
    suspend fun updateContact(contact: ContactEntity)

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    suspend fun getContactById(id: Int): ContactEntity
}