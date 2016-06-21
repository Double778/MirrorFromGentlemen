package com.zhao.mirrorfromgentleman.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.adapter.pageradapter.MyViewPagerAdapter;
import com.zhao.mirrorfromgentleman.ui.fragment.RepeatFragment;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.SPUtils;
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
        @BindView(R.id.login_tv)
        TextView loginTv;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<Fragment> fragments;
    private RepeatFragment repeatFragment;
    private String shoppingTv;

    private String titles[] ={"瀏覽所有分類","瀏覽平光鏡","瀏覽太陽鏡","專題分享","我的購物車","瀏覽所有分類"};

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {
        initFragment();
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(myViewPagerAdapter);

        shoppingTv = (String) SPUtils.get(MainActivity.this, "TextChange", "");
        if (shoppingTv.equals("")) {
            loginTv.setText("登錄");
        } else {
            loginTv.setText(shoppingTv);
        }
        if (loginTv.getText().toString().equals("登錄")) {

            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else if (loginTv.getText().toString().equals("我的購物車")) {
            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager.setCurrentItem(4);
                }
            });
        }
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


    @Override
    protected void onResume() {
        super.onResume();

        shoppingTv = (String) SPUtils.get(MainActivity.this, "TextChange", "");
        if (shoppingTv.equals("")) {
            loginTv.setText("登錄");
        } else {
            loginTv.setText(shoppingTv);
        }
        if (loginTv.getText().toString().equals("登錄")) {

            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else if (loginTv.getText().toString().equals("我的購物車")) {
            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager.setCurrentItem(4);
                }
            });
        }
    }

}
