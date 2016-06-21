package com.zhao.mirrorfromgentleman.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;

/**
 * Created by dllo on 16/6/21.
 */
@BindContent(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.aty_wel_iv)
    ImageView imageView;
    @BindView(R.id.aty_wel_tv)
    TextView textView;
    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {
        imageView.setImageResource(R.mipmap.welcome);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timer.start();
    }

    CountDownTimer timer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            textView.setText("跳转");
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    }.start();
}
