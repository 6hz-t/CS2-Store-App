package com.example.csapp_10;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.example.csapp_10.NetMapper.HttpUtils;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.net.MalformedURLException;

public class RepoGoodDetailsActivity extends AppCompatActivity {
    String imageUrl;
    String steamid;
    String name;
    String sellerId;
    int tradeable;
    int state;
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
        steamid=getSharedPreferences("user_info", MODE_PRIVATE).getString("steamid", "");
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
        intent.putExtra("product_type", product.getType());
        intent.putExtra("product_id", product.getAssetId());*/
            // 图标地址 https://community.fastly.steamstatic.com/economy/image/{iconUrl}/96fx96f?allow_animated=1
            imageUrl = extras.getString("product_icon_url");
            name = extras.getString("product_name");
            type = extras.getString("product_type");
            assetId = extras.getString("product_id");
            sellerId = extras.getString("product_seller_id");
            // intent.putExtra("product_state", product.getState());
            tradeable = extras.getInt("product_tradeable");
            state = extras.getInt("product_state");
            Glide.with(this)
                    .load("https://community.fastly.steamstatic.com/economy/image/"+imageUrl+"/512fx512f?allow_animated=1")
                    .placeholder(R.drawable.more)
                    .error(R.drawable.wrong)
                    .into(imageView);
            nameTextView.setText(name);
            productType.setText(type);

        }
        MaterialButton buyButton = findViewById(R.id.details_repo_buy_button);
        if(tradeable==1){
            //Log.e("CSApp_Log testeeeeeeeeeeeeeeeeeeeeeee", "onCreate: " + status+" "+assetId+" "+steamid+" "+name);


            if(state==1){
                buyButton.setText("下架");
                buyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RepoGoodDetailsActivity.this);
                        builder.setTitle("确定");
                        builder.setMessage("确定下架 "+name+" 吗？");
                        builder.setPositiveButton("确定", (dialog, which) -> {
                            // 下架商品
                            HttpUtils htu = null;
                            try {
                                htu = new HttpUtils();
                                int code=htu.getoutMarket(assetId);
                                if(code==200){
                                    Toast.makeText(RepoGoodDetailsActivity.this, "下架成功", Toast.LENGTH_SHORT).show();
                                }
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                        });
                        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());
                    }
                });

            }
            else{
                buyButton.setBackgroundColor(Color.parseColor("#FFA500"));
                buyButton.setText("上架");buyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //输入价格
                        // 输入价格
                        AlertDialog.Builder builder = new AlertDialog.Builder(RepoGoodDetailsActivity.this);
                        builder.setTitle("输入价格");

                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_price_input, null);
                        EditText input = dialogView.findViewById(R.id.price_input);
                        input.setHint("请输入价格");
                        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                        builder.setView(dialogView);

                        // 设置确定按钮
                        builder.setPositiveButton("确定", (dialog, which) -> {
                            // 获取输入框中的文本
                            String price = input.getText().toString();
                            if(price.equals("")){
                                Toast.makeText(RepoGoodDetailsActivity.this, "请输入价格", Toast.LENGTH_SHORT).show();
                            }
                            // public int[] sellgood(String assetId, String sellerId, double price)
                            else{
                                HttpUtils htu = null;
                                try {
                                    htu = new HttpUtils();
                                    int []code=htu.sellgood(assetId, steamid, Double.parseDouble(price));
                                    if(code[1]==200&&code[0]==200){
                                        Toast.makeText(RepoGoodDetailsActivity.this, "上架成功", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (IOException e) {
                                    throw new RuntimeException(e);
                                }


                            }


                        });

                        // 设置取消按钮
                        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());

                        // 显示对话框
                        builder.show();

                    }
                });
            }

        }else{
            //Log.e("CSApp_Log testeeeeeeeeeeeeeeeeeeeeeee", "onCreate: " + status+" "+assetId+" "+steamid+" "+name);
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RepoGoodDetailsActivity.this, "饰品不可交易", Toast.LENGTH_SHORT).show();
                }
            });
            buyButton.setText("不可交易");
            buyButton.setBackgroundColor(Color.GRAY);

        }

    }
}