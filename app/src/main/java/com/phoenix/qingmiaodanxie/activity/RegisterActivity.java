package com.phoenix.qingmiaodanxie.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.entity.Register;
import com.phoenix.qingmiaodanxie.http.Contants;
import com.phoenix.qingmiaodanxie.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends Activity {
    @BindView(R.id.act_regist_touxiang)
    CircleImageView act_regist_touxiang;
    @BindView(R.id.act_regist_tel)
    EditText act_regist_tel;
    @BindView(R.id.act_regist_name)
    EditText act_regist_name;
    @BindView(R.id.act_regist_password)
    EditText act_regist_password;
    @BindView(R.id.act_regist_password_repeat)
    EditText act_regist_password_repeat;
    @BindView(R.id.act_regist_btn)
    Button act_regist_btn;
    private String touxiang="";
    private String tel="";
    private String name="";
    private String pass="";
    private String passRep="";
    private OkHttpUtils httpUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_regist);
        ButterKnife.bind(this);
        initData();
        setAvatar();
    }
    private void requestRegisterData() {
        if(!"".equals(tel)&&!"".equals(name)&&!"".equals(pass)&&!"".equals(passRep)
                &&pass.equals(passRep)&&tel.length()==11&&"1".equals(tel.substring(0,1))) {
            request();
        }else {
            validate();
        }

    }

    private void request() {
        String url = Contants.API.REGIST;
        Log.e("TAG", "url=====-=" + url);
        httpUtils = OkHttpUtils.getInstance();
        httpUtils.post()
                .url(url)
                .addParams("phone", tel)
                .addParams("user_name", name)
                .addParams("avatar",touxiang)
                .addParams("sign", "这家伙很懒，什么都没留下")
                .addParams("password", pass)
                .build()
                .execute(new UserCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onResponse(Register response, int id) {
                        if (response != null) {
                            if (response.isResult()) {
                                ToastUtils.show(RegisterActivity.this, "注册成功");
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.putExtra("tel", tel);
                                intent.putExtra("password", pass);
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtils.show(RegisterActivity.this, "注册失败");
                                Log.e("TAG", "onResponse response.isResult() is false");
                            }
                        }else {
                            ToastUtils.show(RegisterActivity.this, "注册失败");
                            Log.e("TAG", "onResponse response is null");
                        }
                    }
                });

    }
    @OnClick(R.id.act_regist_btn)
    public void onClickRegist(View view) {
        switch (view.getId()) {
            case R.id.act_regist_btn:
                initData();
                requestRegisterData();
                break;
        }
    }

    private void setAvatar() {
        act_regist_touxiang.setImageURI(Uri.parse(touxiang));
    }

    private void initData() {
        touxiang = "http://ww1.sinaimg.cn/orj480/9651d910gw1f6ateg975tj21kw0w0q9h.jpg";
        tel = act_regist_tel.getText().toString().trim();
        name = act_regist_name.getText().toString().trim();
        pass = act_regist_password.getText().toString().trim();
        passRep = act_regist_password_repeat.getText().toString().trim();
    }

    private void validate() {
        if ("".equals(tel)) {
            ToastUtils.show(this,"手机号不能为空");
            return;
        }
        if (!tel.startsWith("1")||tel.length()!=11) {
            ToastUtils.show(this,"你输入的手机号不合法");
        }
        if ("".equals(name)) {
            ToastUtils.show(this,"昵称不能为空");
            return;
        }
        if ("".equals(pass)) {
            ToastUtils.show(this,"密码不能为空");
            return;
        }
        if (!pass.equals(passRep)) {
            ToastUtils.show(this,"两次输入密码不一致");
            return;
        }

    }

    public abstract class UserCallback extends Callback<Register> {
        @Override
        public Register parseNetworkResponse(Response response, int id) throws Exception {
            String string = response.body().string();
            Log.e("TAG","Register接口接收的json数据==="+string);
            Register register = new Gson().fromJson(string, Register.class);
            Log.e("TAG", "from json register.isResult() "+register.isResult());
            return register;
        }
    }

}