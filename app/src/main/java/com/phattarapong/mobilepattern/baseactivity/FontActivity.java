package com.phattarapong.mobilepattern.baseactivity;

import android.content.Context;
import android.os.Bundle;

import com.akexorcist.localizationactivity.LocalizationActivity;
import com.phattarapong.mobilepattern.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Phattarapong on 9/15/2017.
 */

public abstract class FontActivity extends LocalizationActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CSPraKas.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void initWidget() {
        setDefaultLanguage("th");
    }
}
