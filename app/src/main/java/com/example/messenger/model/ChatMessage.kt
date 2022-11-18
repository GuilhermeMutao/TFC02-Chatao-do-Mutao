package com.example.messenger.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatMessage (
    val author: Long,
    val text: String,
    var time: LocalDateTime,
    var id: Long = 0
    )
    {
        constructor(id: Long): this(0, "", LocalDateTime.now()) {
            this.id = id
        }
        fun formatTime(): String{
           return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }
        override fun equals(other: Any?): Boolean {
            return this.id == (other as? ChatMessage)?.id
        }

        override fun toString(): String {
            return "ChatMessage(text='$text')"
        }
    }