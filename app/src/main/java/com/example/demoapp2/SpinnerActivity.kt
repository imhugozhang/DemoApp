package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class SpinnerActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_spinner)
		val option= findViewById<Spinner>(R.id.spinnerOption)
		val result= findViewById<TextView>(R.id.textViewResult)
		var options= arrayListOf("Option 1", "Option 2", "Option 3")
		option.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
		option.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
			override fun onNothingSelected(parent: AdapterView<*>?) {
				result.text= "Select an Option"
			}

			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				result.text= options.get(position)
			}

		}
	}
}
