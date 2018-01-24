package com.example.hientt17.example.di;


import com.example.hientt17.example.MyApp;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by HienTT17 on 1/24/2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApp application);
        AppComponent build();
    }
    void inject(MyApp app);

}
