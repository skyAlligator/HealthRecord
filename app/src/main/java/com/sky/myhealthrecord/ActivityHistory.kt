package com.sky.myhealthrecord

import android.app.SearchManager
import android.content.Context
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.act_history.*
import kotlinx.android.synthetic.main.item_history.view.*


/**
 * Created by hcltmac029 on 28/10/17.
 * dinesh
 */
class ActivityHistory : ActivityAlpha(R.layout.act_history) {
    private val historyRecords = arrayListOf<Transaction>()
    private var adapter: CommonAdapter<Transaction>? = null

    override fun init() {
        setSupportActionBar(ui_actHistory_toolbarV)
//        getHistoryFromJson()

        callHistoryApi()

        adapter = CommonAdapter(R.layout.item_history, historyRecords) { view, transaction ->
            //            ImageLoader.getInstance().displayImage("assets://profile_pics/${transaction.img}", view.ui_landing_PatientImgV)
//            view.ui_item_history_HeadingTV.text = transaction.name
//            view.ui_item_history_dateTV.text = transaction.date
//            view.ui_item_history_DescriptionTV.text = transaction.Description
//            view.ui_item_history_actionImgV.setImageDrawable(getDrawable(R.drawable.hospital))
        }
        ui_actHistory_listV.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.history_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    //                    val list = historyRecords.filter { it.name.toLowerCase().contains(newText.toLowerCase()) }
//                    adapter?.updateList(list)
                }
                return true
            }

        })

        searchView.setOnCloseListener {
            adapter?.updateList(historyRecords)
            return@setOnCloseListener true
        }
        return true
    }

}