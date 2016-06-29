package com.zhao.mirrorfromgentleman.ui.activity;

import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.GlassesInforBean;
import com.zhao.mirrorfromgentleman.model.bean.ProductInforBean;
import com.zhao.mirrorfromgentleman.ui.MyApplication;
import com.zhao.mirrorfromgentleman.ui.activity.adornimage.AdornImageActivity;
import com.zhao.mirrorfromgentleman.ui.activity.adornimage.WearShowActivity;
import com.zhao.mirrorfromgentleman.ui.activity.buydetails.OrderDetailsActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.details.BottomAdapter;
import com.zhao.mirrorfromgentleman.ui.adapter.details.TopAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.cache.VolleyImageLoaderTool;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.ScreenUtils;
import com.zhao.mirrorfromgentleman.view.NoScrollListView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 华哥哥 on 16/6/17.
 * 二级界面(商品详情)
 */
@BindContent(R.layout.activity_details)
public class DetailActivity extends BaseActivity implements AbsListView.OnScrollListener, View.OnClickListener {
    @BindView(R.id.activity_details_background_iv)
    private ImageView background;
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
    private ImageView shareIv;

    private TextView goodsName;
    private TextView goodsBrand;
    private TextView goodsInfor;
    private TextView goodsPrice;

    private GlassesInforBean glassesInforBean;
    private BottomAdapter bottomAdapter = new BottomAdapter(this);
    private TopAdapter topAdapter = new TopAdapter(this);

    @Override
    public void initData() {

        glassesInforBean = readFile("productDetails.txt");
        VolleyImageLoaderTool.showImage(background, glassesInforBean.getData().getGoods_img());
        // 一开始就利用属性动画将底部功能栏滑出屏幕
        showObjectAnimator(0, -ScreenUtils.getScreenHeight(MyApplication.getContext()), 0);

        // 底层ListView头布局
        View view = LayoutInflater.from(this).inflate(R.layout.head_view_activity_detail, null);

        goodsName = (TextView) view.findViewById(R.id.head_view_activity_detail_name_tv);
        goodsBrand = (TextView) view.findViewById(R.id.head_view_activity_detail_brand_tv);
        goodsInfor = (TextView) view.findViewById(R.id.head_view_activity_detail_content_tv);
        goodsPrice = (TextView) view.findViewById(R.id.head_view_activity_detail_price_tv);
        setHeadDate();
        // 底层ListView头布局中全透明部分
        AutoFrameLayout frameLayout = (AutoFrameLayout) view.findViewById(R.id.head_view_activity_detail_null_fl);


        shareIv = (ImageView) view.findViewById(R.id.head_view_activity_detail_share_iv);
        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        frameLayout.setMinimumHeight(ScreenUtils.getScreenHeight(MyApplication.getContext()));

        // 表层ListView头布局 只为了把其挤出屏幕
        ImageView nullImage = new ImageView(this);
        nullImage.setMinimumHeight((int) (ScreenUtils.getScreenHeight(MyApplication.getContext()) * 2));
        nullImage.setMinimumWidth(ScreenUtils.getScreenWidth(MyApplication.getContext()));
        nullImage.setAlpha(0f);

        // 下面的listview
        bottomLv.addHeaderView(view);
        //
        noScrollListView.addHeaderView(nullImage);

        bottomAdapter.setGlassesInforBean(glassesInforBean);
        bottomLv.setAdapter(bottomAdapter);

        topAdapter.setGlassesInforBean(glassesInforBean);
        noScrollListView.setAdapter(topAdapter);

    }

    private void setHeadDate() {
        Log.d("DetailActivity", glassesInforBean.getData().getGoods_name());

        goodsName.setText(glassesInforBean.getData().getGoods_name());
        goodsBrand.setText(glassesInforBean.getData().getBrand());
        goodsInfor.setText(glassesInforBean.getData().getInfo_des());
        goodsPrice.setText(glassesInforBean.getData().getGoods_price());
    }

    //分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();

        oks.disableSSOWhenAuthorize();


        oks.setTitleUrl("http://sharesdk.cn");

        oks.setText("我是分享文本");

        oks.setUrl("http://sharesdk.cn");

        oks.setComment("我是测试评论文本");

        oks.setSite(getString(R.string.app_name));

        oks.setSiteUrl("http://sharesdk.cn");

        oks.setTitle("美若");

        oks.setTitleUrl("http://www.liwushuo.com/items/");
        oks.setText("美若");
//        oks.setImageUrl("");

        oks.show(this);
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
     * @param totalItemCount   全部Item个数
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem >= 1) {
            // 当第一个可见的Item >=1 时, 让表层ListView滑动
            noScrollListView.setScrollY((int) (getScrollY(bottomLv) * 1.05));
        }


        if (firstVisibleItem == 0) {
            // 当第一个可见的Item为0时, 将底部功能栏滑出屏幕
            showObjectAnimator(0, -ScreenUtils.getScreenHeight(MyApplication.getContext()), 0);

        } else {
            // 当底部功能栏的X位置为-768 将其滑入屏幕
            if (bottomRl.getX() == -ScreenUtils.getScreenHeight(MyApplication.getContext())) {
                showObjectAnimator(-ScreenUtils.getScreenHeight(MyApplication.getContext()), 0, 300);
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
        switch (v.getId()) {
            case R.id.activity_details_back_iv:
                finish();
                break;
            case R.id.activity_details_bug_iv:
                startActivity(new Intent(this, OrderDetailsActivity.class));
                break;
            case R.id.activity_details_wear_images_btn:
                startActivity(new Intent(this, WearShowActivity.class));
                break;
        }
    }


    private GlassesInforBean readFile(String pathName) {
        // 定义结果
        String result = new String();
        try {
            // 很据文件名获取Assets文件夹下的文件
            InputStream is = getResources().getAssets().open(pathName);// 打开一个文件名
            // 读流
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            // 关流（先关流，在使用数据）
            br.close();
            ir.close();
            is.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Gson gson = new Gson();
        GlassesInforBean glassesInforBean = gson.fromJson(result, GlassesInforBean.class);
        Log.d("DetailActivity######", glassesInforBean.getData().toString());
        // 返回结果
        return glassesInforBean;
    }

}
