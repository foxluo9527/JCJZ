package com.jcjz.one

import android.content.Intent
import com.bumptech.glide.Glide
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.common.StringUtils
import com.xuexiang.xutil.data.SPUtils
import kotlinx.android.synthetic.main.activity_sex.*

class SexActivity : BaseActivity() {
    private var sex= "women"
    override fun initLayoutId(): Int {
        return R.layout.activity_sex
    }

    override fun initView() {
    }

    override fun initData() {
        sex=SPUtils.getString(SPUtils.getDefaultSharedPreferences(),"sex","women")
        Glide.with(this)
            .load(CommonUtils.getInstance().cars[SPUtils.getInt(SPUtils.getDefaultSharedPreferences(),"car",1)])
            .into(iv_car)
        if (StringUtils.equals(sex,"man")){
            tv_sex.text = getString(R.string.boy)
            Glide.with(this)
                .load(R.drawable.man_character_img)
                .into(iv_person)
        }else{
            tv_sex.text = getString(R.string.gril)
            Glide.with(this)
                .load(R.drawable.women_character_img)
                .into(iv_person)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
    override fun initFullScreen(): Boolean {
        return true
    }

    override fun initListener() {
        iv_man.setOnClickListener {
            tv_sex.text = getString(R.string.boy)
            sex = "man"
            Glide.with(this)
                .load(R.drawable.man_character_img)
                .into(iv_person)
        }
        iv_women.setOnClickListener {
            tv_sex.text = getString(R.string.gril)
            sex = "women"
            Glide.with(this)
                .load(R.drawable.women_character_img)
                .into(iv_person)
        }
        enter.setOnClickListener {
            SPUtils.putString(SPUtils.getDefaultSharedPreferences(),"sex",sex)
            startActivity(Intent(this, CarActivity::class.java))
        }

        exit.setOnClickListener {
            ClickUtils.exitBy2Click()
        }
    }
}