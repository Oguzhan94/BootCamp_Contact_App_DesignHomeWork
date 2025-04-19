package com.oguzhan.contactapp.data.repository

import com.oguzhan.contactapp.data.local.ContactDao
import com.oguzhan.contactapp.data.local.ContactEntity
import javax.inject.Inject

class ContactDaoRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) :
    ContactRepository {
    override suspend fun addContact(contact: ContactEntity) {
        contactDao.addContact(contact)
    }

    override suspend fun getAllContacts(): List<ContactEntity> {
        return contactDao.getAllContacts()
    }

    override suspend fun deleteContact(id: Int) {
        contactDao.deleteContact(id)
    }

    override suspend fun updateContact(contact: ContactEntity) {
        contactDao.updateContact(contact)
    }

    override suspend fun getContactById(id: Int): ContactEntity {
        return contactDao.getContactById(id)
    }
}