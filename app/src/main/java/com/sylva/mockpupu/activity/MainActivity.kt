package com.sylva.mockpupu.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sylva.mockpupu.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTransparentStatusBar()
    }
}
