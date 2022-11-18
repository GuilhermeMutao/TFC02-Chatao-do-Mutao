package com.example.messenger.ui.list.viewholders

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.messenger.R
import com.example.messenger.data.DAOUsersSingleton
import com.example.messenger.model.ChatMessage
import com.example.messenger.ui.list.adapter.MessageAdapter


open class MessageViewHolder(
    itemView: View,
    protected val adapter: MessageAdapter
): ViewHolder(itemView) {
    private val txtUser=
        itemView.findViewById<TextView>(R.id.txtUser)
    private val txtMessage =
        itemView.findViewById<TextView>(R.id.txtMessage)
    private val txtTime =
        itemView.findViewById<TextView>(R.id.txtTime)
    open fun bind(message: ChatMessage) {
        val dpRatio = itemView.context.resources.displayMetrics.density
        if (message.author!=0L){
            val constraint = itemView.findViewById<ConstraintLayout>(R.id.ConstrainLayout)
            constraint.background = ContextCompat.getDrawable(itemView.context, R.drawable.their_message_background)
            val params = constraint.layoutParams as RelativeLayout.LayoutParams
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            params.marginEnd = 50*dpRatio.toInt()
            params.marginStart = 8*dpRatio.toInt()
            constraint.layoutParams = params
        } else{
            val constraint = itemView.findViewById<ConstraintLayout>(R.id.ConstrainLayout)
            constraint.background = ContextCompat.getDrawable(itemView.context, R.drawable.my_message_background)
            val params = constraint.layoutParams as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            params.marginEnd = 8*dpRatio.toInt()
            params.marginStart = 50*dpRatio.toInt()
            constraint.layoutParams = params
        }
        this.txtUser.text = DAOUsersSingleton.getUserNameById(message.author)
        this.txtMessage.text = message.text
        this.txtTime.text = message.formatTime()
    }
}