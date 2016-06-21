package com.zhao.mirrorfromgentleman.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.SubjectBean;
import com.zhao.mirrorfromgentleman.ui.adapter.subject.SubjectAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 华哥哥 on 16/6/17.
 * 二级界面(专题页)
 */
@BindContent(R.layout.activity_subject)
public class SubjectActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.activity_subject_background_iv)
    private ImageView backgroundIv;
    @BindView(R.id.activity_subject_text_introduce_vp)
    private VerticalViewPager viewPager;
    @BindView(R.id.activity_subject_close_iv)
    private ImageView closeIv;
    @BindView(R.id.activity_subject_share_iv)
    private ImageView shareIv;

    @Override
    public void initData() {

        final List<SubjectBean> been = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            been.add(new SubjectBean("啊啊啊", "安徽省登记卡收费及客户说", "的雅思U盾雅思U盾和雅思U盾哈苏肯定会雅思复活点撒库房和一u收到货覅", "http://img7.9158.com/200709/01/11/53/200709018758949.jpg"));
            been.add(new SubjectBean("啊啊啊", "安徽省登记卡收费及客户说", "的雅思U盾雅思U盾和雅思U盾哈苏肯定会雅思复活点撒库房和一u收到货覅", "http://image.mirroreye.cn/chicunbf9e59473fa94566740337693a57d92b.jpg"));

        }

        SubjectAdapter adapter = new SubjectAdapter(this);
        adapter.setBeen(been);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ImageLoader.getInstance().displayImage(been.get(position).getUrl(), backgroundIv);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void setListener() {
        closeIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_subject_close_iv:
                finish();
                break;
            case R.id.activity_subject_share_iv:
                // TODO 分享

                break;
        }

    }
}
