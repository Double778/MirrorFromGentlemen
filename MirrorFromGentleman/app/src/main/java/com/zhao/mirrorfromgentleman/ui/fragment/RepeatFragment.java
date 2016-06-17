package com.zhao.mirrorfromgentleman.ui.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.okhttp.Request;
import com.zhao.mirrorfromgentleman.R;
import com.zhao.mirrorfromgentleman.model.bean.MyData;
import com.zhao.mirrorfromgentleman.model.bean.TestBean;
import com.zhao.mirrorfromgentleman.model.net.OkHttpClientManager;
import com.zhao.mirrorfromgentleman.ui.adapter.lvadapter.RepeatLvAdapter;
import com.zhao.mirrorfromgentleman.ui.adapter.rvadapter.MyRvOnclickListener;
import com.zhao.mirrorfromgentleman.ui.adapter.rvadapter.RepeatRvadapter;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindContent;
import com.zhao.mirrorfromgentleman.ui.utils.annotation.BindView;
import com.zhao.mirrorfromgentleman.ui.utils.cache.VolleyImageLoaderTool;
import com.zhao.mirrorfromgentleman.view.SysApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 旭哥哥 on 16/6/15.
 * 同用一个Frm
 */
@BindContent(R.layout.frm_repeat)
public class RepeatFragment extends BaseFragment implements View.OnClickListener {
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
    private RepeatRvadapter rvAdapter;
    private PopupWindow popupWindow;
    private List<MyData> mydata;

    private TextView allTv, flatLightTv, sunglassesTv, shareTv, tv5, tv6, tv7;

    private ImageView iv1, iv2, iv3, iv4, iv5;


    //定义这个内部接口
    private ControlViewpager controlViewpager;

    //定义这个构造方法 在Aty中将值set过来
    private String titles;

    public RepeatFragment(String titles) {
        this.titles = titles;
    }
    //将set这个接口给出去
//    public void setControlViewpager(ControlViewpager controlViewpager) {
//        this.controlViewpager = controlViewpager;
//    }

    //接口回调 初始化接口对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controlViewpager = (ControlViewpager) context;
    }

    @Override
    public void initData() {



        //点击出ppp的点击事件
        pop_up.setOnClickListener(this);


        //设置这个文字的改变
        titlesTv.setText(titles);
        //以titles来判断 如果是我的购物车 将Rv隐藏
        if (titles.equals("我的购物车")){
            recyclerView.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);



        }

        initPupop();



        Map<String, String> params = new HashMap<>();
        params.put("token", "0");
        params.put("device_type", "2");


        OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/products/goods_list", new OkHttpClientManager.ResultCallback<TestBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(TestBean response) {
//                VolleyImageLoaderTool.showImage(imageView, response.getData().getList().get(1).getDesign_des().get(0).getImg());
                Log.d("RepeatFragment", "response.getData().getList().size():" + response.getData().getList().size());
            }


        },params);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i+" ");
        }

        recyclerView.setLayoutManager( new StaggeredGridLayoutManager(data.size(),StaggeredGridLayoutManager.VERTICAL));
        rvAdapter =new RepeatRvadapter(context);
        rvAdapter.setData(data);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setMyRvOnclickListener(new MyRvOnclickListener() {
            @Override
            public void myOnclick(int id, int pos) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pop_up_Lt:
                popupWindow.showAsDropDown(pop_up);
                pop_up.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv1:
                controlViewpager.control(0);
                popupWindow.dismiss();
                break;
            case R.id.tv2:
                controlViewpager.control(1);
                popupWindow.dismiss();
                break;
            case R.id.tv3:
                controlViewpager.control(2);
                popupWindow.dismiss();
                break;
            case R.id.tv4:
                controlViewpager.control(3);
                popupWindow.dismiss();
                break;
            case R.id.tv5:
                controlViewpager.control(4);
                popupWindow.dismiss();
                break;
            case R.id.tv6:
                controlViewpager.control(0);
                popupWindow.dismiss();
                break;
            case R.id.tv7:
                clickQuitDialog();
                popupWindow.dismiss();
                break;
        }


//        popupWindow.showAsDropDown(pop_up);
//        pop_up.setVisibility(View.INVISIBLE);
//        recyclerView.setVisibility(View.INVISIBLE);


    }



    //自定义的接口 来控制Viewpager的位置
    public interface ControlViewpager{
        void control(int pos);
    }


    //弹出的ppp
    private void initPupop() {
        final Animation animation1 = AnimationUtils.loadAnimation(getActivity(),
                R.anim.score_business_query_enter);
        final Animation animation2 = AnimationUtils.loadAnimation(getActivity(),
                R.anim.score_business_query_exit);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        frm_ppp_Lt = LayoutInflater.from(getActivity()).inflate(R.layout.frm_repeat_ppp, null);
        frm_ppp_Lt.startAnimation(animation1);

        initdatas();


        allTv = (TextView) frm_ppp_Lt.findViewById(R.id.all_tv);
        allTv.setOnClickListener(this);
        flatLightTv = (TextView) frm_ppp_Lt.findViewById(R.id.flat_light_tv);
        flatLightTv.setOnClickListener(this);
        sunglassesTv = (TextView) frm_ppp_Lt.findViewById(R.id.sunglasses_tv);
        sunglassesTv.setOnClickListener(this);
        shareTv = (TextView) frm_ppp_Lt.findViewById(R.id.share_tv);
        shareTv.setOnClickListener(this);
        tv5 = (TextView) frm_ppp_Lt.findViewById(R.id.tv5);
        tv5.setOnClickListener(this);
        tv6 = (TextView) frm_ppp_Lt.findViewById(R.id.tv6);
        tv6.setOnClickListener(this);
        tv7 = (TextView) frm_ppp_Lt.findViewById(R.id.tv7);
        tv7.setOnClickListener(this);
        iv1 = (ImageView) frm_ppp_Lt.findViewById(R.id.iv1);
        iv2 = (ImageView) frm_ppp_Lt.findViewById(R.id.iv2);
        iv3 = (ImageView) frm_ppp_Lt.findViewById(R.id.iv3);
        iv4 = (ImageView) frm_ppp_Lt.findViewById(R.id.iv4);
        iv5 = (ImageView) frm_ppp_Lt.findViewById(R.id.iv5);


        switch (titles) {
            case "浏览所有分类":
                tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                iv1.setVisibility(View.VISIBLE);
                break;
            case "浏览平光镜":
                tv2.setTextColor(getResources().getColor(R.color.colorAccent));
                iv2.setVisibility(View.VISIBLE);
                break;
            case "浏览太阳镜":
                tv3.setTextColor(getResources().getColor(R.color.colorAccent));
                iv3.setVisibility(View.VISIBLE);
                break;
            case "专题分享":
                tv4.setTextColor(getResources().getColor(R.color.colorAccent));
                iv4.setVisibility(View.VISIBLE);
                break;
            case "我的购物车":
                tv5.setTextColor(getResources().getColor(R.color.colorAccent));
                iv5.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                iv.setVisibility(View.VISIBLE);
                break;
        }
//        lv = (ListView) frm_ppp_Lt.findViewById(R.id.ppp_lv);
//        adapter = new RepeatLvAdapter(context,mydata,R.layout.frm_repeat_item_lv);




//        lv.setDivider(null);
//        lv.setDividerHeight(0);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position==mydata.size()-2){
//                    controlViewpager.control(0);
//                }else if (position == mydata.size()-1){
//                    clickQuitDialog();
//                }else  {
//                    controlViewpager.control(position);
//                }
//
//                popupWindow.dismiss();
//
//            }
//        });


        popupWindow.setContentView(frm_ppp_Lt);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                pop_up.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                frm_ppp_Lt.startAnimation(animation2);

            }
        });

    }


    private void initdatas() {
        mydata = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            mydata.add(new MyData(titles));
        mydata.add(new MyData("浏览所有分类"));
        mydata.add(new MyData("浏览平光镜"));
        mydata.add(new MyData("浏览太阳镜"));
        mydata.add(new MyData("专题分享"));
        mydata.add(new MyData("我的购物车"));
        mydata.add(new MyData("返回首页"));
        mydata.add(new MyData("退出应用程序"));


//        }

    }

    public void clickQuitDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("你真的忍心退出吗?");

//	        builder.setTitle("提示");
        builder.setPositiveButton("确认",new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                        FirstActivity.this.finish();
//                        System.exit(0);
                SysApplication.getInstance().exit();
            }
        });
        builder.setNegativeButton("取消",new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "你的选择是明智的", Toast.LENGTH_SHORT).show();
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
