package com.zhao.mirrorfromgentleman.ui.adapter.subject;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.SubjectBean;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 华哥哥 on 16/6/17.
 */
public class SubjectAdapter extends PagerAdapter{
    private Context context;
    private List<SubjectBean> been;

    public void setBeen(List<SubjectBean> been) {
        this.been = been;
        notifyDataSetChanged();
    }

    public SubjectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return been == null? 0 :been.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_subject, container, false);
        TextView titleTv = (TextView) view.findViewById(R.id.page_subject_title_tv);
        TextView nameTv = (TextView) view.findViewById(R.id.page_subject_name_tv);
        TextView contentTv = (TextView) view.findViewById(R.id.page_subject_content_tv);
        titleTv.setText(been.get(position).getTitle());
        nameTv.setText(been.get(position).getName());
        contentTv.setText(been.get(position).getContent());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
