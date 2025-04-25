package com.example.csapp_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Toolbar toolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        EditText account = findViewById(R.id.register_account);
        EditText pwd = findViewById(R.id.register_pwd);
        EditText pwd_confirm = findViewById(R.id.register_pwd2);
        EditText steamid = findViewById(R.id.register_steamid);
        Button register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_str = account.getText().toString();
                String pwd_str = pwd.getText().toString();
                String pwd_confirm_str = pwd_confirm.getText().toString();
                String steamid_str = steamid.getText().toString();

                if (!pwd_str.equals(pwd_confirm_str)) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    //dbCon.executeUpdateSQL("INSERT INTO user (user_id, password, steamid) VALUES ('" + account_str + "', '" + pwd_str + "', '" + steamid_str + "')");
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}