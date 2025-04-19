package com.oguzhan.contactapp.domain

import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.data.repository.ContactRepository
import javax.inject.Inject

class GetAllContactsUseCase @Inject constructor(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(): List<ContactEntity> {
        return contactRepository.getAllContacts()
    }

}