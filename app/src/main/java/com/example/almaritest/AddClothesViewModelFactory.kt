//package com.example.almaritest
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.almaritest.repository.Repository
//
//class AddClothesViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return AddClothesViewModel(repository) as T
//    }
//}