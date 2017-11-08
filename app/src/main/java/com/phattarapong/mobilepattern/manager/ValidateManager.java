package com.phattarapong.mobilepattern.manager;

/**
 * Created by Phattarapong on 11/8/2017.
 */

public class ValidateManager {

    private static final ValidateManager ourInstance = new ValidateManager();

    public static  ValidateManager getInstance() {
        return ourInstance;
    }

    private ValidateManager() {
    }

    public boolean getValidateEmptyText(String text) {
        return text.length() <= 0;
    }


    public boolean getValidateEmail(String text) {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    public boolean getValidatePhone(String text) {
        return text.length() < 10;
    }

    public boolean getValidatePassword(String text) {
        return text.length() < 6;
    }

    public boolean getValidateConfirmPasswordNotMatch(String text, String text2) {
        return !text.equals(text2);
    }
}
