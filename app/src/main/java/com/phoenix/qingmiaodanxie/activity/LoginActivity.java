package com.phoenix.qingmiaodanxie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.entity.Login;
import com.phoenix.qingmiaodanxie.http.Contants;
import com.phoenix.qingmiaodanxie.utils.SharedUtils;
import com.phoenix.qingmiaodanxie.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends Activity {
    @BindView(R.id.act_login_tel)
    EditText act_login_tel;
    @BindView(R.id.act_login_password)
    EditText act_login_password;
    @BindView(R.id.act_login_btn)
    Button act_login_btn;
    private String telGet="",telTxt="";
    private String passGet="",passTxt="";
    private OkHttpUtils httpUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
//        getSendData();
//        setDataToET();
    }

    private void saveData() {
        SharedUtils.saveUser(this,telTxt,passTxt);
    }


    private void setDataToET() {
//        act_login_tel.setText(telGet);
//        act_login_password.setText(passGet);
        telTxt = act_login_tel.getText().toString().trim();
        passTxt = act_login_password.getText().toString().trim();
    }

    @OnClick(R.id.act_login_btn)
    public void onClickLogin(View view) {
        switch (view.getId()) {
            case R.id.act_login_btn:
                requestLoginData();
                break;
        }
    }

    private void requestLoginData() {
        setDataToET();
        Log.e("TAG","telTxt=="+telTxt);
        Log.e("TAG","passTxt=="+passTxt);
        if (!"".equals(telTxt) && !"".equals(passTxt)) {
            saveData();
            request();
        } else {
            ToastUtils.show(this,"账号密码不能为空");
        }
    }

    private void request() {
        String url = Contants.API.LOGIN;
        Log.e("TAG", "url=====-=" + url);
        httpUtils = OkHttpUtils.getInstance();
        httpUtils.post()
                .url(url)
                .addParams("phone", telTxt)
                .addParams("password", passTxt)
                .build()
                .execute(new UserCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onResponse(Login response, int id) {
                        if (response != null) {
                            ToastUtils.show(LoginActivity.this, "登录成功");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.show(LoginActivity.this, "登录失败");
                            Log.e("TAG", "onResponse response.isResult() is false");
                        }
                    }
                });
    }

    public void getSendData() {
        if (getIntent()!= null) {
            telGet = getIntent().getStringExtra("tel");
            passGet = getIntent().getStringExtra("password");
        }
    }

    public abstract class UserCallback extends Callback<Login> {
        @Override
        public Login parseNetworkResponse(Response response, int id) throws Exception {
            String string = response.body().string();
            Log.e("TAG","login接口接收的json数据==="+string);
            Login login = new Gson().fromJson(string, Login.class);
            return login;
        }
    }

}