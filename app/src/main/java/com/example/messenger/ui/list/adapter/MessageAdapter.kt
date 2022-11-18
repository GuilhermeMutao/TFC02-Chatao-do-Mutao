package com.example.messenger.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.messenger.R
import com.example.messenger.model.ChatChannel
import com.example.messenger.model.ChatMessage
import com.example.messenger.ui.list.viewholders.ChannelViewHolder
import com.example.messenger.ui.list.viewholders.MessageViewHolder

class MessageAdapter(
    private var messages: ArrayList<ChatMessage>
): Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            R.layout.itemview_message, parent, false
        )
        return MessageViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = this.messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return this.messages.size
    }
}