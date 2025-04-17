package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.ContactDaoRepositoryImpl

class DeleteContactUseCase(private val contactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(id: Int) {
        contactRepository.deleteContact(id)
    }
}