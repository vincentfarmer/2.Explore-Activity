package com.example.zacharywu.exploreactivity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zacharywu.exploreactivity.R;

public class SecondActivity extends AppCompatActivity {

    /**
     * 声明所有的本活动对应布局文件中的控件,实例名与控件名的对应关系为：
     * 控件名使用下划线分割单词的命名方法，
     * 实例名是控件名去除掉下划线后使用java的驼峰法命名
     *
     * 对应关系表为
     * Button startMainActivity=>start_main_activity
     */
    Button startMainActivity;

    /**
     * 活动的七大声明状态之一，初始化启动活动时执行的方法，功能是实例化布局文件和相应的逻辑功能
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initActivity();
    }

    /**
     * 初始化活动，主要是给控件注册点击事件和数据的传递逻辑
     */
    public void initActivity(){
        //获取Intent实例,并解析父活动传入Intent中的数据，根据数据类型选择解析方法
        Intent intent = getIntent();
        String data = intent.getStringExtra("dataToSecondActivity");
        Log.d("SecondActivity",data);

        //实例化控件to_second_activity为toSecondActivityButton对象并设置点击事件
        startMainActivity = findViewById(R.id.start_main_activity);
        startMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的隐式用法
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_ACTIVITY_CATEGROY");
                intent.putExtra("data_return","Hello MainActivity");
                //setResult()方法一般两个参数，第一个参数用于向父活动返回处理结果，一般有RESULT_OK和RESULT_CANCELED两种情况，
                // 第二个参数把带有数据的Intent传回去
                setResult(RESULT_OK, intent);
                //关闭当前活动，回到父活动
                //因为我们是使用startActivityForResult()方法启动SecondActivity的，所以当SecondActivity被销毁时会回调父活动的onActiveResult()方法
                finish();
            }
        });
    }

    /**
     * 此处和to_main_activity按钮点击事件处的逻辑完全相同，只是重写了系统的返回键的方法
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
        intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_ACTIVITY_CATEGROY");
        intent.putExtra("data_return","Hello MainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
