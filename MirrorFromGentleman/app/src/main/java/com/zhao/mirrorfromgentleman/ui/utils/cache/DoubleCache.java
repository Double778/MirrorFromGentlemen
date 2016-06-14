package com.zhao.mirrorfromgentleman.ui.utils.cache;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 华哥哥 on 16/6/14.
 * 二级缓存
 */
public class DoubleCache implements ImageLoader.ImageCache{

    private ImageLoader.ImageCache memoryCache;
    private ImageLoader.ImageCache diskCache;

    public DoubleCache() {
        super();
        memoryCache = new MemoryCache();
        diskCache = new DiskMemory();
    }
    @Override
    public Bitmap getBitmap(String url) {
        if (memoryCache != null) {
            return memoryCache.getBitmap(url);
        }
        return diskCache.getBitmap(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        memoryCache.putBitmap(url, bitmap);
        diskCache.putBitmap(url, bitmap);
    }
}
