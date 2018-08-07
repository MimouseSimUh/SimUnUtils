package com.mimouse.simunutilslib.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//用于禁止横屏
        //加载布局
        initLayout();
        //加载视图
        initView();
        //加载数据
        initData();
        //加载监听器
        initListener();
    }
    public abstract void initLayout();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();


    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
