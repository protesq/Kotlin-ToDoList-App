package com.kodotagi.protolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class loadscreen : AppCompatActivity() {
    private val loadingDuration: Long = 3000
    private val progressBarMaxValue = 100
    private val progressBarIncrement = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadscreen)

        val progressBar = findViewById<ProgressBar>(R.id.loadingProgressBar)

        val handler = Handler()
        val runnable = object : Runnable {
            var progress = 0
            override fun run() {
                progressBar.progress = progress
                if (progress >= progressBarMaxValue) {
                    val intent = Intent(this@loadscreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    progress += progressBarIncrement
                    handler.postDelayed(this, loadingDuration / progressBarMaxValue)
                }
            }
        }

        handler.post(runnable)
    }
}
