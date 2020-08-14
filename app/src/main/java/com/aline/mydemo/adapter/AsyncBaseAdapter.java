package com.aline.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aline.mydemo.R;
import com.aline.mydemo.bean.NewsBean;
import com.aline.mydemo.utils.ImageLoader;

import java.util.List;


/**
 * @author SuTs
 * @create 2020/5/7 11:25
 * @Describe
 */
public class AsyncBaseAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<NewsBean.DataBean> datas;
    private Context mContext;
    private final LayoutInflater inflater;
    private ImageLoader mImageLoader;
    private int mStart;
    private int mEnd;
    public static String[] URLS;
    private boolean mFirstIn = true;

    public AsyncBaseAdapter(List<NewsBean.DataBean> datas, Context mContext, ListView recyclerView) {
        this.datas = datas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        mImageLoader = new ImageLoader(recyclerView);
        URLS = new String[datas.size()];
        //记录所有图片的URL
        for (int i = 0; i < datas.size(); i++) {
            URLS[i] = datas.get(i).getPicSmall();
        }
        //注册事件
        recyclerView.setOnScrollListener(this);
    }

    //滚动的时候不加载，停下来再加载
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //停下来的时候加载可见项
            mImageLoader.loadIamgesWhenVisibility(mStart, mEnd);
        } else {
            //停止加载
            mImageLoader.cancleAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当前可见项
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次加载时调用
        if (mFirstIn && visibleItemCount > 0) {
            mImageLoader.loadIamgesWhenVisibility(mStart, mEnd);
            mFirstIn = false;
        }
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsBean.DataBean dataBean = datas.get(position);
        AsyncBaseViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_async_load, null);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder = new AsyncBaseViewHolder();
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.ivHead = convertView.findViewById(R.id.iv_head);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AsyncBaseViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(dataBean.getDescription());
        viewHolder.tvTitle.setText(dataBean.getName());
        viewHolder.ivHead.setTag(dataBean.getPicSmall());
//        new ImageLoader().showImageByThread(asyncViewHolder.ivHead, dataBean.getPicSmall());
        mImageLoader.showImageByAsync(viewHolder.ivHead, dataBean.getPicSmall());
//        mImageLoader.loadIamgesWhenVisibility(mStart, mEnd);
        return convertView;
    }

    class AsyncBaseViewHolder {
        ImageView ivHead;
        TextView tvTitle;
        TextView tvContent;

    }


}
