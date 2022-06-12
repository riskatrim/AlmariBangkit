package com.example.almaritest

import java.util.*

data class UserModel(
    var id: Int = getAutoId(),
    var uname: String = "",
    var email: String = "",
    var pw: String = "",
    var label: String = "",
    var color: String = "",
    var clothes: Int = 0
){
    companion object {
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}
