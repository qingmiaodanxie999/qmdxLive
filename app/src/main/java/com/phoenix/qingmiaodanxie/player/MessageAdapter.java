package com.phoenix.qingmiaodanxie.player;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phoenix.qingmiaodanxie.R;

import java.util.List;


public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    private List<String> data;

    public MessageAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void NotifyAdapter(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_message, null);
            holder.tvcontent = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        // TODO: 2017/3/16 发送消息内容设置
        String str = data.get(position);
        String newStr1 = str.substring(0,str.indexOf(":"));
        String newStr2 = str.substring(str.indexOf(":"),str.length());
        holder.tvcontent.setText(Html.fromHtml("<font color='#eb4f38'>" +
                newStr1+"</font>"+" "+newStr2), TextView.BufferType.SPANNABLE);
        return convertView;
    }
    //改变字体颜色的方法
   /* public void showTotalPrice() {
        float total = getTotalPrice();
        textView.setText(Html.fromHtml("<font color='#eb4f38'>" +
                "￥" + total + "</font>"), TextView.BufferType.SPANNABLE);
        SpannableString sp = new SpannableString("合计 ￥" + total);
        sp.setSpan(new ForegroundColorSpan(0xFFeb4f38), 3, sp.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(sp);
    }*/
    private final class ViewHolder {
        TextView tvcontent;
    }
}