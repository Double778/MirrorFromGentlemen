package com.zhao.okhttpdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // 定义OkHttp
    private OkHttpClient client;
    private TextView textView;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                String s = (String) msg.obj;
                textView.setText(s);
            }
            return false;
        }
    });

    /**
     * Request 请求 利用url网址创建一个网络请求
     * Response 响应
     * RequestBody 请求体
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        client = new OkHttpClient();
        // 由于OKHttp等同于HttpUrlConnection
        // 因此需要在线程中执行
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    syncGet();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        // 异步get请求
        //enqueue();
       // withHeaderGet();
        postRequest();
    }


    private String url = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c0-p1-s30.json";

    /**
     * 利用OKHttp完成同步get请求
     */
//    private void syncGet() throws IOException {
//        // 创建一个请求
//        Request request = new Request.Builder().url(url).build();
//        // 同步请求会返回响应, execute
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
////            response.message();
//            Looper.prepare();
//            // 在线程中将消息插入消息队列
//
//            Toast.makeText(this, response.body().string(), Toast.LENGTH_SHORT).show();
//            Looper.loop();
////            response.code();
//        }
//    }

    // 异步Get请求
    private void enqueue(){
        Request request = new Request.Builder().url(url).build();
        // 异步请求, CallBack里封装了线程
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    Looper.loop();

                    Log.d("MainActivity", response.message());
                    Log.d("MainActivity", "response.code():" + response.code());

                }
            }
        });
    }

    /**
     * get请求需要请求头
     */
    private void withHeaderGet() {
        String apiKey = "7ed1315843f4f062bbbcf33d57e3899b";
        String headUrl = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=15668696045";
        final Request request = new Request.Builder().url(headUrl).addHeader("apikey", apiKey).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            textView.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
    private void postRequest(){
        String postUrl = "http://api101.test.mirroreye.cn/index.php/products/goods_list";
        // 请求体
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("token", "");
        builder.add("device_type", "2");
        RequestBody body = builder.build();
        final Request request = new Request.Builder().url(postUrl).post(body).build();
        // 发出异步post请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message message = new Message();
                message.what = 1;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
    }


}
