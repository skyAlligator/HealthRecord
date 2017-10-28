package com.sky.myhealthrecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Sky_Alligator on 10/22/2017.
 * 8:42 PM
 */
abstract class ActivityAlpha(val layout:Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        init()
    }

    abstract fun init()
}