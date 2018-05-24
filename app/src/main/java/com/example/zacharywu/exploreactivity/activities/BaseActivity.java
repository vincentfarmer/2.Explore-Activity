package com.example.zacharywu.exploreactivity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zacharywu.exploreactivity.classes.ActivityCollector;

/**
 * BaseActivity活动本质上不是一个真正的活动，而是一个模板类，用来定义格式，并且监视当前活跃的活动是哪一个活动
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName());
        //表明当前正在创建的的活动添加到活动管理器中
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //活动销毁时，在活动管理器中移除该活动，如果想在任何地方退出程序的话调用ActivityCollector.finishAll()方法就行了
        ActivityCollector.removeActivity(this);
    }
}
