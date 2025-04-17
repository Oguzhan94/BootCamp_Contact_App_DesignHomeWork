package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.database.ContactEntity

class GetContactByIdUseCase(private val contactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(id: Int): ContactEntity {
        return contactRepository.getContactById(id)
    }
}