package com.phoenix.qingmiaodanxie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王东 on 2017/2/22.
 */

public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    protected final Context mContext;
    protected int mLayoutResId;
    protected List<T> mDatas;
    private OnItemClickListener mOnItemClickListener=null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public BaseAdapter(Context context, int layoutResId, List<T> datas) {
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId,parent,false);
        return new BaseViewHolder(view,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T item = getItem(position);
        convert((H) holder, item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void addData(List<T> datas) {
        addData(0,datas);
    }
    public void addData(int position,List<T> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }
    }
    public void clearData() {
        mDatas.clear();
        notifyItemRangeRemoved(0,mDatas.size());
    }
    public void refreshData(List<T> list) {
        if (list != null && list.size() > 0) {
            clearData();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                mDatas.add(i, list.get(i));
                notifyItemChanged(i);
            }
        }
    }
    public void loadMoreData(List<T> list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            int begin = mDatas.size();
            for (int i = 0; i < size; i++) {
                mDatas.add(list.get(i));
                notifyItemChanged(i+begin);
            }
        }
    }
    protected abstract void convert(H holder, T item);

    public T getItem(int position) {
        return position >= mDatas.size() ? null : mDatas.get(position);
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
}
