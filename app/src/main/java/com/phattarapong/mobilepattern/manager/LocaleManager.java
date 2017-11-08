package com.phattarapong.mobilepattern.manager;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;


import com.phattarapong.mobilepattern.config.SettingPreference;

import java.util.Locale;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;


/**
 * Created by Phattarapong on 10/17/2017.
 */

public class LocaleManager {

    private static final LocaleManager ourInstance = new LocaleManager();

    public static LocaleManager getInstance() {
        return ourInstance;
    }

    private LocaleManager() {
    }

    public  Context setLocale(Context c) {
        return updateResources(c, getLanguage(c));
    }

    public  Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public String getLanguage(Context c) {
        SettingPreference settingPreference = SettingPreference.getInstance(c);
        return settingPreference.getValueString(LANGUAGE, "en");
    }

    private void persistLanguage(Context c, String language) {
        SettingPreference settingPreference = SettingPreference.getInstance(c);
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        settingPreference.setValueString(LANGUAGE,language);
    }

    @SuppressWarnings("deprecation")
    private Context updateResources(Context context, String language) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();

//        if (Build.VERSION.SDK_INT >= 25) {
//            Log.d("LocaleManager", "Check1");
//            config.setLocale(locale);
//            context = context.createConfigurationContext(config);
//        } else {
//            Log.d("LocaleManager", "Check2");
//            config.locale = locale;
//            res.updateConfiguration(config, displayMetrics);
//        }

        config.locale = locale;
        res.updateConfiguration(config, displayMetrics);

        return context;
    }

    public Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }

}
