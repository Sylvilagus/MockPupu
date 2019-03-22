package com.sylva.mockpupu.util

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sylva.mockpupu.R
import com.sylva.mockpupu.widget.InnerViewPager

class IndexToolbarFadeBehavior1: IndexNestedScrollBehavior{
    private var banner: View? = null
    private var tabLayout: TabLayout? = null
    private var tvLocation: TextView? = null
    private var ivMessage: ImageView? = null
    private var appBarLayout: AppBarLayout? = null
    private var toolbarSpace: View? = null
    private var statusbarSpace: View? = null
    private var viewPager: InnerViewPager? = null
    private var specialColorList: ColorStateList? = null
    private var normalColorList: ColorStateList? = null
    private var colorPrimary: Int = 0

    constructor(): super()
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    override fun onNestedScrollViewMove(parent: CoordinatorLayout, child: View, dependency: View, percent: Float, delta: Int) {
        if(specialColorList == null){
            with(parent.context.resources){
                specialColorList = getColorStateList(R.color.tab_color_special)
                normalColorList = getColorStateList(R.color.tab_color_normal)
                colorPrimary = getColor(R.color.colorPrimary)
            }
        }
        if (banner == null){
            banner = parent.findViewById(R.id.indexSearchBarContainer)
            tabLayout = parent.findViewById(R.id.tabLayout)
            tvLocation = parent.findViewById(R.id.indexLocation)
            ivMessage = parent.findViewById(R.id.indexMessage)
            appBarLayout = parent.findViewById(R.id.indexAppBarLayout)
            toolbarSpace = parent.findViewById(R.id.indexToolbarSpace)
            statusbarSpace = parent.findViewById(R.id.indexStatusBarSpace)
            viewPager = parent.findViewById(R.id.bannerViewPager)
        }
        banner?.let {
            val bgColor = (it.background as ColorDrawable).color
            val r = Color.red(bgColor)
            val g = Color.green(bgColor)
            val b = Color.blue(bgColor)
            val a = (percent * 255).toInt()
            val color = Color.argb(a, r, g, b)
            it.setBackgroundColor(color)
            toolbarSpace?.setBackgroundColor(color)
            statusbarSpace?.apply {
                setBackgroundColor(color)
                translationY = -delta.toFloat()
            }
        }
        tabLayout?.apply {
            if(percent == 0f){
                tabTextColors = specialColorList
                setSelectedTabIndicatorColor(Color.WHITE)
                (context as Activity).window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }else{
                tabTextColors = normalColorList
                setSelectedTabIndicatorColor(colorPrimary)
                (context as Activity).window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            viewPager?.translationY = -delta.toFloat()
        }
    }
    //根本不执行。。。
    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        if(velocityY < 0){
            appBarLayout?.translationY = 30f
        }
        return true
    }

}