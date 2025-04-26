package com.example.csapp_10.activity.frament.adapter;

/**
 * @author 10
 * @date 2024/5/28 16:56
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.csapp_10.DBUtils.SQLite;
import com.example.csapp_10.Entity.MarketGood;
import com.example.csapp_10.GoodDetailsActivity;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class ProductListAdapter extends ArrayAdapter<MarketGood> {

    private List<MarketGood> productList;
    private Context context;
    SharedPreferences sharedPreferences;
    HttpUtils htu= null;
    public ProductListAdapter(@NonNull Context context, List<MarketGood> productList) throws MalformedURLException {
        super(context, R.layout.market_product_list, productList);
        this.productList = productList;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        htu = new HttpUtils();

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.market_product_list,parent,false);
        }
        //        android:id="@+id/market_product_image"
        //        android:id="@+id/market_product_name"
        //        android:id="@+id/market_product_price"
        //        android:id="@+id/market_product_salecount"




        ImageView iv =convertView.findViewById(R.id.market_product_image);
        TextView name = convertView.findViewById(R.id.market_product_name);
        TextView price = convertView.findViewById(R.id.market_product_price);
        TextView salecount = convertView.findViewById(R.id.market_product_salecount);
        TextView sellername = convertView.findViewById(R.id.market_product_seller);
        MarketGood product = productList.get(position);


        // 图标地址 https://community.fastly.steamstatic.com/economy/image/{iconUrl}/96fx96f?allow_animated=1



        Glide.with(getContext())
                .load("https://community.fastly.steamstatic.com/economy/image/"+product.getIconUrl()+"/128fx128f?allow_animated=1")
                .placeholder(R.drawable.more)
                .error(R.drawable.wrong)
                .into(iv);
        name.setText(product.getName());
        price.setText(String.valueOf("价格:" + product.getPrice()));
        salecount.setText(String.valueOf("在售数量:1" ));
        sellername.setText("卖家:"+product.getSellerId());


        LinearLayout parentLayout = convertView.findViewById(R.id.market_parent_layout);
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGoodDetails(product);
                //跳转到商品详情页面
            }
        });



        return convertView;
    }
    private void showGoodDetails(MarketGood product){
         /*"assetId": "",
            "sellerId": "",
            "price": 0,
            "state": 0,
            "appId": 0,
            "classId": "",
            "instanceId": "",
            "iconUrl": "",
            "name": "",
            "type": "",
            "nameColor": "",
            "marketName": "",*/
        Intent intent = new Intent(context, GoodDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("product_price", product.getPrice());
        intent.putExtra("product_icon_url", product.getIconUrl());
        intent.putExtra("product_seller_id", product.getSellerId());
        intent.putExtra("product_type", product.getType());
        intent.putExtra("product_asset_id", product.getAssetId());


        context.startActivity(intent);
    }
}