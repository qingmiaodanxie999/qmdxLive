package com.phoenix.qingmiaodanxie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoenix.qingmiaodanxie.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王东 on 2017/3/8.
 */

public class FragmentHome extends Fragment {
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.vp_view)
    ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private Fragment fragment1, fragment2;//页卡视图
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合
    private FragmentManager mFragmentManager;

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);
        mInflater = LayoutInflater.from(getActivity());
        mFragmentManager = getFragmentManager();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
//       view1 = mInflater.inflate(R.layout.fragment_jingxuan, null);
//       view2 = mInflater.inflate(R.layout.fragment_hot, null);
        fragment1 = FragmentJingxuan.newInstance();
        fragment2 = FragmentHot.newInstance();
       //添加页卡视图
       mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);

       //添加页卡标题
       mTitleList.add("精选");
       mTitleList.add("热门");

       mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
       MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(mFragmentManager,mFragmentList);
       mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
       mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.getTabAt(0).setText("精选");
        mTabLayout.getTabAt(1).setText("热门");
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
//       mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
    }
    //ViewPager适配器
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }
    }
