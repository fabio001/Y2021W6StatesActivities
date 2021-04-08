package com.iatli.y2021w6statesactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    var counter =0
    val TAG:String = "Week6App"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val txt = findViewById<TextView>(R.id.txt_counter)
        txt.text = "$counter"

        Log.d(TAG, "onCreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")

        val shared = getSharedPreferences("mypref", MODE_PRIVATE)

        counter = shared.getInt("counter", 0)
        val txt = findViewById<TextView>(R.id.txt_counter)
        txt.text = "$counter"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstance called")
        
        /*counter = savedInstanceState.getInt("counter", 0)
        val txt = findViewById<TextView>(R.id.txt_counter)
        txt.text = "$counter"
        */

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")

        //save the counter for next launch
        val pref = getSharedPreferences("mypref", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("counter", counter)

        //you need to save the file by calling commit() function
        editor.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    fun onclick_increase(view: View) {
        counter++
        val txt = findViewById<TextView>(R.id.txt_counter)
        txt.text = "$counter"
    }

    fun onclick_announcment(view: View) {

        val thread = Thread(Runnable {
            val doc = Jsoup.connect("https://aybu.edu.tr/muhendislik/bilgisayar/").get()
            val element = doc.select("div.caContent")

            runOnUiThread {
                val txtview = findViewById<TextView>(R.id.txt_announcement)
                txtview.text = element.text()
            }
        })

        thread.start()

    }


}