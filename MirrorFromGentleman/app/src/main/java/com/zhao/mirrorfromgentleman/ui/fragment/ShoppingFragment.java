package com.zhao.mirrorfromgentleman.ui.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.Bean;
import com.zhao.mirrorfromgentleman.model.bean.MyData;
import com.zhao.mirrorfromgentleman.model.net.OkHttpClientManager;
import com.zhao.mirrorfromgentleman.ui.activity.DetailActivity;
import com.zhao.mirrorfromgentleman.ui.adapter.lvadapter.RepeatLvAdapter;
import com.zhao.mirrorfromgentleman.ui.adapter.rvadapter.RepeatRvadapter;
import com.zhao.mirrorfromgentleman.ui.adapter.rvadapter.ShoppongRvadapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.view.SysApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 旭哥哥 on 16/6/15.
 * 同用一个Frm
 */
@BindContent(R.layout.frm_repeat)
public class ShoppingFragment extends BaseFragment implements View.OnClickListener, ShoppongRvadapter.MyRvOnclickListener {
    //弹出这个ppp需要的点击
    @BindView(R.id.pop_up_Lt)
    View pop_up;
    //需要改变文字的titles的Tv
    @BindView(R.id.titles_tv)
    TextView titlesTv;
    //弹出这个ppp的布局
    @BindView(R.id.frm_repeat_ppp_Lt)
    View frm_ppp_Lt;
    @BindView(R.id.repeat_Rv)
    RecyclerView recyclerView;
    @BindView(R.id.img)
    ImageView iv;

    //pppLv的定义
    private ListView lv;
    private RepeatLvAdapter adapter;
    private ShoppongRvadapter shoppongRvadapter;
    private PopupWindow popupWindow;
    private List<MyData> mydata;

    private TextView allTv, flatLightTv, sunglassesTv, shareTv, shoppingCartTv, returnTv, exitTv;

    private ImageView allIv, flatLightIv, sunglassesIv, shareIv, shoppingCartIv;


    //定义这个内部接口
    private ControlViewpager controlViewpager;

    //定义这个构造方法 在Aty中将值set过来
    private String titles;


    private Bean bean;

    public ShoppingFragment(String titles) {
        this.titles = titles;
    }

    //接口回调 初始化接口对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        controlViewpager = (ControlViewpager) context;
    }

    public void setControlViewpager(ControlViewpager controlViewpager) {
        this.controlViewpager = controlViewpager;

    }

    @Override
    public void initData() {

        //点击出ppp的点击事件
        pop_up.setOnClickListener(this);

        //设置这个文字的改变
        titlesTv.setText(titles);
        //以titles来判断 如果是我的购物车 将Rv隐藏
        if (titles.equals("我的購物車")) {
            iv.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        initPupop();

        Map<String, String> params = new HashMap<>();
        params.put("token", "0");
        params.put("device_type", "2");

        shoppongRvadapter = new ShoppongRvadapter(context);


        OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/products/goods_list", new OkHttpClientManager.ResultCallback<Bean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Bean response) {
                bean = response;


                Log.d("RepeatFragment", "response.getData().getList().size():" + response.getData().getList().size());
                recyclerView.setLayoutManager(new GridLayoutManager(context, response.getData().getList().size()));
                shoppongRvadapter.setBean(bean);
                recyclerView.setAdapter(shoppongRvadapter);
            }


        }, params);

        shoppongRvadapter.setMyRvOnclickListener(this);
    }

    //通过点击事件控制这些东西显示和不显示
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pop_up_Lt:
                iv.setVisibility(View.INVISIBLE);
                popupWindow.showAsDropDown(pop_up);
                pop_up.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.all_tv:
                controlViewpager.control(0);
                popupWindow.dismiss();
                break;
            case R.id.flat_light_tv:
                controlViewpager.control(1);
                popupWindow.dismiss();
                break;
            case R.id.sunglasses_tv:
                controlViewpager.control(2);
                popupWindow.dismiss();
                break;
            case R.id.share_tv:
                controlViewpager.control(3);
                popupWindow.dismiss();
                break;
            case R.id.shopping_cart_tv:
                controlViewpager.control(4);
                popupWindow.dismiss();

                break;
            case R.id.return_tv:
                controlViewpager.control(0);
                popupWindow.dismiss();
                break;
            case R.id.exit_tv:
                clickQuitDialog();
                popupWindow.dismiss();
                break;
        }

    }

    @Override
    public void myOnclick() {
        startActivity(new Intent(context, DetailActivity.class));
    }


    //自定义的接口 来控制Viewpager的位置
    public interface ControlViewpager {
        void control(int pos);
    }


    //弹出的ppp
    private void initPupop() {

        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        frm_ppp_Lt = LayoutInflater.from(getActivity()).inflate(R.layout.frm_repeat_ppp, null);

        //初始化这些个组件
        allTv = (TextView) frm_ppp_Lt.findViewById(R.id.all_tv);
        allTv.setOnClickListener(this);
        flatLightTv = (TextView) frm_ppp_Lt.findViewById(R.id.flat_light_tv);
        flatLightTv.setOnClickListener(this);
        sunglassesTv = (TextView) frm_ppp_Lt.findViewById(R.id.sunglasses_tv);
        sunglassesTv.setOnClickListener(this);
        shareTv = (TextView) frm_ppp_Lt.findViewById(R.id.share_tv);
        shareTv.setOnClickListener(this);
        shoppingCartTv = (TextView) frm_ppp_Lt.findViewById(R.id.shopping_cart_tv);
        shoppingCartTv.setOnClickListener(this);
        returnTv = (TextView) frm_ppp_Lt.findViewById(R.id.return_tv);
        returnTv.setOnClickListener(this);
        exitTv = (TextView) frm_ppp_Lt.findViewById(R.id.exit_tv);
        exitTv.setOnClickListener(this);
        allIv = (ImageView) frm_ppp_Lt.findViewById(R.id.all_iv);
        flatLightIv = (ImageView) frm_ppp_Lt.findViewById(R.id.flat_light_iv);
        sunglassesIv = (ImageView) frm_ppp_Lt.findViewById(R.id.sunglasses_iv);
        shareIv = (ImageView) frm_ppp_Lt.findViewById(R.id.share_iv);
        shoppingCartIv = (ImageView) frm_ppp_Lt.findViewById(R.id.shopping_cart_iv);


        //通过判断titles 来改变点击后的字体颜色
        switch (titles) {
            case "瀏覽所有分類":
                allTv.setTextColor(getResources().getColor(R.color.colorwhirt));
                allIv.setVisibility(View.VISIBLE);
                break;
            case "瀏覽平光鏡":
                flatLightTv.setTextColor(getResources().getColor(R.color.colorwhirt));
                flatLightIv.setVisibility(View.VISIBLE);
                break;
            case "瀏覽太陽鏡":
                sunglassesTv.setTextColor(getResources().getColor(R.color.colorwhirt));
                sunglassesIv.setVisibility(View.VISIBLE);
                break;
            case "專題分享":
                shareTv.setTextColor(getResources().getColor(R.color.colorwhirt));
                shareIv.setVisibility(View.VISIBLE);
                break;
            case "我的購物車":
                shoppingCartTv.setTextColor(getResources().getColor(R.color.colorwhirt));
                shoppingCartIv.setVisibility(View.VISIBLE);
                break;
        }

        popupWindow.setContentView(frm_ppp_Lt);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (titles.equals("我的購物車")) {
                    pop_up.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    pop_up.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
//                frm_ppp_Lt.startAnimation(animation2);

            }
        });

    }


    public void clickQuitDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("妳真的忍心退出嗎");

//	        builder.setTitle("提示");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                        FirstActivity.this.finish();
//                        System.exit(0);
                SysApplication.getInstance().exit();
            }
        });
        builder.setNegativeButton("掫消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "妳的選擇是明智的", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //event.getRepeatCount() == 0 防止重复点击
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            clickQuitDialog();
            return false;
        }
        return false;
    }


}

