package com.example.csapp_10.DBUtils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.csapp_10.Entity.Product;

import java.util.ArrayList;
import java.util.List;


public class MySQLiteHelper extends SQLiteOpenHelper {
    public static int version = 20;
    private static String databaseName = "cstore.db";
    private Context context;
    public static SQLiteDatabase con;

    public MySQLiteHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

           /* db.execSQL("drop table if exists users");
            db.execSQL("create table users(username varchar(20), " +
                    "pasword varchar(15), " +
                    "steamid varchar(72))");
            db.execSQL("insert into users(username, pasword, steamid) values(?,?,?)", new Object[]{"123456", "123456", "76561198075974488"});

            db.execSQL("drop table if exists products");
            db.execSQL("create table products(id integer primary key autoincrement, " +
                    "name varchar(20), " +
                    "description varchar(50), " +
                    "price double, " +
                    "imageUrl varchar(50), " +
                    "quantity integer, " +
                    "categoryId integer)");
            for (Object[] row : data) {
                db.execSQL("insert into products(name, description, price, imageUrl, quantity, categoryId) values(?,?,?,?,?,?)", row);
            }*/

    }
    public boolean insertData(Product product)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        values.put("imageUrl", product.getImageUrl());
        values.put("quantity", product.getQuantity());
        values.put("categoryId", product.getCategoryId());
        long result = db.insert("products", null, values);
        return result != -1;

    };
    //查询所有商品,返回Cursor
    @SuppressLint("Range")
    public Cursor queryallmarketproducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("products", null, null, null, null, null, null);
        return cursor;

    }

   //查询所有库存
    @SuppressLint("Range")
    public Cursor queryallrepo() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("products", null, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 处理数据库版本升级的逻辑
        if (oldVersion < newVersion) {
            db.execSQL("drop table if exists users");
            db.execSQL("drop table if exists products");
            onCreate(db);
        }
    }
}

