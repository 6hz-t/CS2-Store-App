package com.example.csapp_10.activity.frament.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import com.example.csapp_10.Entity.MarketGood;
import com.example.csapp_10.GoodDetailsActivity;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;
import com.example.csapp_10.RepoGoodDetailsActivity;

import java.io.IOException;

import java.util.List;

public class RepoListAdapter extends ArrayAdapter<MarketGood> {


    private List<MarketGood> productList;
    private Context context;

    public RepoListAdapter(@NonNull Context context, List<MarketGood> productList) {
        super(context, R.layout.repo_product_list, productList);
        this.productList = productList;
        this.context = context;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.repo_product_list,parent,false);
        }
        //        android:id="@+id/market_product_image"
        //        android:id="@+id/market_product_name"
        //        android:id="@+id/market_product_price"
        //        android:id="@+id/market_product_salecount"




        ImageView iv =convertView.findViewById(R.id.repo_product_image);
        TextView name = convertView.findViewById(R.id.repo_product_name);
        TextView price = convertView.findViewById(R.id.repo_product_price);
        TextView salecount = convertView.findViewById(R.id.repo_product_salecount);
        MarketGood product = productList.get(position);


        // 图标地址 https://community.fastly.steamstatic.com/economy/image/{iconUrl}/96fx96f?allow_animated=1

        Glide.with(getContext())
                .load("https://community.fastly.steamstatic.com/economy/image/"+product.getIconUrl()+"/128fx128f?allow_animated=1")
                .placeholder(R.drawable. more)
                .error(R.drawable.wrong)
                .into(iv);
        name.setText(product.getName());
        salecount.setText(String.valueOf(product.getType()));
        if(product.getState()==0){
            price.setText(String.valueOf("不可交易" ));
        }else{
            price.setText(String.valueOf("可交易" ));
        }



        LinearLayout parentLayout = convertView.findViewById(R.id.repo_parent_layout);
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showrepogooddetail(product);



            }
        });



        return convertView;
    }
    private void showrepogooddetail(MarketGood product){
        Intent intent = new Intent(context, RepoGoodDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("product_price", product.getPrice());
        intent.putExtra("product_icon_url", product.getIconUrl());
        intent.putExtra("product_seller_id", product.getSellerId());
        intent.putExtra("product_type", product.getType());
        intent.putExtra("product_id", product.getAssetId());
        context.startActivity(intent);













        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("上架");
        builder.setMessage("是否确认上架 " + product.getName() + "？");
        builder.setPositiveButton("上架", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("价格设置");

                // 创建一个 EditText 用于输入
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                int leftRightMargin = 50;
                params.setMargins(leftRightMargin, 60, leftRightMargin, 20);
                input.setLayoutParams(params);
                input.setGravity(Gravity.CENTER_VERTICAL);
                input.setBackgroundResource(android.R.drawable.edit_text);

                input.setHint("请输入价格");

                builder.setView(input);

                // 设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double price = Double.parseDouble(input.getText().toString());
                        int[] respcode;
                                        HttpUtils httpUtils;
                                       try {
                                           httpUtils = new HttpUtils();
                                           SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                                           String steamid = sharedPreferences.getString("steamid", "");
                                           respcode=httpUtils.sellProduct(product.getAssetId(),steamid, price);
                                       } catch (IOException e) {
                                           Log.e("CSApp_Log", "Error creating HttpUtils", e);
                                           throw new RuntimeException(e);
                                       }
                                       if(respcode[0]==200&&respcode[1]==200){
                                           Toast.makeText(context, "上架成功", Toast.LENGTH_SHORT).show();
                                       }
                                       else if(respcode[0]==200&&respcode[1]==400){
                                           Toast.makeText(context, "该商品已上架", Toast.LENGTH_SHORT).show();
                                       }
                                       else{
                                           Toast.makeText(context, "上架失败"+respcode[1], Toast.LENGTH_SHORT).show();
                                       }
                    }
                });

                // 设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // 显示对话框
                builder.show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 用户取消购买，不做任何操作或可以添加提示信息
                Toast.makeText(context, "已取消", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();*/
    }
}
