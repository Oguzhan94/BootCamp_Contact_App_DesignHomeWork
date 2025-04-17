package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.database.ContactEntity

class GetAllContactsUseCase(private val contactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(): List<ContactEntity> {
        return contactRepository.getAllContacts()
    }

}