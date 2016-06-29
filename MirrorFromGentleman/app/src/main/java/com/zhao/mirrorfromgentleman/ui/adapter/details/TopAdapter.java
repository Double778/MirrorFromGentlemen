package com.zhao.mirrorfromgentleman.ui.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.GlassesInforBean;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 华哥哥 on 16/6/18.
 */
public class TopAdapter extends BaseAdapter{
    private GlassesInforBean glassesInforBean;
    private Context context;

    public TopAdapter(Context context) {
        this.context = context;
    }

    public void setGlassesInforBean(GlassesInforBean glassesInforBean) {
        this.glassesInforBean = glassesInforBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return glassesInforBean.getData().getGoods_data().size();
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

        holder.nameTv.setText(glassesInforBean.getData().getGoods_data().get(position).getName());
        holder.contentTv.setText(glassesInforBean.getData().getGoods_data().get(position).getIntroContent());
        holder.country.setText(glassesInforBean.getData().getGoods_data().get(position).getCountry());
        holder.location.setText(glassesInforBean.getData().getGoods_data().get(position).getLocation());
        holder.english.setText(glassesInforBean.getData().getGoods_data().get(position).getEnglish());

        return convertView;
    }
    class ViewHolder{
        TextView nameTv;
        TextView contentTv;
        TextView location;
        TextView country;
        TextView english;

        public ViewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_details_top_name_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_details_top_content_tv);
            location = (TextView) itemView.findViewById(R.id.item_details_top_location_tv);
            country = (TextView) itemView.findViewById(R.id.item_details_top_country_tv);
            english  = (TextView) itemView.findViewById(R.id.item_details_top_english_tv);

        }
    }
}
