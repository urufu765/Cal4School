package com.nuwulf.cal4school

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_events.*
import java.util.logging.Logger

/**
 * TODO: Research listview, program buttons, add new activity for add and edit
 */
class EventActivity : AppCompatActivity() {

    lateinit var eventDBHelper: EventDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        val Log = Logger.getLogger(MainActivity::class.java.name)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        Log.warning("Welcome")
        eventDBHelper = EventDBHelper(this)
    }
}