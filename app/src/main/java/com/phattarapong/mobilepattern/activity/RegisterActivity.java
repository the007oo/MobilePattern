package com.phattarapong.mobilepattern.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;

import java.util.Calendar;

public class RegisterActivity extends ToolBarSimpleActivity implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText namepasswordbox;
    private TextInputLayout nametextlayout;
    private TextInputEditText lastnamepasswordbox;
    private TextInputLayout lastnametextlayout;
    private TextInputEditText emailbox;
    private TextInputLayout emailtextlayout;
    private TextInputEditText passwordbox;
    private TextInputLayout passwordtextlayout;
    private TextInputEditText confirmpasswordbox;
    private TextInputLayout confirmpasswordtextlayout;
    private TextInputEditText birthdaybox;
    private TextInputLayout birthdaytextlayout;
    private TextInputEditText phonebox;
    private TextInputLayout phonetextlayout;
    private Button registerButton;
    private RadioButton maleRadioButton, femaleRadioButton;
    private DatePickerDialog datePickerDialog;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitleToolBar(getString(R.string.title_activity_register));

        registerButton = (Button) findViewById(R.id.registerButton);
        phonetextlayout = (TextInputLayout) findViewById(R.id.phone_text_layout);
        phonebox = (TextInputEditText) findViewById(R.id.phone_box);
        birthdaytextlayout = (TextInputLayout) findViewById(R.id.birth_day_text_layout);
        birthdaybox = (TextInputEditText) findViewById(R.id.birth_day_box);
        confirmpasswordtextlayout = (TextInputLayout) findViewById(R.id.confirm_password_text_layout);
        confirmpasswordbox = (TextInputEditText) findViewById(R.id.confirm_password_box);
        passwordtextlayout = (TextInputLayout) findViewById(R.id.password_text_layout);
        passwordbox = (TextInputEditText) findViewById(R.id.password_box);
        emailtextlayout = (TextInputLayout) findViewById(R.id.email_text_layout);
        emailbox = (TextInputEditText) findViewById(R.id.email_box);
        lastnametextlayout = (TextInputLayout) findViewById(R.id.last_name_text_layout);
        lastnamepasswordbox = (TextInputEditText) findViewById(R.id.last_name_password_box);
        nametextlayout = (TextInputLayout) findViewById(R.id.name_text_layout);
        namepasswordbox = (TextInputEditText) findViewById(R.id.name_password_box);
        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);

        setUpNameBox();
        setUpLastNameBox();
        setUpEmailBox();
        setUpPasswordBox();
        setUpConfirmPasswordBox();
        setUpPhone();
        initDialogBirthDay();
        registerButton.setOnClickListener(this);
        birthdaybox.setOnClickListener(this);
    }

    private void initDialogBirthDay() {
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                this, RegisterActivity.this, mYear, mMonth, mDay);

        updateCurrentDate();
    }

    private void setUpPhone() {
        phonebox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    phonetextlayout.setError(null);
                } else {
                    if (passwordbox.length() < 0) {
                        phonetextlayout.setEnabled(true);
                        phonetextlayout.setError(getResources().getString(R.string.validate_phone_empty));
                    } else {
                        if (phonebox.length() < 10) {
                            phonetextlayout.setEnabled(true);
                            phonetextlayout.setError(getResources().getString(R.string.validate_phone_format));
                        } else {
                            phonetextlayout.setError(null);
                        }
                    }
                }
            }
        });
    }

    private void setUpConfirmPasswordBox() {
        confirmpasswordbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    confirmpasswordtextlayout.setError(null);
                } else {
                    if (confirmpasswordbox.length() < 6) {
                        confirmpasswordtextlayout.setEnabled(true);
                        confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
                    } else {
                        if (confirmpasswordbox.getText().toString().equals(passwordbox.getText().toString())) {
                            confirmpasswordtextlayout.setError(null);
                        } else {
                            confirmpasswordtextlayout.setEnabled(true);
                            confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password_not_match));
                        }
                    }
                }
            }
        });
    }

    private void setUpPasswordBox() {
        passwordbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    passwordtextlayout.setError(null);
                } else {
                    if (passwordbox.length() < 6) {
                        passwordtextlayout.setEnabled(true);
                        passwordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
                    } else {
                        passwordtextlayout.setError(null);
                    }
                }
            }
        });
    }

    private void setUpEmailBox() {
        emailbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    emailtextlayout.setError(null);
                } else {
                    if (emailbox.length() < 0) {
                        emailtextlayout.setEnabled(true);
                        emailtextlayout.setError(getResources().getString(R.string.validate_email_empty));
                    } else {
                        if (!validateEmail(emailbox.getText().toString())) {
                            emailtextlayout.setEnabled(true);
                            emailtextlayout.setError(getResources().getString(R.string.validate_email_format));
                        } else {
                            emailtextlayout.setError(null);
                        }
                    }
                }
            }
        });
    }

    private void setUpLastNameBox() {
        lastnamepasswordbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    lastnametextlayout.setError(null);
                } else {
                    if (validateEmptyText(lastnamepasswordbox.getText().toString())) {
                        lastnametextlayout.setEnabled(true);
                        lastnametextlayout.setError(getResources().getString(R.string.validate_last_name_empty));
                    } else {
                        lastnametextlayout.setError(null);
                    }
                }
            }
        });
    }

    private void setUpNameBox() {
        namepasswordbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    nametextlayout.setError(null);
                } else {
                    if (validateEmptyText(namepasswordbox.getText().toString())) {
                        nametextlayout.setEnabled(true);
                        nametextlayout.setError(getResources().getString(R.string.validate_name_empty));
                    } else {
                        nametextlayout.setError(null);
                    }
                }
            }
        });
    }

    private void updateCurrentDate() {
        birthdaybox.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("/")
                        .append(mMonth + 1).append("/")
                        .append(mYear).append(" "));
    }

    private void validateText() {
        boolean check = true;

        if (validateEmptyText(namepasswordbox.getText().toString())) {
            nametextlayout.setEnabled(true);
            nametextlayout.setError(getResources().getString(R.string.validate_name_empty));
            check = false;
        }

        if (validateEmptyText(lastnamepasswordbox.getText().toString())) {
            lastnametextlayout.setEnabled(true);
            lastnametextlayout.setError(getResources().getString(R.string.validate_last_name_empty));
            check = false;
        }

        if (validateEmptyText(emailbox.getText().toString())) {
            emailtextlayout.setEnabled(true);
            emailtextlayout.setError(getResources().getString(R.string.validate_email_empty));
            check = false;
        } else {
            if (validateEmail(emailbox.getText().toString())) {
                emailtextlayout.setEnabled(true);
                emailtextlayout.setError(getResources().getString(R.string.validate_email_format));
                check = false;
            }
        }

        if (validateEmptyText(passwordbox.getText().toString())) {
            passwordtextlayout.setEnabled(true);
            passwordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        }

        if (validateEmptyText(confirmpasswordbox.getText().toString())) {
            confirmpasswordtextlayout.setEnabled(true);
            confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        } else {
            if (validateConfirmPasswordNotMatch(passwordbox.getText().toString(), confirmpasswordbox.getText().toString())) {
                confirmpasswordtextlayout.setEnabled(true);
                confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password_not_match));
                check = false;
            }
        }

        if(validateEmptyText(phonebox.getText().toString())){
            phonetextlayout.setEnabled(true);
            phonetextlayout.setError(getResources().getString(R.string.validate_phone_empty));
            check = false;
        }else {
            if(validatePhone(phonebox.getText().toString())){
                phonetextlayout.setEnabled(true);
                phonetextlayout.setError(getResources().getString(R.string.validate_phone_format));
                check = false;
            }
        }

        if (check) {
            shortToast("Success");
        }

        hideKeyboard();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.birth_day_box:
                datePickerDialog.show();
                break;
            case R.id.registerButton:
                validateText();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mYear = year;
        mMonth = month;
        mDay = dayOfMonth;
        updateCurrentDate();
    }

}
