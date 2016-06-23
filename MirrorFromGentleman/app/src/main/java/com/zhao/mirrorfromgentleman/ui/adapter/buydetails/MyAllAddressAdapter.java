package com.zhao.mirrorfromgentleman.ui.adapter.buydetails;


/**
 * Created by ${巴为焱} on 16/6/21.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;

public abstract class MyAllAddressAdapter extends BaseAdapter {
    public Context mContext;

    public MyAllAddressAdapter(Context context) {
        this.mContext = context;
    }

    protected abstract View generateLeftView(final int position);

    protected abstract View generateRightView(final int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = new LinearLayout(mContext);
        convertView = layout;

        layout.addView(generateLeftView(position));
        layout.addView(generateRightView(position));
        return convertView;
    }

//    class ViewHolder {
//        TextView nameTv, addressTv, numTv;
//
//        public ViewHolder(View view) {
//            nameTv = (TextView) view.findViewById(R.id.address_item_recipient_name);
//            addressTv = (TextView) view.findViewById(R.id.address_item_address);
//            numTv = (TextView) view.findViewById(R.id.address_item_tel);
//        }
//    }
}
