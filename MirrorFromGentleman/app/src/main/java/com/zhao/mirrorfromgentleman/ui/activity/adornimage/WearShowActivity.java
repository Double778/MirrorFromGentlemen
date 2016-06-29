package com.zhao.mirrorfromgentleman.ui.activity.adornimage;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.showadpter.WearShowLvAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.CommonVideoView;

/**
 * Created by liangduo on 16/6/16.
 * 佩戴图集
 */

@BindContent(R.layout.activity_wear_show)
public class WearShowActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.wear_show_button_layout)
    private static RelativeLayout wearShowBtnLayout;
    @BindView(R.id.activity_wearshow)
    private RelativeLayout wearShowAty;
    @BindView(R.id.wear_show_lv)
    public static ListView wearShowLv;
    private int[] images = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private WearShowLvAdapter wearShowLvAdapter;

    private ImageView lvHeadVvIv;
    public static CommonVideoView lvHeadVv;
    public static ImageView lvHeadVvStartIv;
    private ImageView stopPlayIv;

    private PopupWindow wearImagePopupWindow;
    @BindView(R.id.wear_show_back)
    private ImageView back;
    @BindView(R.id.wear_show_buy)
    private ImageView buy;
    @BindView(R.id.personage_detail_title_tv)
    private TextView title;

    public static View head;
    @BindView(R.id.wear_show_title)
    public static LinearLayout titleLayout;

    private String videoUrl = "http://7xr7f7.com2.z0.glb.qiniucdn.com/Jimmy%20fairly%20-%20Spring%202014-HD.mp4";


    @Override
    public void initData() {
        title.setText("商品细节展示");
        back.setOnClickListener(this);
        buy.setOnClickListener(this);

        wearShowLv.setOnItemClickListener(this);

        head = LayoutInflater.from(this).inflate(R.layout.lv_head_show, null);
        lvHeadVvIv = (ImageView) head.findViewById(R.id.wear_show_head_iv);
        lvHeadVv = (CommonVideoView) head.findViewById(R.id.common_videoView);
        lvHeadVvStartIv = (ImageView) head.findViewById(R.id.wear_head_vv_start_iv);
        stopPlayIv = (ImageView) head.findViewById(R.id.wear_show_stop_play_iv);
        wearShowLv.addHeaderView(head);

        lvHeadVvStartIv.setOnClickListener(this);
        stopPlayIv.setOnClickListener(this);

        wearShowLvAdapter = new WearShowLvAdapter(this);
        wearShowLvAdapter.setImage(images);
        wearShowLv.setAdapter(wearShowLvAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wear_show_back:
                finish();
                break;
            case R.id.wear_show_buy:
//                Toast.makeText(this, "不要點我", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wear_head_vv_start_iv:
                stopPlayIv.setVisibility(View.VISIBLE);
                lvHeadVvStartIv.setVisibility(View.GONE);
                lvHeadVv.setVisibility(View.VISIBLE);
                lvHeadVv.start(videoUrl);
                break;
            case R.id.wear_show_stop_play_iv:
                lvHeadVv.setVisibility(View.GONE);
                stopPlayIv.setVisibility(View.GONE);
                lvHeadVvStartIv.setVisibility(View.VISIBLE);
                int i = getResources().getConfiguration().orientation;
                if (i == Configuration.ORIENTATION_PORTRAIT) {
                    lvHeadVv.stopPlayback();
                } else if (i == Configuration.ORIENTATION_LANDSCAPE) {
                     this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
                    lvHeadVv.stopPlayback();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
        Log.d("WearShowActivity", "走没走");
        if (position > 0) {
            showPopupWindow(position);
            wearImagePopupWindow.isShowing();
            wearImagePopupWindow.showAtLocation(wearShowAty, Gravity.CENTER, 0, 0);
        }
    }

    private void showPopupWindow(int pos) {
        wearImagePopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View v = LayoutInflater.from(this).inflate(R.layout.ppw_wear_show, null);
        ImageView popupImageView = (ImageView) v.findViewById(R.id.ppw_wear_show_iv);

        /**
         * 这是等以后有实际数据的时候用的
         */
//        int picture = (int) wearShowLv.getItemAtPosition(pos);

        wearImagePopupWindow.setContentView(v);
        popupImageView.setImageResource(images[pos - 1]);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wearImagePopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            lvHeadVv.setFullScreen();
        }else {
            lvHeadVv.setNormalScreen();
        }
    }

    public static ImageView getLvHeadVvStartIv(){
        return lvHeadVvStartIv;
    }

    public static  LinearLayout getTitleLayout(){
        return  titleLayout;
    }

    public static View getHead(){
        return head;
    }

    public static ListView getListView(){
        return wearShowLv;
    }

    public static RelativeLayout getWearShowBtnLayout(){
        return wearShowBtnLayout;
    }

    public static CommonVideoView getVideoView(){
        return lvHeadVv;
    }

    @Override
    protected void setListener() {

    }

}