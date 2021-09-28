package com.jcjz.one

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import com.bumptech.glide.Glide
import com.jcjz.one.common.CommonUtils
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.data.SPUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
        mask.setOnClickListener {
            description_man.visibility=View.GONE
            description_women.visibility=View.GONE
            description_menu.visibility=View.GONE
            mask.visibility=View.GONE
        }
        tv_exit.setOnClickListener {
            finish()
        }
        tv_set.setOnClickListener {
            startActivity(Intent(this,SetActivity::class.java))
        }
        tv_man_data.setOnClickListener {
            val intent=Intent(this,BillActivity::class.java)
            intent.putExtra("sex","man")
            startActivity(intent)
        }
        tv_women_data.setOnClickListener {
            val intent=Intent(this,BillActivity::class.java)
            intent.putExtra("sex","women")
            startActivity(intent)
        }
    }

    override fun initView() {
    }

    override fun initData() {
        Glide.with(this)
            .load(CommonUtils.getInstance().cars[SPUtils.getInt(SPUtils.getDefaultSharedPreferences(),"car",0)])
            .into(iv_car)
    }

    override fun initFullScreen(): Boolean {
        return true
    }

    fun onLogEvent(view: View) {
        val intent=Intent(this,LogActivity::class.java)
        when(view.id){
            R.id.gloves->intent.putExtra("type","手套")
            R.id.helmet->intent.putExtra("type","头盔")
            R.id.tire->intent.putExtra("type","轮胎")
            R.id.cloth->intent.putExtra("type","服装")
            R.id.other->intent.putExtra("type","其他")
        }
        startActivity(intent)
    }
}