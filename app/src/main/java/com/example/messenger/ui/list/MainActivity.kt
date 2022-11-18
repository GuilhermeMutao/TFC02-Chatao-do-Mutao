package com.example.messenger.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.R
import com.example.messenger.data.DAOChannelSingleton
import com.example.messenger.ui.list.adapter.ChannelAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvChannelList: RecyclerView
    private lateinit var btnAddChannel: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.rvChannelList = findViewById(R.id.rvChannelList)
        this.btnAddChannel = findViewById(R.id.btnAddChannel)
        this.rvChannelList.layoutManager = LinearLayoutManager(this)
        val adapter = ChannelAdapter(DAOChannelSingleton.getChannels())
        this.rvChannelList.adapter = adapter
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == RESULT_OK && result.data != null) {
                val channelId = result.data!!.getLongExtra("channelId", -1)
                val channelPosition = DAOChannelSingleton.getChannelPositionById(channelId)
                adapter.notifyItemChanged(channelPosition)
                adapter.notifyDataSetChanged()
            }
        }
        adapter.setOnClickChannelListener { channel ->
            val openChannelIntent =
                Intent(this, ChannelDetailActivity::class.java)
            openChannelIntent.putExtra("channelId", channel.id)
            resultLauncher.launch(openChannelIntent)
        }
    }

    fun onClickInsert(view: View) {
            DAOChannelSingleton.add()
            this.rvChannelList.adapter?.notifyItemInserted(0)
            this.rvChannelList.smoothScrollToPosition(0)
        }
}