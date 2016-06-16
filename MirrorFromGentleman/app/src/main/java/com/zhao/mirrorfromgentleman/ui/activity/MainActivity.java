package com.zhao.mirrorfromgentleman.ui.activity;

import android.support.v4.app.Fragment;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.adapter.pageradapter.MyViewPagerAdapter;
import com.zhao.mirrorfromgentleman.ui.fragment.RepeatFragment;
import com.zhao.mirrorfromgentleman.ui.fragment.ShoppingFragment;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 旭哥哥 on 16/6/15.
 */
@BindContent(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RepeatFragment.ControlViewpager {
    @BindView(R.id.main_vp)
    private VerticalViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<Fragment> fragments;
    private RepeatFragment repeatFragment;
    private String titles[] ={"浏览所有分类","浏览平光镜","浏览太阳镜","专题分享","我的购物车","浏览所有分类"};

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {
        initFragment();
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(myViewPagerAdapter);
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            repeatFragment = new RepeatFragment(titles[i]);
            //在这个Frm中通过set出内部类定义的接口
//            repeatFragment.setControlViewpager(this);
            fragments.add(repeatFragment);
        }




    }

    //接口回调
    @Override
    public void control(int pos) {
        viewPager.setCurrentItem(pos);
    }
}
