package com.sky.myhealthrecord

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.utils.StorageUtils
import java.util.Collections.addAll
import android.text.method.TextKeyListener.clear



/**
 * Created by Sky_Alligator on 8/22/2017.
 * 12:23 PM
 */

enum class TransactionType{
    REGISTER,ADMISSION,DISCHARGE, APPOINTMENT
}

fun Context.setupListProperties(recyclerView: RecyclerView) {
    recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    recyclerView.itemAnimator = DefaultItemAnimator()
}

open class CommonAdapter<T>(val layout: Int, var li: List<T>, val bindV: (View, T) -> Unit) :
        RecyclerView.Adapter<CommonAdapter.ViewHolder<T>>() {

    open fun updateList(list: List<T>) {
        li = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = li.size

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindData(li[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder<T> {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(layout, parent, false), bindV)
    }

    class ViewHolder<in T>(val itemV: View, val bindV: (View, T) -> Unit) : RecyclerView.ViewHolder(itemV) {

        fun bindData(data: T) {
            bindV(itemV, data)
        }
    }
}

fun configureQualityUIL(context: Context) {
    val defaultOptions = DisplayImageOptions.Builder()
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build()
    val config = ImageLoaderConfiguration.Builder(context)
            .diskCacheExtraOptions(1200, 1200, null)
            .memoryCacheExtraOptions(1200, 1200)
            .diskCache(UnlimitedDiskCache(StorageUtils.getCacheDirectory(context))) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .threadPoolSize(1)
            .diskCacheFileNameGenerator(HashCodeFileNameGenerator()) // default
            .defaultDisplayImageOptions(defaultOptions) // default
            .writeDebugLogs()
            .build()
    ImageLoader.getInstance().init(config)
}

/*
ui_fabV.setOnClickListener { view ->
    Snackbar.make(view, "Add patient", Snackbar.LENGTH_LONG)
            .setAction("New") {
                startActivity(Intent(this, AddPatientActivity::class.java))
            }.show()
}*/
