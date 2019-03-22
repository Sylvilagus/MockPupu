package com.sylva.mockpupu.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sylva.mockpupu.MppApplication
import com.sylva.mockpupu.R
import com.sylva.mockpupu.widget.InnerViewPager

class IndexHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var viewPager: InnerViewPager
    init {
        val indexBannerImageList = (itemView.context.applicationContext as MppApplication).ramStorage.indexBannerImageList
        viewPager = itemView.findViewById(R.id.indexViewPager)
        viewPager.adapter = ImagePagerAdapter(itemView.context, indexBannerImageList)
    }
}