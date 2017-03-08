package com.phoenix.qingmiaodanxie.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 王东 on 2017/2/24.
 */

public class PreferenceUtils {
    private static final String PREFERENCE_NAME="shop_common";
    public static boolean putString(Context context,String key,String value) {
        SharedPreferences setting = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(key, value);
        return editor.commit();
    }
    public static String getString(Context context,String key) {
        return getString(context, key,null);
    }

    private static String getString(Context context, String key, String defValue) {
        SharedPreferences setting = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return setting.getString(key, defValue);
    }
}
