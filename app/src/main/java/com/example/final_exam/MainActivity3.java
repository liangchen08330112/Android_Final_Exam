package com.example.final_exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.final_exam1.R;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private Button button_bind, button_call, button_unbind;
    private MyConnection connection;
    private MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        button_bind = findViewById(R.id.button_bind);
        button_call = findViewById(R.id.button_call);
        button_unbind = findViewById(R.id.button_unbind);

        button_bind.setOnClickListener(this);
        button_call.setOnClickListener(this);
        button_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_bind:
                if(connection==null){
                    connection = new MyConnection();
                }
                Intent intent = new Intent(MainActivity3.this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_zhengque).setTitle("提示").setMessage("服务绑定成功")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.button_call:
                if(binder == null){
                    return;
                }
                binder.callMethodInService();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("提示").setIcon(R.drawable.ic_zhengque).setMessage("服务调用成功")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
                break;
            case R.id.button_unbind:
                if(connection!=null){
                    unbindService(connection);
                    connection = null;
                    builder = null;
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    builder2.setIcon(R.drawable.ic_zhengque).setTitle("提示").setMessage("服务解绑成功")
                            .setCancelable(true)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                }else {
                    Toast.makeText(this,"没有服务",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
            Log.i("TAG","服务已绑定，内存地址为："+binder.toString());
            Toast.makeText(MainActivity3.this,"服务绑定成功",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}