package com.example.final_exam;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService","服务已绑定，执行onBind()方法");
        return new MyBinder();
    }
    class MyBinder extends Binder {
        public void callMethodInService() {
            methodInService();
        }
    }

    private void methodInService() {
        Log.i("MyService","执行服务中的methodInService()方法");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService","服务已创建，执行onCreate()方法");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService","服务已解绑，执行onUnbind()方法");
        return super.onUnbind(intent);
    }
}
