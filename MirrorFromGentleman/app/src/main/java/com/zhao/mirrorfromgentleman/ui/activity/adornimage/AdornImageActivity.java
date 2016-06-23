package com.zhao.mirrorfromgentleman.ui.activity.adornimage;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.adornimage.AdornImageAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.AdornImageUtil;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.CommonVideoView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情展示页
 * Created by ${巴为焱} on 16/6/14.
 */
@BindContent(R.layout.activity_adornimage)
public class AdornImageActivity extends BaseActivity {

    @BindView(R.id.aty_adornimg_lv)
    private ListView adornImgLv;
    private List<String> imgs;
    private AdornImageAdapter adornImageAdapter;
    private CommonVideoView adornVideoView;
    public static final String SDCARD_PATH = Environment.getExternalStorageState().toString();
    public static final String IMAGES_FOLDER = SDCARD_PATH + File.separator + "demo" +
            File.separator + "images" + File.separator;

    @Override
    public void initData() {
        //给listView添加头布局
        View view = LayoutInflater.from(this).inflate(R.layout.item_commonvideoview, null);
        adornVideoView = (CommonVideoView) view.findViewById(R.id.item_commonvv);
        adornImgLv.addHeaderView(view);
       //设定播放器
        adornVideoView.start("http://7xr7f7.com2.z0.glb.qiniucdn.com/Jimmy%20fairly%20-%20Spring%202014-HD.mp4");

        adornImageAdapter = new AdornImageAdapter(this);

        imgs = new ArrayList<>();
        for (int i = 0;i<15;i++){
            imgs.add(AdornImageUtil.getBmpUrl());
        }
        adornImageAdapter.setImgs(imgs);
        adornImgLv.setAdapter(adornImageAdapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "1:" + 1, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "巴神好帅。。", Toast.LENGTH_SHORT).show();
            adornVideoView.setFullScreen();
        } else {
            adornVideoView.setNormalScreen();
        }
    }

    @Override
    protected void setListener() {

    }

}
