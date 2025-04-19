package com.oguzhan.contactapp.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.domain.DeleteContactUseCase
import com.oguzhan.contactapp.domain.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
) : ViewModel() {

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