package com.sylva.mockpupu.fragment

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sylva.mockpupu.R
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment: BaseFragment(){
    lateinit var titleList: List<String?>
    val fragmentList: MutableList<Fragment> = arrayListOf()
    lateinit var indexAdapter: FragmentPagerAdapter
    private var specialColorList: ColorStateList? = null
    private var normalColorList: ColorStateList? = null
    private var colorPrimary: Int = 0
    private var vScrollAnimationDisabled = false
    private lateinit var locationWhite: Drawable
    private lateinit var arrowWhite: Drawable
    private lateinit var locationBlack: Drawable
    private lateinit var arrowBlack: Drawable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_index, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unknownCategory = resources.getString(R.string.unknown_category)
        ramStorage?.apply {
            titleList = indexCategoryList
                    .mapIndexed{index, e ->
                        fragmentList += if(index == 0)IndexListFragment() else MockListFragment()
                        tabLayout.addTab(tabLayout.newTab())
                        e.name ?: unknownCategory
                    }

        }
        indexAdapter = object : FragmentPagerAdapter(activity!!.supportFragmentManager){
            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getCount(): Int {
                return fragmentList.size
            }

        }
        viewPager.adapter = indexAdapter
        tabLayout.setupWithViewPager(viewPager)
        with(tabLayout){
            (0 until tabCount).forEach {
                getTabAt(it)?.text = titleList[it]
            }
        }
        val toolbarHeight = context!!.resources.getDimension(R.dimen.toolbar_height)


        context?.resources?.apply {
            specialColorList = getColorStateList(R.color.tab_color_special)
            normalColorList = getColorStateList(R.color.tab_color_normal)
            colorPrimary = getColor(R.color.colorPrimary)

            locationWhite = getDrawable(R.mipmap.ic_place_light)
            arrowWhite = getDrawable(R.mipmap.ic_arrow_right_light)

            locationBlack = getDrawable(R.mipmap.ic_place_dark)
            arrowBlack = getDrawable(R.mipmap.ic_arrow_right_dark)
            locationWhite.setBounds(0, 0, 56, 56)
            arrowWhite.setBounds(0, 0, 23, 53)
            locationBlack.setBounds(0, 0, 56, 56)
            arrowBlack.setBounds(0, 0, 23, 53)
        }
        indexAppBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, yOffset: Int) {
                if(vScrollAnimationDisabled)
                    return
                appBarLayout?.apply {
                    val absY = Math.abs(yOffset).toFloat()
                    val deltaY = if(absY > toolbarHeight) toolbarHeight else absY
                    val percent = deltaY / toolbarHeight
                    val bgColor = (background as ColorDrawable).color
                    val r = Color.red(bgColor)
                    val g = Color.green(bgColor)
                    val b = Color.blue(bgColor)
                    val a = (percent * 255).toInt()
                    val color = Color.argb(a, r, g, b)
                    setBackgroundColor(color)

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
                    }
                }

            }

        })

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position == 0 && positionOffsetPixels == 0){
                    indexLocation.setCompoundDrawables(locationWhite, null, arrowWhite, null)
                    indexMessage.setImageResource(R.mipmap.ic_msg_light)
                    vScrollAnimationDisabled = false
                    tabLayout?.apply {
                        setSelectedTabIndicatorColor(Color.WHITE)
                        tabTextColors = specialColorList
                    }
                    indexAppBarLayout.setBackgroundResource(R.color.transparent)
                    indexSearchBarContainer.setBackgroundResource(R.color.transparent)
                    indexFakeToolbar.setBackgroundResource(R.color.transparent)
                    indexLocation.setTextColor(resources.getColorStateList(R.color.index_text_white))

                }else{
                    indexLocation.setCompoundDrawables(locationBlack, null, arrowBlack, null)
                    indexMessage.setImageResource(R.mipmap.ic_msg_dark)
                    vScrollAnimationDisabled = true
                    tabLayout?.apply {
                        setSelectedTabIndicatorColor(colorPrimary)
                        tabTextColors = normalColorList
                    }
                    indexAppBarLayout.setBackgroundResource(R.color.transparent)
                    indexSearchBarContainer.setBackgroundResource(R.color.white)
                    indexFakeToolbar.setBackgroundResource(R.color.white)
                    indexLocation.setTextColor(resources.getColorStateList(R.color.index_text_grey))
                }
            }

            override fun onPageSelected(p0: Int) {
            }

        })
    }
}