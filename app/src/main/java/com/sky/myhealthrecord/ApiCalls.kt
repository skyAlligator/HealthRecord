package com.sky.myhealthrecord

import android.content.Context
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

/**
 * Created by hcltmac029 on 28/10/17.
 * 8:56 PM
 */

fun Context.callHistoryApi(){
    Fuel.get("http://192.168.43.39:3000/api/system/transactions").responseString { request, response, result ->
        print(request)
        print(response)
        when(result){
            is Result.Failure -> {
                Toast.makeText(this,"Request Failed",Toast.LENGTH_LONG).show()
            }
            is Result.Success -> {
                Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
                val data=result.getAs<String>()
                println(data)
            }
        }
    }
}