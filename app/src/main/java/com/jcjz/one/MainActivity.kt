package com.jcjz.one

import android.os.Bundle
import android.view.KeyEvent
import com.xuexiang.xutil.common.ClickUtils

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.cleanTaskExpectTop()
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click()
        }
        return true
    }
    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initFullScreen(): Boolean {
        return true
    }
}