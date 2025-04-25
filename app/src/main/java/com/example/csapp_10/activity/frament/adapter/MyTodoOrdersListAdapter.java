package com.example.csapp_10.activity.frament.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.csapp_10.Entity.Order;
import com.example.csapp_10.NetMapper.OrderHttp;
import com.example.csapp_10.R;

import java.io.IOException;
import java.util.List;

public class MyTodoOrdersListAdapter extends ArrayAdapter<Order> {

    List<Order> orderList;
    OrderHttp orderHttp;
    private Context context;

    public MyTodoOrdersListAdapter(@NonNull Context context, List<Order> orderList) {

        super(context, R.layout.order_todo_list, orderList);
        this.orderList = orderList;
        this.context = context;
        Log.d("MyOrdersActivity", "Order list size: " + orderList.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_todo_list,parent,false);
        }
        //        android:id="@+id/market_product_image"
        //        android:id="@+id/market_product_name"
        //        android:id="@+id/market_product_price"
        //        android:id="@+id/market_product_salecount"


/*        order.setProductName(orderNode.path("goodName").asText());
        order.setUserId(orderNode.path("sellerName").asText());
        order.setProductId(orderNode.path("buyerName").asText());
        order.setProductPrice(orderNode.path("price").asInt());
        order.setImageUrl(orderNode.path("goodImg").asText());*/
        TextView name = convertView.findViewById(R.id.todo_order_goodname);
        TextView price = convertView.findViewById(R.id.todo_order_goodprice);
        TextView status = convertView.findViewById(R.id.todo_order_status);
        ImageView iv =convertView.findViewById(R.id.todo_order_goodimage);
        Button btn = convertView.findViewById(R.id.deal_button);

        Order order = orderList.get(position);
        // 图标地址 https://community.fastly.steamstatic.com/economy/image/{iconUrl}/96fx96f?allow_animated=1
        Glide.with(getContext())
                .load("https://community.fastly.steamstatic.com/economy/image/"+order.getImageUrl()+"/128fx128f?allow_animated=1")
                .placeholder(R.drawable.more)
                .error(R.drawable.wrong)
                .into(iv);

        name.setText(order.getProductName());
        price.setText(String.valueOf("购入价:" + order.getProductPrice()));
        status.setText(String.valueOf("状态:" + order.getStatus()));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前Order
                Order order = orderList.get(position);
                if(order.getStatus().equals("卖家已发货")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("确认收货");
                    builder.setMessage("是否向卖家确认收货？");
                    builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //发送确认收货请求
                            orderHttp = new OrderHttp();
                            try {
                                orderHttp.buyergetin(order);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Toast.makeText(context, "确认收货成功", Toast.LENGTH_SHORT).show();

                        }


                    });
                    builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //拒绝交易请求

                            Toast.makeText(context, "取消交易", Toast.LENGTH_SHORT).show();

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }else if(order.getStatus().equals("卖家未发货")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("交易进行中");
                    builder.setMessage("请等待卖家发货......");
                    builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "已通知卖家发货", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("卖家拒绝交易");
                    builder.setMessage("交易已结束，无法进行交易");
                    builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        return convertView;
    }
}
