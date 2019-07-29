package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.*

class AutoCompleteTextView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_text_view)
        var suggestion = mutableListOf("Apple", "Google", "Samsung", "Huawei", "HTC")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suggestion)
        autoCompleteTextView.threshold = 0
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnFocusChangeListener({ v: View?, hasFocus: Boolean ->
            if (hasFocus) autoCompleteTextView.showDropDown()
        })
    }
}
