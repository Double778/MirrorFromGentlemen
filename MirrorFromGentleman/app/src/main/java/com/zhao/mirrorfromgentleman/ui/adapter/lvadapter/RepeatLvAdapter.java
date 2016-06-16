package com.zhao.mirrorfromgentleman.ui.adapter.lvadapter;

import android.content.Context;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.MyData;

import java.util.List;

/**
 * Created by dllo on 16/6/15.
 */
public class RepeatLvAdapter extends CommonAdapter<MyData> {

    public RepeatLvAdapter(Context mContext, List<MyData> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, MyData myData) {
        TextView titlesTv;
        titlesTv = holder.getView(R.id.frm_repeat_item_tv);
        titlesTv.setText(myData.getName());
    }


}
