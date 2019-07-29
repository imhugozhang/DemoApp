package com.example.demoapp2

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_async_task_question.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException

class AsyncTaskQuestionActivity : AppCompatActivity() {
    lateinit var context: Context
    var hasInternet = false
    var questionList: MutableList<Question> = mutableListOf()
    var index = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_question)
        context = this
        buttonNext.isEnabled = false
        buttonNext.alpha = 0.5f
        getQuestions().execute()
    }

    private fun isNetworkAvaliable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    internal inner class getQuestions : AsyncTask<Void, Void, String>() {
        lateinit var progressDialog: ProgressDialog

        fun UpdateQuestion() {
            val radioGroupChoice = findViewById<RadioGroup>(R.id.radioGroup)
            val selected = radioGroupChoice.checkedRadioButtonId
            if (selected == -1) {
                Toast.makeText(context, "Select an answer.", Toast.LENGTH_SHORT).show()
                return
            }
            if (index < questionList.size) {
                when (selected) {
                    radioButtonChoice1.id -> {
                        if (questionList[index].answer == 1) score++
                    }
                    radioButtonChoice2.id -> {
                        if (questionList[index].answer == 2) score++
                    }
                    radioButtonChoice3.id -> {
                        if (questionList[index].answer == 3) score++
                    }
                    radioButtonChoice4.id -> {
                        if (questionList[index].answer == 4) score++
                    }
                }
                index++
                if (index < questionList.size) {
                    radioGroupChoice.clearCheck()
                    textViewQuestion.text = questionList[index].question
                    radioButtonChoice1.text = questionList[index].option1
                    radioButtonChoice2.text = questionList[index].option2
                    radioButtonChoice3.text = questionList[index].option3
                    radioButtonChoice4.text = questionList[index].option4
                    if ((index + 1) == questionList.size)
                        buttonNext.text = "Finish"
                } else {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setTitle("Your Score")
                    dialog.setMessage("You have answered correct $score out of ${questionList.size} questions")
                    dialog.setPositiveButton("Close the App") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.dismiss()
                        finish()
                    }
                    dialog.show()
                }
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Downloading questions...")
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void?): String {
            if (isNetworkAvaliable()) {
                hasInternet = true
                val client = OkHttpClient()
                val url = "https://script.googleusercontent.com/macros/echo?user_content_key=sU70_s2Wa6bGK6" +
                        "flU1GTgSKExhJHPpmi5W9JJ7yANszkBqALJgV3qYlsZTfc1wEZl3RwQEUrZdCOyjMXa-CmJPM11A0s_l-2m5" +
                        "_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBg4Wj9So2Q_mI0_S0Bm21-AGmcRnplmVaRc" +
                        "xvVzvCi9cnQQJegsnVb9TgJzPufw35cdv3aNHr6K&lib=MKMzvVvSFmMa3ZLOyg67WCThf1WVRYg6Z"
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                return response.body?.string().toString()
            } else return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            if (hasInternet) {
                try {
                    val resultArray = JSONArray(result)
                    for (i in 0 until resultArray.length()) {
                        val currentObject = resultArray.getJSONObject(i)
                        val obj = Question()
                        obj.question = currentObject.getString("Question")
                        obj.option1 = currentObject.getString("Option1")
                        obj.option2 = currentObject.getString("Option2")
                        obj.option3 = currentObject.getString("Option3")
                        obj.option4 = currentObject.getString("Option4")
                        obj.answer = currentObject.getInt("Answer")
                        questionList.add(obj)
                    }
                    if (index == 0) {
                        textViewQuestion.text = questionList[index].question
                        radioButtonChoice1.text = questionList[index].option1
                        radioButtonChoice2.text = questionList[index].option2
                        radioButtonChoice3.text = questionList[index].option3
                        radioButtonChoice4.text = questionList[index].option4
                    }
                    buttonNext.isEnabled = true
                    buttonNext.alpha = 1f
                    buttonNext.setOnClickListener({ UpdateQuestion() })
                } catch (e: JSONException) {
                }
            }
        }
    }
}