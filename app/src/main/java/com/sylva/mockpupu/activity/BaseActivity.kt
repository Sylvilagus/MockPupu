package com.sylva.mockpupu.activity

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sylva.mockpupu.MppApplication



open class BaseActivity: AppCompatActivity() {
    val ramStorage by lazy { (application as MppApplication).ramStorage }
    val statusBarManager by lazy { (application as MppApplication).statusBarManager }
    fun setTransparentStatusBar(){
        if(Build.VERSION.SDK_INT > 21){
            window.apply {
                decorView.apply {
                    systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
                statusBarColor = Color.TRANSPARENT
            }
        }
    }
}