package com.zhao.mirrorfromgentleman.ui.adapter.rvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.Bean;
import com.zhao.mirrorfromgentleman.ui.utils.cache.VolleyImageLoaderTool;

/**
 * Created by dllo on 16/6/14.
 */
public class SungLassesRvadapter extends RecyclerView.Adapter<SungLassesRvadapter.MyViewHolder> {

    private Bean bean;
    private Context context;
    private MyRvOnclickListener myRvOnclickListener;

    public void setMyRvOnclickListener(MyRvOnclickListener myRvOnclickListener) {
        this.myRvOnclickListener = myRvOnclickListener;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public SungLassesRvadapter(Context context) {
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.frm_repeat_item_rv, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(bean.getData().getList().get(position).getGoods_price());
        VolleyImageLoaderTool.showImage(holder.iv, bean.getData().getList().get(position).getGoods_img());
        if (myRvOnclickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myRvOnclickListener.myOnclick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().getList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.Rv_tv);
            iv= (ImageView) itemView.findViewById(R.id.frm_repeat__item_iv);
        }
    }

    public interface MyRvOnclickListener {
        void myOnclick();
    }

}
