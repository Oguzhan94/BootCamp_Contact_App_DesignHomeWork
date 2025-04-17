package com.oguzhan.contactapp.presentation.screens.addContact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.database.ContactDatabase
import com.oguzhan.contactapp.data.database.ContactEntity
import com.oguzhan.contactapp.domain.AddContactUseCase
import com.oguzhan.contactapp.domain.ContactDaoRepositoryImpl
import com.oguzhan.contactapp.domain.GetContactByIdUseCase
import com.oguzhan.contactapp.domain.UpdateContactUseCase
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application) : AndroidViewModel(application) {

    private val database = ContactDatabase.Companion.getDatabase(application)
    private val noteDao = database.contactDao()
    private val contactDaoRepositoryImpl = ContactDaoRepositoryImpl(noteDao)
    private val getAllContactsUseCase = AddContactUseCase(contactDaoRepositoryImpl)
    private val updateContactUseCase = UpdateContactUseCase(contactDaoRepositoryImpl)
    private val getContactByIdUseCase = GetContactByIdUseCase(contactDaoRepositoryImpl)

    private val _contact = MutableLiveData<ContactEntity>()
    val contact: LiveData<ContactEntity> = _contact

    fun addContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            getAllContactsUseCase(contactEntity)
        }
    }

    fun updateContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            updateContactUseCase(contactEntity)
        }
    }

    fun getContactById(id: Int) {
        viewModelScope.launch {
            _contact.value = getContactByIdUseCase(id)
        }
    }

}