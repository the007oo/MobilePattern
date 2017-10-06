package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarNotificationActivity;

public class LoginActivity extends ToolBarNotificationActivity {

    private TextInputEditText usernamebox;
    private TextInputLayout usernametextlayout;
    private TextInputEditText passwordbox;
    private TextInputLayout passwordtextlayout;
    private TextView registerLabel;
    private TextView forgetLabel;
    private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        hideBackButton();
        setTitleToolBar(getString(R.string.title_activity_login));

        submitButton = (Button) findViewById(R.id.submitButton);
        forgetLabel = (TextView) findViewById(R.id.forgetLabel);
        registerLabel = (TextView) findViewById(R.id.registerLabel);
        passwordtextlayout = (TextInputLayout) findViewById(R.id.password_text_layout);
        passwordbox = (TextInputEditText) findViewById(R.id.password_box);
        usernametextlayout = (TextInputLayout) findViewById(R.id.user_name_text_layout);
        usernamebox = (TextInputEditText) findViewById(R.id.user_name_box);

        submitButton.setOnClickListener(this);
        forgetLabel.setOnClickListener(this);
        registerLabel.setOnClickListener(this);

    }

    private void validateText() {

        String msg = "";

        if (validateEmail(usernamebox.getText().toString())) {
            msg += getString(R.string.validate_email_format) + "\n";
        }

        if (validatePassword(passwordbox.getText().toString())) {
            msg += getString(R.string.validate_password_login_activity) + "\n";
        }

        if (msg.equals("")) {
            finish();
            startActivityNoAnimation(SettingsActivity.class);
        } else {
            alertDialog(getString(R.string.action_title_alert), msg);
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == submitButton) {
            validateText();
        } else if (v == forgetLabel) {
            startActivityNoAnimation(ForgetPasswordActivity.class);
        } else if (v == registerLabel) {
            startActivityNoAnimation(RegisterActivity.class);
        }
    }
}
