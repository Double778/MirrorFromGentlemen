package com.zhao.mirrorfromgentleman.ui.activity.buydetails;

import android.view.View;
import android.widget.ImageView;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;

/**
 * 此页面是编辑收件人地址页面
 * Created by ${巴为焱} on 16/6/18.
 */
@BindContent(R.layout.activity_modifiaddress)
public class ModifiedAddressActivity extends BaseActivity {
    @BindView(R.id.aty_modifiadress_returniv)
    private ImageView returnImg;


    @Override
    public void initData() {

    }


    /**
     * 此方法中是所有组件的监听事件
     */
    @Override
    protected void setListener() {

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
