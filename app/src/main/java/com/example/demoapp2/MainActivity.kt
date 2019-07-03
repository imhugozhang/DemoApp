package com.example.demoapp2

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var audioManager: AudioManager
//    lateinit var playProgress:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this, R.raw.mp3)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        var maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        var volumeControl: SeekBar = findViewById(R.id.seekBar) as SeekBar
        volumeControl.max = maxVolume
        volumeControl.progress = curVolume
        volumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            fun iLoveKotlin(){}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

//        var curProgress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//        var maxProgress = audioManager.get(AudioManager.STREAM_MUSIC)
//        var progressControl: SeekBar = findViewById(R.id.audioSeekBar) as SeekBar
//        progressControl.max = mediaPlayer.duration
//        volumeControl.progress = curVolume
//        progressControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
//            }
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//            fun iLoveKotlin(){}
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//        })
    }
    fun playAudio(view:View){
        mediaPlayer?.start()
    }
    fun pauseAudio(view: View){
        mediaPlayer?.pause()
    }
}
