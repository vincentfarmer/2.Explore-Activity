package com.example.zacharywu.exploreactivity.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zacharywu.exploreactivity.R;

public class MainLifeCycleActivity extends AppCompatActivity {
    /**
     * 控件声明：
     * 对应关系表为
     * Button startNormalActivity=>start_normal_activity
     * Button startDialogActivity =>start_dialog_activity
     */
    Button startNormalActivityButton;
    Button startDialogActivityButton;
    /**
     * 活动的七大声明状态之一，初始化启动活动时执行的方法，功能是实例化布局文件和相应的逻辑功能
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_life_cycle);
        //初始化活动逻辑
        initActivity();
    }

    /**
     * 初始化活动逻辑
     */
    public void initActivity(){
        //初始化start_normal_activity控件为startNormalActivity对象，并为其添加一个点击事件
        startNormalActivityButton = (Button)findViewById(R.id.start_normal_activity);
        startNormalActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_NORMAL_ACTIVITY_CATEGROY");
                startActivity(intent);
            }
        });

        //初始化start_dialog_activity控件为startDialogActivity对象，并为其添加一个点击事件
        startDialogActivityButton = (Button)findViewById(R.id.start_dialog_activity);
        startDialogActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_DIALOG_ACTIVITY_CATEGROY");
                startActivity(intent);
            }
        });
    }

    /**
     * 重写其他六大生命周期函数
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainLifeCycleActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainLifeCycleActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainLifeCycleActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainLifeCycleActivity", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainLifeCycleActivity", "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainLifeCycleActivity", "onRestart");
    }





}
