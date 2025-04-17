package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.database.ContactDao
import com.oguzhan.contactapp.data.database.ContactEntity

class ContactDaoRepositoryImpl(private val contactDao: ContactDao) : ContactDao {
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