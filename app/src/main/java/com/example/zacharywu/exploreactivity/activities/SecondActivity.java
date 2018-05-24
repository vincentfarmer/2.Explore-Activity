package com.example.zacharywu.exploreactivity.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zacharywu.exploreactivity.R;

public class SecondActivity extends BaseActivity {

    /**
     * 声明所有的本活动对应布局文件中的控件,实例名与控件名的对应关系为：
     * 控件名使用下划线分割单词的命名方法，
     * 实例名是控件名去除掉下划线后使用java的驼峰法命名
     * <p>
     * 对应关系表为
     * Button startMainActivityButton=>start_main_activity
     * Button returnMainActivityButton=>return_main_activity
     * Button startMyHTTPActivityButton =>start_my_http_activity
     */
    Button startMainActivityButton;
    Button returnMainActivityButton;
    Button startMyHTTPActivityButton;


    /**
     * 活动的七大声明状态之一，初始化启动活动时执行的方法，功能是实例化布局文件和相应的逻辑功能
     *
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
    public void initActivity() {
        //获取Intent实例,并解析父活动传入Intent中的数据，根据数据类型选择解析方法
        Intent intent = getIntent();
        String data = intent.getStringExtra("dataToSecondActivity");
        Log.d("SecondActivity", data);
        Log.d("SecondActivity", SecondActivity.this.toString());
        Log.d("SecondActivity","Task id is " + getTaskId());

        //实例化控件return_main_activity为returnMainActivityButton对象并设置点击事件
        returnMainActivityButton = findViewById(R.id.return_main_activity);
        returnMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的隐式用法
                Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
                intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_ACTIVITY_CATEGROY");
                intent.putExtra("data_return", "Hello MainActivity");
                //setResult()方法一般两个参数，第一个参数用于向父活动返回处理结果，一般有RESULT_OK和RESULT_CANCELED两种情况，
                // 第二个参数把带有数据的Intent传回去
                setResult(RESULT_OK, intent);
                //关闭当前活动，回到父活动
                //因为我们是使用startActivityForResult()方法启动SecondActivity的，所以当SecondActivity被销毁时会回调父活动的onActiveResult()方法
                finish();
            }
        });
        //实例化控件start_main_activity为startMainActivityButton对象并设置点击事件
        startMainActivityButton = findViewById(R.id.start_main_activity);
        startMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的显式用法
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //实例化控件start_main_activity为startMainActivityButton对象并设置点击事件

        startMyHTTPActivityButton = findViewById(R.id.start_my_http_activity);
        startMyHTTPActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Intent的显式用法
                Intent intent = new Intent(SecondActivity.this, MyHTTPActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 此处和return_main_activity按钮点击事件处的逻辑完全相同，只是重写了系统的返回键的方法
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent("com.example.zacharywu.exploreactivity.ACTION_START");
        intent.addCategory("com.example.zacharywu.exploreactivity.START_MAIN_ACTIVITY_CATEGROY");
        intent.putExtra("data_return", "Hello MainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 七大活动的生命周期函数之一onDestory();
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestory");
    }

    /**
     * 这里我们定义一个静态的规范的活动的启动方法，可供其他活动类直接调用，所要传递的数据就可以在参数列表中一目了然，省去了阅读源码和询问同事的时间,
     * 这种标准写法知识和与向后传参而不适合向前传参，应为startActivityForResult()方法不能放在静态方法中
     * @param context
     * @param data
     */
    public static void actionStart(Context context, String data){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param",data);
        context.startActivity(intent);
    }
}
