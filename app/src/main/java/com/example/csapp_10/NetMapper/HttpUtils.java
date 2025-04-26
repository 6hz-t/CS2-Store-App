package com.example.csapp_10.NetMapper;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.example.csapp_10.Entity.MarketGood;
import com.example.csapp_10.Entity.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    String BASE_URL = "http://1.15.41.43:8080";
    String LOGIN_URL = BASE_URL + "/user/login";
    String getSteamid = BASE_URL + "/user/getSteamId";
    String buyGood = BASE_URL + "/trade/buy";
    String getProductList = BASE_URL + "/good/getGoodList";
    String getRepoList = BASE_URL + "/inventory/get";
    String sellGood = BASE_URL + "/good/sell";
    String getuserinfio = BASE_URL + "/user/info";
    String getoutMarket=BASE_URL+"/good/unsell";
    MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public HttpUtils() throws MalformedURLException {

    }

    public int getoutMarket(String assetId) throws IOException {
        int[]code=new int[1];
        Thread getoutMarketThread=new Thread(new Runnable() {
            @Override
            public void run() {
                // /good/unsell  get方法
                StringBuilder url = new StringBuilder(getoutMarket);
                url.append("?assetId=").append(assetId);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url.toString())
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body()!= null) {
                        String result = response.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);
                        code[0] = jsonNode.get("code").asInt();
                        Log.e("CSApp_Log", "run: "+jsonNode.get("data").asText());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        getoutMarketThread.start();
        try {
            getoutMarketThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e("CSApp_Log", "Httputils.getoutMarket "+code[0]);

        return code[0];
    }
    public String getSteamid(String account) throws IOException {
        final String[] steamid = {""};

        Thread Login=new Thread(new Runnable() {
            @Override
            public void run() {
                String url = getSteamid + "?phone="+account ;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);
                        steamid[0] = jsonNode.get("data").asText();
                    }
                } catch (JsonMappingException e) {
                    throw new RuntimeException(e);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Login.start();
        try {
            Login.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e("CSApp_Log", "Httputils steamid "+steamid[0]);
        return steamid[0];

    }
    public List<MarketGood> getMarketGoods(String steamid) throws IOException {
        List<MarketGood> productList = new LinkedList<>();
       Thread getProductListThread=new Thread(new Runnable() {
           @Override
           public void run() {
               // /good/getGoodList  get方法
               ObjectMapper objectMapper = new ObjectMapper();
               OkHttpClient client = new OkHttpClient();
               Request request = new Request.Builder()
                       .url(getProductList)
                       .build();
               try (Response response = client.newCall(request).execute()) {
                   if (response.isSuccessful() && response.body() != null) {
                       String result = response.body().string();
                       JsonNode jsonNode = objectMapper.readTree(result);
                       JsonNode data = jsonNode.get("data");
                       if (data != null&&data.isArray()) {
                           for (JsonNode productNode : data) {
                                     /*"assetId": "",
                                       "sellerId": "",
                                       "price": 0,
                                       "state": 0,
                                       "appId": 0,
                                       "classId": "",
                                       "instanceId": "",
                                       "iconUrl": "",
                                       "name": "",
                                       "type": "",
                                       "nameColor": "",
                                       "marketName": ""*/
                               String sellerId=productNode.get("sellerId").asText();
                               if(sellerId.equals(steamid)){
                                   continue;
                               }
                               MarketGood good = new MarketGood();
                               good.setAssetId(productNode.get("assetId").asText());
                               good.setSellerId(productNode.get("sellerId").asText());
                               good.setPrice(productNode.get("price").asInt());
                               good.setTardeable(productNode.get("state").asInt());
                               good.setAppId(productNode.get("appId").asInt());
                               good.setClassId(productNode.get("classId").asText());
                               good.setInstanceId(productNode.get("instanceId").asText());
                               good.setIconUrl(productNode.get("iconUrl").asText());
                               good.setName(productNode.get("name").asText());
                               good.setType(productNode.get("type").asText());
                               good.setNameColor(productNode.get("nameColor").asText());
                               good.setMarketName(productNode.get("marketName").asText());
                               productList.add(good);

                           }
                           //释放资源
                          /* response.body().close();
                           client.dispatcher().executorService().shutdown();
                           client.connectionPool().evictAll();
                           client.cache().close();*/

                       }
                       //释放资源




                   }
               } catch (IOException e) {
                   Log.e("CSApp_Log", "run: "+e.getMessage());
                   throw new RuntimeException(e);
               }


           }
       });
       getProductListThread.start();
       try {
           getProductListThread.join();
       }catch (InterruptedException e) {
           e.printStackTrace();
       }
        Collections.shuffle(productList);
       Log.e("CSApp_Log", "HttpUtils.getMarketGoods: "+productList.size());
        return productList;
    }
    public List<MarketGood> getRepoGoods(String steamid,int page) throws IOException {
        List<MarketGood> productList = new LinkedList<>();
        Thread getProductListThread=new Thread(new Runnable() {
            @Override
            public void run() {
                // /inventory/get  get方法
                StringBuilder finalurl = new StringBuilder(getRepoList);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    finalurl.append("?steamId=").append(URLEncoder.encode(steamid, StandardCharsets.UTF_8)).append("&page=").append(page).append("&size=50");
                }
                ObjectMapper objectMapper = new ObjectMapper();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalurl.toString())
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        String result = response.body().string();
                        JsonNode jsonNode = objectMapper.readTree(result);
                        JsonNode data = jsonNode.path("data").path("list");
                        if (data != null&&data.isArray()) {
                            for (JsonNode productNode : data) {
                                /*"appId": 0,
                                        "assetId": "",
                                        "classId": "",
                                        "instanceId": "",
                                        "iconUrl": "",
                                        "name": "",
                                        "type": "",
                                        "nameColor": "",
                                        "marketName": "",
                                        "tradable": 0,
*/
                                MarketGood good = new MarketGood();
                                good.setAssetId(productNode.get("assetId").asText());
                                good.setAppId(productNode.get("appId").asInt());
                                good.setClassId(productNode.get("classId").asText());
                                good.setInstanceId(productNode.get("instanceId").asText());
                                good.setIconUrl(productNode.get("iconUrl").asText());
                                good.setName(productNode.get("name").asText());
                                good.setType(productNode.get("type").asText());
                                good.setNameColor(productNode.get("nameColor").asText());
                                good.setMarketName(productNode.get("marketName").asText());
                                good.setTardeable(productNode.get("tradable").asInt());
                                good.setState(productNode.get("state").asInt());
                                productList.add(good);
                            }

                        }


                    }
                } catch (IOException e) {
                   //throw new RuntimeException(e);
                    if (e instanceof ConnectException) {
                        Log.e("CSApp_Log ConnectionFailed", "连接超时", e);
                    }
                }
            }
        });
        getProductListThread.start();
        try {
            getProductListThread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("CSApp_Log", "HttpUtils.getRepoGoods: "+productList.size());
        return productList;
    }
    public int buyProduct(String assetId, String sellerId, String purchaserId, double price, int state) throws IOException {
        int code[]=new int[1];
        Thread buyProductThread=new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                try {
                    Log.e("CSApp_Log", "buyProduct: "+assetId+" "+sellerId+" "+purchaserId+" "+price+" "+state);
                    json.put("assetId", assetId);

                    json.put("sellerId", sellerId);
                    json.put("purchaserId", purchaserId);
                    json.put("price", price);
                    json.put("state", state);
                } catch (JSONException e) {
                    Log.e("CSApp_Log", "JSON 构建错误", e);
                    code[0]=500;
                }

                RequestBody body = RequestBody.create(json.toString(), JSON);
                Request request = new Request.Builder()
                        .url(buyGood)
                        .post(body)
                        .build();
                OkHttpClient client = new OkHttpClient();
                try (Response response = client.newCall(request).execute()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(response.body().string());
                    if (response.isSuccessful() && response.body() != null) {
                        Log.e("CSApp_Log", "请求成功:" +jsonNode.get("code")+jsonNode.get("msg").asText() );
                        code[0]=jsonNode.get("code").asInt();
                    } else {
                        Log.e("CSApp_Log", "请求失败:" +jsonNode.get("msg").asText() );
                        code[0]=500;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        buyProductThread.start();
        try {
            buyProductThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e("CSApp_Log", "HttpUtils.buyProduct: code"+code[0]);
        return code[0];

    }
    public int[] sellgood(String assetId, String sellerId, double price) throws IOException {
        int code[]=new int[2];
        Thread sellProductThread=new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                try {
                    json.put("assetId", assetId);
                    json.put("sellerId", sellerId);
                    json.put("price", price);
                }
                catch (JSONException e) {
                    Log.e("CSApp_Log", "JSON 构建错误", e);
                }
                RequestBody body = RequestBody.create(json.toString(), JSON);
                Request request = new Request.Builder()
                       .url(sellGood)
                       .post(body)
                       .build();

                OkHttpClient client = new OkHttpClient();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body()!= null) {
                        code[0]=response.code();
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(response.body().string());
                        if (jsonNode!= null&&jsonNode.isObject()) {
                            code[1]=jsonNode.get("code").asInt();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        sellProductThread.start();
        try {
            sellProductThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e("CSApp_Log", "Https.sellgood: code"+code[0]+" "+code[1]);
        return code;
    }
    public UserInfo getUserInfo(String account) throws IOException {
        UserInfo user=new UserInfo();
        Thread getUserInfoThread=new Thread(new Runnable() {
            @Override
            public void run() {
                String url = getuserinfio + "?phone=" + account;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        /*{
                            "code": 200,
                                "msg": null,
                                "data": {
                                    "userId": "f987d112c197492396c1f3b3b133d223",
                                    "userName": "Hzing",
                                    "steamName": null,
                                    "phone": "666666",
                                    "password": "f379eaf3c831b04de153469d1bec345e",
                                    "steamId": "76561199528067223",
                                    "avatarUrl": "https://avatars.steamstatic.com/b1766563d2bd519a27252c26f219431a3ccf0b29_full.jpg",
                                    "createdAt": "2025-04-23T20:52:01",
                                    "lastLogin": "2025-04-23T20:52:01",
                                    "fund": null,
                                    "newPassword": null
                        }*/
                        String result = response.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);
                        JsonNode data = jsonNode.get("data");
                        if (data != null && data.isObject()) {
                            user.setUsername(data.get("userName").asText());
                            user.setPhone(data.get("phone").asText());
                            user.setAvatarUrl(data.get("avatarUrl").asText());
                            user.setSteamId(data.get("steamId").asText());
                            user.setPassword(data.get("password").asText());
                            user.setWallet(data.get("fund").asInt());
                        }
                    }

                } catch (JsonMappingException e) {
                    throw new RuntimeException(e);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getUserInfoThread.start();
        try{
            getUserInfoThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Log.e("CSApp_Log", "HttpUtils.getUserInfo: "+user.getUsername());
        return user;
    }
}
