package com.zhao.mirrorfromgentleman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by ${巴为焱} on 16/6/14.
 */
public class AdornVideoView extends VideoView {

    public AdornVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AdornVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdornVideoView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        this.getHolder().setFixedSize(width,height);
        setMeasuredDimension(width, height);
    }

}
