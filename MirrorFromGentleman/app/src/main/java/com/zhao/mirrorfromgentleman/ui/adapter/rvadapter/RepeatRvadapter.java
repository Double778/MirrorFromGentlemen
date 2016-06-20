package com.zhao.mirrorfromgentleman.ui.adapter.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.Bean;

import java.util.List;

/**
 * Created by dllo on 16/6/14.
 */
public class RepeatRvadapter extends RecyclerView.Adapter<RepeatRvadapter.MyViewHolder> {

    private List<String> data;


    private List<Bean> bean;
    private Context context;

    private MyRvOnclickListener myRvOnclickListener;

    public void setMyRvOnclickListener(MyRvOnclickListener myRvOnclickListener) {
        this.myRvOnclickListener = myRvOnclickListener;
    }

    public void setBean(List<Bean> bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public RepeatRvadapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.frm_repeat_item_rv, null);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
        Log.d("RepeatRvadapter", data.get(position));
//        holder.textView.setText(bean.get(position).getData().getList().get(position).getGoods_price());
//        Log.d("RepeatRvadapter",bean.get(position).getData().getList().get(position).getGoods_price());
        if (myRvOnclickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.Rv_tv);
        }
    }
}
