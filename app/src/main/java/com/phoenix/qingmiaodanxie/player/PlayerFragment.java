package com.phoenix.qingmiaodanxie.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.application.MyApplication;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerFragment extends Fragment {
//    private String mDataSource = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    private String mDataSource = "rtmp://cncplay.bingdou.tv/live/";
    private String liveId;
    // 播放器的对象
    private KSYMediaPlayer ksyMediaPlayer;
    // 播放SDK提供的监听器
    // 播放器在准备完成，可以开播时会发出onPrepared回调
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    // 播放完成时会发出onCompletion回调
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    // 播放器遇到错误时会发出onError回调
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangeListener;
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener;
    // SurfaceView需在Layout中定义，此处不在赘述
    @BindView(R.id.frg_player_surface)
    SurfaceView mVideoSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private  SurfaceHolder.Callback mSurfaceCallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_palyer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        liveId = getActivity().getIntent().getStringExtra("liveId");
        mDataSource += liveId;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void init(){
        ksyMediaPlayer = new KSYMediaPlayer.Builder(MyApplication.getContext()).build();
        initSurCallback();
        initPlayerListener();
        mSurfaceHolder = mVideoSurfaceView.getHolder();
        mVideoSurfaceView.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(mSurfaceCallback);
    }

    private void initPlayerListener() {
        mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                if (ksyMediaPlayer != null) {
                    // 设置视频伸缩模式，此模式为裁剪模式
                    ksyMediaPlayer.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                    // 开始播放视频
                    ksyMediaPlayer.start();
                }
            }
        };
        mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                // 播放完成，用户可选择释放播放器
                if(ksyMediaPlayer != null) {
                    ksyMediaPlayer.stop();
                    ksyMediaPlayer.release();
                }
            }
        };
        ksyMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        ksyMediaPlayer.setOnPreparedListener(mOnPreparedListener);
        ksyMediaPlayer.setOnInfoListener(mOnInfoListener);
        ksyMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        ksyMediaPlayer.setOnErrorListener(mOnErrorListener);
        ksyMediaPlayer.setOnSeekCompleteListener(mOnSeekCompletedListener);

        //设置 surfaceView点击监听
        mVideoSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (ksyMediaPlayer.isPlaying()) {
                            ksyMediaPlayer.pause();
                        } else {
                            ksyMediaPlayer.start();
                        }
                        break;
                }
                //返回True代表事件已经处理了
                return true;
            }
        });
    }

    private void initSurCallback() {
        mSurfaceCallback= new SurfaceHolder.Callback() {
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    ksyMediaPlayer.reset();
                    if (ksyMediaPlayer != null) {
                        ksyMediaPlayer.setDisplay(holder);
                        ksyMediaPlayer.setScreenOnWhilePlaying(true);
                        ksyMediaPlayer.setDataSource(mDataSource);
                        //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                        //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                        //mediaPlayer.prepare();
                        //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                        ksyMediaPlayer.prepareAsync();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 此处非常重要，必须调用!!!
                if(ksyMediaPlayer != null) {
                    ksyMediaPlayer.setDisplay(null);
                    ksyMediaPlayer.release();
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ksyMediaPlayer != null && ksyMediaPlayer.isPlaying()){
            initSurCallback();
            ksyMediaPlayer.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(ksyMediaPlayer != null){
            ksyMediaPlayer.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(ksyMediaPlayer != null){
            ksyMediaPlayer.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ksyMediaPlayer.setDisplay(null);
        ksyMediaPlayer.release();

    }
}