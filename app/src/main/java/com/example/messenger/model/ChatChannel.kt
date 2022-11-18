package com.example.messenger.model

class ChatChannel (
    var group: Boolean,
    val name: String,
    var id: Long = 0
)
{
    constructor(id: Long): this(false, "") {
        this.id = id
    }

    private val messages = ArrayList<ChatMessage>()
    private val users = ArrayList<Users>()
    fun addMessage(message: ChatMessage){
        this.messages.add(message)
    }
    fun addUsers(users: ArrayList<Users>){
        this.users.addAll(users)
    }
    fun getMessages() : ArrayList<ChatMessage>{
        return this.messages
    }

    fun getLastMessage() : String {
        return if (this.messages.isEmpty()) {
            ""
        }else{
            val lastMessage = this.messages.last()
            val userName = this.users.find { it.id == lastMessage.author }?.name ?: ""
            return userName + ": " + lastMessage.text
        }
    }

    fun getRandomUser() : Users {
        return this.users.random()
    }

    override fun equals(other: Any?): Boolean {
        return this.id == (other as? ChatChannel)?.id && this.messages.size == ((other as? ChatChannel)?.messages?.size
            ?: 0)
    }

    override fun toString(): String {
        return "ChatMessage(text='$name')"
    }

}