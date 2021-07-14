package ua.com.koval.andrey.spacechat.utilits

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH:FirebaseAuth

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
}