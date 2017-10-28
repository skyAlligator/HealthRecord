package com.sky.myhealthrecord

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_login.*

/**
 * Created by Sky_Alligator on 8/22/2017.
 * 11:30 AM
 */
class ActivityLogin : ActivityAlpha(R.layout.act_login) {

    override fun init() {
        ui_loginBtn.setOnClickListener {
            startActivity(Intent(this, ActivityHistory::class.java))
        }

        ui_login_registerBtn.setOnClickListener {
            startActivity(Intent(this, ActivityRegister::class.java))
        }
    }
}