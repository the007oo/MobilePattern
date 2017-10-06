package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;

public class FeedBackActivity extends ToolBarSimpleActivity {

    private EditText headerBox;
    private EditText descBox;
    private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitleToolBar(getString(R.string.title_activity_feed_back));
        submitButton = (Button) findViewById(R.id.submitButton);
        descBox = (EditText) findViewById(R.id.descBox);
        headerBox = (EditText) findViewById(R.id.headerBox);
        submitButton.setOnClickListener(this);
    }

    private void validateText() {
        String msg = "";
        if (validateEmptyText(headerBox.getText().toString())) {
            msg += getString(R.string.validate_header_feed_back_activity) + "\n";
        }

        if (validateEmptyText(descBox.getText().toString())) {
            msg += getString(R.string.validate_desc_feed_back_activity) + "\n";
        }

        if (!msg.equals("")) {
            alertDialog(getString(R.string.action_title_alert), msg);
        } else {
            shortToast("Success");
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == submitButton) {
            validateText();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
