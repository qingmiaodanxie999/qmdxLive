package com.phoenix.qingmiaodanxie.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoenix.qingmiaodanxie.R;
public class PlayerFragment extends Fragment {
    private String mDataSource = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_palyer, container, false);
    }
}