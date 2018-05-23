package com.example.zacharywu.exploreactivity.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zacharywu.exploreactivity.R;

/**
 *  2.3使用Intent在活动之间穿梭
 *  2.3.1 使用显示的Intent
 *      Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
 *      startActivity(intent);
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 声明所有的本活动对应布局文件中的控件,实例名与控件名的对应关系为：
     * 控件名使用下划线分割单词的命名方法，
     * 实例名是控件名去除掉下划线后使用java的驼峰法命名
     *
     * 对应关系表为
     * Button toSecondActivityButton=>to_second_activity
     * Button toBaiduActivityButton =>to_baidu_activity
     * Button toDialActivity=>to_dial_activity
     */
    Button toSecondActivityButton;
    Button toBaiduActivityButton;
    Button toDialActivityButton;

    /**
     * 活动的七大声明状态之一，初始化启动活动时执行的方法，功能是实例化布局文件和相应的逻辑功能
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    /**
     * 初始化活动，主要是为按钮添加点击事件
     */
    public void initActivity(){
        //实例化控件to_second_activity为toSecondActivityButton对象并设置点击事件
        toSecondActivityButton = findViewById(R.id.to_second_activity);
        toSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的隐式用法，先指定action参数，再使用addCategory()方法指定Category参数
                //Intent提供了一系列的putExtraData()方法的重载，可以把我们想要传递的数据暂存在Intent中，启动了目标活动后自从Intent中将数据取出
                String data = "Hello SecondActivity";
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_SECOND_ACTIVITY_CATEGROY");
                intent.putExtra("dataToSecondActivity", data);
                //需要子活动返回数据的情况，则调用startActivityForResult()方法启动子活动，与startActivity()方法不同的是前者需要提供一个请求码来识别返回给谁。
                // 但一般只有一个父活动
                startActivityForResult(intent,2);
            }
        });

        //实例化to_baidu_activity控件为toBaiduActivityButton对象并设置点击事件
        toBaiduActivityButton = findViewById(R.id.to_baidu_activity);
        toBaiduActivityButton.setOnClickListener(new View.OnClickListener() {
            //使用Intent的隐式用法，先指定action参数，再使用addCategory()方法指定Category参数,其中ACTION_VIEW是Android系统内置浏览器的action.
            // 常量值为android.intent.action.VIEW
            //setData()方法接受一个Uri对象，这个对象通常是使用Uri.parse()方法解析得到的
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        //实例化to_dial_activity控件为toDialActivityButton对象并设置点击事件
        toDialActivityButton = findViewById(R.id.to_dial_activity);
        toDialActivityButton.setOnClickListener(new View.OnClickListener() {
            //使用Intent的隐式用法，先指定action参数，再使用addCategory()方法指定Category参数,其中ACTION_DIAL是Android系统内置浏览器的action.
            // 常量值为android.intent.action.DIAL
            //setData()方法接受一个Uri对象，这个对象通常是使用Uri.parse()方法解析得到的
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }

    /**
     * 因为我们是使用startActivityForResult()方法启动SecondActivity的，所以当SecondActivity被销毁时会回调父活动的onActiveResult()方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //requestCode用来识别是从哪一个子活动返回的数据，由父活动启动子活动时在startActivityForResult()方法的第二个参数中定义
        switch (requestCode){
            case 2:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.d("MainActivity", returnData);
                }
        }

    }


}