package com.example.csapp_10.activity.frament.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.csapp_10.Entity.UserInfo;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;

import java.io.IOException;
import java.net.MalformedURLException;

public class MyAccountInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_account_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.edit_info_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://6hz.fun");
                // 创建一个用于查看网页的 Intent
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }


        });
        TextView textView = findViewById(R.id.username_text);
        TextView textView2 = findViewById(R.id.phone_text);
        TextView textView3 = findViewById(R.id.steamid_text);
        TextView textView4 = findViewById(R.id.wallet_text);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "");

        try {
            HttpUtils htu = new HttpUtils();
            UserInfo userInfo = htu.getUserInfo(phone);
            if (userInfo != null) {
                textView.setText(userInfo.getUsername());
                textView2.setText(userInfo.getPhone());
                textView3.setText(userInfo.getSteamId());
                textView4.setText(String.valueOf(userInfo.getWallet()));
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}