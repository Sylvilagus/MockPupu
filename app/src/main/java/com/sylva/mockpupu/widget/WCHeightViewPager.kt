package com.sylva.mockpupu.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log

class WCHeightViewPager: ViewPager{
    constructor(context: Context): super(context)
    constructor(context: Context, attributes: AttributeSet): super(context, attributes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val start = System.currentTimeMillis()
        val maxChildHeight = (0 until childCount)
                .map {
                    val child = getChildAt(it)
                    child.measure(0, MeasureSpec.UNSPECIFIED)
                    child.measuredHeight
                }
                .max()?:0
        val heightMeasureSpecFromChild = MeasureSpec.makeMeasureSpec(maxChildHeight, MeasureSpec.EXACTLY)
        val end = System.currentTimeMillis()
        Log.e("millis","kotlin cost ${end - start}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpecFromChild)
    }
}