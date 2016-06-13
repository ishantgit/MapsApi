package com.example.ishant.driventest;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.orm.SugarContext;

/**
 * Created by Ishant Rana on 13/06/16.
 */
public class MyApplication extends Application {
    private static MyApplication singleInstance = null;

    public static MyApplication getInstance() {
        return singleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (singleInstance == null){
            singleInstance = this;
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        SugarContext.init(singleInstance);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
