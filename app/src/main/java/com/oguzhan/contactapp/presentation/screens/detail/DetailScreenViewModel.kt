package com.oguzhan.contactapp.presentation.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.database.ContactDatabase
import com.oguzhan.contactapp.data.database.ContactEntity
import com.oguzhan.contactapp.domain.ContactDaoRepositoryImpl
import com.oguzhan.contactapp.domain.GetContactByIdUseCase
import kotlinx.coroutines.launch

class DetailScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val database = ContactDatabase.Companion.getDatabase(application)
    private val noteDao = database.contactDao()
    private val contactDaoRepositoryImpl = ContactDaoRepositoryImpl(noteDao)
    private val getContactByIdUseCase = GetContactByIdUseCase(contactDaoRepositoryImpl)

    private val _contact = MutableLiveData<ContactEntity>()
    val contact: LiveData<ContactEntity> = _contact

    init {

    }

    fun getContactById(id: Int) {
        viewModelScope.launch {
            _contact.value = getContactByIdUseCase(id)
        }
    }
}