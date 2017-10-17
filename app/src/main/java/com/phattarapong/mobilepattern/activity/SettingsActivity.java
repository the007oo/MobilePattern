package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarNotificationActivity;


public class SettingsActivity extends ToolBarNotificationActivity {


    private ImageView flagLanguageImageView;
    private LinearLayout changeLanguageLayout, notificationLayout, changePasswordLayout,
            versionLayout, helpLayout, aboutUsLayout, feedbackLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitleToolBar(getResources().getString(R.string.title_activity_settings));
        hideBackButton();

        flagLanguageImageView = (ImageView) findViewById(R.id.flagLanguageImageView);

        if (getLanguage().equals("th")) {
            flagLanguageImageView.setImageResource(R.drawable.ic_en_flag);
        } else if (getLanguage().equals("en")) {
            flagLanguageImageView.setImageResource(R.drawable.ic_th_flag);
        }

        changeLanguageLayout = (LinearLayout) findViewById(R.id.changeLanguageLayout);
        notificationLayout = (LinearLayout) findViewById(R.id.notificationLayout);
        changePasswordLayout = (LinearLayout) findViewById(R.id.changePasswordLayout);
        versionLayout = (LinearLayout) findViewById(R.id.versionLayout);
        helpLayout = (LinearLayout) findViewById(R.id.helpLayout);
        aboutUsLayout = (LinearLayout) findViewById(R.id.aboutUsLayout);
        feedbackLayout = (LinearLayout) findViewById(R.id.feedBackLayout);

        changeLanguageLayout.setOnClickListener(this);
        notificationLayout.setOnClickListener(this);
        changePasswordLayout.setOnClickListener(this);
        versionLayout.setOnClickListener(this);
        helpLayout.setOnClickListener(this);
        aboutUsLayout.setOnClickListener(this);
        feedbackLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.changeLanguageLayout) {
            if (getLanguage().equals("th")) {
                setLanguage("en");
            } else if (getLanguage().equals("en")) {
                setLanguage("th");
            }
        } else if (v.getId() == R.id.notificationLayout) {
            startActivityNoAnimation(NotificationActivity.class);
        } else if (v.getId() == R.id.changePasswordLayout) {
            startActivityNoAnimation(ChangePasswordActivity.class);
        } else if (v.getId() == R.id.versionLayout) {
            startActivityNoAnimation(VersionActivity.class);
        } else if (v.getId() == R.id.helpLayout) {
            startActivityNoAnimation(HelpActivity.class);
        } else if (v.getId() == R.id.aboutUsLayout) {
            startActivityNoAnimation(AboutUsActivity.class);
        } else if (v.getId() == R.id.feedBackLayout) {
            startActivityNoAnimation(FeedBackActivity.class);
        }

    }


}
