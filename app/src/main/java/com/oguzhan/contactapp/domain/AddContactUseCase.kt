package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.data.repository.ContactRepository
import javax.inject.Inject

class AddContactUseCase @Inject constructor(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(contact: ContactEntity) {
        contactRepository.addContact(contact)
    }
}