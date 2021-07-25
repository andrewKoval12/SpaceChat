package ua.com.koval.andrey.spacechat.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ua.com.koval.andrey.spacechat.models.users.Users

lateinit var AUTH:FirebaseAuth
lateinit var REF_DB_ROOT:DatabaseReference
lateinit var USER:Users
lateinit var UID:String

const val NODE_USERNAMES = "usernames"
const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"


fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DB_ROOT = FirebaseDatabase.getInstance().reference
    USER = Users()
    UID = AUTH.currentUser?.uid.toString()
}