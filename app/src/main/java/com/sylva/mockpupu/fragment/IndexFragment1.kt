package com.sylva.mockpupu.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sylva.mockpupu.R
import com.sylva.mockpupu.adapter.ImagePagerAdapter
import kotlinx.android.synthetic.main.fragment_index1.*

class IndexFragment1: BaseFragment(){
    lateinit var titleList: List<String?>
    val fragmentList: MutableList<Fragment> = arrayListOf()
    lateinit var indexAdapter: FragmentPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_index, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unknownCategory = resources.getString(R.string.unknown_category)
        ramStorage?.apply {
            titleList = indexCategoryList
                    .map{
                        fragmentList += MockListFragment()
                        tabLayout.addTab(tabLayout.newTab())
                        it.name ?: unknownCategory
                    }
            bannerViewPager.adapter = ImagePagerAdapter(activity!!, indexBannerImageList)

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
    }
}