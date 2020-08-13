package com.aline.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aline.mydemo.R;
import com.aline.mydemo.bean.DataBean;

import java.util.List;


/**
 * 日期:2018/3/9
 * <p>
 * 作者:xudiwei
 * <p>
 * 描述:
 */
public class StickItemAdapter extends RecyclerView.Adapter<StickItemAdapter.MyHolder> {
    private List<DataBean> mList;

    public StickItemAdapter(Context context, List<DataBean> list) {
        this.mList = list;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);

        return new MyHolder(item);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        MyHolder viewHolder = (MyHolder) holder;
        viewHolder.mTextView.setText(mList.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
