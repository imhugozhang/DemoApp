package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    lateinit var timesTableSeekBar: SeekBar
    lateinit var timesTableListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timesTableSeekBar = findViewById<SeekBar>(R.id.timesTableSeekBar)
        timesTableListView = findViewById<ListView>(R.id.timeTableListView)
        timesTableSeekBar.max = 20
        timesTableSeekBar.progress = 10
//        timesTableSeekBar.min = 1
        timesTableSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var min = 1
//                var timesTable: Int
                if (progress < min){
                    timesTable = min
                    timesTableSeekBar.progress = min
                }
                else timesTable = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}
