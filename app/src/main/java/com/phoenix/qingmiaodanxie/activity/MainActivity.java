package com.phoenix.qingmiaodanxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.fragment.FragmentHome;
import com.phoenix.qingmiaodanxie.fragment.FragmentWo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.content)
    ViewPager content;
    @BindView(R.id.main_bottom_tabs)
    RadioGroup group;
    @BindView(R.id.main_home)
    RadioButton main_home;
    @BindView(R.id.main_wode)
    RadioButton main_wode;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mFragments.add(FragmentHome.newInstance());
        mFragments.add(FragmentWo.newInstance());
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        content.setAdapter(mAdapter);
    }

    @OnClick({R.id.main_home, R.id.main_wode,R.id.main_live})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_home:
                content.setCurrentItem(0);
                break;
            case R.id.main_wode:
                content.setCurrentItem(1);
                break;
            case R.id.main_live:
                startActivity(new Intent(MainActivity.this,CameraActivity.class));
                break;
        }
    }
}
