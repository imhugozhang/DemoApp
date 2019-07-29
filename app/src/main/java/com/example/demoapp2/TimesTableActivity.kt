package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_times_table.*

class TimesTableActivity : AppCompatActivity() {

    fun generateTimesTable(timesTable: Int) {
        val timesTableContent = arrayListOf<String>()
        for (i in 1..10) {
            timesTableContent.add((i * timesTable).toString())
        }
        val arrayAdapter = ArrayAdapter( this,
            android.R.layout.simple_list_item_1, timesTableContent
        )
        timeTableListView.adapter = arrayAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times_table)
        timesTableSeekBar.max = 20
        timesTableSeekBar.progress = 10
        var timesTable: Int
        timesTableSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val min = 1
                if (progress < min){
                    timesTable = min
                    timesTableSeekBar.progress = min
                }
                else timesTable = progress
                generateTimesTable(timesTable)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        generateTimesTable(10)
    }
}
