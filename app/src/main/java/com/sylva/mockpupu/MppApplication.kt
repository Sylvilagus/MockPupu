package com.sylva.mockpupu

import android.app.Application
import com.sylva.mockpupu.util.RAMStorage
import com.sylva.mockpupu.util.StatusBarManager

class MppApplication: Application(){
    lateinit var ramStorage: RAMStorage
    lateinit var statusBarManager: StatusBarManager
    override fun onCreate() {
        super.onCreate()
        ramStorage = RAMStorage(this)
        statusBarManager = StatusBarManager(this)
    }
}