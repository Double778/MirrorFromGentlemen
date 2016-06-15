package com.zhao.mirrorfromgentleman.ui.adapter.adornimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhao.mirrorfromgentleman.R;

import java.util.List;

/**
 * Created by ${巴为焱} on 16/6/14.
 */
public class AdornImageAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> imgs;

    public AdornImageAdapter(Context context) {
        this.context = context;
    }

    public void setImgs(List<Integer> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adornimg_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.adornImg.setImageResource(imgs.get(position));
        return convertView;
    }


    class ViewHolder {
        ImageView adornImg;

        public ViewHolder(View view) {
            adornImg = (ImageView) view.findViewById(R.id.item_adornimg_iv);
        }
    }
}
