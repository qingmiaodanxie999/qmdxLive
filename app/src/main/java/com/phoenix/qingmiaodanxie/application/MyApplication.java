package com.phoenix.qingmiaodanxie.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 王东 on 2017/3/8.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        Fresco.initialize(this);
    }
}
