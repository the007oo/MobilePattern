package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarNotificationActivity;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;

public class ForgetPasswordActivity extends ToolBarSimpleActivity {

    private TextInputEditText emailbox;
    private TextInputLayout emailtextlayout;
    private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        setTitleToolBar(getString(R.string.title_activity_forget));

        submitButton = (Button) findViewById(R.id.submitButton);
        emailtextlayout = (TextInputLayout) findViewById(R.id.email_text_layout);
        emailbox = (TextInputEditText) findViewById(R.id.email_box);

        submitButton.setOnClickListener(this);
    }

    private void validateText() {
        String msg = "";
        if (!validateEmail(emailbox.getText().toString())) {
            msg = getString(R.string.validate_email_format);
        }

        if (msg.equals("")) {
            shortToast("Success");
        } else {
            alertDialog(getString(R.string.action_title_alert), msg);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v == submitButton) {
            validateText();
        }

    }
}
