package com.phattarapong.mobilepattern.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.phattarapong.mobilepattern.manager.Contextor;
import com.phattarapong.mobilepattern.config.SettingPreference;


public abstract class PermissionActivity extends FontActivity {
    protected SettingPreference settingPreference;


    @Override
    protected void initWidget() {
        super.initWidget();

        settingPreference = SettingPreference.getInstance(Contextor.getInstance().getContext());
    }

    protected void longToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void shortToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void startActivityNoAnimation(Class<?> aClass) {
        this.startActivityNoAnimation(aClass, null);
    }

    public void startActivityNoAnimation(Class<?> aClass, Bundle bundle) {
        if (!getClass().equals(aClass)) {
            Intent intent = new Intent(this, aClass);
            if (bundle != null)
                intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

}
