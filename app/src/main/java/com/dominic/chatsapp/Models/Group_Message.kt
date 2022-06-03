package com.dominic.chatsapp.Models

class Group_Message {
    var user_uid:String? = null
    var user_name:String? = null
    var user_image:String? = null
    var user_message:String? = null
    var group_uid:String? = null
    constructor()
    constructor(
        user_uid: String?,
        user_name: String?,
        user_image: String?,
        user_message: String?,
        group_uid: String?
    ) {
        this.user_uid = user_uid
        this.user_name = user_name
        this.user_image = user_image
        this.user_message = user_message
        this.group_uid = group_uid
    }

    constructor(user_uid: String?, user_name: String?, user_image: String?, user_message: String?) {
        this.user_uid = user_uid
        this.user_name = user_name
        this.user_image = user_image
        this.user_message = user_message
    }


}