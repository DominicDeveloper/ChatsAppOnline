package com.dominic.chatsapp.Models

import java.io.Serializable

class Users:Serializable{
    var uid:String? = null
    var name:String? = null
    var phoneNumber:String? = null
    var profileImage:String? = null

    constructor(uid: String?, name: String?, phoneNumber: String?, profileImage: String?) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
        this.profileImage = profileImage
    }
    constructor()
    constructor(uid: String?, name: String?, phoneNumber: String?) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
    }

    constructor(name: String?, phoneNumber: String?) {
        this.name = name
        this.phoneNumber = phoneNumber
    }

    constructor(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }


}