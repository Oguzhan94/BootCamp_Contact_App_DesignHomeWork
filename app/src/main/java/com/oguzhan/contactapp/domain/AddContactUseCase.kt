package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.ContactDaoRepositoryImpl
import com.oguzhan.contactapp.data.database.ContactEntity

class AddContactUseCase(private val contactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(contact: ContactEntity) {
        contactRepository.addContact(contact)
    }
}