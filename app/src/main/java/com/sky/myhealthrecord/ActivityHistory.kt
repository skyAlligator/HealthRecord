package com.sky.myhealthrecord

import android.app.SearchManager
import android.content.Context
import android.support.v7.widget.SearchView
import android.view.Menu
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.act_history.*
import kotlinx.android.synthetic.main.item_history.view.*


/**
 * Created by hcltmac029 on 28/10/17.
 * dinesh
 */
class ActivityHistory : ActivityAlpha(R.layout.act_history) {
    private var transactionHistoryList = arrayListOf<Transaction>()
    private var adapter: CommonAdapter<Transaction>? = null

    override fun init() {
        setSupportActionBar(ui_actHistory_toolbarV)
        configureQualityUIL(this)
//        callHistoryApi()
        transactionHistoryList = getHistoryFromJson(transactionJson)

        adapter = CommonAdapter(R.layout.item_history, transactionHistoryList) { view, transactionItem ->
            view.ui_item_history_dateTV.text = transactionItem.timestamp
            view.ui_item_history_HeadingTV.text = transactionItem.recordType
            val img = when (transactionItem.recordType) {
                "join" -> {

                    view.ui_item_history_DescriptionTV.text = "${transactionItem.firstName} ${transactionItem.lastName} \n ${transactionItem.location} \n ${transactionItem.insurance}"
                    "user.png"
                }
                "appoinment" -> {
                    view.ui_item_history_DescriptionTV.text = "${transactionItem.hospitalId} ${transactionItem.physician} \n ${transactionItem.Diagnosis} ${transactionItem.reason}"
                    "user.png"
                }
                "labreport" -> {
                    view.ui_item_history_DescriptionTV.text = "${transactionItem.hospitalId} ${transactionItem.physician} \n ${transactionItem.Diagnosis} ${transactionItem.type}"
                    "hospital.png"
                }
                "admission" -> {
                    view.ui_item_history_DescriptionTV.text = "${transactionItem.hospitalId} ${transactionItem.physician} \n ${transactionItem.Diagnosis} ${transactionItem.reason} \n ${transactionItem.insurance}"
                    "hospital.png"
                }
                "discharge" -> {
                    view.ui_item_history_DescriptionTV.text = "${transactionItem.hospitalId} ${transactionItem.physician} \n ${transactionItem.Diagnosis} "
                    "hospital.png"
                }
                else -> "insurer.png"
            }
            ImageLoader.getInstance().displayImage("assets://profile_pics/$img", view.ui_item_history_actionImgV)
        }
        ui_actHistory_listV.adapter = adapter
        setupListProperties(ui_actHistory_listV)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.history_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val list = transactionHistoryList.filter { item ->
                        item.recordType.notNull { it.contains(newText, true) }
                    }
                    adapter?.updateList(list)
                }
                return true
            }
        })

        searchView.setOnCloseListener {
            adapter?.updateList(transactionHistoryList)
            return@setOnCloseListener true
        }
        return true
    }

}