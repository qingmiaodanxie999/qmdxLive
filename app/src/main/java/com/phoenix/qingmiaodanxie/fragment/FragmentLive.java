package com.phoenix.qingmiaodanxie.fragment;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoenix.qingmiaodanxie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王东 on 2017/3/8.
 */
public class FragmentLive extends Fragment {
    @BindView(R.id.camera_preview)
    GLSurfaceView mCameraPreview;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
