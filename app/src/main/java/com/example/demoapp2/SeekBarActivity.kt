package com.example.demoapp2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_seek_bar.*

class SeekBarActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_seek_bar)
		val seekBar= findViewById<SeekBar>(R.id.seekBar)
		val textViewResult= findViewById<TextView>(R.id.textViewResult)
		seekBar.max= 50
		seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
			override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
				textViewResult.text= "Seeking to: ${seekBar.progress}"
			}

			override fun onStartTrackingTouch(seekBar: SeekBar) {
				textViewResult.text= "Start at: ${seekBar.progress}"

			}

			override fun onStopTrackingTouch(seekBar: SeekBar) {
				textViewResult.text= "Selected: ${seekBar.progress}"
			}
		})
		val buttonToCalander= findViewById<Button>(R.id.buttonToCalander)
		buttonToCalander.setOnClickListener { var i= Intent( this, CalanderActivity:: class.java)
		startActivity(i)}
	}
}
