package com.jcjz.one

import android.content.Intent
import com.xuexiang.xutil.data.SPUtils

class SplashActivity : BaseActivity() {
    override fun initLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initListener() {

    }

    override fun initView() {

    }

    override fun initData() {
        if (SPUtils.getBoolean(SPUtils.getDefaultSharedPreferences(), "unselect_sex_car", true)) {
            startActivity(Intent(this, SexActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    override fun initFullScreen(): Boolean {
        return true
    }
}