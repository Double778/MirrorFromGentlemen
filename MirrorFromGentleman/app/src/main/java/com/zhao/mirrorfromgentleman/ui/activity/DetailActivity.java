package com.zhao.mirrorfromgentleman.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.activity.adornimage.AdornImageActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.details.BottomAdapter;
import com.zhao.mirrorfromgentleman.ui.adapter.details.TopAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;
import com.zhao.mirrorfromgentleman.view.NoScrollListView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by 华哥哥 on 16/6/17.
 * 二级界面(商品详情)
 */
@BindContent(R.layout.activity_details)
public class DetailActivity extends BaseActivity implements AbsListView.OnScrollListener, View.OnClickListener {
    // 底层的ListView
    @BindView(R.id.activity_details_bottom_lv)
    private ListView bottomLv;
    // 表层不能手动滑动的ListView
    @BindView(R.id.activity_details_top_lv)
    private NoScrollListView noScrollListView;

    // 界面底部的功能栏布局
    @BindView(R.id.activity_details_bottom_rl)
    private AutoRelativeLayout bottomRl;

    @BindView(R.id.activity_details_back_iv)
    private ImageView closeIv;
    @BindView(R.id.activity_details_wear_images_btn)
    private Button wearImagesBtn;
    @BindView(R.id.activity_details_bug_iv)
    private ImageView bugIv;


    @Override
    public void initData() {

        // 一开始就利用属性动画将底部功能栏滑出屏幕
        showObjectAnimator(0, -768, 0);


        // 底层ListView头布局
        View view = LayoutInflater.from(this).inflate(R.layout.head_view_activity_detail, null);
        // 底层ListView头布局中全透明部分
        AutoFrameLayout frameLayout = (AutoFrameLayout) view.findViewById(R.id.head_view_activity_detail_null_fl);
        frameLayout.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext()));

        // 表层ListView头布局 只为了把其挤出屏幕
        ImageView nullImage = new ImageView(this);
        nullImage.setMinimumHeight((int) (ScreenUtils.getScreenHeight(MyApplication.getContext()) * 2));
        nullImage.setMinimumWidth(ScreenUtils.getScreenWidth(MyApplication.getContext()));
        nullImage.setAlpha(0f);

        bottomLv.addHeaderView(view);
        noScrollListView.addHeaderView(nullImage);

        bottomLv.setAdapter(new BottomAdapter(this));
        noScrollListView.setAdapter(new TopAdapter(this));

    }

    @Override
    protected void setListener() {
        bottomLv.setOnScrollListener(this);
        bugIv.setOnClickListener(this);
        closeIv.setOnClickListener(this);
        wearImagesBtn.setOnClickListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 底层ListView 滑动监听
     *
     * @param view
     * @param firstVisibleItem 第一个可见的Item
     * @param visibleItemCount 屏幕中可见的Item个数
     * @param totalItemCount 全部Item个数
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem >= 1) {
            // 当第一个可见的Item >=1 时, 让表层ListView滑动
            noScrollListView.setScrollY((int) (getScrollY(bottomLv) * 1.2));
        }


        if (firstVisibleItem ==0) {
            // 当第一个可见的Item为0时, 将底部功能栏滑出屏幕
            showObjectAnimator(0, -768, 0);

        } else {
            // 当底部功能栏的X位置为-768 将其滑入屏幕
            if (bottomRl.getX() == -768) {
                showObjectAnimator(-768, 0, 300);
            }

        }
    }


    // 获取滑动距离方法
    public int getScrollY(ListView l) {
        View v = l.getChildAt(0);
        if (v == null) {
            return 0;
        }
        int firstVisibleItem = l.getFirstVisiblePosition();
        int top = v.getTop();
        return -top + firstVisibleItem * v.getHeight();
    }


    /**
     * 让底部功能栏滑动的属性动画
     *
     * @param fromX
     * @param toX
     * @param time
     */
    public void showObjectAnimator(float fromX, float toX, long time) {
        ObjectAnimator.ofFloat(bottomRl, "translationX", fromX, toX).setDuration(time).start();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_details_back_iv:
                finish();
                break;
            case R.id.activity_details_bug_iv:
                // TODO 跳转购买详情

                break;
            case R.id.activity_details_wear_images_btn:
                startActivity(new Intent(this, AdornImageActivity.class));
                break;
        }
    }
}
