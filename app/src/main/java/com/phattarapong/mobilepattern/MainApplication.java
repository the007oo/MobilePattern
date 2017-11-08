package com.phattarapong.mobilepattern;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.phattarapong.mobilepattern.manager.Contextor;
import com.phattarapong.mobilepattern.manager.LocaleManager;

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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.getInstance().setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.getInstance().setLocale(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
