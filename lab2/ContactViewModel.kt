package com.example.pr2_contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    private val _contacts = MutableLiveData<MutableList<Contact>>(mutableListOf())
    val contacts: LiveData<MutableList<Contact>> get() = _contacts

    private val _currentIndex = MutableLiveData<Int>(0)
    val currentIndex: LiveData<Int> get() = _currentIndex

    fun addContact(contact: Contact) {
        _contacts.value?.add(contact)
        _currentIndex.value = (_contacts.value?.size ?: 1) - 1
        _contacts.value = _contacts.value
    }

    fun updateCurrentContact(contact: Contact) {
        val index = _currentIndex.value ?: 0
        _contacts.value?.let {
            if (index in it.indices) {
                it[index] = contact
                _contacts.value = it
            }
        }
    }

    fun deleteCurrentContact() {
        val index = _currentIndex.value ?: 0
        _contacts.value?.let {
            if (index in it.indices) {
                it.removeAt(index)
                if (index >= it.size && index > 0) {
                    _currentIndex.value = it.size - 1
                }
                _contacts.value = it
            }
        }
    }

    fun nextContact() {
        val size = _contacts.value?.size ?: 0
        val index = _currentIndex.value ?: 0
        if (index < size - 1) {
            _currentIndex.value = index + 1
        }
    }

    fun prevContact() {
        val index = _currentIndex.value ?: 0
        if (index > 0) {
            _currentIndex.value = index - 1
        }
    }

    fun getCurrentContact(): Contact? {
            val index = _currentIndex.value ?: 0
    return _contacts.value?.getOrNull(index)
    }
}
