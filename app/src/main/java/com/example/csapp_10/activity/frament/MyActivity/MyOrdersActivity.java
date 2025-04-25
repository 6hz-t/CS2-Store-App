package com.example.csapp_10.activity.frament.MyActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.csapp_10.Entity.Order;
import com.example.csapp_10.NetMapper.OrderHttp;
import com.example.csapp_10.R;
import com.example.csapp_10.activity.frament.adapter.MyDoneOrdersListAdapter;
import com.example.csapp_10.activity.frament.adapter.MyTodoOrdersListAdapter;

import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String steamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_orders);

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        steamId = sharedPreferences.getString("steamid", "");
        ListView todolv = findViewById(R.id.orders_todo_list);
        ListView donelv = findViewById(R.id.orders_done_list);
        List<Order> todoorderList = null;
        List<Order> doneorderList = null;
        try {
            OrderHttp oh = new OrderHttp();
            todoorderList = oh.gettodoOrder(steamId);
            doneorderList = oh.getdoneOrder(steamId);
        }catch (Exception e) {
            Log.e("CSApp_Log  MyOrdersActivity", "Error fetching orders", e);
            e.printStackTrace();
        }
        //显示订单列表
        //TODO: 显示订单列表
        if (todoorderList == null || todoorderList.size() == 0) {
            Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
        }else {
            MyTodoOrdersListAdapter adapter = new MyTodoOrdersListAdapter(this, todoorderList);
            MyDoneOrdersListAdapter adapter2 = new MyDoneOrdersListAdapter(this, doneorderList);
            todolv.setAdapter(adapter);
            donelv.setAdapter(adapter2);
        }




    }


}