package com.zhao.mirrorfromgentleman.ui.activity.adornimage;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.view.SmoothImageView;

import java.util.ArrayList;

/**
 * Created by ${巴为焱} on 16/6/16.
 */
public class AdornImageDetailsActivity extends BaseActivity {
    private ArrayList<String> mDatas;
    private int mPosition;
    private int mLocationX;
    private int mLocationY;
    private int mWidth;
    private int mHeight;
    SmoothImageView imageView = null;

    @Override
    public void initData() {
       // mDatas = (ArrayList<String>) getIntent().getSerializableExtra("images");
       // mPosition = getIntent().getIntExtra("position", 0);
        String url = getIntent().getStringExtra("url");
        mLocationX = getIntent().getIntExtra("locationX", 0);
        mLocationY = getIntent().getIntExtra("locationY", 0);
        mWidth = getIntent().getIntExtra("width", 0);
        mHeight = getIntent().getIntExtra("height", 0);

        imageView = new SmoothImageView(this);
        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(imageView);
        ImageLoader.getInstance().displayImage(url, imageView);

    }


    @Override
    public void onBackPressed() {
        imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    finish();
                }
            }
        });
        imageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void setListener() {

    }


}
