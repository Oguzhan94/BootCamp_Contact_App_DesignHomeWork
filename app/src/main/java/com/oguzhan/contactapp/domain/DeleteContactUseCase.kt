package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.repository.ContactRepository
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(id: Int) {
        contactRepository.deleteContact(id)
    }
}