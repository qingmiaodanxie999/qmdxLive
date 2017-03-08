package com.phoenix.qingmiaodanxie.utils;

import android.content.Context;
import android.text.TextUtils;

import com.phoenix.qingmiaodanxie.entity.User;
import com.phoenix.qingmiaodanxie.http.Contants;

/**
 * Created by 王东 on 2017/3/2.
 */

public class UserLocalData {
    public static void putUser(Context context, User user) {
        String user_json = GsonUtils.toJson(user);
        PreferenceUtils.putString(context, Contants.USER_JSON, user_json);
    }
    public static void putToken(Context context,String token) {
        PreferenceUtils.putString(context, Contants.TOKEN, token);
    }
    public static User getUser(Context context) {
        String user_josn = PreferenceUtils.getString(context, Contants.USER_JSON);
        if (!TextUtils.isEmpty(user_josn)) {
            return GsonUtils.fromJson(user_josn, User.class);
        }
        return null;
    }
    public static String getToken(Context context) {
        return PreferenceUtils.getString(context,Contants.TOKEN);
    }
    public static void clearUser(Context context) {
        PreferenceUtils.putString(context,Contants.USER_JSON,"");
    }
    public static void clearToken(Context context) {
        PreferenceUtils.putString(context,Contants.TOKEN,"");
    }
}
