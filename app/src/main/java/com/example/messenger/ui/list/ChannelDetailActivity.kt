package com.example.messenger.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.R
import com.example.messenger.data.DAOChannelSingleton
import com.example.messenger.data.DAOUsersSingleton
import com.example.messenger.model.ChatMessage
import com.example.messenger.ui.list.adapter.ChannelAdapter
import com.example.messenger.ui.list.adapter.MessageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime

class ChannelDetailActivity : AppCompatActivity() {
    private lateinit var rvMessageList: RecyclerView
    private lateinit var btnSendMessage: FloatingActionButton
    private lateinit var etxtMessage: EditText
    private var channelId: Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_detail)
        this.rvMessageList = findViewById(R.id.rvMessageList)
        this.btnSendMessage = findViewById(R.id.btnSendMessage)
        this.etxtMessage = findViewById(R.id.etxtMessage)
        this.channelId = intent.getLongExtra("channelId", -1)
        val channel = DAOChannelSingleton.getChannelById(channelId)
        this.rvMessageList.layoutManager = LinearLayoutManager(this)
        if (channel != null) {
            this.rvMessageList.adapter = MessageAdapter(channel.getMessages())
        }
    }

    override fun onBackPressed() {
        val output = Intent()
        output.putExtra("channelId", this.channelId)
        setResult(RESULT_OK, output)
        super.onBackPressed()
    }

    fun onClickInsertMessage(view: View) {
        val text = this.etxtMessage.text.toString()
        if(text.isNotBlank()) {
            val channel = DAOChannelSingleton.getChannelById(channelId)
            this.etxtMessage.text.clear()
        if (channel != null) {
            val author = channel.getRandomUser()
            val m = ChatMessage(author.id, text, LocalDateTime.now())
            channel.addMessage(m)
            this.rvMessageList.adapter?.notifyItemInserted(channel.getMessages().size-1)
            this.rvMessageList.smoothScrollToPosition(channel.getMessages().size-1)
        }
        }
    }
}