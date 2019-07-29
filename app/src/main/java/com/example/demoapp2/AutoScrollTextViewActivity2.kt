package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_auto_scroll_text_view2.*

class AutoScrollTextViewActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll_text_view2)
        textViewData.setSingleLine()
        textViewData.isSelected= true
        textViewData.text= "The type of safeDivide is (Int, Int) -> Double. Note that unlike function type declarations, " +
                "the parameter list of a lambda expression must not be enclosed in parentheses.\n" +
                "\n" +
                "Note that the other uses of curly braces in Kotlin, such as in function and class " +
                "definitions and after if/else/for/while statements, are not lambda expressions " +
                "(so it is not the case that if is a function that conditionally executes a lambda function)."
    }
}
