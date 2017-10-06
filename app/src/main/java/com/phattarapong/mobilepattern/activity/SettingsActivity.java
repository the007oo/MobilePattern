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

        flagLanguageImageView = (ImageView) findViewById(R.id.flag_language_image_view);

        if (getLanguage().equals("th")) {
            flagLanguageImageView.setImageResource(R.drawable.ic_en_flag);
        } else if (getLanguage().equals("en")) {
            flagLanguageImageView.setImageResource(R.drawable.ic_th_flag);
        }

        changeLanguageLayout = (LinearLayout) findViewById(R.id.change_language_layout);
        notificationLayout = (LinearLayout) findViewById(R.id.notification_layout);
        changePasswordLayout = (LinearLayout) findViewById(R.id.changepassword_layout);
        versionLayout = (LinearLayout) findViewById(R.id.version_layout);
        helpLayout = (LinearLayout) findViewById(R.id.help_layout);
        aboutUsLayout = (LinearLayout) findViewById(R.id.aboutus_layout);
        feedbackLayout = (LinearLayout) findViewById(R.id.feedbackLayout);

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

        if (v.getId() == R.id.change_language_layout) {
            if (getLanguage().equals("th")) {
                setLanguage("en");
            } else if (getLanguage().equals("en")) {
                setLanguage("th");
            }
        } else if (v.getId() == R.id.notification_layout) {
            startActivityNoAnimation(NotificationActivity.class);
        } else if (v.getId() == R.id.changepassword_layout) {
            startActivityNoAnimation(ChangePasswordActivity.class);
        } else if (v.getId() == R.id.version_layout) {
            startActivityNoAnimation(VersionActivity.class);
        } else if (v.getId() == R.id.help_layout) {
            startActivityNoAnimation(HelpActivity.class);
        } else if (v.getId() == R.id.aboutus_layout) {
            startActivityNoAnimation(AboutUsActivity.class);
        } else if (v.getId() == R.id.feedbackLayout) {
            startActivityNoAnimation(FeedBackActivity.class);
        }

    }


}
