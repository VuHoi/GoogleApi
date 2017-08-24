package com.example.vukhachoi.musicapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.R.attr.start
import android.media.MediaPlayer
import android.view.View
import android.widget.AdapterView



class MainActivity : AppCompatActivity() {
    var lsv: ListView? = null
    var listbaihat: ArrayList<baihat>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listbaihat = ArrayList<baihat>()

        listbaihat.add(baihat(R.raw.giatu, "gia tu", "dam ving hung"))
        listbaihat.add(baihat(R.raw.yeumotchanghoangtu, "yeu mot chang hoang tu", "chi bi hoang yen"))
        val adapter = baihatadapter(this, R.layout.itembaihat, listbaihat)
        lsv.setAdapter(adapter)
        lsv.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val Baihat = listbaihat.get(i)
                val media = MediaPlayer.create(this@MainActivity, Baihat.file)
                media.start()
            }
        })
    }
}
