package com.zhao.mirrorfromgentleman.ui.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.adapter.details.BottomAdapter;
import com.zhao.mirrorfromgentleman.ui.adapter.details.TopAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.cache.VolleyImageLoaderTool;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;
import com.zhao.mirrorfromgentleman.view.NoScrollListView;
import com.zhao.mirrorfromgentleman.view.NoScrollScrollView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by 华哥哥 on 16/6/17.
 */
@BindContent(R.layout.activity_details)
public class DetailActivity extends BaseActivity implements AbsListView.OnScrollListener {
    @BindView(R.id.activity_details_bottom_lv)
    private ListView bottomLv;
    @BindView(R.id.activity_details_top_lv)
    private NoScrollListView noScrollListView;
    @BindView(R.id.activity_details_sv)
    private NoScrollScrollView noScrollScrollView;

    @Override
    public void initData() {
        bottomLv.setAdapter(new BottomAdapter(this));
        noScrollListView.setAdapter(new TopAdapter(this));

        View view = LayoutInflater.from(this).inflate(R.layout.head_view_activity_detail, null);
        AutoFrameLayout frameLayout = (AutoFrameLayout) view.findViewById(R.id.head_view_activity_detail_null_fl);
        frameLayout.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext()));

        ImageView nullImage = new ImageView(this);
        nullImage.setMinimumHeight((int) (ScreenUtils.getScreenHeight(MyApplication.getContext())*2));
        nullImage.setMinimumWidth(ScreenUtils.getScreenWidth(MyApplication.getContext()));
        nullImage.setAlpha(0f);
        bottomLv.addHeaderView(view);
        //bottomLv.addHeaderView(nullImage);
        noScrollListView.addHeaderView(nullImage);
        // noScrollListView.addHeaderView(nullImage);


    }

    @Override
    protected void setListener() {
        bottomLv.setOnScrollListener(this);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem >= 1) {
            Log.d("DetailActivity", "firstVisibleItem:" + firstVisibleItem);
            noScrollListView.setScrollY((int) (getScrollY(bottomLv) * 1.2));

        }


    }


    // 获取滑动距离
    public int getScrollY(ListView l) {
        View v = l.getChildAt(0);
        if (v == null) {
            return 0;
        }
        int firstVisibleItem = l.getFirstVisiblePosition();
        int top = v.getTop();
        return -top + firstVisibleItem * v.getHeight();
    }

}
