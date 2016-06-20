package com.zhao.mirrorfromgentleman.ui.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 华哥哥 on 16/6/18.
 */
public class TopAdapter extends BaseAdapter{
    private Context context;

    public TopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_top_lv,parent, false);
            convertView.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext())/3 *2);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        TextView nameTv;
        TextView contentTv;

        public ViewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_details_top_name_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_details_top_content_tv);

        }
    }
}
