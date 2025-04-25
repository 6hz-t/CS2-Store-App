package com.example.csapp_10;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.csapp_10.DBUtils.InitDB;
import com.example.csapp_10.activity.frament.HomeFrament;
import com.example.csapp_10.activity.frament.MineFrament;
import com.example.csapp_10.activity.frament.RepoFrament;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       /* InitDB dbutil = new InitDB(this);
        dbutil.getReadableDatabase();*/


        //实现第一次访问加载的界面
        FragmentManager fragment_container = getSupportFragmentManager();
        FragmentTransaction transaction = fragment_container.beginTransaction();
        transaction.replace(R.id.frame_layout, new HomeFrament());
        transaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            FragmentTransaction newTransaction = fragment_container.beginTransaction();
            if (itemId == R.id.action_home) {
                newTransaction.replace(R.id.frame_layout, new HomeFrament());
            } else if (itemId == R.id.action_repo) {
                newTransaction.replace(R.id.frame_layout, new RepoFrament());
            } else if (itemId == R.id.action_mine) {
                newTransaction.replace(R.id.frame_layout, new MineFrament());
            }
            newTransaction.commit();
            return true;
        });
    }
}