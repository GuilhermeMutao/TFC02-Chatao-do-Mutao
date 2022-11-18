package com.example.messenger.ui.list.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.messenger.R
import com.example.messenger.model.ChatChannel
import com.example.messenger.ui.list.adapter.ChannelAdapter

open class ChannelViewHolder(
    itemView: View,
    protected val adapter: ChannelAdapter
): ViewHolder(itemView) {
    private val txtChannelName =
        itemView.findViewById<TextView>(R.id.txtMessage)
    private val txtLastMessage =
        itemView.findViewById<TextView>(R.id.txtLastMessage)
    protected lateinit var currentChannel: ChatChannel
    init {
        this.itemView.setOnClickListener {
            this.adapter
                .getOnClickTaskListener()?.onClick(this.currentChannel)
        }
    }
    open fun bind(channel: ChatChannel) {
        this.currentChannel = channel
        this.txtChannelName.text = this.currentChannel.name
        this.txtLastMessage.text = this.currentChannel.getLastMessage()
    }
}