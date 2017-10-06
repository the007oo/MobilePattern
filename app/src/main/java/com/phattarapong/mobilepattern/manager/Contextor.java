package com.phattarapong.mobilepattern.manager;

import android.content.Context;

/**
 * Created by Phattarapong on 10/3/2017.
 */

public class Contextor {

    private Context mContext;

    private static final Contextor ourInstance = new Contextor();

    public static Contextor getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
