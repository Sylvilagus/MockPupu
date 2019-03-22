package com.sylva.mockpupu.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.sylva.mockpupu.R
import com.sylva.mockpupu.entity.IndexCategory
import io.reactivex.Emitter
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Observable.create{emitter: Emitter<String> ->
            ramStorage.indexCategoryList = arrayListOf("推荐", "掌柜推荐", "限时抢购", "特价专区", "人气热卖", "本周上新")
                    .mapIndexed { index, s ->  IndexCategory(s, "$index")}
            ramStorage.indexBannerImageList = arrayListOf("https://icweiliimg1.pstatp.com/weili/l/239018134144811322.webp", "https://weiliicimg9.pstatp.com/weili/l/248610093196902809.webp")
            Thread.sleep(200)
            emitter.onNext("")
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
    }
}