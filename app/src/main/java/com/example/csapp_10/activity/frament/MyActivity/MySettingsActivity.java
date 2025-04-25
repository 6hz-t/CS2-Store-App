package com.example.csapp_10.activity.frament.MyActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.csapp_10.R;

public class MySettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout aboutLayout = findViewById(R.id.about_us_layout);
        LinearLayout feedbackLayout = findViewById(R.id.clear_cache_layout);
        LinearLayout privacyLayout = findViewById(R.id.check_update_layout);
        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理关于我们的点击事件
                Uri webpage = Uri.parse("https://6hz.fun");
                // 创建一个用于查看网页的 Intent
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                // 检查是否有应用可以处理该 Intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MySettingsActivity.this, "没有找到应用可以处理该网页", Toast.LENGTH_SHORT).show();
                }



            }
        });
        feedbackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理反馈的点击事件
                Toast.makeText(MySettingsActivity.this, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });
        privacyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理隐私政策的点击事件
                Toast.makeText(MySettingsActivity.this, "当前版本已是最新版", Toast.LENGTH_SHORT).show();
            }
        });
    }
}