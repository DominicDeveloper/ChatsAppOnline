package com.dominic.chatsapp.Models

class Message {
    var messageId :String? = null
    var message :String? = null
    var senderId :String? = null
    var imageUrl :String? = null
    var timeStamp :Long = 0

    constructor(messageId: String?, message: String?, senderId: String?, imageUrl: String?, timeStamp: Long){
        this.messageId = messageId
        this.message = message
        this.senderId = senderId
        this.imageUrl = imageUrl
        this.timeStamp = timeStamp
    }
    constructor()
    constructor(message: String?, senderId: String?, imageUrl: String?, timeStamp: Long) {
        this.message = message
        this.senderId = senderId
        this.imageUrl = imageUrl
        this.timeStamp = timeStamp
    }

    constructor(messageId: String?, message: String?, senderId: String?) {
        this.messageId = messageId
        this.message = message
        this.senderId = senderId
    }
    constructor(message :String?, senderId :String?, timeStamp :Long){
        this.message = message
        this.senderId = senderId
        this.timeStamp = timeStamp
    }



}