package com.example.mykotlinapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class menuViewModel : ViewModel(){
    val message = MutableLiveData<String>()
    val message2 = MutableLiveData<String>()
    val message3 = MutableLiveData<String>()
    fun sendMessage(text: String) {
        message.value = text
    }fun sendMessage2(text: String) {
        message2.value = text
    }
    fun sendMessage3(text: String) {
        message3.value = text
    }
}