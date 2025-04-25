package com.example.csapp_10.Entity;

public class MarketGood {


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
            "marketName": "",*/

    private String assetId;
    private String sellerId;
    private int price;
    private int state;
    private int appId;
    private String classId;
    private String instanceId;
    private String iconUrl;
    private String name;
    private String type;
    private String nameColor;
    private String marketName;

    public MarketGood() {

    }

    public MarketGood(String assetId, String sellerId, int price, int state, int appId, String classId, String instanceId, String iconUrl, String name, String type, String nameColor, String marketName) {
        this.assetId = assetId;
        this.sellerId = sellerId;
        this.price = price;
        this.state = state;
        this.appId = appId;
        this.classId = classId;
        this.instanceId = instanceId;
        this.iconUrl = iconUrl;
        this.name = name;
        this.type = type;
        this.nameColor = nameColor;
        this.marketName = marketName;

    }
    public String getAssetId() {
        return assetId;
    }
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getAppId() {
        return appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getInstanceId() {
        return instanceId;
    }
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNameColor() {
        return nameColor;
    }
    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
    public String getMarketName() {
        return marketName;
    }
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
    @Override
    public String toString() {
        return "MarketGood{" +
                "assetId='" + assetId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", appId=" + appId +
                ", classId='" + classId + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", nameColor='" + nameColor + '\'' +
                ", marketName='" + marketName + '\'' +
                '}';
    }

}
