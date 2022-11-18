package com.example.messenger.model

class Users (
    var name: String,
    var id: Long = 0
){
    override fun equals(other: Any?): Boolean {
        return this.id == (other as? Users)?.id
    }

    override fun toString(): String {
        return name
    }
}