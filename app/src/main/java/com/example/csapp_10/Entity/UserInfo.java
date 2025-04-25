package com.example.csapp_10.Entity;

public class UserInfo {

    /* "userId": "f987d112c197492396c1f3b3b133d223",
             "userName": "Hzing",
             "steamName": null,
             "phone": "666666",
             "password": "f379eaf3c831b04de153469d1bec345e",
             "steamId": "76561199528067223",
             "avatarUrl": "https://avatars.steamstatic.com/b1766563d2bd519a27252c26f219431a3ccf0b29_full.jpg",
             "createdAt": "2025-04-23T20:52:01",
             "lastLogin": "2025-04-23T20:52:01",
             "fund": null,
             "newPassword": null*/
    String username;
    String phone;
    String avatarUrl;

    String steamId;
    String password="******";
    //getter setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getSteamId() {
        return steamId;
    }
    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserInfo(String username, String phone, String avatarUrl, String steamId, String password) {
        this.username = username;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.steamId = steamId;
        this.password = password;
    }
    public UserInfo() {
        this.username = "未登录";
        this.phone = "未登录";
        this.avatarUrl = "未登录";
        this.steamId = "未登录";
        this.password = "未登录";
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", steamId='" + steamId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
