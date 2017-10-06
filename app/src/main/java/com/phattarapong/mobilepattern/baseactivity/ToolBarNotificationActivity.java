package com.phattarapong.mobilepattern.baseactivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;


public abstract class ToolBarNotificationActivity extends ProgressActivity implements View.OnClickListener {

    private Toolbar toolBar;
    private ImageButton backImageButton, notificationImageButton;
    private TextView titleToolBar;

    @Override
    protected void initWidget() {
        super.initWidget();

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        titleToolBar = (TextView) findViewById(R.id.titleToolBar);
        backImageButton = (ImageButton) findViewById(R.id.backImageButton);
        notificationImageButton = (ImageButton) findViewById(R.id.notificationImageButton);

        setSupportActionBar(toolBar);

        backImageButton.setOnClickListener(this);
        notificationImageButton.setOnClickListener(this);
    }

    protected void setTitleToolBar(String text) {
        titleToolBar.setText(text);
    }

    protected void hideBackButton() {
        backImageButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backImageButton) {
            onBackPressed();
        } else if (v.getId() == R.id.notificationImageButton) {
            //start activity Notification
        }
    }
}
