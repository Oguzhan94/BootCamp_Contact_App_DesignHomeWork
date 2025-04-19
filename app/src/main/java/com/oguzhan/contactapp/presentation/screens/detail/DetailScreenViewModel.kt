package com.oguzhan.contactapp.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhan.contactapp.data.local.ContactEntity
import com.oguzhan.contactapp.domain.GetContactByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val getContactByIdUseCase: GetContactByIdUseCase) :
    ViewModel() {

    private val _contact = MutableLiveData<ContactEntity>()
    val contact: LiveData<ContactEntity> = _contact


    fun getContactById(id: Int) {
        viewModelScope.launch {
            _contact.value = getContactByIdUseCase(id)
        }
    }
}