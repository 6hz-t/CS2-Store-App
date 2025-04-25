package com.example.csapp_10.activity.frament.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.csapp_10.Entity.Order;
import com.example.csapp_10.R;

import java.util.List;

public class MyDoneOrdersListAdapter extends ArrayAdapter<Order> {
    private List<Order> orders;
    private Context context;
    public MyDoneOrdersListAdapter(Context context, List<Order> orders) {
        super(context, R.layout.order_done_list, orders);
        this.orders = orders;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_done_list, parent, false);
        }
        TextView nametv = convertView.findViewById(R.id.done_order_goodname);
        TextView pricetv = convertView.findViewById(R.id.done_order_goodprice);
        TextView statetv = convertView.findViewById(R.id.done_order_status);
        ImageView img = convertView.findViewById(R.id.done_order_goodimage);
        Order order = orders.get(position);
        nametv.setText(order.getProductName());
        pricetv.setText("购入价:"+order.getProductPrice());
        statetv.setText(order.getStatus());
        Glide.with(context)
                .load("https://community.fastly.steamstatic.com/economy/image/"+order.getImageUrl()+"/128fx128f?allow_animated=1")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(img);
        return convertView;
    }
}
