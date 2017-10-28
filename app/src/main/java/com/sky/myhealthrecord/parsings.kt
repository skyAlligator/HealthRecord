package com.sky.myhealthrecord

import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson

/**
 * Created by Sky_Alligator on 10/26/2017.
 * 1:31 PM
 */
data class Transaction(var patientId: String?,
                       var recordType: String?,
                       var timestamp: String?,

                       val date: String?,
                       val time: String?,
                       val type: String?,

                       val hospitalId: String?,
                       val physician: String?,
                       val Diagnosis: String?,
                       val reason: String?,
                       val docs: List<String>?,
                       val insurance: String?,

                       val firstName: String?,
                       val lastName: String?,
                       val age: String?,
                       val location: String?,
                       val phNo: String?)

class OuterData(val resources: List<InnerData>)

class InnerData(val patientId: String, val recordType: String, val record: String,val timestamp:String)

fun getHistoryFromJson() {

    val dataObj = Gson().fromJson<List<OuterData>>(transactionJson)
    val transactionList = arrayListOf<Transaction>()

    for (obj in dataObj) {
        val rs = obj.resources[0]

        val replaced = rs.record.replace("'", "\"")
        Log.d("parsing",replaced)
        val transactionObj = Gson().fromJson<Transaction>(replaced)

        transactionObj.patientId= rs.patientId
        transactionObj.recordType= rs.recordType
        transactionObj.timestamp=rs.timestamp
        transactionList.add(transactionObj)
    }

}

val transactionJson = """
[
{
    "resources":[
    {
        "patientId":"100",
        "recordType":"join",
        "record":"{'date':'10/11/2017',
                    'time':'23:10',
                    'firstName':'dinesh',
                    'lastName':'balasundaram',
                    'age':'28',
                    'location':'chennai',
                    'phNo':'9790785993',
                    'insurance':'AGQ13882513397204'
                    }"
    }
    ],
    "timestamp":"2017-10-28T12:24:31.388Z"
},
{
    "resources":[
    {
        "patientId":"100",
        "recordType":"appoinment",
        "record":"{'date':'12/11/2017',
                    'time':'12:00',
                    'hospitalId':'101',
                    'physician':'Rajapandi',
                    'Diagnosis':'Bone Fracture',
                    'reason':'surgery'
                    }"
    }
    ],
    "timestamp":"2017-10-28T12:25:32.005Z"
},
{
    "resources":[
    {
        "patientId":"100",
        "recordType":"labreport",
        "record":"{'date':'15/11/2017',
                    'time':'09:10',
                    'hospitalId':'101',
                    'physician':'Rajapandi',
                    'Diagnosis':'Bone Fracture',
                    'type':'scan',
                    'docs':['image.jpg','reportsummary.pdf']
                    }"
    }
    ],
    "timestamp":"2017-10-28T12:25:32.005Z"
},
{
    "resources":[
    {
        "patientId":"100",
        "recordType":"admission",
        "record":"{'date':'17/11/2017',
                    'time':'14:15',
                    'hospitalId':'101',
                    'physician':'Rajapandi',
                    'Diagnosis':'Bone Fracture',
                    'reason':'surgery',
                    'type':'inpatient',
                    'insurance':'AGQ13882513397204'
                    }"
    }
    ],
    "timestamp":"2017-10-28T12:25:32.005Z"
},
{
    "resources":[
    {
        "patientId":"100",
        "recordType":"discharge",
        "record":"{'date':'18/11/2017',
                    'time':'10:30',
                    'hospitalId':'101',
                    'physician':'Rajapandi',
                    'Diagnosis':'Bone Fracture',
                    'docs':['image.jpg','reportsummary.pdf']
                    }"
    }
    ],
    "timestamp":"2017-10-28T12:25:32.005Z"
}
]
"""



