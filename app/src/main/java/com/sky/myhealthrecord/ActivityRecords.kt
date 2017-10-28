package com.sky.myhealthrecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.act_records.*
import kotlinx.android.synthetic.main.item_gallery.view.*

/**
 * Created by Sky_Alligator on 8/22/2017.
 * 12:13 PM
 */
class ActivityRecords : AppCompatActivity() {

    val docFiles = arrayListOf("doc1.jpg", "doc2.jpg", "doc3.jpg", "doc4.jpg", "doc5.jpg", "doc6.jpg", "doc7.jpg")

    val scanFiles = arrayListOf("scan1.jpg", "scan2.jpg", "scan3.jpg", "scan4.jpg", "scan5.jpg", "scan6.jpg", "scan7.jpg", "scan8.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_records)
        configureQualityUIL(this)

        setupListProperties(ui_home_scansRecyV)
        ui_home_scansRecyV.adapter = CommonAdapter(R.layout.item_gallery, scanFiles) { view, imgFile ->
            ImageLoader.getInstance().displayImage("assets://scans/$imgFile", view.ui_gallery_list_resizeImgV)
        }

        setupListProperties(ui_home_docsRecyV)
        ui_home_docsRecyV.adapter = CommonAdapter(R.layout.item_gallery, docFiles) { view, imgFile ->
            ImageLoader.getInstance().displayImage("assets://docs/$imgFile", view.ui_gallery_list_resizeImgV)
        }
    }
}