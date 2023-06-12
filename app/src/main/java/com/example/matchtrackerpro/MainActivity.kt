package com.example.matchtrackerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //Layout wird festgelegt und geladen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Farbe der Statusleiste wird auf Dunkelgrün gesetzt
        window.apply {
            statusBarColor = getColor(R.color.dark_green)
        }
    }
}