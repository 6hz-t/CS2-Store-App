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


public class RepoFrament extends Fragment {
    View rootview;
    List<MarketGood> repo;
    HttpUtils httpUtils = null;
    int page=1;
    String phone;
    String steamid=null;
    RepoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview= inflater.inflate(R.layout.fragment_repo_frament, container, false);
        Context context = getContext();


        ListView lv= rootview.findViewById(R.id.repo_list);
        if (context != null) {
            try {
                httpUtils = new HttpUtils();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            try {
                try {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                    phone = sharedPreferences.getString("phone", "");
                    steamid = httpUtils.getSteamid(phone);
                }catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                repo= httpUtils.getRepoGoods(steamid,page);


                if (repo!=null && repo.size()>0) {
                    adapter= new RepoListAdapter(context, repo);
                    lv.setAdapter(adapter);
                }
            } catch (IOException e) {
                Log.e("CSApp_Log", "getSteamid: " + e.getMessage());
                throw new RuntimeException(e);
            }


        } else {
            Toast.makeText(getActivity(), "Context is null", Toast.LENGTH_SHORT).show();
        }

        lv.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
                // TODO Auto-generated method stub
                //滚动到最底部
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    if (view.getLastVisiblePosition() == view.getCount() - 5)
                    {
                       //更新数据
                        updateData();

                    }
                }
            }

            private void updateData() {
                try {
                    page++;
                    List<MarketGood> newpagedata=httpUtils.getRepoGoods(steamid,page);
                    if (newpagedata!=null && newpagedata.size()>0) {
                        adapter.addAll(newpagedata);
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                        page--;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        }
        );


      /*RemoteDB rd= new RemoteDB();
        ResultSet rs = null;//= rd.query("select * from product");
        List<Product> allProducts= new ArrayList<>();
        try {
            while (rs.next()) {
                Product product= new Product();
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("icon_url"));
                allProducts.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (allProducts== null || allProducts.size()==0) {
            lv.setAdapter(null);
        }
        else {
            lv.setAdapter(new RepoListAdapter(getContext(), allProducts));
        }*/
        return rootview;
    }

}