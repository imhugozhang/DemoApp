package com.example.demoapp2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.my_custom_layout.view.*

class MyCustomView (context: Context, attrs: AttributeSet): LinearLayout(context, attrs){
//    private var autoCompleteTextView
    init{
        val inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= inflater.inflate(R.layout.my_custom_layout, this, true)
        autoCompleteTextView
    }
}