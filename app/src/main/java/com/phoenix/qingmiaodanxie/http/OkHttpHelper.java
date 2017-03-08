package com.phoenix.qingmiaodanxie.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 王东 on 2017/2/16.
 */

public class OkHttpHelper {
    private static OkHttpClient okHttpClient;
    private Gson mGson;
    private static OkHttpHelper mInstance;
    private Handler mHandler;
    static {
        mInstance = new OkHttpHelper();
    }
    private OkHttpHelper(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
        mGson=new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }
    public static OkHttpHelper getInstance(){
        return mInstance;
    }
    public void doRequest(final Request request, final BaseCallBack callBack){
        callBack.onRequestBefore(request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(request,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resultStr = response.body().string();
                    if (callBack.mType == String.class) {
//                        callBack.onSuccess(response, resultStr);
                        callbackSuccess(callBack,response,resultStr);
                    } else {
                        try {
                            Object object = mGson.fromJson(
                                    resultStr,callBack.mType);
                            callBack.onSuccess(response, object);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    callBack.onError(response,response.code(),null);
                }
//                mGson.fromJson(response.body().string(), callBack.mType);
                callBack.onResponse(response);
            }
        });
    }

    public void get(String url,BaseCallBack callBack) {
        Request request = buildRequest(url,null, HttpMethodType.GET);
        doRequest(request,callBack);
    }

    public void post(String url, Map<String, Object> params,BaseCallBack callBack) {
        Request request = buildRequest(url,params, HttpMethodType.POST);
        doRequest(request,callBack);
    }
    private Request buildRequest(String url,Map<String, Object> params,
                                 HttpMethodType methodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {
            RequestBody body = buildFormData(params);
            builder.post(body);
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.add(entry.getKey(),
                        entry.getValue()==null ? "" :entry.getValue().toString());
            }
        }
        return builder.build();
    }

    private void callbackSuccess(final BaseCallBack baseCallBack, final Response response,
                                 final Object object){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onSuccess(response,object);
            }
        });
    }
    private void callbackFailure(final BaseCallBack baseCallBack,final Request request){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onFailure(request,null);
            }
        });

    }
    private void callbackError(final BaseCallBack baseCallBack, final Response response,
                                 final Object object){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onError(response,response.code(),null);
            }
        });

    }
    enum HttpMethodType {
        GET,
        POST
    }
}
