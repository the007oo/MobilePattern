package com.phattarapong.mobilepattern.baseactivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;


/**
 * Created by Phattarapong on 9/14/2017.
 */

public abstract class ToolBarSimpleActivity extends ProgressActivity implements View.OnClickListener {

    private Toolbar toolBar;
    private TextView titleToolBar;
    private TextView backLabel;

    @Override
    protected void initWidget() {
        super.initWidget();

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        titleToolBar = (TextView) findViewById(R.id.titleToolBar);
        backLabel = (TextView) findViewById(R.id.backLabel);

        setSupportActionBar(toolBar);

        backLabel.setOnClickListener(this);
    }

    protected void setTitleToolBar(String text) {
        titleToolBar.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backLabel) {
            onBackPressed();
        }
    }
}
