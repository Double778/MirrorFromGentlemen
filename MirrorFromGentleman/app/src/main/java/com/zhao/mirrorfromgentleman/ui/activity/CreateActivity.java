package com.zhao.mirrorfromgentleman.ui.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.RegisteredBean;
import com.zhao.mirrorfromgentleman.model.net.OkHttpClientManager;
import com.zhao.mirrorfromgentleman.ui.activity.BaseActivity;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 旭哥哥 on 16/6/17.
 */
@BindContent(R.layout.activity_create)
public class CreateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.send_tv)
    TextView sendTv;
    @BindView(R.id.create_phone_et)
    EditText createPhoneEt;
    @BindView(R.id.create_exit_iv)
    ImageView ExitIV;
    @BindView(R.id.create_new_account_btn)
    Button createNewBtn;
    @BindView(R.id.create_validation_et)
    EditText createValidationEt;
    @BindView(R.id.create_new_password_et)
    EditText createNewPasswordEt;

    private CountDownTimer timer;


    @Override
    protected void setListener() {
    }

    @Override
    public void initData() {
        ExitIV.setOnClickListener(this);
        sendTv.setOnClickListener(this);
        createNewBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        switch (v.getId()) {
            case R.id.create_exit_iv:
                finish();
                break;
            case R.id.send_tv:
                //判断是否为电话号码调用本类方法
                boolean phoneNumber = isMobileNO(createPhoneEt.getText().toString());
                if (phoneNumber == true) {
                    params.put("phone_number", createPhoneEt.getText().toString());

                    OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/send_code", new OkHttpClientManager.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Toast.makeText(CreateActivity.this, "發送失敗,請開啟網絡", Toast.LENGTH_SHORT).show();
                        }

                        //如果发送成功会弹出吐司 这个Okhttp给泛型是String
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(CreateActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                            //通过判断这个timer改变时间来控制TextView的点击状态 如果没走完60s 不可以再次点击
                            timer = new CountDownTimer(59000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    sendTv.setText(millisUntilFinished / 1000 + "s後重新發送");
                                    sendTv.setClickable(false);

                                }

                                //如果结束后会变回TextView的文字效果
                                @Override
                                public void onFinish() {
                                    sendTv.setText("發送驗證碼");
                                    sendTv.setClickable(true);
                                    timer.cancel();

                                }
                            }.start();
                        }

                    }, params);
                } else {
                    Toast.makeText(this, "您輸入的電話號碼不合法", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.create_new_account_btn:

                param.put("phone_number", createPhoneEt.getText().toString());
                param.put("number", createValidationEt.getText().toString());
                param.put("password", createNewPasswordEt.getText().toString());

                //注册时候的OkHttp的时候给了一个数据类 控制后面的
                OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/reg", new OkHttpClientManager.ResultCallback<RegisteredBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(CreateActivity.this, "發送失敗,請開啟網絡", Toast.LENGTH_SHORT).show();
                    }

                    //成功是会在服务器post回调一个码为1 加以判断
                    @Override
                    public void onResponse(RegisteredBean response) {
                        if (response.getResult().toString().equals("1")) {
                            Toast.makeText(CreateActivity.this, "您以成功註冊", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateActivity.this, response.getMsg().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                }, param);
                break;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
