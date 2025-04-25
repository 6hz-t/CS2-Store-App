package com.example.csapp_10;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class RepoGoodDetailsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_repo_good_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView imageView = findViewById(R.id.details_repo_product_image);
        TextView nameTextView = findViewById(R.id.details_repo_product_name);
        TextView productType = findViewById(R.id.details_repo_product_type);
        Toolbar toolbar = findViewById(R.id.gobackhome2_toolbar);
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
            type = extras.getString("product_type");
            assetId = extras.getString("product_asset_id");
            Glide.with(this)
                    .load("https://community.fastly.steamstatic.com/economy/image/"+imageUrl+"/512fx512f?allow_animated=1")
                    .placeholder(R.drawable.more)
                    .error(R.drawable.wrong)
                    .into(imageView);
            nameTextView.setText(name);
            productType.setText(type);

        }
        MaterialButton buyButton = findViewById(R.id.details_repo_buy_button);
    }
}