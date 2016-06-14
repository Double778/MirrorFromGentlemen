package com.zhao.mirrorfromgentleman.ui.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 华哥哥 on 16/6/14.
 * 磁盘缓存
 */
public class DiskMemory implements ImageLoader.ImageCache{

    private static final String path = "/sdcard/Download/";

    @Override
    public Bitmap getBitmap(String url) {
        return BitmapFactory.
                decodeFile(path + url + ".jpg");
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        File file = new File(path);
        // 如果该文件不存在
        if (!file.exists()){
            file.mkdir();// 创建
        }
        File imgFile = new File(path, url + ".jpg");

        if (!imgFile.exists()){
            try {
                // 如果图片不存在,创建该文件
                imgFile.createNewFile();
                // 利用输出流将图片存储到内存
                FileOutputStream fos =
                        new FileOutputStream(imgFile);
                // 压缩,设置格式和品质,输出流是该bitmap要去的方向
                bitmap.compress
                        (Bitmap.CompressFormat.JPEG,
                                100, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
