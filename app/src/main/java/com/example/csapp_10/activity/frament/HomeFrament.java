package com.example.csapp_10.activity.frament;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HomeFrament extends Fragment {
    View rootview;
    int status;
    ProductListAdapter adapter;
    ListView lv;
    List<MarketGood> marketGoods = new LinkedList<>();
    String steamid;

    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        rootview = inflater.inflate(R.layout.fragment_home_frament, container, false);
        lv = rootview.findViewById(R.id.listView);
        SharedPreferences sp = context.getSharedPreferences("user_info", MODE_PRIVATE);
        steamid = sp.getString("steamid", "");

        SearchView searchView = rootview.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                HttpUtils httpUtils = null;
                try {
                    httpUtils = new HttpUtils();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                CompletableFuture<List<MarketGood>> future = httpUtils.getMarketGoodsAsync(steamid);
                future.thenAcceptAsync(goods -> {
                    List<MarketGood> filteredList = goods.stream()
                            .filter(good -> good.getName().toLowerCase().contains(name.toLowerCase()))
                            .collect(Collectors.toList());
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            if (filteredList != null && !filteredList.isEmpty()) {
                                try {
                                    adapter = new ProductListAdapter(context, filteredList);
                                } catch (MalformedURLException e) {
                                    throw new RuntimeException(e);
                                }
                                lv.setAdapter(adapter);
                            } else {
                                Toast.makeText(getActivity(), "暂无相关饰品", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, getActivity().getMainExecutor());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (context != null) {
            HttpUtils httpUtils = null;
            try {
                httpUtils = new HttpUtils();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            CompletableFuture<List<MarketGood>> future = httpUtils.getMarketGoodsAsync(steamid);
            future.thenAcceptAsync(goods -> {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        try {
                            adapter = new ProductListAdapter(context, goods);
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        lv.setAdapter(adapter);
                    });
                }
            }, getActivity().getMainExecutor());
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
