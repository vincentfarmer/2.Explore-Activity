package com.example.zacharywu.exploreactivity.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zacharywu.exploreactivity.R;
import com.example.zacharywu.exploreactivity.classes.ActivityCollector;

public class MyHTTPActivity extends BaseActivity {
    /**
     * 声明所有的本活动对应布局文件中的控件,实例名与控件名的对应关系为：
     * 控件名使用下划线分割单词的命名方法，
     * 实例名是控件名去除掉下划线后使用java的驼峰法命名
     * <p>
     * 对应关系表为
     * Button exitAppButton=>exit_app
     */
    Button exitAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_http);
        //初始化活动
        initActivity();
    }

    /**
     * 初始化活动
     */
    public void initActivity(){
        Log.d("MyHTTPActivity", "Task id is "+ getTaskId());
        //实例化控件return_main_activity为returnMainActivityButton对象并设置点击事件
        exitAppButton = findViewById(R.id.exit_app);
        exitAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁管理器中的所有活动
                ActivityCollector.finishAll();
            }
        });
    }
}
