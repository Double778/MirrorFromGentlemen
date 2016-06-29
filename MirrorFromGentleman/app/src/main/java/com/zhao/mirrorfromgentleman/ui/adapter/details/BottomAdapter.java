package com.zhao.mirrorfromgentleman.ui.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.GlassesInforBean;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.utils.cache.VolleyImageLoaderTool;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;

/**
 * Created by 华哥哥 on 16/6/18.
 */
public class BottomAdapter extends BaseAdapter {
    private GlassesInforBean glassesInforBean;
    private Context context;

    public BottomAdapter(Context context) {
        this.context = context;
    }

    public void setGlassesInforBean(GlassesInforBean glassesInforBean) {
        this.glassesInforBean = glassesInforBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return glassesInforBean.getData().getDesign_des().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_bottom_lv, parent, false);
            convertView.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext()) / 3 * 2);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext()) / 3 * 2);
        VolleyImageLoaderTool.showImage(holder.imageView, glassesInforBean.getData().getDesign_des().get(position).getImg());
//        holder.imageView.setMinimumWidth(ScreenUtils.getScreenWidth(MyApplication.getContext()));
//
//        holder.imageView.setImageResource(imgs.get(position));
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.item_details_bottom_iv);

        }
    }


}
