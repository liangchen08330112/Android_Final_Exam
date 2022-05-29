package com.example.final_exam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.final_exam1.R;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText editText_number, editText_password;
    private ImageView button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();

        Map<String,String> userInfo = FileSave.getUserInfo(this);
        if(userInfo!=null){
            editText_number.setText(userInfo.get("username"));
            editText_password.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        editText_number = findViewById(R.id.editText_number);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_number.getText().toString().trim();
                String password = editText_password.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(MainActivity2.this,"用户名为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity2.this,"密码为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity2.this,"登录成功",Toast.LENGTH_SHORT).show();
                boolean isSuccess = FileSave.saveUserInfo(MainActivity2.this,username,password);
                if(isSuccess){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setIcon(R.drawable.ic_zhengque).setTitle("提示").setMessage("信息保存成功")
                            .setCancelable(true)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setIcon(R.drawable.ic_notice).setTitle("提示").setMessage("信息保存失败")
                            .setCancelable(true)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
}