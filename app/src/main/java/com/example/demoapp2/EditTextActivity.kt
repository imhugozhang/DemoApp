package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.*
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.editText
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_text)
//		var editText= findViewById<EditText>(R.id.editText)
//		var textViewResult= findViewById<TextView>(R.id.textViewResult)
//		var buttonCopy= findViewById<Button>(R.id.buttonCopy)
//		buttonCopy.setOnClickListener { }
		editText.addTextChangedListener(object: TextWatcher{
			override fun afterTextChanged(s: Editable?) {

			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				textViewResult.text=  editText.text
			}
		})
	}
}
