package ua.com.koval.andrey.spacechat.models.users

data class Users(
    val id:String = "",
    var username:String = "",
    var bio:String = "",
    var fullname:String = "",
    var status:String = "",
    var phone:String = "",
    var photoUrl:String = "",
)
