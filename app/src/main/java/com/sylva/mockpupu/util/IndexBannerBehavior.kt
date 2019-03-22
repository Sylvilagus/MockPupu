package com.sylva.mockpupu.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.sylva.mockpupu.R

class IndexBannerBehavior: DependOnNestedScrollBehavior{

    private var appBarLayout: AppBarLayout? = null
    constructor(): super()
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    override fun onDependencyScrollY(parent: CoordinatorLayout, child: View, dependency: NestedScrollView, scrollY: Int, percent: Float) {
        if(appBarLayout == null){
            appBarLayout = parent.findViewById(R.id.indexAppBarLayout)
        }
        Log.e("behavior", "percent is $percent, scrollY is $scrollY")
        val bgColor = (appBarLayout!!.background as ColorDrawable).color
        val r = Color.red(bgColor)
        val g = Color.green(bgColor)
        val b = Color.blue(bgColor)
        val a = (percent * 255).toInt()
        val color = Color.argb(a, r, g, b)
        appBarLayout!!.setBackgroundColor(color)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.e("nestedScroll", "dyConsumed is $dyConsumed, dyUnconsumed is $dyUnconsumed")
    }
}