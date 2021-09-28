package com.jcjz.one

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jcjz.one.adapter.CarsAdapter
import com.jcjz.one.common.CommonUtils
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.data.SPUtils
import kotlinx.android.synthetic.main.activity_car.*

class CarActivity : BaseActivity() {
    private var carPosition=0
    private val carsAdapter = CarsAdapter(this)
    override fun initLayoutId(): Int {
        return R.layout.activity_car
    }

    override fun initListener() {
        carsAdapter.setCarSelectListener {
            carPosition=it
            Glide.with(this)
                .load(CommonUtils.getInstance().cars[it])
                .into(iv_car)
        }
        iv_sex.setOnClickListener {
            finish()
        }
        exit.setOnClickListener {
            ClickUtils.exitBy2Click()
        }
        enter.setOnClickListener {
            SPUtils.putInt(SPUtils.getDefaultSharedPreferences(),"car",carPosition)
            startActivity(Intent(this,MainActivity::class.java))
        }
        mask.setOnClickListener {
            prompt_cars.visibility= View.GONE
            prompt_enter.visibility= View.GONE
            mask.visibility=View.GONE
        }
    }

    override fun initView() {
        rv_cars.adapter = carsAdapter
        rv_cars.layoutManager=LinearLayoutManager(this)
    }

    override fun initData() {
        Glide.with(this)
            .load(CommonUtils.getInstance().cars[SPUtils.getInt(SPUtils.getDefaultSharedPreferences(),"car",0)])
            .into(iv_car)
        if (SPUtils.getString(SPUtils.getDefaultSharedPreferences(), "sex", "women")
                .equals("man")
        ) {
            tv_sex.text = getString(R.string.boy)
            Glide.with(this)
                .load(R.drawable.ic_man)
                .into(iv_sex)
            Glide.with(this)
                .load(R.drawable.man_character_img)
                .into(iv_person)
        } else {
            tv_sex.text = getString(R.string.gril)
            Glide.with(this)
                .load(R.drawable.ic_women)
                .into(iv_sex)
            Glide.with(this)
                .load(R.drawable.women_character_img)
                .into(iv_person)
        }
    }

    override fun initFullScreen(): Boolean {
        return true
    }

}