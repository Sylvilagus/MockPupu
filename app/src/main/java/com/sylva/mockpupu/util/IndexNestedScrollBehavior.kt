package com.sylva.mockpupu.util

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.sylva.mockpupu.R

abstract class IndexNestedScrollBehavior: CoordinatorLayout.Behavior<View>{
    private var toolbarHeight = 0f
    private var initialDependencyTop = 0
    private lateinit var context: Context
    constructor(): super()
    constructor(context: Context, attrs: AttributeSet){
        this.context = context
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View) = dependency.id == R.id.nestedScrollView

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if(toolbarHeight == 0f){
            toolbarHeight = context.resources.getDimension(R.dimen.toolbar_height)
            initialDependencyTop = dependency.top
        }
        val deltaDependencyTop = initialDependencyTop - dependency.top
        val percent = deltaDependencyTop / toolbarHeight
        onNestedScrollViewMove(parent, child, dependency, percent, deltaDependencyTop)
        return true
    }

    abstract fun onNestedScrollViewMove(parent: CoordinatorLayout, child: View, dependency: View, percent: Float, delta: Int)
}