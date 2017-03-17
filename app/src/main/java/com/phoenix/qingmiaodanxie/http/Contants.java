package com.phoenix.qingmiaodanxie.http;

/**
 * Created by 王东 on 2017/2/17.
 */

public class Contants {
    public static final String USER_JSON = "user_json";
    public static final String TOKEN = "token";
    public static final int CODE_SUCCESS = 1;
    public static class API{

        public static final String BASE_URL = "http://121.42.26.175:14444";
        public static final String JINGXUAN = BASE_URL + "/live/find.json";
        public static final String HOT = BASE_URL + "/live/find.json";
        public static final String LOGIN = BASE_URL +"/live/login.json";
        public static final String REGIST = BASE_URL +"/live/register.json";
        public static final String CREAT_STREAMER = BASE_URL +"/live/create.json";
        public static final String  URL_PUSH_STREAMER = "rtmp://cncpublish.bingdou.tv/live/";
        public static final String UPDATE_LIVE_STATUS =BASE_URL+"/live/status/update.json" ;
    }
}
