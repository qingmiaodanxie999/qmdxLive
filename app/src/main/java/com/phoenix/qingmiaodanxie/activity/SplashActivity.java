package com.phoenix.qingmiaodanxie.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.entity.Login;
import com.phoenix.qingmiaodanxie.entity.User;
import com.phoenix.qingmiaodanxie.http.Contants;
import com.phoenix.qingmiaodanxie.permission.MPermission;
import com.phoenix.qingmiaodanxie.permission.OnMPermissionDenied;
import com.phoenix.qingmiaodanxie.permission.OnMPermissionGranted;
import com.phoenix.qingmiaodanxie.utils.Preferences;
import com.phoenix.qingmiaodanxie.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 王东 on 2017/3/12.
 */
public class SplashActivity extends BaseActivity{
    private static final long DELAY_MILLIS = 1 * 1000;
    private static final int MSG_WHAT_STARTMAIN = 1;
    public static String device_token;
    private Handler mHandler;


    private Animation alphaAnimation;
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;
    private OkHttpUtils httpUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        Preferences.setDeviceToken(device_token);
        setContentView(R.layout.act_splash);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        requestBasicPermission();
        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessageDelayed(MSG_WHAT_STARTMAIN, DELAY_MILLIS);
        if (Preferences.isLogin()) {
//          更新登陆信息
            getMyInfo();
        }
    }
    private void getMyInfo() {
        long id = Preferences.getAccountId();
        String token = Preferences.getToken();
        String url = Contants.API.LOGIN;
        Log.e("TAG", "url=====-=" + url);
        httpUtils = OkHttpUtils.getInstance();
        final String phone = Preferences.getAccountUser().getPhone();
        final String passTxt = Preferences.getAccountUser().getPassword();
        httpUtils.post()
                .url(url)
                .addParams("phone", phone)
                .addParams("password", passTxt)
                .build()
                .execute(new UserCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Login response, int id) {
                        try {
                            if (response != null) {
                                long uId = response.getResult().getId();
                                Preferences.updateAccountUser(new User(uId,phone,passTxt));
                                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                                intent.putExtra("phone",phone);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JsonIOException e) {
                            ToastUtils.show(SplashActivity.this,"JSON转换异常");
                        }
                    }
                });
    }

    /**
     * 请求权限
     * 因为是在6.0开发，这些权限需要手动去请求
     */
    private void requestBasicPermission() {
        MPermission.with(SplashActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        //延迟3秒进入主界面
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                /*if (!TextUtils.isEmpty(Preferences.getsessionId(SplashActivity.this))) {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }*/
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        }, 3000);

    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    private static class MyHandler extends Handler {
        WeakReference<SplashActivity> mActivity;

        MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity theActivity = mActivity.get();
            if (theActivity != null) {
                switch (msg.what) {
                    case MSG_WHAT_STARTMAIN:
                        if (Preferences.isLogin()) {
//                      更新版本信息
                            Preferences.updateVersionCode();
                            theActivity.startActivity(new Intent(theActivity,
                                    MainActivity.class));
                        } else {
                            theActivity.startActivity(new Intent(theActivity,
                                    LoginActivity.class));
                            Preferences.updateVersionCode();
                        }
                        theActivity.finish();
                        break;
                }
            }
        }
    }
    public abstract class UserCallback extends Callback<Login> {
        @Override
        public Login parseNetworkResponse(Response response, int id) throws Exception {
            String string = response.body().string();
            Login login = new Gson().fromJson(string, Login.class);
            return login;
        }
    }
}
