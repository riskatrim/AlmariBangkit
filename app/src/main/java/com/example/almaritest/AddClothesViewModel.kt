//package com.example.almaritest
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.almaritest.model.Clothes
//import com.example.almaritest.repository.Repository
//import kotlinx.coroutines.launch
//import retrofit2.Response
//
//class AddClothesViewModel(private val repository: Repository): ViewModel() {
//
//    var myResponse: MutableLiveData<Response<Clothes>> = MutableLiveData()
//
//    fun pushClothes(clothes: Clothes) {
//        viewModelScope.launch {
//            val response = repository.pushClothes(clothes)
//            myResponse.value = response
//        }
//    }
////
////    fun getPost(){
////        viewModelScope.launch {
////            val response = repository.getPost()
////            myResponse.value = response
////        }
////    }
//
//}