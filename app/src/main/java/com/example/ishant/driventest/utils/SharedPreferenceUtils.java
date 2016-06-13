package com.example.ishant.driventest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ishant.driventest.MyApplication;

import java.util.Set;

/**
 * Created by Ishant Rana on 13/06/16.
 */
public class SharedPreferenceUtils {
    //shared preferences keys
    public static final String DRIVEN_TEST = "driven_test";
    public static final String ACCESS_TOKEN_FACEBOOK = "accessToken";
    public static final String EMAIL_ID_FACEBOOK = "email";
    public static final String USER_NAME = "userName";
    public static final String ACCESS_TOKEN_GOOGLE = "accessTokenGoogle";


    //Class local variables
    private static SharedPreferenceUtils singleInstance = new SharedPreferenceUtils();
    private final SharedPreferences sharedPreferences;

    public SharedPreferenceUtils() {
        Context context = MyApplication.getInstance().getApplicationContext();
        sharedPreferences = context.getSharedPreferences(DRIVEN_TEST, Context.MODE_PRIVATE); // 0 - for private mode
    }

    public synchronized static SharedPreferenceUtils getInstance() {
        if(singleInstance == null) {
            singleInstance = new SharedPreferenceUtils();
        }
        return singleInstance;
    }

    public synchronized void storeStringValue(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public synchronized String retrieveStringValue(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public synchronized void storeIntegerValue(String key, Integer value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public synchronized Integer retrieveIntegerValue(String key, Integer defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public synchronized void storeBooleanValue(String key, Boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public synchronized Boolean retrieveBooleanValue(String key, Boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public synchronized void storeStringSet(String key, Set<String> values){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    public synchronized Set<String> retrieveStringSet(String key){
        return sharedPreferences.getStringSet(key, null);
    }


    public synchronized void clearSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    public synchronized void removeKey(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key).apply();
    }

}
