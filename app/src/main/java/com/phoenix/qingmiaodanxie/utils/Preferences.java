package com.phoenix.qingmiaodanxie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.phoenix.qingmiaodanxie.application.MyApplication;
import com.phoenix.qingmiaodanxie.entity.User;

/**
 * Created by 王东 on 2017/3/16.
 */

public final class Preferences {
    public static SharedPreferences sSHARED_REFERENCES = null;
    private static Context sAPPLICATION_CONTEXT;
    private static String sDEVICE_ID;
    private static String sVERSION_NAME;
    private static long sUSER_ID = -1;// 说明没有初始化


    public static void init() {

        if (sSHARED_REFERENCES == null) {
            sAPPLICATION_CONTEXT = MyApplication.getContext();
            sSHARED_REFERENCES = PreferenceManager
                    .getDefaultSharedPreferences(sAPPLICATION_CONTEXT);
        }
    }

    public static boolean isLogin() {
        return getAccountId() > 0 ? true : false;
    }

    public static boolean isShowWelcome() {
        try {
            PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                    .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(), 0);
            // 当前版本的版本号
            int versionCode = info.versionCode;
            int saveVersionCode = sSHARED_REFERENCES.getInt("version_code", 0);
            return versionCode != saveVersionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return true;
        }
    }


    public static boolean updateVersionCode() {
        init();
        try {
            PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                    .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(), 0);
            // 当前版本的版本号
            int versionCode = info.versionCode;
            SharedPreferences.Editor editor = sSHARED_REFERENCES.edit();
            editor.putInt("version_code", versionCode);
            return editor.commit();
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static SharedPreferences getSharedPreferences() {
        return sSHARED_REFERENCES;
    }

    public static String getDeviceId() {
        if (sDEVICE_ID == null) {
            sDEVICE_ID = ((TelephonyManager) sAPPLICATION_CONTEXT.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }
        return sDEVICE_ID;
    }

    public static String getVersionName() {
        if (sVERSION_NAME == null) {
            try {
                PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                        .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(),
                                0);
                // 当前应用的版本名称
                sVERSION_NAME = info.versionName;
                // 当前版本的版本号
                // int versionCode = info.versionCode;

                // 当前版本的包名
                // String packageNames = info.packageName;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sVERSION_NAME;
    }


    /**
     * 获取当前登录的用户
     *
     * @return 如果已经登录返回User，否则返回null
     */
    public static User getAccountUser() {
        User user = new User();
        sUSER_ID = sSHARED_REFERENCES.getLong("user_id", 0);
        if (sUSER_ID == 0) {
            return null;
        }
        String gsonUser=sSHARED_REFERENCES.getString("user_json", "");
        user = GsonUtils.fromJson(gsonUser, User.class);
        return user;
//        JSONObject jsonObject;
//                  jsonObject = new JSONObject(sSHARED_REFERENCES.getString(
//                          "user_json", null));
//        user.setToken(sSHARED_REFERENCES.getString("access_token", null));
//        user.setId(sSHARED_REFERENCES.getLong("user_id", 0));
//        user.setPhone(sSHARED_REFERENCES.getString("user_phone","0"));
//        user.setPassword(sSHARED_REFERENCES.getString("user_pass","0"));
//        user.setUser_name(sSHARED_REFERENCES.getString("bind_name", null));
    }

    public static boolean setAccountUser(User user, String accessToken) {
        init();
        SharedPreferences.Editor editor = sSHARED_REFERENCES.edit();
        editor.putString("access_token", accessToken);
        editor.putLong("user_id", user.getId());
        editor.putString("user_json", GsonUtils.toJson(user));

        // TODO json format save
        if (editor.commit()) {
            sUSER_ID = user.getId();
            return true;
        }
        return false;
    }


    public static boolean setDeviceToken(String device_token){
        init();
        SharedPreferences.Editor editor = sSHARED_REFERENCES.edit();
        editor.putString("device_token", device_token);
        if(editor.commit()){
            return true;
        }
        return false;

    }

    public static String getDeviceToken(){
        String device_token = sSHARED_REFERENCES.getString("device_token", null);
        return device_token;
    }

    public static boolean userClear() {
        SharedPreferences.Editor editor = sSHARED_REFERENCES.edit();
        editor.putString("access_token", "");
        editor.putLong("user_id", 0);
        editor.putString("create_time", "");
        // TODO json format save
        if (editor.commit()) {
            sUSER_ID = 0;
            return true;
        }
        return false;
    }
    public static boolean updateAccountUser(User user) {
        init();
        SharedPreferences.Editor editor = sSHARED_REFERENCES.edit();
        editor.putLong("user_id", user.getId());
        editor.putString("user_json", GsonUtils.toJson(user));
        if (editor.commit()) {
            Log.e("TAG","sp保存用户成功-保存的id====="+sSHARED_REFERENCES.getLong("user_id",0));
            sUSER_ID = user.getId();
            return true;
        }
        return false;
    }

    /**
     * 获取登录用户的ID
     *
     * @return 如果已登录返回用户ID，否则返回0
     */
    public static long getAccountId() {
        init();
        if (sUSER_ID == -1) {
            sUSER_ID = sSHARED_REFERENCES.getLong("user_id", 0);
        }
        return sUSER_ID;
    }
    public static String getToken() {
        return sSHARED_REFERENCES.getString("access_token", null);
    }

}
