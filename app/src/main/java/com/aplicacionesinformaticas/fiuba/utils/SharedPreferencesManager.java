package com.aplicacionesinformaticas.fiuba.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aplicacionesinformaticas.fiuba.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Gregorio on 6/17/2017.
 */

public class SharedPreferencesManager {

    public static final String KEY_USER = "User";
    public static final String KEY_PASSWORD = "Pass";

    private static SharedPreferencesManager instance = null;
    private SharedPreferences pref;
    private String filename = "user.txt";

    private SharedPreferencesManager(Context context){
        pref = context.getSharedPreferences("com.aplicacionesinformaticas.fiuba", Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context){
        if (instance == null){
            instance = new SharedPreferencesManager(context);
        }

        return instance;
    }

    public void setValue(String key, int value){
        pref.edit().putInt(key, value).commit();
    }

    public void setValue(String key, String value){
        pref.edit().putString(key, value).commit();
    }

    public int getInt(String key, int defaultValue){
        return pref.getInt(key, defaultValue);
    }

    public int getInt(String key){
        return getInt(key, -1);
    }

    public String getString(String key){
        return getString(key, "");
    }

    public String getString(String key, String defaultValue){
        return pref.getString(key, defaultValue);
    }

    public void saveUser(User u){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(u);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        User u = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            u = (User) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return u;
    }
}
