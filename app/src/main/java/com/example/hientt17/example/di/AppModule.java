package com.example.hientt17.example.di;

import android.content.Context;

import com.example.hientt17.example.MyApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HienTT17 on 1/24/2018.
 */

@Module
public class AppModule {
    @Provides
    Context provideContext(MyApp application) {
        return application.getApplicationContext();
    }
}
