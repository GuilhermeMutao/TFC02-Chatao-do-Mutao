package com.example.messenger.data

import com.example.messenger.model.ChatChannel
import com.example.messenger.model.Users
import kotlin.random.Random

object DAOUsersSingleton {
    private var serial: Long = 0
    private val users = ArrayList<Users>()

    init {
        val names = arrayListOf("Mutão", "Lindovaldo", "Brocha", "Global", "Brunim", "Jonas")
        for (name in names) {

            users.add(Users(name, serial++))

        }
    }
    fun addUsers(group: Boolean) : ArrayList<Users>{

        val userNames = users.drop(1)
        
        return if (group){

            val groupNames = userNames.asSequence().shuffled().take(Random.nextInt(2, 5)).toList() as ArrayList<Users>
            groupNames.add(0, users[0])
            groupNames

        } else {

            arrayListOf(users[0], userNames.random())

        }
    }

    fun getUserNameById(id: Long): String {

        for(u in this.users) {

            if(u.id == id){
                    
                    return u.name
    
            }
    
        }
        return ""
    }
}