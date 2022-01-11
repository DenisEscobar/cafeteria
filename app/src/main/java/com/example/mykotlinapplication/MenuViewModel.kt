package com.example.mykotlinapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel  : ViewModel(){

    val message = MutableLiveData<String>()
    val message2 = MutableLiveData<String>()
    val message3 = MutableLiveData<String>()
    val preu = MutableLiveData<String>()
    val preu2 = MutableLiveData<String>()
    val preu3 = MutableLiveData<String>()

    fun getP1(): String {return ""+message.value}
    fun getp2(): String {return ""+message2.value}
    fun getP3(): String {return ""+message3.value}
    fun getpreu(): String {return ""+preu.value}
    fun getpreu2(): String {return ""+preu2.value}
    fun getpreu3(): String {return ""+preu3.value}

    fun sendMessage(text: String) {
        message.value = text
    }fun sendMessage2(text: String) {
        message2.value = text
    }fun sendMessage3(text: String) {
        message3.value = text
    }fun sendPreu(text: String) {
        preu.value = text
    }fun sendPreu2(text: String) {
        preu2.value = text
    }fun sendPreu3(text: String) {
        preu3.value = text
    }
}