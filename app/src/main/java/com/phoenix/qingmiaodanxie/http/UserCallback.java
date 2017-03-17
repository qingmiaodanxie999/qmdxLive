package com.phoenix.qingmiaodanxie.http;

import android.util.Log;

import com.google.gson.Gson;
import com.phoenix.qingmiaodanxie.entity.LiveBean;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by 王东 on 2017/3/12.
 */

public abstract class UserCallback extends Callback<LiveBean.ResultBean> {
//
//    public Type mType;
//    /**
//     * 将泛型T转为Type
//     */
//    static Type getSuperclassTypeParameter(Class<?> subclass){
//        Type superclass = subclass.getGenericSuperclass();
//        if (superclass instanceof Class){
//            throw new RuntimeException("Missing type parameter.");
//        }
//        ParameterizedType parameterized = (ParameterizedType)superclass;
//        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
//    }
//
//    public UserCallback(){
//        mType = getSuperclassTypeParameter(getClass());
//    }
    @Override
    public LiveBean.ResultBean parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        Log.e("TAG","UserCallback.response========="+string);
        LiveBean liveBean = new Gson().fromJson(string, LiveBean.class);
        Log.e("TAG","PageResult========="+liveBean.getResult().getList().size());
        return liveBean.getResult();
    }

//    public abstract void onSuccess(Response response,LiveBean.ResultBean t);
}
