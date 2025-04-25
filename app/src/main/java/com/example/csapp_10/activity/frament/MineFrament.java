package com.example.csapp_10.activity.frament;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.csapp_10.Entity.UserInfo;
import com.example.csapp_10.MainActivity;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;
import com.example.csapp_10.activity.frament.MyActivity.MyAccountInfoActivity;
import com.example.csapp_10.activity.frament.MyActivity.MyChPwdActivity;
import com.example.csapp_10.activity.frament.MyActivity.MyOrdersActivity;
import com.example.csapp_10.activity.frament.MyActivity.MySettingsActivity;

import java.net.MalformedURLException;


public class MineFrament extends Fragment {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview= inflater.inflate(R.layout.fragment_mine_frament, container, false);
      /*  Intent intent = new Intent(getActivity(), MyChPwdActivity.class);
        startActivity(intent);*/


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        HttpUtils htu= null;
        try {
            htu = new HttpUtils();
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        UserInfo userInfo = null;
        try {
            userInfo = htu.getUserInfo(sharedPreferences.getString("phone", ""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        TextView untv=rootview.findViewById(R.id.phone);
        String phone = sharedPreferences.getString("phone", "");
        untv.setText("账号:"+phone);

        TextView nametv=rootview.findViewById(R.id.name);
        nametv.setText(userInfo.getUsername());
        TextView emailtv=rootview.findViewById(R.id.steam_status);
        ImageView emailiv=rootview.findViewById(R.id.steam_status_icon);
        if(userInfo.getSteamId()==""){
            emailtv.setText("Steam未绑定");
            emailtv.setTextColor(Color.parseColor("#dd171a"));
            emailiv.setImageResource(R.drawable.steamerror);

        }





        ImageView iv = rootview.findViewById(R.id.avatar);
        Glide.with(getContext())
                .load(userInfo.getAvatarUrl())
                .placeholder(R.drawable.more)
                .error(R.drawable.wrong)
                .into(iv);

        LinearLayout logout=rootview.findViewById(R.id.logout_option);
        LinearLayout settings=rootview.findViewById(R.id.settings_option);
        LinearLayout myorders=rootview.findViewById(R.id.myorders_option);
        LinearLayout accountinfo=rootview.findViewById(R.id.accountinfo_option);
        LinearLayout chpwd=rootview.findViewById(R.id.ch_password_option);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin", false);
                editor.apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingsActivity.class);
                startActivity(intent);
            }
        });
        chpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyChPwdActivity.class);
                startActivity(intent);
            }
        });
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOrdersActivity.class);
                startActivity(intent);
            }
        });
        accountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyAccountInfoActivity.class);
                startActivity(intent);
            }
        });


        return rootview;
    }


}