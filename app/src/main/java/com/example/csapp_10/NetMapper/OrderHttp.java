package com.example.csapp_10.NetMapper;

import android.util.Log;

import com.example.csapp_10.Entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderHttp {
    String BASE_URL = "http://1.15.41.43:8080";
    String gettodoOrderUrl = BASE_URL + "/trade/getMyTrade";
    String getdoneOrderUrl = BASE_URL + "/trade/getMyHistoryTrade";
    String sellersendout = BASE_URL + "/trade/confirm";
    String buyergetin = BASE_URL + "/trade/receive";
    String sellerrefuse = BASE_URL + "/trade/refuse";

    public boolean sellersendout(Order order) throws IOException {
        /*{
                "id": "",
                "assetId": "",
                "sellerId": "",
                "purchaserId": "",
                "price": 0,
                "state": 0
        }*/
        int[]responsecode = new int[1];
        Thread sellersendoutthread = new Thread(new Runnable() {
           @Override
           public void run() {
               String json = String.format(Locale.US, "{\"id\":\"%s\",\"assetId\":\"%s\",\"sellerId\":\"%s\",\"purchaserId\":\"%s\",\"price\":%f,\"state\":%d}",
                       order.getOrderId(),
                       order.getProductId(),
                       order.getSellerid(),
                       order.getBuyerid(),
                       (double) order.getProductPrice(),
                       // 这里假设需要根据业务逻辑设置 state 的值，暂时用 0 示例
                       0
               );

               MediaType JSON = MediaType.get("application/json; charset=utf-8");
               RequestBody body = RequestBody.create(json, JSON);

               OkHttpClient client = new OkHttpClient();
               Request request = new Request.Builder()
                       .url(String.valueOf(sellersendout))
                       .post(body)
                       .build();

               try (Response response = client.newCall(request).execute()) {
                   ObjectMapper objectMapper = new ObjectMapper();
                   JsonNode jsonNode = objectMapper.readTree(response.body().string());
                   int code = jsonNode.path("code").asInt();
                   responsecode[0] = code;

                   Log.e("CSApp_Log",response.body().string());
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
           }
        });
        sellersendoutthread.start();
        try {
            sellersendoutthread.join();
        }   catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(responsecode[0]==200){
            Log.e("CSApp_Log","sellersendout success");
            return true;
        }else{
            Log.e("CSApp_Log","sellersendout failed");
            return false;
        }
    }
    public boolean buyergetin(Order order) throws IOException {
       /* {
                "id": "",
                "assetId": "",
                "sellerId": "",
                "purchaserId": "",
                "price": 0,
                "state": 0
        }*/
        int[]responsecode = new int[1];
        Thread buyergetinthread = new Thread(new Runnable() {
            @Override
            public void run() {
                String json = String.format(Locale.US, "{\"id\":\"%s\",\"assetId\":\"%s\",\"sellerId\":\"%s\",\"purchaserId\":\"%s\",\"price\":%f,\"state\":%d}",
                        order.getOrderId(),
                        order.getProductId(),
                        order.getSellerid(),
                        order.getBuyerid(),
                        (double) order.getProductPrice(),
                        // 这里假设需要根据业务逻辑设置 state 的值，暂时用 0 示例
                        0
                );

                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(json, JSON);

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(String.valueOf(buyergetin))
                        .post(body)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(response.body().string());
                    int code = jsonNode.path("code").asInt();
                    responsecode[0] = code;
                    Log.e("response",response.body().string());
                } catch (IOException e) {
                    Log.e("CSApp_Log","buyergetin failed");
                    throw new RuntimeException(e);
                }
            }
        });
        buyergetinthread.start();
        try {
            buyergetinthread.join();
        }   catch (InterruptedException e) {
            Log.e("CSApp_Log","buyergetin failed");
            throw new RuntimeException(e);
        }
        if(responsecode[0]==200){
            Log.e("CSApp_Log","buyergetin success");
            return true;
        }else {
            Log.e("CSApp_Log","buyergetin failed");
            return false;
        }
    }
    public boolean sellerrefuse(Order order) throws IOException {
        /* {
                "id": "",
                "assetId": "",
                "sellerId": "",
                "purchaserId": "",
                "price": 0,
                "state": 0
        }*/
        int[]responsecode = new int[1];
        Thread sellerrefusethread = new Thread(new Runnable() {
            @Override
            public void run() {
                String json = String.format(Locale.US, "{\"id\":\"%s\",\"assetId\":\"%s\",\"sellerId\":\"%s\",\"purchaserId\":\"%s\",\"price\":%f,\"state\":%d}",
                        order.getOrderId(),
                        order.getProductId(),
                        order.getSellerid(),
                        order.getBuyerid(),
                        (double) order.getProductPrice(),
                        // 这里假设需要根据业务逻辑设置 state 的值，暂时用 0 示例
                        0
                );
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(json, JSON);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                       .url(String.valueOf(sellerrefuse))
                       .post(body)
                       .build();
                try (Response response = client.newCall(request).execute()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(response.body().string());
                    int code = jsonNode.path("code").asInt();
                    responsecode[0] = code;
                    Log.e("response",response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sellerrefusethread.start();
        try {
            sellerrefusethread.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(responsecode[0]==200){
            Log.e("CSApp_Log","sellerrefuse success");
            return true;
        }
        else {
            Log.e("CSApp_Log","sellerrefuse failed");
            return false;
        }

    }



    public List<Order> gettodoOrder(String steamid) throws IOException {
        List<Order> orders = new ArrayList<>();
        Thread gettodoOrder = new Thread(new Runnable() {
            @Override
            public void run() {
                //steamId		query	true string

                StringBuilder sb = new StringBuilder();
                sb.append(gettodoOrderUrl);
                sb.append("?steamId=");
                sb.append(steamid);

                    ObjectMapper objectMapper = new ObjectMapper();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(sb.toString())
                            .get()
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            JsonNode jsonNode = objectMapper.readTree(responseBody);
                            /*{
                                    "id": "25042415012249455562",
                                    "sellerSteamId": "76561199528067223",
                                    "buyerSteamId": "76561199190828590",
                                    "assetId": "38055975442",
                                    "goodName": "P90 | 摧枯拉朽",
                                    "goodImg": "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopuP1FBRw7P7NYjV95NOiq4GFk8j3PLfVqWZU7Mxkh6eW99X30QDl-Rdrazz2cdCWIwE4MlvQ-wTtw7rt1sO_6ZWcnSA2uHEi-z-DyDdxjIvl",
                                    "sellerName": "Hzing",
                                    "buyerName": "梨花染雪",
                                    "price": 12,
                                    "url": "dadadad",
                                    "state": 1
                            }*/
                            JsonNode dataNode = jsonNode.path("data");
                            if (dataNode.isArray()&&dataNode.size()>0) {
                                for (JsonNode orderNode : dataNode) {
                                    Order order = new Order();
                                    order.setOrderId(orderNode.path("id").asText());
                                    order.setProductId(orderNode.path("assetId").asText());
                                    order.setProductName(orderNode.path("goodName").asText());
                                    order.setSellerName(orderNode.path("sellerName").asText());
                                    order.setBuyerName(orderNode.path("buyerName").asText());
                                    order.setBuyerid(orderNode.path("buyerSteamId").asText());
                                    order.setSellerid(orderNode.path("sellerSteamId").asText());
                                    order.setProductPrice(orderNode.path("price").asInt());
                                    order.setImageUrl(orderNode.path("goodImg").asText());
                                    if(orderNode.path("state").asInt()==1){
                                        //卖家已发货
                                        order.setStatus("卖家已发货");
                                    }else if (orderNode.path("state").asInt()==0){
                                        //卖家未发货
                                        order.setStatus("卖家未发货");
                                    }

                                    orders.add(order);
                                }

                            }
                        }
                    } catch (JsonMappingException e) {
                        Log.e("OrderHttp", "Error mapping JSON to Order object", e);
                        throw new RuntimeException(e);
                    } catch (JsonProcessingException e) {
                        Log.e("OrderHttp", "Error processing JSON", e);
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        Log.e("OrderHttp", "Error making HTTP request", e);
                        throw new RuntimeException(e);
                    }


            }
        });
        gettodoOrder.start();
        try {
            gettodoOrder.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return orders;

    }
    public List<Order> getdoneOrder(String steamId) throws IOException {
        List<Order> orders = new ArrayList<>();
        Thread getdoneOrder = new Thread(new Runnable() {
            @Override
            public void run() {
                //steamId		query	true string
                StringBuilder sb = new StringBuilder();
                sb.append(getdoneOrderUrl);
                sb.append("?steamId=");
                sb.append(steamId);
                ObjectMapper objectMapper = new ObjectMapper();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(sb.toString())
                        .get()
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        JsonNode jsonNode = objectMapper.readTree(responseBody);
                        /*{
                            "id": "25042415013836350605",
                                "sellerSteamId": "76561199528067223",
                                "buyerSteamId": "76561199190828590",
                                "assetId": "38055975442",
                                "goodName": "P90 | 摧枯拉朽",
                                "goodImg": "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopuP1FBRw7P7NYjV95NOiq4GFk8j3PLfVqWZU7Mxkh6eW99X30QDl-Rdrazz2cdCWIwE4MlvQ-wTtw7rt1sO_6ZWcnSA2uHEi-z-DyDdxjIvl",
                                "sellerName": "Hzing",
                                "buyerName": "梨花染雪",
                                "price": 12,
                                "url": "dadadad",
                                "state": 4
                        }*/
                        JsonNode dataNode = jsonNode.path("data");
                        if (dataNode.isArray()&&dataNode.size()>0) {
                            for (JsonNode orderNode : dataNode) {
                                Order order = new Order();
                                order.setSellerid(orderNode.path("sellerSteamId").asText());
                                order.setProductName(orderNode.path("goodName").asText());
                                order.setSellerName(orderNode.path("sellerName").asText());
                                order.setBuyerName(orderNode.path("buyerName").asText());
                                order.setProductPrice(orderNode.path("price").asInt());
                                order.setImageUrl(orderNode.path("goodImg").asText());
                                if(orderNode.path("state").asInt()==2){
                                    //卖家已发货，待收货
                                    order.setStatus("交易完成");
                                }else if (orderNode.path("state").asInt()==4){
                                    //卖家未发货
                                    order.setStatus("卖家拒绝交易");
                                }else {
                                    order.setStatus("交易取消");

                                }
                                orders.add(order);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        getdoneOrder.start();
        try {
            getdoneOrder.join();
        }        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
