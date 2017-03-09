package com.phoenix.qingmiaodanxie.entity;

import java.util.List;

/**
 * Created by 王东 on 2017/3/9.
 */

public class ResponseObject {

    /**
     * currentPage : 2
     * list : [{"headIcon":"http://192.168.155.101:80/RealTimePlay/head/6.jpg","id":6,"informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","name":"主播6号","place":"北京","status":"直播"},{"headIcon":"http://192.168.155.101:80/RealTimePlay/head/7.jpg","id":7,"informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","name":"主播7号","place":"上海","status":"重播"},{"headIcon":"http://192.168.155.101:80/RealTimePlay/head/8.jpg","id":8,"informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","name":"主播8号","place":"北京","status":"重播"},{"headIcon":"http://192.168.155.101:80/RealTimePlay/head/9.jpg","id":9,"informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","name":"主播9号","place":"上海","status":"直播"},{"headIcon":"http://192.168.155.101:80/RealTimePlay/head/10.jpg","id":10,"informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","name":"主播10号","place":"北京","status":"重播"}]
     * pageSize : 5
     * totalCount : 100
     * totalPage : 20
     */

    private int currentPage;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<ListBean> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * headIcon : http://192.168.155.101:80/RealTimePlay/head/6.jpg
         * id : 6
         * informationImage : http://192.168.155.101:80/RealTimePlay/information/1.jpg
         * name : 主播6号
         * place : 北京
         * status : 直播
         */

        private String headIcon;
        private int id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
}
