package com.sylva.mockpupu.util

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.sylva.mockpupu.R

abstract class DependOnNestedScrollBehavior: CoordinatorLayout.Behavior<View>{
    private lateinit var context: Context
    private var toolbarHeight = 0f
    constructor(): super()
    constructor(context: Context, attrs: AttributeSet){
        this.context = context
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        Log.e("dependency", "dependency class is ${dependency.javaClass.name}, is nestedScrollView? ${dependency.id == R.id.nestedScrollView}")
        return dependency.id == R.id.nestedScrollView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if(toolbarHeight == 0f){
            toolbarHeight = context.resources.getDimension(R.dimen.toolbar_height)
        }
        with(dependency as NestedScrollView){
            val scrolled: Int = if(scrollY > toolbarHeight) toolbarHeight.toInt() else scrollY
            val percent = scrolled / toolbarHeight
            onDependencyScrollY(parent, child, this, scrollY, percent)
        }

        return true
    }

    abstract fun onDependencyScrollY(parent: CoordinatorLayout, child: View, dependency: NestedScrollView, scrollY: Int, percent: Float)
}