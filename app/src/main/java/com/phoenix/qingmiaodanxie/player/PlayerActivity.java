package com.phoenix.qingmiaodanxie.player;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.phoenix.qingmiaodanxie.R;
public class PlayerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_player);
        PlayerFragment liveViewFragment = new PlayerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fra_player, liveViewFragment).commit();
        FunFragment funFragment = FunFragment.newInstance();
        funFragment.show(getSupportFragmentManager(), "fun");

    }


}