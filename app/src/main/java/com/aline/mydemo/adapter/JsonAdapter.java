package com.aline.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aline.mydemo.R;

import java.util.List;

/**
 * @author SuTs
 * @create 2020/4/28 9:27
 * @Describe
 */
public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.JsonViewHolder> {

    private Context mContext;
    private List<String> datas;
    private final LayoutInflater inflater;

    public JsonAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.datas = datas;
    }

    @Override
    public JsonAdapter.JsonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JsonViewHolder(inflater.inflate(R.layout.item_json, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JsonViewHolder holder, int position) {
        holder.tvJson.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class JsonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJson;

        public JsonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJson = itemView.findViewById(R.id.tv_show_json);
        }
    }
}
