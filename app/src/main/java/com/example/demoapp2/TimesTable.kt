package com.example.demoapp2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar

class TimesTable : AppCompatActivity() {
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
                var timesTable: Int
                if (progress < min){
                    timesTable = min
                    timesTableSeekBar.progress = min
                }
                else timesTable = progress
                timesTableGenerator(timesTable)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        timesTableGenerator(10)

    }

    fun timesTableGenerator(timesTable: Int) {
//        var timesTable = 10
        lateinit var timesTableContent: MutableList<Int>
        for (i in 1..10) {
            timesTableContent.add(i * timesTable)
        }
        var arrayAdapter: ArrayAdapter<Int> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, timesTableContent)
        timesTableListView.adapter = arrayAdapter
    }
}
