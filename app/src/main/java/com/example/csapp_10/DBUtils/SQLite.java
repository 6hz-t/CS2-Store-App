package com.example.csapp_10.DBUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.csapp_10.Entity.Order;
import com.example.csapp_10.Entity.Product;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    private static final int version = 23;
    private static String databaseName = "cstore.db";
    private Context context;


    public SQLite(Context context) {
        super(context, databaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users(phone varchar(20), " +
                "pasword varchar(64), " +
                "steamid varchar(128))");
        db.execSQL("create table if not exists orders(id integer primary key autoincrement, " +
                "userId varchar(64), " +
                "productName varchar(255), " +
                "imageUrl varchar(255), " +
                "productPrice integer, " +
                "orderDate varchar(255), " +
                "status boolean)");
    }

    public boolean addorder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into orders(userId, productName, imageUrl, productPrice, orderDate, status) values(?, ?, ?, ?, ?, ?)";
        try {
            db.execSQL(sql, new Object[]{order.getUserId(), order.getProductName(), order.getImageUrl(), order.getProductPrice(), order.getOrderDate(),false});
            return true;
        }catch (Exception e) {
            Log.e("CSApp_Log SQLite", "添加订单失败", e);
            return false;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    //获取数据库steamid


    public List<Order> getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM orders", null);


        List<Order> orderList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                // 获取各列索引
                int idIndex = cursor.getColumnIndex("id");
                int userIdIndex = cursor.getColumnIndex("userId");
                int productNameIndex = cursor.getColumnIndex("productName");
                int imageUrlIndex = cursor.getColumnIndex("imageUrl");
                int productPriceIndex = cursor.getColumnIndex("productPrice");
                int orderDateIndex = cursor.getColumnIndex("orderDate");
                int statusIndex = cursor.getColumnIndex("status");

                // 根据索引设置 Order 对象的属性
                if (idIndex != -1) {
                    order.setId(cursor.getInt(idIndex));
                }
                if (userIdIndex != -1) {
                    order.setUserId(cursor.getString(userIdIndex));
                }
                if (productNameIndex != -1) {
                    order.setProductName(cursor.getString(productNameIndex));
                }
                if (imageUrlIndex != -1) {
                    order.setImageUrl(cursor.getString(imageUrlIndex));
                }
                if (productPriceIndex != -1) {
                    order.setProductPrice((int) cursor.getDouble(productPriceIndex));
                }
                if (orderDateIndex != -1) {
                    order.setOrderDate(cursor.getString(orderDateIndex));
                }
                if (statusIndex != -1) {
                    // SQLite 中 boolean 通常用 0 或 1 表示
                    boolean status = cursor.getInt(statusIndex) == 1;
                    if (status) {
                        order.setStatus("可出售");
                    }else {
                        order.setStatus("冷却中");
                    }

                }

                orderList.add(order);


            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orderList;
    }
}
