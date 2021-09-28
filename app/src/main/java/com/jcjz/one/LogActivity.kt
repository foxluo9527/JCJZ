package com.jcjz.one

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import com.jcjz.one.adapter.BillAdapter
import com.jcjz.one.common.MyApplication
import com.jcjz.one.data.Bill
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.data.SPUtils
import com.xuexiang.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_log.*
import java.util.*
import kotlin.collections.ArrayList

class LogActivity : BaseActivity() {
    private var type: String? = null
    private val allBills=MyApplication.mDBService.queryAll()
    private val monthGroupBills=ArrayList<ArrayList<Bill>>()
    private val yearGroupBills=ArrayList<ArrayList<Bill>>()
    private val adapter:BillAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
    }

    override fun initLayoutId(): Int {
        return R.layout.activity_log
    }

    @SuppressLint("SetTextI18n")
    override fun initListener() {
        iv_in_sure.setOnClickListener {
            addBill(
                et_in_price.text.toString(),
                et_in_name.text.toString(),
                tv_in_date.text.toString()
            )
        }
        iv_out_sure.setOnClickListener {
            addBill(
                et_out_price.text.toString(),
                et_out_name.text.toString(),
                tv_out_date.text.toString()
            )
        }
        ctv_in.setOnClickListener {
            ctv_title.text = "${type}>支出"
            ctv_in.isChecked = true
            ctv_out.isChecked = false
            ctv_all.isChecked = false
            cv_in.visibility = View.VISIBLE
            cv_all.visibility = View.GONE
            cv_out.visibility = View.GONE
            tv_none.visibility = View.GONE
        }
        ctv_out.setOnClickListener {
            ctv_title.text = "${type}>收入"
            ctv_out.isChecked = true
            ctv_in.isChecked = false
            ctv_all.isChecked = false
            cv_in.visibility = View.GONE
            cv_all.visibility = View.GONE
            cv_out.visibility = View.VISIBLE
            tv_none.visibility = View.GONE
        }
        ctv_all.setOnClickListener {
            ctv_title.text = "${type}>全部"
            ctv_all.isChecked = true
            ctv_out.isChecked = false
            ctv_in.isChecked = false
            cv_in.visibility = View.GONE
            cv_out.visibility = View.GONE
            if (allBills.size>0) {
                cv_all.visibility = View.VISIBLE
                tv_none.visibility=View.GONE
            } else{
                cv_all.visibility = View.GONE
                tv_none.visibility=View.VISIBLE
            }
        }
        tv_back.setOnClickListener {
            finish()
        }
        exit.setOnClickListener {
            ClickUtils.exitBy2Click()
        }
    }

    override fun initView() {
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun initData() {
        type = intent.getStringExtra("type")
        ctv_title.text = "${type}>支出"
        val map: Map<String, ArrayList<Bill>> = HashMap<String,ArrayList<Bill>>()
        for (bill in allBills){
            val month=bill.year_month
            if (map.containsKey(bill.year)){
                map[bill.year]?.add(bill)
            }else{
//                map[bill.year]=ArrayList<Bill>()
            }
        }
    }

    override fun initFullScreen(): Boolean {
        return true
    }

    fun getGroupBill(byYear: Boolean, date: String): ArrayList<Bill> {
        return if (byYear)
            MyApplication.mDBService.queryByColumn("year", date) as ArrayList<Bill>
        else
            MyApplication.mDBService.queryByColumn("year_month", date) as ArrayList<Bill>
    }

    @SuppressLint("StaticFieldLeak")
    fun addBill(price: String, name: String, date: String) {
        if (price.isEmpty()) {
            ToastUtils.toast("请输入价格")
            return
        }
        if (name.isEmpty()) {
            ToastUtils.toast("请输入名称")
            return
        }
        if (date.isEmpty()) {
            ToastUtils.toast("请选择日期")
            return
        }
        val bill = Bill()
        bill.date = date
        bill.year = date.substring(0, 4)
        bill.year_month = date.substring(0, 7)
        bill.name = name
        bill.price = price.toDouble()
        bill.sex = SPUtils.getString(SPUtils.getDefaultSharedPreferences(), "sex", "women")
        bill.type = type
        AlertDialog.Builder(this)
            .setMessage("添加此笔记账?")
            .setPositiveButton(
                "确认"
            ) { dialogInterface: DialogInterface?, _: Int ->
                object : AsyncTask<Void?, Void?, Void?>() {
                    override fun doInBackground(vararg p0: Void?): Void? {
                        MyApplication.mDBService.insert(bill)
                        return@doInBackground null
                    }

                    override fun onPostExecute(aVoid: Void?) {
                        dialogInterface?.dismiss()
                    }
                }.execute()
            }
            .setNegativeButton("取消", null)
            .create()
    }
}