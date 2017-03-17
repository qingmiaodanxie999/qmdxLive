package com.phoenix.qingmiaodanxie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.adapter.HottAdapter;
import com.phoenix.qingmiaodanxie.entity.LiveBean;
import com.phoenix.qingmiaodanxie.http.Contants;
import com.phoenix.qingmiaodanxie.http.UserCallback;
import com.phoenix.qingmiaodanxie.player.PlayerActivity;
import com.phoenix.qingmiaodanxie.widget.OnLoadMoreListener;
import com.phoenix.qingmiaodanxie.widget.OnMultiItemClickListeners;
import com.phoenix.qingmiaodanxie.widget.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 王东 on 2017/3/8.
 */
public class FragmentJingxuan extends Fragment {
    @BindView(R.id.jingxuan_rv)
    RecyclerView mRecyclerView;//控件对应的ID
    @BindView(R.id.jingxuan_swipe)
    SwipeRefreshLayout favSwipe;
    private Context mContext;
    private HottAdapter mAdapter;
    OkHttpUtils httpUtils;
    private List<LiveBean.ResultBean.ListBean> mListBeen;
    private List<LiveBean> loadlist = new ArrayList<>();
    private int page = 1;
    public static FragmentJingxuan newInstance() {
        FragmentJingxuan fragment = new FragmentJingxuan();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jingxuan, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        httpUtils = OkHttpUtils.getInstance();
        mListBeen = new ArrayList<>();
        initRefreshView();
    }

    private void initRefreshView() {
        favSwipe.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary));
        favSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getFavLive(page);

            }
        });
        //初始化adapter
        mAdapter = new HottAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                getFavLive(page);
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<LiveBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, LiveBean.ResultBean.ListBean data, int position, int viewType) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                long liveId= data.getId();
                intent.putExtra("liveId", liveId+"");
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        getFavLive(page);
    }

    private void getFavLive(final int page) {
        String url = Contants.API.JINGXUAN;
        Log.e("TAG", "url=====-=" + url);
        httpUtils.post()
                .url(url)
                .addParams("type", "0")
                .addParams("page", page + "")
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(LiveBean.ResultBean response, int id) {
                        mListBeen = response.getList();
                        if (page == 1) {
                            mAdapter.setNewData(mListBeen);
                            favSwipe.setRefreshing(false);
                            mAdapter.setLoadingView(R.layout.load_more_layout);
                        } else {
                            if (mListBeen != null && mListBeen.size() > 0) {
                                mAdapter.setLoadMoreData(response.getList());
                            } else {
                                mAdapter.setLoadEndView(R.layout.load_end_layout);
                            }
                        }
                    }
                });
    }

}
