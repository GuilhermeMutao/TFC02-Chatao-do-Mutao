package com.example.messenger.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.messenger.R
import com.example.messenger.model.ChatChannel
import com.example.messenger.ui.list.viewholders.ChannelViewHolder

class ChannelAdapter(
    private var channels: ArrayList<ChatChannel>
): Adapter<ChannelViewHolder>() {

    fun interface OnClickChannelListener {
        fun onClick(channel: ChatChannel)
    }

    private var listener: OnClickChannelListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            R.layout.itemview_channel_list, parent, false
        )
        return ChannelViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = this.channels[position]
        holder.bind(channel)
    }

    override fun getItemCount(): Int {
        return this.channels.size
    }

    fun setOnClickChannelListener(listener: OnClickChannelListener?) {
        this.listener = listener
    }

    fun getOnClickTaskListener(): OnClickChannelListener? {
        return this.listener
    }
}