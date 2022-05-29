package com.example.final_exam;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileSave {
    public static boolean saveUserInfo(Context context,String username,String password) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput("data.txt",Context.MODE_PRIVATE);
            fos.write((username + ":" + password).getBytes());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(fos!=null){
                    fos.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static Map<String,String> getUserInfo(Context context) {
        String content = "";
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("data.txt");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            content = new String(bytes);
            Map<String,String> map = new HashMap<String,String>();
            String[] infos = content.split(":");
            map.put("username",infos[0]);
            map.put("password",infos[1]);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
