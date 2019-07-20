package com.example.demoapp2

import android.icu.util.ChineseCalendar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

	lateinit var radioGroupGender: RadioGroup
	lateinit var radioButtonMale: RadioButton
	lateinit var radioButtonFemale: RadioButton
	lateinit var checkBoxEnglish: CheckBox
	lateinit var checkBoxHindi: CheckBox
	lateinit var checkBoxChinese: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
	    radioGroupGender= findViewById(R.id.radioGroupGender)
	    radioButtonMale= findViewById(R.id.radioButtonMale)
	    radioButtonFemale= findViewById(R.id.radioButtonFemale)
	    checkBoxEnglish= findViewById(R.id.checkBoxEnglish)
	    checkBoxHindi= findViewById(R.id.checkBoxHindi)
	    checkBoxChinese= findViewById(R.id.checkBoxChinese)
	    var buttonSubmit= findViewById<Button>(R.id.buttonSubmit)
	    var textViewAnswer= findViewById<TextView>(R.id.textViewAnswer)
	    buttonSubmit.setOnClickListener{
		    var result = ""
		    if (radioGroupGender.checkedRadioButtonId != -1) {
			    result += "Selected gender: "
			    if (radioButtonMale.isChecked)
				    result += "male\n"
			    else if (radioButtonFemale.isChecked)
				    result += "female\n"
		    }
		    result += "Languages Known: "
		    if (checkBoxEnglish.isChecked)
			    result += "English "
		    if (checkBoxHindi.isChecked)
			    result += "Hindi "
		    if (checkBoxChinese.isChecked)
			    result += "Chinese "
		    textViewAnswer.text = result}
    }
}
