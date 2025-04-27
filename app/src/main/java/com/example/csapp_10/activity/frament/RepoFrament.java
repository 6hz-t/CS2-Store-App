package com.example.csapp_10.activity.frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.csapp_10.DBUtils.SQLite;
import com.example.csapp_10.Entity.MarketGood;
import com.example.csapp_10.NetMapper.HttpUtils;
import com.example.csapp_10.R;
import com.example.csapp_10.activity.frament.adapter.RepoListAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RepoFrament extends Fragment {
    View rootview;
    List<MarketGood> repo;
    HttpUtils httpUtils = null;
    int page = 1;
    String phone;
    String steamid = null;
    RepoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_repo_frament, container, false);
        Context context = getContext();

        ListView lv = rootview.findViewById(R.id.repo_list);
        if (context != null) {
            try {
                httpUtils = new HttpUtils();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
            phone = sharedPreferences.getString("phone", "");
            try {
                steamid = httpUtils.getSteamid(phone);
            } catch (IOException e) {
                Log.e("CSApp_Log", "getSteamid: " + e.getMessage());
                Toast.makeText(getActivity(), "获取Steam ID失败", Toast.LENGTH_SHORT).show();
                return rootview;
            }

            if (steamid == null || steamid.isEmpty()) {
                Toast.makeText(getActivity(), "请绑定Steam", Toast.LENGTH_SHORT).show();
            } else {
                // 调用异步方法获取商品列表
                CompletableFuture<List<MarketGood>> future = httpUtils.getRepoGoodsAsync(steamid, page);
                future.thenAcceptAsync(goods -> {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            if (goods != null && !goods.isEmpty()) {
                                adapter = new RepoListAdapter(context, goods);
                                lv.setAdapter(adapter);
                            }
                        });
                    }
                }, getActivity().getMainExecutor()).exceptionally(ex -> {
                    Log.e("CSApp_Log", "获取仓库商品失败", ex);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getActivity(), "获取仓库商品失败", Toast.LENGTH_SHORT).show());
                    }
                    return null;
                });
            }
        } else {
            Toast.makeText(getActivity(), "Context is null", Toast.LENGTH_SHORT).show();
        }

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 滚动到最底部
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        // 更新数据
                        updateData(lv);
                    }
                }
            }

            private void updateData(ListView lv) {
                page++;
                // 调用异步方法获取新一页商品列表
                CompletableFuture<List<MarketGood>> future = httpUtils.getRepoGoodsAsync(steamid, page);
                future.thenAcceptAsync(goods -> {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            if (goods != null && !goods.isEmpty()) {
                                if (adapter == null) {
                                    adapter = new RepoListAdapter(getContext(), goods);
                                    lv.setAdapter(adapter);
                                } else {
                                    adapter.addAll(goods);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                page--;
                            }
                        });
                    }
                }, getActivity().getMainExecutor()).exceptionally(ex -> {
                    Log.e("CSApp_Log", "更新数据失败", ex);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            Toast.makeText(getActivity(), "更新数据失败", Toast.LENGTH_SHORT).show();
                            page--;
                        });
                    }
                    return null;
                });
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        return rootview;
    }
}
