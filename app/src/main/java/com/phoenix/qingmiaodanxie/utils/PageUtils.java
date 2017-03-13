package com.phoenix.qingmiaodanxie.utils;

import android.content.Context;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.phoenix.qingmiaodanxie.entity.PageResult;
import com.phoenix.qingmiaodanxie.http.OkHttpHelper;
import com.phoenix.qingmiaodanxie.http.SpotsCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

/**
 * Created by 王东 on 2017/2/28.
 */

public class PageUtils {
    public static Builder builder;
        private OkHttpHelper okHttpHelper ;
    //    private HttpUtils httpUtils;
    private OkHttpUtils okHttpUtils;
    private static final int STATE_NORMAL = 0;//正常状态
    private static final int STATE_REFRESH = 1;//刷新状态
    private static final int STATE_MORE = 2;//加载跟多状态
    private int state = STATE_NORMAL;//默认状态是正常状态

    private PageUtils() {
        okHttpHelper= OkHttpHelper.getInstance();
        okHttpUtils = OkHttpUtils.getInstance();
        initRefreshLayout();
    }
    public static Builder newBuilder() {
        if (builder == null) {
            builder = new Builder();
        }
        return builder;
    }
    public void request() {
        requestData();
    }
    public void putParam(String key,Object value) {
        builder.putParam(key, value);
    }
    private void initRefreshLayout(){
        builder.refreshLayout.setLoadMore(builder.canLoadMore);
        builder.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();
            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if(builder.page<=builder.totalPage) {
                    loadMoreData();
                }else {
                    Toast.makeText(builder.mContext, "已经没有更多数据", Toast.LENGTH_SHORT).show();
                    builder.refreshLayout.finishRefreshLoadMore();
                }
            }
        });
    }
    private void refreshData() {
        builder.page = 1;
        state = STATE_REFRESH;
       requestData();
    }
    private void loadMoreData() {
        builder.page += 1;
        state = STATE_MORE;
        refreshData();
    }
    private void requestData() {
        okHttpHelper.get(buildUrl(),
                new RequestCallBack(builder.mContext));
    }
    private String buildUrl() {
        return builder.url + "?" + builderUrlParams();
    }
    private String builderUrlParams() {
        HashMap<String, Object> map = builder.params;
        map.put("type",builder.type);
        map.put("page",builder.page);
        StringBuffer sb = new StringBuffer();
        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    private <T> void showData(List<T> datas,int totalPage,int totalCount) {
        switch (state) {
            case STATE_NORMAL:
                if (builder.onPageListener != null) {
                    builder.onPageListener.load(datas,totalPage,totalCount);
                }
                break;
            case STATE_REFRESH:
               builder.refreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                builder.refreshLayout.finishRefreshLoadMore();
                break;
        }
    }
    public interface OnPageListener<T>{
        void load(List<T> datas, int totalPage, int totalCount);
        void refresh(List<T> datas, int totalPage, int totalCount);
        void loadMore(List<T> datas, int totalPage, int totalCount);
    }
    public static class Builder{
        private Context mContext;
        private Type type;
        private String url;
        private MaterialRefreshLayout refreshLayout;
        private boolean canLoadMore;
        private int totalPage = 1;
        private int type2 = 1;
        private int page = 1;
        private HashMap<String, Object> params = new HashMap<>(5);
        private OnPageListener onPageListener;
        public Builder setOnPageListener(OnPageListener listener) {
            this.onPageListener= listener;
            return builder;
        }
        public Builder setUrl(String url) {
            this.url = url;
            return builder;
        }
        public Builder setRefreshLayout(MaterialRefreshLayout refreshLayout) {
            this.refreshLayout = refreshLayout;
            return builder;
        }
        public Builder setLoadMore(boolean loadMore) {
            this.canLoadMore = loadMore;
            return builder;
        }
        public Builder setPageSize(int pageSize) {
            this.page = pageSize;
            return builder;
        }

        public PageUtils build(Context context,Type type) {
            this.mContext = context;
            this.type = type;
            validata();
            return new PageUtils();
        }
        private void validata() {
            if (mContext == null) {
                throw new RuntimeException("Context can't be null");
            }
            if (this.url == null || "".equals(this.url)) {
                throw new RuntimeException("URL can't be null");
            }
            if (this.refreshLayout == null) {
                throw new RuntimeException("MaterialRefreshLayout can't be null");
            }
        }
        public Builder putParam(String key, Object value) {
            params.put(key, value);
            return builder;
        }
    }
    class RequestCallBack<T> extends SpotsCallBack<PageResult<T>> {

        public RequestCallBack(Context mContext) {
            super(mContext);
            super.mType = builder.type;
        }

        @Override
        public void onSuccess(Response response, PageResult<T> tPageResult) {

            builder.type2 = tPageResult.getCurrentPage();
            builder.page = tPageResult.getTotalPage();
            showData(tPageResult.getList(),builder.totalPage,
                    tPageResult.getTotalCount());
        }

        @Override
        public void onError(Response response, int code, Exception e) {

        }
    }
}
