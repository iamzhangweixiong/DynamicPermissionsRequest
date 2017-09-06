package com.zhangwx.dynamicpermissionsrequest;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangweixiong on 2017/9/6.
 */

public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
