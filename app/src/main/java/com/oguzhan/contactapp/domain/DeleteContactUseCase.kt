package com.oguzhan.contactapp.domain

class DeleteContactUseCase(private val contactRepository: ContactDaoRepositoryImpl) {
    suspend operator fun invoke(id: Int) {
        contactRepository.deleteContact(id)
    }

}