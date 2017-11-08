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
import android.widget.Toast;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;
import com.phattarapong.mobilepattern.manager.ValidateManager;

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

        linearLayout = (LinearLayout) findViewById(R.id.contentLayout);

        emailLabel = (TextView) findViewById(R.id.emailLabel);
        //emailLabel.setText(memberPreference.getValueString(memberPreference.MEMBER_EMAIL));

        oldPasswordBox = (TextInputEditText) findViewById(R.id.oldPasswordBox);
        newPasswordBox = (TextInputEditText) findViewById(R.id.newPasswordBox);
        confirmPasswordBox = (TextInputEditText) findViewById(R.id.confirmPasswordBox);

        oldPasswordTextLayout = (TextInputLayout) findViewById(R.id.oldPasswordTextLayout);
        newPasswordTextLayout = (TextInputLayout) findViewById(R.id.newPasswordTextLayout);
        confirmPasswordTextLayout = (TextInputLayout) findViewById(R.id.confirmPasswordTextLayout);

        changePasswordBtn = (Button) findViewById(R.id.changePasswordBtn);
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

        if (ValidateManager.getInstance().getValidatePassword(confirmPass)) {
            confirmPasswordBox.requestFocus();
            confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        } else {
            if (ValidateManager.getInstance().getValidateConfirmPasswordNotMatch(newPass, confirmPass)) {
                confirmPasswordBox.requestFocus();
                confirmPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password_not_match));
                check = false;
            } else {
                confirmPasswordTextLayout.setErrorEnabled(false);
                confirmPasswordTextLayout.setError(null);
            }
        }

        if (ValidateManager.getInstance().getValidatePassword(newPass)) {
            newPasswordBox.requestFocus();
            newPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        } else {
            newPasswordTextLayout.setErrorEnabled(false);
            newPasswordTextLayout.setError(null);
        }

        if (ValidateManager.getInstance().getValidatePassword(oldPass)) {
            oldPasswordBox.requestFocus();
            oldPasswordTextLayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        } else {
            oldPasswordTextLayout.setErrorEnabled(false);
            oldPasswordTextLayout.setError(null);
        }

        if (check) {
            confirmPasswordTextLayout.setErrorEnabled(false);
            newPasswordTextLayout.setErrorEnabled(false);
            oldPasswordTextLayout.setErrorEnabled(false);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
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
        if (v.getId() == R.id.changePasswordBtn) {
            validateText();
        }
    }

}
