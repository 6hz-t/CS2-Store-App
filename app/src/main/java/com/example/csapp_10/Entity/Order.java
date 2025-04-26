package com.example.csapp_10.Entity;

public class Order {
    private int id;
    private String orderid;
    private String userId;
    private String sellerid;
    private String buyerid;
    private String sellerName;
    private String buyerName;
    private String productId;//buyername
    private String productName;
    private double totalPrice;
    private String orderDate;
    private int productPrice;
    private String status;
    private String imageUrl;

    // Getters and Setters
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getSellerid() {
        return sellerid;
    }
    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }
    public String getBuyerid() {
        return buyerid;
    }
    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }
    public String getOrderId() {
        return orderid;
    }
    public void setOrderId(String orderid) {
        this.orderid = orderid;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }


    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    // Constructors
    public Order() {
    }
    //"insert into orders(userId, productName, imageUrl, productPrice, orderDate, status) values(?, ?, ?, ?, ?, ?)"
    //构造
    public Order(String userId, String productName, String imageUrl, int productPrice, String orderDate, String status) {
        this.userId = userId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.productPrice = productPrice;
        this.orderDate = orderDate;
        this.status = status;
    }
    public Order(int id, String userId, String productId, int quantity, double totalPrice, String orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }
    // toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", totalPrice=" + totalPrice +
                ", orderDate='" + orderDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
