package com.sylva.mockpupu.fragment

import android.support.v4.app.Fragment
import com.sylva.mockpupu.MppApplication
import com.sylva.mockpupu.util.RAMStorage
import com.sylva.mockpupu.util.StatusBarManager

open class BaseFragment: Fragment(){
    val ramStorage: RAMStorage? by lazy {
        var storage: RAMStorage? = null
        activity?.apply {
            storage = (application as MppApplication).ramStorage
        }
        storage
    }
    val statusBarManager: StatusBarManager? by lazy {
        var sbm: StatusBarManager? = null
        activity?.apply {
            sbm = (application as MppApplication).statusBarManager
        }
        sbm
    }
}