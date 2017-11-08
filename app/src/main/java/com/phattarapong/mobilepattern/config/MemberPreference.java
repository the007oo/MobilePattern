package com.phattarapong.mobilepattern.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.compat.BuildConfig;


public class MemberPreference {
    private static MemberPreference memberPreference = null;

    public final String NOTIFICATION = "notification_allow";
    public static final String NOTIFICATION_NEWS = "notification_news";
    public static final String NOTIFICATION_PROMOTION = "notification_promotions";
    public static final String NOTIFICATION_ORDER = "notification_order";

    public static final String CURRENCY = "currency";
    public static final String CURRENCY_POSITION = "currency_position";

    public static final String OPEN_APP_FRIST_TIME = "open_app";

    public final String LOCATION = "location";
    public final String LANGUAGE = "language";
    public final String KEY = "key";

    private final String preferenceName = BuildConfig.APPLICATION_ID + "Setting";
    private SharedPreferences preference = null;
    private Editor editor = null;
    private Context context = null;

    /**
     * Constructor method
     *
     * @param context
     */
    public MemberPreference(Context context) {
        this.context = context;
        int mode = Context.MODE_PRIVATE;
        this.preference = this.context.getSharedPreferences(this.preferenceName, mode);
        this.editor = this.preference.edit();
    }

    /**
     * Factory method
     *
     * @param context
     * @return
     */
    public static MemberPreference getInstance(Context context) {
        if (memberPreference == null) {
            memberPreference = new MemberPreference(context);
        }
        return memberPreference;
    }

    public void setValueString(String key, String value) {
        this.editor.putString(key, value);
        this.editor.commit();
    }

    public String getValueString(String key) {
        return this.preference.getString(key, "");
    }

    public String getValueString(String key, String defaultValue) {
        return this.preference.getString(key, defaultValue);
    }

    public void setValueInt(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.commit();
    }

    public int getValueInt(String key, int defaultValue) {
        return this.preference.getInt(key, defaultValue);
    }


    public int getValueInt(String key) {
        return this.preference.getInt(key, 0);
    }

    public void setValueLong(String key, long value) {
        this.editor.putLong(key, value);
        this.editor.commit();
    }

    public long getValueLong(String key, long defaultValue) {
        return this.preference.getLong(key, defaultValue);
    }

    public long getValueLong(String key) {
        return this.preference.getLong(key, 0);
    }

    public void setValueDouble(String key, double value) {
        this.editor.putFloat(key, (float) value);
        this.editor.commit();
    }

    public double getValueDouble(String key, float defaultValue) {
        float value = this.preference.getFloat(key, defaultValue);
        return value;
    }

    public double getValueDouble(String key) {
        float value = this.preference.getFloat(key, 0.0f);
        return value;
    }

    public void setValueFloat(String key, float value) {
        this.editor.putFloat(key, value);
        this.editor.commit();
    }

    public float getValueFloat(String key,float defaultValue) {
        float value = this.preference.getFloat(key, defaultValue);
        return value;
    }

    public float getValueFloat(String key) {
        float value = this.preference.getFloat(key, 0.0f);
        return value;
    }

    public void setValueBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    public boolean getValueBoolean(String key) {
        return this.preference.getBoolean(key, false);
    }

    public void clear() {
        this.editor.clear();
        this.editor.commit();
    }

    public void deleteKey(String key) {
        this.editor.remove(key);
        this.editor.commit();
    }

}
