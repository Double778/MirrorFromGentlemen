package com.zhao.mirrorfromgentleman.ui.activity;

import android.os.CountDownTimer;
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
 * Created by dllo on 16/6/17.
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

                params.put("phone_number", createPhoneEt.getText().toString());

                OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/send_code", new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(CreateActivity.this, "發送失敗,請開啟網絡", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();

                        timer = new CountDownTimer(59000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                sendTv.setText(millisUntilFinished / 1000 + "s後重新發送");
                                sendTv.setClickable(false);

                            }

                            @Override
                            public void onFinish() {
                                sendTv.setText("發送驗證碼");
                                sendTv.setClickable(true);
                                timer.cancel();

                            }
                        }.start();
                    }

                }, params);
                break;

            case R.id.create_new_account_btn:

                param.put("phone_number", createPhoneEt.getText().toString());
                param.put("number", createValidationEt.getText().toString());
                param.put("password", createNewPasswordEt.getText().toString());

                OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/reg", new OkHttpClientManager.ResultCallback<RegisteredBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(CreateActivity.this, "發送失敗,請開啟網絡", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisteredBean response) {
                        if (response.getResult().toString().equals("1")){
                            Toast.makeText(CreateActivity.this, "您以成功註冊", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(CreateActivity.this, response.getMsg().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                },param);
                break;
        }
    }
}
