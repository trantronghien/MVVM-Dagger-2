package com.example.hientt17.example;

import android.app.Application;

import com.example.hientt17.example.di.DaggerAppComponent;

/**
 * Created by HienTT17 on 1/24/2018.
 */

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }
}
