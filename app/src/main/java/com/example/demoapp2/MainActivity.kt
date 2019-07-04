package com.example.demoapp2

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.fixedRateTimer

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
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        var progressControl: SeekBar = findViewById(R.id.audioSeekBar) as SeekBar
        progressControl.max = mediaPlayer.duration
        fixedRateTimer("default", false, 0L, 1000){
            progressControl.progress = mediaPlayer.currentPosition
        }
        progressControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {mediaPlayer.seekTo(progress)}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {mediaPlayer?.pause()}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {mediaPlayer?.start()}
        })
    }
    fun playAudio(view:View){
        mediaPlayer?.start()
    }
    fun pauseAudio(view: View){
        mediaPlayer?.pause()
    }
}
