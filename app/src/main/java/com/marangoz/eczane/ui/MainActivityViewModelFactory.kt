package com.marangoz.eczane.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marangoz.eczane.repository.Repository

class MainActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }

}