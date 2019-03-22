package com.sylva.mockpupu.util

import android.content.Context
import com.sylva.mockpupu.entity.IndexCategory

class RAMStorage(private val context: Context){
    lateinit var indexCategoryList: List<IndexCategory>
    lateinit var indexBannerImageList: List<String>
}