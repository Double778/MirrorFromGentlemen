package com.zhao.mirrorfromgentleman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by liangduo on 16/6/29.
 */
public class MyListView extends ListView {
    private boolean isTouch = true;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isTouch == false){
            return  isTouch;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }
}
