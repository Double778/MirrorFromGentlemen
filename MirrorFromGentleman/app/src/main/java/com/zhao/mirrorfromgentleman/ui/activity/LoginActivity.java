package com.zhao.mirrorfromgentleman.ui.activity;

import android.content.Intent;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;
import com.squareup.okhttp.Request;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.RegisteredBean;
import com.zhao.mirrorfromgentleman.model.net.OkHttpClientManager;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.usedtools.SPUtils;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by 旭哥哥 on 16/6/17.
 */
@BindContent(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_exit_iv)
    ImageView longExitIv;
    @BindView(R.id.create_account_btn)
    Button createBtn;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.weibo_icon_Iv)
    ImageView weiboIv;
    @BindView(R.id.weixin_icon_Iv)
    ImageView QQIv;


    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {

        ShareSDK.initSDK(this);
        longExitIv.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (phoneEt.length() > 0 && passwordEt.length() > 0) {
                    loginBtn.setBackgroundColor(getResources().getColor(R.color.colorclicklogin));
                } else {
                    loginBtn.setBackgroundColor(getResources().getColor(R.color.colorlogin));
                }

            }
        };
        phoneEt.addTextChangedListener(textWatcher);
        passwordEt.addTextChangedListener(textWatcher);
        weiboIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);

                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                weibo.authorize();
            }
        });
        QQIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform QQ = ShareSDK.getPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
                QQ.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                QQ.authorize();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Map<String, String> params = new HashMap<>();
        switch (v.getId()) {
            case R.id.login_exit_iv:
                finish();
                break;
            case R.id.create_account_btn:
                Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                params.put("phone_number", phoneEt.getText().toString());
                params.put("password", passwordEt.getText().toString());

                //判断
                boolean phoneNumber = isMobileNO(phoneEt.getText().toString());
                if (phoneNumber == true) {
                    OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/login", new OkHttpClientManager.ResultCallback<RegisteredBean>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Toast.makeText(LoginActivity.this, "發送失敗,請開啟網絡", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(RegisteredBean response) {
                            String text = "我的購物車";
                            if (response.getResult().toString().equals("1")) {
                                Toast.makeText(LoginActivity.this, "登錄成功", Toast.LENGTH_SHORT).show();
                                SPUtils.put(LoginActivity.this, "TextChange", text);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, response.getMsg().toString(), Toast.LENGTH_SHORT).show();

                            }

                        }


                    }, params);
                } else {
                    Toast.makeText(this, "您輸入的電話號碼不合法", Toast.LENGTH_SHORT).show();
                }
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
