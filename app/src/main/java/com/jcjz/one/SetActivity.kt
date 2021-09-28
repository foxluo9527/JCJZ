package com.jcjz.one

import android.view.View
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.data.DateUtils
import com.xuexiang.xutil.data.SPUtils
import com.xuexiang.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_set.*
import java.util.*

class SetActivity : BaseActivity() {

    override fun initLayoutId(): Int {
        return R.layout.activity_set
    }

    override fun initListener() {
        exit.setOnClickListener {
            ClickUtils.exitBy2Click()
        }
        tv_back.setOnClickListener {
            finish()
        }
        ctv_mine.setOnClickListener {
            ctv_mine.isChecked = true
            ctv_about.isChecked = false
            fl_mine.visibility = View.VISIBLE
            fl_about.visibility = View.GONE
        }
        ctv_about.setOnClickListener {
            ctv_mine.isChecked = false
            ctv_about.isChecked = true
            fl_mine.visibility = View.GONE
            fl_about.visibility = View.VISIBLE
        }
        tv_date.setOnClickListener {
            TimePickerBuilder(this) { date: Date?, _: View? ->
                tv_date.text = DateUtils.date2String(date, DateUtils.yyyyMMdd.get())
            }
                .setTitleText("生日选择")
                .build()
                .show()
        }
        iv_sure.setOnClickListener {
            val sp = SPUtils.getDefaultSharedPreferences()
            val name = et_name.text.toString()
            val age = et_age.text.toString()
            val date = tv_date.text.toString()
            if (name.isEmpty()) {
                ToastUtils.toast("请输入名称")
                return@setOnClickListener
            }
            if (age.isEmpty()) {
                ToastUtils.toast("请选择车龄")
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                ToastUtils.toast("请选择生日日期")
                return@setOnClickListener
            }
            SPUtils.putString(sp, "name", name)
            SPUtils.putString(sp, "age", age)
            SPUtils.putString(sp, "birth", date)
            ToastUtils.toast("修改成功")
        }
    }

    override fun initView() {
    }

    override fun initData() {
        SPUtils.getString(SPUtils.getDefaultSharedPreferences(), "birth", null)?.let {
            tv_date.text = it
        }
        SPUtils.getString(SPUtils.getDefaultSharedPreferences(), "name", null)?.let {
            et_name.setText(it)
        }
        SPUtils.getString(SPUtils.getDefaultSharedPreferences(), "age", null)?.let {
            et_age.setText(it)
        }
    }

    override fun initFullScreen(): Boolean {
        return true
    }
}