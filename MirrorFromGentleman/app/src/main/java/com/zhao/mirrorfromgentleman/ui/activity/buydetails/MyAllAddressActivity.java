package com.zhao.mirrorfromgentleman.ui.activity.buydetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.buydetails.MyAllAddressAdapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.SwipListView;

import java.util.ArrayList;
import java.util.List;

import static com.zhao.mirrorfromgentleman.R.layout.item_address;

/**
 * 此页面是显示收件人所有收货地址页面
 * 此页面可以点击添加地址按钮 进入添加地址页面
 * 收货地址信息可以实现修改 删除功能
 * Created by ${巴为焱} on 16/6/18.
 */
@BindContent(R.layout.activity_myalladress)
public class MyAllAddressActivity extends BaseActivity {
    @BindView(R.id.aty_myaddress_btn)
    private Button addAddressBtn;
    @BindView(R.id.aty_myaddress_lv)
    private SwipListView addressLv;
    @BindView(R.id.aty_myaddress_returniv)
    private ImageView returnImg;
    private List<String> addressBeen = new ArrayList<>();

    @Override
    public void initData() {
        AddressAdapter addressAdapter = new AddressAdapter(this);

        for (int i = 0; i < 20; i++) {
            addressBeen.add("花花");
        }
        addressLv.setAdapter(addressAdapter);
        addressLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyAllAddressActivity.this, "现在的位置是:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 此方法中是各个组件的监听事件
     */
    @Override
    protected void setListener() {
        //添加地址按钮的监听
        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAllAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        //返回按钮的监听
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class AddressAdapter extends MyAllAddressAdapter {
        private List<String> datas;

        public AddressAdapter(Context context) {
            super(context);
        }

        public void setDatas(List<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return addressBeen == null ? 0 : addressBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return addressBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        @Override
        protected View generateLeftView(int position) {
            LinearLayout layout = new LinearLayout(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(lp);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_address, layout, false);
//            TextView nameTv, addressTv, numTv;
//            nameTv = (TextView) view.findViewById(R.id.address_item_recipient_name);
//            addressTv = (TextView) view.findViewById(R.id.address_item_address);
//            nameTv.setText("花花");
//            nameTv.setPadding(0, 30, 10, 30);
            ImageView changeImg;
            changeImg = (ImageView) view.findViewById(R.id.address_change_img);
            changeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyAllAddressActivity.this, ModifiedAddressActivity.class);
                    startActivity(intent);
                }
            });

            return view;
        }

        @Override
        protected View generateRightView(final int position) {
            LinearLayout layout = new LinearLayout(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(lp);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_delete, layout, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "del " + addressBeen.get(position), Toast.LENGTH_SHORT).show();
                    addressBeen.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
