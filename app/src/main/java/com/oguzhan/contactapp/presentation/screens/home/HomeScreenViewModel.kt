package com.oguzhan.contactapp.presentation.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.ContactDaoRepositoryImpl
import com.oguzhan.contactapp.data.database.ContactDatabase
import com.oguzhan.contactapp.data.database.ContactEntity
import com.oguzhan.contactapp.domain.DeleteContactUseCase
import com.oguzhan.contactapp.domain.GetAllContactsUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val database = ContactDatabase.Companion.getDatabase(application)
    private val noteDao = database.contactDao()
    private val contactDaoRepositoryImpl = ContactDaoRepositoryImpl(noteDao)
    private val getAllContactsUseCase = GetAllContactsUseCase(contactDaoRepositoryImpl)
    private val deleteContactUseCase = DeleteContactUseCase(contactDaoRepositoryImpl)

    private val _contacts = MutableLiveData<List<ContactEntity>>(emptyList())
    val contacts: LiveData<List<ContactEntity>> = _contacts

    init {
        getAllContacts()
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            _contacts.value = getAllContactsUseCase()
        }

    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            deleteContactUseCase(id)
            getAllContacts()
        }
    }
}