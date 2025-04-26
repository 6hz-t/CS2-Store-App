package com.example.csapp_10;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.csapp_10.DBUtils.SQLite;
import com.example.csapp_10.Entity.Order;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

public class GoodDetailsActivity extends AppCompatActivity {
    String imageUrl;
    String name;
    int price;
    String sellerId;
    String type;
    String assetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_good_details);







        ImageView imageView = findViewById(R.id.details_product_image);
        TextView nameTextView = findViewById(R.id.details_product_name);
        TextView productType = findViewById(R.id.details_product_type);
        TextView priceTextView = findViewById(R.id.details_product_price);
        //TextView descriptionTextView = findViewById(R.id.details_product_description);
        TextView sellerIdTextView = findViewById(R.id.details_seller_id);
        Toolbar toolbar = findViewById(R.id.gobackhome_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                finish();
            }
        });
        // 获取传递过来的数据
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // 设置数据到对应的视图
            /*Intent intent = new Intent(context, GoodDetailsActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_icon_url", product.getIconUrl());
            intent.putExtra("product_seller_id", product.getSellerId());
            intent.putExtra("product_asset_id", product.getAssetId());
            intent.putExtra("product_class_id", product.getClassId());
            intent.putExtra("product_instance_id", product.getInstanceId());
            intent.putExtra("product_app_id", product.getAppId());
            intent.putExtra("product_type", product.getType());
            intent.putExtra("product_name_color", product.getNameColor());
            intent.putExtra("product_market_name", product.getMarketName());*/
            // 图标地址 https://community.fastly.steamstatic.com/economy/image/{iconUrl}/96fx96f?allow_animated=1
             imageUrl = extras.getString("product_icon_url");
             name = extras.getString("product_name");
             price = extras.getInt("product_price");
             sellerId = extras.getString("product_seller_id");
             type = extras.getString("product_type");
             assetId = extras.getString("product_asset_id");
            Glide.with(this)
                    .load("https://community.fastly.steamstatic.com/economy/image/"+imageUrl+"/512fx512f?allow_animated=1")
                   .placeholder(R.drawable.more)
                   .error(R.drawable.wrong)
                   .into(imageView);
            nameTextView.setText(name);
            priceTextView.setText("价格：￥"+price);
            productType.setText(type);
            sellerIdTextView.setText("卖家ID: " + sellerId);

        }
        MaterialButton buyButton = findViewById(R.id.details_buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                  /*HttpUtils htu= new HttpUtils();
                  SharedPreferences sharedPreferences = GoodDetailsActivity.this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                  String steamid = sharedPreferences.getString("steamid", "");
                  htu.buyProduct(assetId,sellerId,steamid,price,1);*/

                AlertDialog.Builder builder = new AlertDialog.Builder(GoodDetailsActivity.this);
                builder.setTitle("支付");
                builder.setMessage("是否支付￥"+price+"？");
                builder.setPositiveButton("是", (dialog, which) -> {
                    // 执行购买操作
                    HttpUtils htu= null;
                    try {
                        htu = new HttpUtils();
                        SharedPreferences sharedPreferences = GoodDetailsActivity.this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                        String steamid = sharedPreferences.getString("steamid", "");
                        int respcode=htu.buyProduct(assetId,sellerId,steamid,price,1);
                        if(respcode==200){
                            Toast.makeText(GoodDetailsActivity.this, "已发送交易", Toast.LENGTH_SHORT).show();
                        }
                        else if(respcode==400){
                            Toast.makeText(GoodDetailsActivity.this, "请先设置交易链接", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(GoodDetailsActivity.this, "发送交易失败", Toast.LENGTH_SHORT).show();
                        }

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }).setNegativeButton("否", (dialog, which) -> {
                            // 取消购买操作
                });
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });



    }
}