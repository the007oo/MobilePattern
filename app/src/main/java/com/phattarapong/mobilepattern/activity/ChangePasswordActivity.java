package com.phattarapong.mobilepattern.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordActivity extends ToolBarSimpleActivity {

    private TextInputEditText oldPasswordBox, newPasswordBox, confirmPasswordBox;
    private Button changePasswordBtn;
    private TextInputLayout oldPasswordTextLayout, newPasswordTextLayout, confirmPasswordTextLayout;
    private TextView emailLabel;
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitleToolBar(getResources().getString(R.string.title_activity_changepassword));

        linearLayout = (LinearLayout) findViewById(R.id.content_layout);

        emailLabel = (TextView) findViewById(R.id.email_label);
        //emailLabel.setText(memberPreference.getValueString(memberPreference.MEMBER_EMAIL));

        oldPasswordBox = (TextInputEditText) findViewById(R.id.old_password_box);
        newPasswordBox = (TextInputEditText) findViewById(R.id.new_password_box);
        confirmPasswordBox = (TextInputEditText) findViewById(R.id.confirm_password_box);

        oldPasswordTextLayout = (TextInputLayout) findViewById(R.id.old_password_text_layout);
        newPasswordTextLayout = (TextInputLayout) findViewById(R.id.new_password_text_layout);
        confirmPasswordTextLayout = (TextInputLayout) findViewById(R.id.confirm_password_text_layout);

        changePasswordBtn = (Button) findViewById(R.id.change_password_btn);
        changePasswordBtn.setOnClickListener(this);

        setViewOldPasswordBox();
        setViewNewPasswordBox();
        setViewConfirmPasswordBox();
        showContentLayout();
    }

    private void setViewOldPasswordBox() {

        oldPasswordBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    oldPasswordTextLayout.setError(null);
                } else {
                    if (oldPasswordBox.length() < 6) {
                        oldPasswordTextLayout.setEnabled(true);
                        oldPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
                    } else {
                        oldPasswordTextLayout.setError(null);
                    }
                }
            }
        });

    }

    private void setViewNewPasswordBox() {
        newPasswordBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    newPasswordTextLayout.setError(null);
                } else {
                    if (newPasswordBox.length() < 6) {
                        newPasswordTextLayout.setEnabled(true);
                        newPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
                    } else {
                        newPasswordTextLayout.setError(null);
                    }
                }
            }
        });
    }

    private void setViewConfirmPasswordBox() {

        confirmPasswordBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    confirmPasswordTextLayout.setError(null);
                } else {
                    if (confirmPasswordBox.length() < 6) {
                        confirmPasswordTextLayout.setEnabled(true);
                        confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
                    } else {
                        if (confirmPasswordBox.getText().toString().equals(newPasswordBox.getText().toString())) {
                            confirmPasswordTextLayout.setError(null);
                        } else {
                            confirmPasswordTextLayout.setEnabled(true);
                            confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password_not_match));
                        }
                    }
                }
            }
        });

        confirmPasswordBox.setImeOptions(EditorInfo.IME_ACTION_DONE);
        confirmPasswordBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    validateText();
                    return true;
                }
                return false;
            }
        });

    }

    private void validateText() {
        String oldPass = oldPasswordBox.getText().toString();
        String newPass = newPasswordBox.getText().toString();
        String confirmPass = confirmPasswordBox.getText().toString();

        boolean check = true;

        if (!validateConfirmPasswordNotMatch(newPass, confirmPass)) {
            confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password_not_match));

            check = false;
        }
        if (validatePassword(confirmPass)) {
            confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));

            check = false;
        }
        if (validatePassword(newPass)) {
            newPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));

            check = false;
        }
        if (validatePassword(oldPass)) {
            oldPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));

            check = false;
        }

        if (check) {
            shortToast("Success");
        }

        hideKeyboard();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.change_password_btn) {
            validateText();
        }
    }

}
