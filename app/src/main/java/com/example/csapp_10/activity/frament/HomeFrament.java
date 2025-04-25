package com.example.csapp_10.activity.frament;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.csapp_10.DBUtils.SQLite;
import com.example.csapp_10.Entity.MarketGood;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;
import com.example.csapp_10.activity.frament.adapter.ProductListAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFrament extends Fragment {
    View rootview;
    int status;
    ProductListAdapter adapter;
    ListView lv;
    List<MarketGood> marketGoods=new LinkedList<>();




    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        rootview = inflater.inflate(R.layout.fragment_home_frament, container, false);
        lv = rootview.findViewById(R.id.listView);

        try {
            adapter = new ProductListAdapter(context, marketGoods);
            lv.setAdapter(adapter);
        } catch (MalformedURLException e) {
            Log.e("CSApp_Log", "Error creating ProductListAdapter", e);
            throw new RuntimeException(e);
        }
        /*SQLite dbUtil = new SQLite(context);*/


        SearchView searchView = rootview.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                HttpUtils httpUtils;
                List<MarketGood> marketGoods = Collections.emptyList();
                try {
                    httpUtils= new HttpUtils();
                    marketGoods= httpUtils.getMarketGoods();
                    List<MarketGood> filteredList = marketGoods.stream()
                            .filter(good -> good.getName().toLowerCase().contains(name.toLowerCase()))
                            .collect(Collectors.toList());
                    if (filteredList!= null &&!filteredList.isEmpty()) {
                       adapter = new ProductListAdapter(context, filteredList);
                       lv.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(getActivity(), "暂无相关饰品", Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e) {
                    Log.e("CSApp_Log", "Error creating HttpUtils", e);
                } catch (IOException e) {

                    throw new RuntimeException(e);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (context != null) {
            /*List<Product> productList = dbUtil.getProductList();*/
            /*Collections.shuffle(productList);
            if (productList == null || productList.size() == 0) {
                Toast.makeText(getActivity(), "No products found", Toast.LENGTH_SHORT).show();
            }*/
            HttpUtils httpUtils;
            List<MarketGood> marketGoods = Collections.emptyList();
            try {
                httpUtils= new HttpUtils();
                marketGoods.clear();
                marketGoods=httpUtils.getMarketGoods();
                adapter.notifyDataSetChanged();
            } catch (MalformedURLException e) {
                Log.e("CSApp_Log", "Error creating HttpUtils", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                adapter = new ProductListAdapter(context, marketGoods);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            lv.setAdapter(adapter);
        } else {
            Log.e("CSApp_Log", "Context is null");
            Toast.makeText(getActivity(), "Context is null", Toast.LENGTH_SHORT).show();
        }

        return rootview;
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}