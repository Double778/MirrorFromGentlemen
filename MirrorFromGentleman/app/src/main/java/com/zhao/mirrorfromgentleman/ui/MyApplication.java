package com.zhao.mirrorfromgentleman.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by 华哥哥 on 16/6/12.
 * 自定义Application
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
