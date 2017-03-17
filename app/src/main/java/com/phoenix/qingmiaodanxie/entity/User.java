package com.phoenix.qingmiaodanxie.entity;

/**
 * Created by 王东 on 2017/3/10.
 */
public class User extends BaseBean {
    private String phone;
    private String user_name;
    private String avatar;
    private String sign;
    private String password;
    private String token;
    public User() {
    }

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User(long uId, String phone, String password) {
        this.id = uId;
        this.phone = phone;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
