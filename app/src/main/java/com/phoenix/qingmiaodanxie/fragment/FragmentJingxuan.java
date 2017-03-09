package com.phoenix.qingmiaodanxie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.adapter.JXAdapter;
import com.phoenix.qingmiaodanxie.decoration.DividerItemDecoration;
import com.phoenix.qingmiaodanxie.entity.JXObject;
import com.phoenix.qingmiaodanxie.entity.User;
import com.phoenix.qingmiaodanxie.http.BaseCallBack;
import com.phoenix.qingmiaodanxie.http.Contants;
import com.phoenix.qingmiaodanxie.http.OkHttpHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王东 on 2017/3/8.
 */
public class FragmentJingxuan extends Fragment {
    @BindView(R.id.jingxuan_rv)
    RecyclerView mRecyclerView;//控件对应的ID
    @BindView(R.id.jingxuan_mrl)
    MaterialRefreshLayout mRefreshLayout;
    private Context mContext;
    private JXAdapter mAdapter;
    private OkHttpHelper okHttpHelper;
    private List<User> mUsers = new ArrayList<>();
    public static FragmentJingxuan newInstance() {
        FragmentJingxuan fragment = new FragmentJingxuan();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jingxuan,container,false);
        ButterKnife.bind(this,view);
        mContext = getActivity();
        okHttpHelper = OkHttpHelper.getInstance();
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View v) {
        String url = Contants.API.JINGXUAN + "?curPage=2&pageSize=5";
        Log.e("TAG","url=====-="+url);
        okHttpHelper.get(url,
                new BaseCallBack<JXObject<User>>() {
                    @Override
                    public void onRequestBefore(Request request) {

                    }
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onSuccess(Response response, JXObject<User> jxObject) {
                        mUsers = jxObject.getList();
                        initData(mUsers);
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                    }

                    @Override
                    public void onResponse(Response response) {
                    }
                });
    }

    private void initData(List<User> users) {
        mAdapter = new JXAdapter(mContext,users);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getContext(),DividerItemDecoration.VERTICAL_LIST));
    }
}
