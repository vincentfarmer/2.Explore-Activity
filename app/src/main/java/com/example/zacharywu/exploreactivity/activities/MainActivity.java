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
public class MainActivity extends BaseActivity {

    /**
     * 声明所有的本活动对应布局文件中的控件,实例名与控件名的对应关系为：
     * 控件名使用下划线分割单词的命名方法，
     * 实例名是控件名去除掉下划线后使用java的驼峰法命名
     *
     * 对应关系表为
     * Button startSelfMainActivityButton=>start_self_main_activity
     * Button startSecondActivityButton=>start_second_activity
     * Button toBaiduActivityButton =>start_baidu_activity
     * Button startDialActivityButton=>start_dial_activity
     * Button startMainLifeCycleActivityButton=>start_main_life_cycle_activity
     */
    Button startSelfMainActivityButton;
    Button startSecondActivityButton;
    Button startBaiduActivityButton;
    Button startDialActivityButton;
    Button startMainLifeCycleActivityButton;

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

        //Standard:从这里打印的信息可以看出Standard模式不会在乎这个活动在返回栈中是否存在，每次启动都会创建该活动的一个实例
        //singleTop:在AndroidMainfest中修改主活动的启动方式为singleTop之后无论点击多少次start_main_activity按钮都不会创建新的活动，而且只需要一次返回键就可以退出应用
        //但是从mainActivity进入second Activity之后在打开mainActivity就可以重新启动一个新的mainActivity，简单来说singleTop只检查栈顶是否由同名活动而不检查栈中
        //singleTask:singleTask模式下开启的活动如果在返回栈中存在则会将要开启的活动上的所有活动弹出销毁，直接激活想要打开的活动
        //singleInstance:singleInstance模式较为复杂，指定为singleInstance的活动会启用一个新的返回栈来管理这个活动，这种模式下会有一个单独的返回栈来管理这个指定的活动
        //不管用哪一个应用程序来访问这一个活动，都共用同一个返回栈Task，这样就觉觉了实力共享的问题
        Log.d("MainActivity",MainActivity.this.toString());
        Log.d("MainActivity","Task id is " + getTaskId());

        //实例化控件start_main_activity为startMainActivityButton对象并设置点击事件
        startSelfMainActivityButton = findViewById(R.id.start_self_main_activity);
        startSelfMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的隐式用法，先指定action参数，再使用addCategory()方法指定Category参数
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_ACTIVITY_CATEGROY");
                startActivity(intent);
            }
        });

        //实例化控件start_second_activity为startSecondActivityButton对象并设置点击事件
        startSecondActivityButton = findViewById(R.id.start_second_activity);
        startSecondActivityButton.setOnClickListener(new View.OnClickListener() {
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

        //实例化start_baidu_activity控件为startBaiduActivityButton对象并设置点击事件
        startBaiduActivityButton = findViewById(R.id.start_baidu_activity);
        startBaiduActivityButton.setOnClickListener(new View.OnClickListener() {
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

        //实例化start_dial_activity控件为startDialActivityButton对象并设置点击事件
        startDialActivityButton = findViewById(R.id.start_dial_activity);
        startDialActivityButton.setOnClickListener(new View.OnClickListener() {
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
        //实例化start_dial_activity控件为startMainLifeCycleActivity对象并设置点击事件
        startMainLifeCycleActivityButton = findViewById(R.id.start_main_life_cycle_activity);
        startMainLifeCycleActivityButton.setOnClickListener(new View.OnClickListener() {
            //使用Intent的隐式用法，先指定action参数，再使用addCategory()方法指定Category参数,其中ACTION_DIAL是Android系统内置浏览器的action.
            // 常量值为android.intent.action.DIAL
            //setData()方法接受一个Uri对象，这个对象通常是使用Uri.parse()方法解析得到的
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_LIFE_CYCLE_ACTIVITY_CATEGROY");
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