package com.yml.fblivevid;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by amlan on 17/6/16.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate();
    }
}
