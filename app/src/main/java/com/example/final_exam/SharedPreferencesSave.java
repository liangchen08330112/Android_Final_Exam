package com.example.final_exam;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesSave {
    public static boolean saveUserInfo(Context context,String username,String password) {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        return true;
    }
    public static Map<String,String> getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String username = sp.getString("username",null);
        String password = sp.getString("password",null);
        Map<String,String> map = new HashMap<String,String>();
        map.put("username",username);
        map.put("password",password);
        return map;
    }
}
