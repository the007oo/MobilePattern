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
import android.widget.Toast;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;
import com.phattarapong.mobilepattern.manager.ValidateManager;

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
        phonetextlayout = (TextInputLayout) findViewById(R.id.phoneTextLayout);
        phonebox = (TextInputEditText) findViewById(R.id.phoneBox);
        birthdaytextlayout = (TextInputLayout) findViewById(R.id.birthDayTextLayout);
        birthdaybox = (TextInputEditText) findViewById(R.id.birthDayBox);
        confirmpasswordtextlayout = (TextInputLayout) findViewById(R.id.confirmPasswordTextLayout);
        confirmpasswordbox = (TextInputEditText) findViewById(R.id.confirmPasswordBox);
        passwordtextlayout = (TextInputLayout) findViewById(R.id.passwordTextLayout);
        passwordbox = (TextInputEditText) findViewById(R.id.passwordBox);
        emailtextlayout = (TextInputLayout) findViewById(R.id.emailTextLayout);
        emailbox = (TextInputEditText) findViewById(R.id.emailBox);
        lastnametextlayout = (TextInputLayout) findViewById(R.id.lastNameTextLayout);
        lastnamepasswordbox = (TextInputEditText) findViewById(R.id.lastNamePasswordBox);
        nametextlayout = (TextInputLayout) findViewById(R.id.nameTextLayout);
        namepasswordbox = (TextInputEditText) findViewById(R.id.namePasswordBox);
        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);

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

        if (ValidateManager.getInstance().getValidateEmptyText(namepasswordbox.getText().toString())) {
            nametextlayout.setEnabled(true);
            nametextlayout.setError(getResources().getString(R.string.validate_name_empty));
            check = false;
        }

        if (ValidateManager.getInstance().getValidateEmptyText(lastnamepasswordbox.getText().toString())) {
            lastnametextlayout.setEnabled(true);
            lastnametextlayout.setError(getResources().getString(R.string.validate_last_name_empty));
            check = false;
        }

        if (ValidateManager.getInstance().getValidateEmptyText(emailbox.getText().toString())) {
            emailtextlayout.setEnabled(true);
            emailtextlayout.setError(getResources().getString(R.string.validate_email_empty));
            check = false;
        } else {
            if (ValidateManager.getInstance().getValidateEmail(emailbox.getText().toString())) {
                emailtextlayout.setEnabled(true);
                emailtextlayout.setError(getResources().getString(R.string.validate_email_format));
                check = false;
            }
        }

        if (ValidateManager.getInstance().getValidateEmptyText(passwordbox.getText().toString())) {
            passwordtextlayout.setEnabled(true);
            passwordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        }

        if (ValidateManager.getInstance().getValidateEmptyText(confirmpasswordbox.getText().toString())) {
            confirmpasswordtextlayout.setEnabled(true);
            confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password));
            check = false;
        } else {
            if (ValidateManager.getInstance().getValidateConfirmPasswordNotMatch(passwordbox.getText().toString(), confirmpasswordbox.getText().toString())) {
                confirmpasswordtextlayout.setEnabled(true);
                confirmpasswordtextlayout.setError(getResources().getString(R.string.msg_validate_password_not_match));
                check = false;
            }
        }

        if (ValidateManager.getInstance().getValidateEmptyText(phonebox.getText().toString())) {
            phonetextlayout.setEnabled(true);
            phonetextlayout.setError(getResources().getString(R.string.validate_phone_empty));
            check = false;
        } else {
            if (ValidateManager.getInstance().getValidatePhone(phonebox.getText().toString())) {
                phonetextlayout.setEnabled(true);
                phonetextlayout.setError(getResources().getString(R.string.validate_phone_format));
                check = false;
            }
        }

        if (check) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

        hideKeyboard();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.birthDayBox:
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
