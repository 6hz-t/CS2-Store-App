package com.example.csapp_10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.csapp_10.NetMapper.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        boolean iflogin = sharedPreferences.getBoolean("isLogin", false);
        if (iflogin) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // 结束当前的登录界面 Activity
            return; // 避免继续执行后续代码
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);





        String BASE_URL = "http://1.15.41.43:8080";
        String LOGIN_URL = BASE_URL + "/user/login";
        MediaType JSON = MediaType.get("application/json; charset=utf-8");


        //--------------------------------------


       /* Intent intent = new Intent(MainActivity.this, MyOrdersActivity.class);
        startActivity(intent);*/



        //--------------------------------------


        // 处理系统栏
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button register_btn = findViewById(R.id.register_btn);
        Button login_btn = findViewById(R.id.login_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils htu = null;
                // 获取用户输入的账号和密码
                int[] code = new int[1];
                EditText accountEditText = findViewById(R.id.login_account);
                EditText pwdEditText = findViewById(R.id.login_pwd);
                String account = accountEditText.getText().toString();
                String password = pwdEditText.getText().toString();

                JSONObject json = new JSONObject();
                // 检查账号和密码是否为空
                if (account.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Thread Login=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        try {
                            json.put("phone", account);
                            json.put("password", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestBody body = RequestBody.create(json.toString(), JSON);
                        Request request = new Request.Builder()
                                .url(LOGIN_URL)
                                .post(body)
                                .build();

                        try (Response response = client.newCall(request).execute()) {
                            if (response.isSuccessful()) {
                                String result = response.body().string();
                                ObjectMapper objectMapper = new ObjectMapper();
                                JsonNode jsonNode = objectMapper.readTree(result);
                                code[0] = jsonNode.get("code").asInt();
                                if (code[0] == 200) {




                                    Log.e("Login", "登录成功");
                                }
                            } else {
                                Log.e("Login", "请求失败，状态码: " + response.code()+" "+response.body().string());
                            }
                        } catch (IOException  e) {
                           Log.e("Login", "请求失败: " + e.getMessage());
                           throw new RuntimeException(e);
                        }

                    }
                });


                Login.start();
                try {
                    Login.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (code[0] == 200) {

                    //把账号和密码存入本地数据库
                   /* RemoteDB rdb = new RemoteDB();
                    ResultSet rs = rdb.query("select * from user where phone='" + account + "'");*/
                    System.out.println(account + " " + password);
                    SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phone", account);
                    editor.putString("password", password);
                    editor.putBoolean("isLogin", true);
                    try {
                        htu= new HttpUtils();
                        editor.putString("steamid", htu.getSteamid(account));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    editor.apply();


                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    // 设置标志位，清除任务栈并创建新任务栈
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "登录失败"+code[0], Toast.LENGTH_SHORT).show();
                }
                //password = ToMD5Str.toMD5(password);
                    /*rs = rdb.query("select * from user where phone='" + account + "' and password='" + password + "'");
                    System.out.println(account + " " + password);*/

                //rs = rdb.query("select * from user where phone='" + account + "'");
                //if (rs.next()) {
                //String pwd = rs.getString("password");
                //if (pwd.equals(password)) {
                //
                //}
                //else {
                //Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                return;
                //}
                //}

            }
        });
    }
}