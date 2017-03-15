package com.phoenix.qingmiaodanxie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.phoenix.qingmiaodanxie.entity.User;

/**
 * Created by 王东 on 2017/1/27.
 */
public class SharedUtils {
    private static SharedPreferences sp;
    public static void saveUser(Context context, String name,String pass) {
        if (sp == null) {
            sp = context.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        }else{
            sp.edit().putString("tel",name);
            sp.edit().putString("pass",pass);
            sp.edit().commit();
        }
        Log.e("TAG","sp（Tel）==="+sp.getString("tel","未获取"));
    }

    public static User getUser(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("user_data",
                    Context.MODE_PRIVATE);
        }
        String name = sp.getString("tel", "");
        String pass = sp.getString("pass", "");
       return new User(name,pass);
    }
}
