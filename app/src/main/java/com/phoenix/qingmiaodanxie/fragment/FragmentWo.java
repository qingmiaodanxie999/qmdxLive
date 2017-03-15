package com.phoenix.qingmiaodanxie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.activity.LoginActivity;
import com.phoenix.qingmiaodanxie.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 王东 on 2017/3/8.
 */
public class FragmentWo extends Fragment {
    @BindView(R.id.mine_userlogin_btn)
    Button btn_log;
    @BindView(R.id.mine_reg_btn)
    Button btn_reg;
    public static FragmentWo newInstance() {
        FragmentWo fragment = new FragmentWo();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick({R.id.mine_userlogin_btn,R.id.mine_reg_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_userlogin_btn:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.mine_reg_btn:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
        }
    }
}
