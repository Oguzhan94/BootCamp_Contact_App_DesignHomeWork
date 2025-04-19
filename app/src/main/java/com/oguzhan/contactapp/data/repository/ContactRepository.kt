package com.oguzhan.contactapp.data.repository

import com.oguzhan.contactapp.data.local.ContactEntity

interface ContactRepository {
    suspend fun addContact(contact: ContactEntity)
    suspend fun getAllContacts(): List<ContactEntity>
    suspend fun deleteContact(id: Int)
    suspend fun updateContact(contact: ContactEntity)
    suspend fun getContactById(id: Int): ContactEntity
}