package com.phattarapong.mobilepattern.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Phattarapong on 11/17/2017.
 */

public class GsonManager {

    private Gson gson;

    private static final GsonManager ourInstance = new GsonManager();

    public static GsonManager getInstance() {
        return ourInstance;
    }

    private GsonManager() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    }

    public Gson getGson() {
        return gson;
    }

}
