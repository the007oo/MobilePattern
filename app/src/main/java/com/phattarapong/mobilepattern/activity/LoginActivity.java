package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.OnRequestPermission;
import com.phattarapong.mobilepattern.baseactivity.ToolBarNotificationActivity;
import com.phattarapong.mobilepattern.manager.ValidateManager;

public class LoginActivity extends ToolBarNotificationActivity implements OnRequestPermission {

    private TextInputEditText usernamebox;
    private TextInputLayout usernametextlayout;
    private TextInputEditText passwordbox;
    private TextInputLayout passwordtextlayout;
    private TextView registerLabel;
    private TextView forgetLabel;
    private Button submitButton;

    private LinearLayout contentLayout;

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
        passwordtextlayout = (TextInputLayout) findViewById(R.id.passwordTextLayout);
        passwordbox = (TextInputEditText) findViewById(R.id.passwordBox);
        usernametextlayout = (TextInputLayout) findViewById(R.id.userNameTextLayout);
        usernamebox = (TextInputEditText) findViewById(R.id.userNameBox);
        contentLayout = findViewById(R.id.contentLayout);

        submitButton.setOnClickListener(this);
        forgetLabel.setOnClickListener(this);
        registerLabel.setOnClickListener(this);

    }

    private void validateText() {

        String msg = "";

        if (ValidateManager.getInstance().getValidateEmail(usernamebox.getText().toString())) {
            msg += getString(R.string.validate_email_format) + "\n";
        }

        if (ValidateManager.getInstance().getValidatePassword(passwordbox.getText().toString())) {
            msg += getString(R.string.validate_password_login_activity) + "\n";
        }

        if (msg.equals("")) {
            requestMultiplePermission(LoginActivity.this, contentLayout);
            setOnSelectPermission(this);
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

    @Override
    public void onGranted() {
        finish();
        startActivityNoAnimation(SettingsActivity.class);
    }

    @Override
    public void onDenied() {

    }

}
