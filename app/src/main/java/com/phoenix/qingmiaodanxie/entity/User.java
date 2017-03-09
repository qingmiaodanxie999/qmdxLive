package com.phoenix.qingmiaodanxie.entity;

import java.io.Serializable;

/**
 * Created by 王东 on 2017/3/2.
 */

public class User extends BaseBean implements Serializable{
    private String headIcon;
    private String informationImage;
    private String name;
    private String place;
    private String status;

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(String informationImage) {
        this.informationImage = informationImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
