package com.phoenix.qingmiaodanxie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.adapter.JXAdapter;
import com.phoenix.qingmiaodanxie.entity.Live;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王东 on 2017/3/8.
 */
public class FragmentHot extends Fragment {
    @BindView(R.id.hot_rv)
    RecyclerView mRecyclerView;//控件对应的ID
    @BindView(R.id.hot_mrl)
    MaterialRefreshLayout mRefreshLayout;
    private Context mContext;
    private JXAdapter mAdapter;
    private List<Live>  mLives = new ArrayList<>();
    public static FragmentHot newInstance() {
        FragmentHot fragment = new FragmentHot();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot,container,false);
        ButterKnife.bind(this,view);
        mContext = getActivity();
        initTestData();
        return view;
    }
    private  void initTestData(){
        for (int i = 0; i < 5; i++) {
            Live live = new Live();
            live.name ="啦啦啦啦";
            live.head = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489052733292&di=e77762cd7687af72f7fe6cc175c353a7&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fd50735fae6cd7b89f4327ac50a2442a7d9330e1d.jpg";
            live.place = "金星";
            mLives.add(live);
        }
    }
//    private void initData() {
//        mAdapter = new JXAdapter(mContext,mLives);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getContext(),DividerItemDecoration.VERTICAL_LIST));
//    }
}
