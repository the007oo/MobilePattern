package com.phattarapong.mobilepattern;

import android.app.Application;

import com.phattarapong.mobilepattern.manager.Contextor;

/**
 * Created by Phattarapong on 10/3/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
