package com.phoenix.qingmiaodanxie.entity;

/**
 * Created by 王东 on 2017/3/2.
 */

public class User extends BaseBean {
    private String mobi;
    private String email;
    private String username;
    private String logo_url;

    public String getMobi() {
        return mobi;
    }

    public void setMobi(String mobi) {
        this.mobi = mobi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
