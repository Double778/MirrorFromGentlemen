package com.example.dllo.myday0620;

import android.app.Activity;

/**
 * Created by dllo on 16/6/20.
 */
public interface Testinterface {

    //方法泛型 局部泛型
    <T> void  a(T  a);

    //接口可以继承
    public interface  s extends  Testinterface{

    }
    //接口中都是静态的

    void  loginSuccess();

    interface  Failuer{
        void nameError();


    }

    //

}
