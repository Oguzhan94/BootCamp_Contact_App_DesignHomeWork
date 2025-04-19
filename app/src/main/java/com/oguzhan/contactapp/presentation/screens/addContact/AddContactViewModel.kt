package com.oguzhan.contactapp.presentation.screens.addContact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.domain.AddContactUseCase
import com.oguzhan.contactapp.domain.GetContactByIdUseCase
import com.oguzhan.contactapp.domain.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val getContactByIdUseCase: GetContactByIdUseCase,
) : ViewModel() {

    private val _contact = MutableLiveData<ContactEntity>()
    val contact: LiveData<ContactEntity> = _contact

    fun addContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            addContactUseCase(contactEntity)
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