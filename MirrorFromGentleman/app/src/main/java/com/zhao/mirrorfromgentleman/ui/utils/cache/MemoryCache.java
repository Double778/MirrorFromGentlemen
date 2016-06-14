package com.zhao.mirrorfromgentleman.ui.utils.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 华哥哥 on 16/6/14.
 * 内存缓存
 */
public class MemoryCache implements ImageLoader.ImageCache{

    private LruCache<String, Bitmap> caches;
    // LruCache 最近最少使用算法

    public MemoryCache() {
        super();
        // 手机的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 不能把手机的所有内存都给图片缓存
        // 除以8,除以1024转换为kb
        // 图片的大小一般都是xxKB,
        int maxSize = maxMemory / 8 / 1024;
        // 指定当前缓存所需要的最大内存
        caches = new LruCache<String, Bitmap>(maxSize){
            // 在该括号中 指定缓存的每一张图片多大,占多少内存

            @Override
            protected int sizeOf(String url, Bitmap bitmap) {
                // 每一行有多少字节 * 高度 / 1024 转换为kb
                return bitmap.getRowBytes()
                        * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return caches.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        caches.put(url, bitmap);
    }
}
