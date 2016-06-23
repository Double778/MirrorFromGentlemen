package com.zhao.mirrorfromgentleman.ui.activity.buydetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;

/**
 * 此页面是添加收件人地址页面
 * 在此页面可以添加收件人的姓名 地址 手机号
 * 并将填好的地址提交
 * Created by ${巴为焱} on 16/6/18.
 */
@BindContent(R.layout.activity_addaddress)
public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.aty_addadress_nameet)
    private EditText nameEt;
    @BindView(R.id.aty_addadress_numet)
    private EditText numEt;
    @BindView(R.id.aty_addadress_adresset)
    private EditText addressEt;
    @BindView(R.id.aty_addadress_retruniv)
    private ImageView returnImg;
    @BindView(R.id.aty_addadress_presentbtn)
    private Button presentBtn;


    @Override
    public void initData() {

    }


    /**
     * 此方法是各个组件的监听事件
     */
    @Override
    protected void setListener() {

        //返回按钮的监听
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置提交地址的监听
        presentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                String num = numEt.getText().toString();
                String address = addressEt.getText().toString();

                if (name.length() == 0 || num.length() == 0 || address.length() == 0) {
                    Toast.makeText(AddAddressActivity.this, "请填写信息", Toast.LENGTH_SHORT).show();
                }
                if (name.length() != 0 && num.length() != 0 && address.length() != 0) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("information", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("name", name);
//                    editor.putString("num", num);
//                    editor.putString("address", address);
//                    editor.commit();
                    Toast.makeText(AddAddressActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddAddressActivity.this, MyAllAddressActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
