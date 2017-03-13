package com.phoenix.qingmiaodanxie.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.entity.LiveBean;
import com.phoenix.qingmiaodanxie.widget.MultiBaseAdapter;
import com.phoenix.qingmiaodanxie.widget.ViewHolder;

import java.util.List;
/**
 * @author wd
 * 这个是精选跟热门的适配器
 */

public class HottAdapter extends MultiBaseAdapter<LiveBean.ResultBean.ListBean> {
    private Context context;
    public HottAdapter(Context context, List<LiveBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
        setHaed(false);//false是无Banner true是有Banner
    }

    @Override
    protected void convert(ViewHolder holder, LiveBean.ResultBean.ListBean listBean, int viewType) {
        if (viewType == 0) {
            //如果返回0需要加载banner的布局
        } else {

            SimpleDraweeView imgDraweeView = holder.getView(R.id.jingxuan_img_sdv);
            String imgUrl=listBean.getUser().getUser_data().getAvatar();
            imgDraweeView.setImageURI(Uri.parse(imgUrl));
            String captureUrl = listBean.getData().getPic();
            SimpleDraweeView captureDraweeView =  holder.getView(R.id.jingxuan_captuer_sdv);
            captureDraweeView.setImageURI(Uri.parse(captureUrl));
            TextView nameTv=holder.getView(R.id.jingxuan_user_name);
            TextView placeTv=holder.getView(R.id.jingxuan_user_location);
            TextView stateTv=holder.getView(R.id.jingxuan_user_status);
            nameTv.setText(listBean.getUser().getUser_data().getUser_name());
            placeTv.setText("北京");
            int i = listBean.getData().getStatus();
            if (i == 0) {
                stateTv.setText("直播");
            } else if (i == 1) {
                stateTv.setText("重播");
            }

        }
    }


    @Override
    protected int getViewType(int position, LiveBean.ResultBean.ListBean data) {
        if (data == null) {
            return 1;
            //这里是有Banner 如果 setHaed(true) 需要返回0
        } else {
            return 1;
        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.list_item_jingxuan_;
    }

}
