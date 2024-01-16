package com.empirik.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    private lateinit var chronometerForeground: Chronometer
    private lateinit var chronometerBackground: Chronometer
    private var pauseChronoFore: Long = SystemClock.elapsedRealtime()
    private var pauseChronoBack: Long = SystemClock.elapsedRealtime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometerForeground = findViewById<View>(R.id.chronometer1) as Chronometer
        chronometerForeground.setBase(SystemClock.elapsedRealtime())

        chronometerBackground = findViewById<View>(R.id.chronometer2) as Chronometer
        chronometerBackground.setBase(SystemClock.elapsedRealtime())
    }


    override fun onResume() {
        super.onResume()
        chronometerForeground.setBase(chronometerForeground.getBase() + SystemClock.elapsedRealtime() - pauseChronoFore)
        chronometerForeground.start()

        pauseChronoBack = SystemClock.elapsedRealtime()
        chronometerBackground.setBase(chronometerBackground.getBase() + SystemClock.elapsedRealtime() - pauseChronoBack)
        chronometerBackground.stop()
    }

    override fun onStop() {
        super.onStop()
        pauseChronoFore = SystemClock.elapsedRealtime()
        chronometerForeground.stop()

        chronometerBackground.setBase(chronometerBackground.getBase() + SystemClock.elapsedRealtime() - pauseChronoBack)
        chronometerBackground.start()
    }
}