package com.jcjz.one;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xutil.display.Colors;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isFullScreen;
    abstract int initLayoutId();
    abstract void initListener();
    abstract void initView();
    abstract void initData();
    //是否全屏
    abstract boolean initFullScreen();
    public void setStatusBarTranslate() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.TRANSPARENT);
    }

    public void setStatusBarDark(boolean isDark) {
        if (isDark)
            StatusBarUtils.setStatusBarLightMode(this);
        else
            StatusBarUtils.setStatusBarDarkMode(this);
    }

    public void setFullScreen(boolean fullScreen){
        if (fullScreen) {
            StatusBarUtils.fullScreen(this);
        } else {
            StatusBarUtils.cancelFullScreen(this);
        }
    }
    public int getStatusBarHeight() {
        return StatusBarUtils.getNavigationBarHeight(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        XUI.initTheme(this);
        isFullScreen=initFullScreen();
        setContentView(initLayoutId());
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        setFullScreen(isFullScreen);
        super.onResume();
    }
}
