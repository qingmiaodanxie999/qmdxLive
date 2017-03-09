package com.phoenix.qingmiaodanxie.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.phoenix.qingmiaodanxie.R;
import com.phoenix.qingmiaodanxie.entity.User;

import java.util.List;

/**
 * Created by 王东 on 2017/2/22.
 */

public class JXAdapter extends SimpleAdapter<User> {
    public JXAdapter(Context context, List<User> datas) {
        super(context, R.layout.list_item_jingxuan_, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, final User item) {
        SimpleDraweeView imgDraweeView = (SimpleDraweeView) holder.getView(R.id.jingxuan_img_sdv);
        imgDraweeView.setImageURI(Uri.parse(item.getHeadIcon()));
        SimpleDraweeView captureDraweeView = (SimpleDraweeView) holder.getView(R.id.jingxuan_captuer_sdv);
        captureDraweeView.setImageURI(Uri.parse(item.getInformationImage()));
        holder.getTextView(R.id.jingxuan_user_name).setText(item.getName());
        holder.getTextView(R.id.jingxuan_user_location).setText(item.getPlace());
        holder.getTextView(R.id.jingxuan_user_status).setText(item.getStatus());
    }
    public void resetLayout(int layoutId) {
        this.mLayoutResId = layoutId;
        notifyItemRangeChanged(0,getItemCount());
    }
}
