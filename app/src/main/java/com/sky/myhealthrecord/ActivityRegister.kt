package com.sky.myhealthrecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.act_register.*


/**
 * Created by Sky_Alligator on 8/22/2017.
 * 12:13 PM
 */
class ActivityRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_register)

        val adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planets_spinner.adapter = adapter

        val adapter2 = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planets_spinner2.adapter = adapter2

        ui_login_registerBtn.setOnClickListener {
            finish()
        }
    }
}