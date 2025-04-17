package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.database.ContactEntity

class AddContactUseCase(private val ContactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(contact: ContactEntity) {
        ContactRepository.addContact(contact)
    }
}