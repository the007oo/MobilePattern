package com.phattarapong.mobilepattern.baseactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.config.MemberPreference;
import com.phattarapong.mobilepattern.config.SettingPreference;
import com.phattarapong.mobilepattern.manager.Contextor;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Phattarapong on 9/15/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected SettingPreference settingPreference;
    protected MemberPreference memberPreference;

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
        settingPreference = SettingPreference.getInstance(Contextor.getInstance().getContext());
        memberPreference = MemberPreference.getInstance(Contextor.getInstance().getContext());
    }

    protected void startActivityNoAnimation(Class<?> aClass) {
        this.startActivityNoAnimation(aClass, null);
    }

    protected void startActivityNoAnimation(Class<?> aClass, Bundle bundle) {
        if (!getClass().equals(aClass)) {
            Intent intent = new Intent(this, aClass);
            if (bundle != null)
                intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
