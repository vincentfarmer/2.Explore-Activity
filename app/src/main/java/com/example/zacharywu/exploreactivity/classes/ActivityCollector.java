package com.example.zacharywu.exploreactivity.classes;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    //声明用来盛放activitiy的静态成员变量链表
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 向链表中添加新的activity
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 从链表中移除activity
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 销毁掉List链表中的所有活动
     */
    public static void finishAll(){
        //这里是c#的写法，没想到java中也可以有这样的写法，遍历元素的简单写法
        for (Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
