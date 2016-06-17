package com.zhao.mirrorfromgentleman.ui.adapter.adornimage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.adornimage.AdornImageDetailsActivity;
import com.zhao.mirrorfromgentleman.view.SmoothImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/6/14.
 */
public class AdornImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> imgs;

    public AdornImageAdapter(Context context) {
        this.context = context;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public Object getItem(int position) {
        return imgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(imgs.get(position), holder.smoothImageView);
        holder.smoothImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdornImageDetailsActivity.class);
                int[] location = new int[2];
                holder.smoothImageView.getLocationOnScreen(location);

//                intent.putExtra("othImageView", String.valueOf(imgs));
//                intent.putExtra("position", position);
                intent.putExtra("url", imgs.get(position));

               // Log.d("这个item的位置", "position:" + position);

                intent.putExtra("locationX", location[0]);
                intent.putExtra("locationY", location[1]);

                intent.putExtra("width", holder.smoothImageView.getWidth());
                intent.putExtra("height", holder.smoothImageView.getHeight());
                context.startActivity(intent);

            }
        });
        return convertView;
    }

    class ViewHolder {

        ImageView smoothImageView;

        public ViewHolder(View view) {
            smoothImageView = (ImageView) view.findViewById(R.id.img);
        }
    }
}
