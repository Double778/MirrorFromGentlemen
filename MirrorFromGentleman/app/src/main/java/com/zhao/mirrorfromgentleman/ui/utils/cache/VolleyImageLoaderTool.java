package com.zhao.mirrorfromgentleman.ui.utils.cache;

import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.MyApplication;

/**
 * Created by 华哥哥 on 16/6/14.
 * Volley的ImageLoader工具类
 */
public class VolleyImageLoaderTool {
    private static VolleyImageLoaderTool instance;
    private RequestQueue queue;
    private static ImageLoader imageLoader;


    public VolleyImageLoaderTool() {
        queue = getQueue();
        imageLoader = new ImageLoader(queue, new DoubleCache());

    }

    public static void showImage(ImageView imageView, String url) {
        ImageLoader.ImageListener listener = getInstance().getImageLoader().getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        getInstance().getImageLoader().get(url, listener);
    }


    private ImageLoader getImageLoader() {
        return imageLoader;
    }

    public static VolleyImageLoaderTool getInstance() {
        if (instance == null) {
            synchronized (VolleyImageLoaderTool.class) {
                if (instance == null) {
                    instance = new VolleyImageLoaderTool();
                }
            }
        }
        return instance;
    }


    private RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(MyApplication.getContext());
        }
        return queue;
    }

}
