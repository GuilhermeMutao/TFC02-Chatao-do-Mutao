package com.example.messenger.data

import com.example.messenger.model.ChatChannel
import com.example.messenger.model.Users
import kotlin.random.Random
import com.example.messenger.data.DAOUsersSingleton as DAOUsersSingleton

object DAOChannelSingleton {

    private var serial: Long = 1
    private val channels = ArrayList<ChatChannel>()

    fun add() {

        val c = ChatChannel(false, "Grupo $serial")
        this.channels.add(0, c)

        if (Random.nextBoolean()){
            c.group = true
        }

        c.addUsers(DAOUsersSingleton.addUsers(c.group))
        c.id = serial++
    }

    fun getChannels(): ArrayList<ChatChannel> {
        return this.channels
    }

    fun getChannelById(id: Long): ChatChannel? {

        for(c in this.channels) {

            if(c.id == id){
                return c
            }
        }
        return null
    }

    fun getChannelPositionById(id: Long): Int {

        return this.channels.indexOf(ChatChannel(id))
        
    }
}