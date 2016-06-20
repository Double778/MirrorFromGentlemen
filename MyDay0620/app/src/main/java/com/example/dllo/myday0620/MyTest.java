package com.example.dllo.myday0620;

/**
 * Created by dllo on 16/6/20.
 */
public class MyTest<T> {
    //类泛型 全局泛型
    //一般用接口中用泛型
    //利用接口传值,将需要传递的值写成泛型 避免了方法的重载
    public T setText(T t){
        return (T)(t +"泛型处理");
    }

    {
        //面试中 java:构造器,静态的东西如:静态代码块,静态方法,类方法
        //需要自己去查找了解
        //代码块:代表什么 对象创建时编译才是静态编译
        //在什么情况下回运行代码块及代码块的使用

        MyTest<Boolean> myTest = new MyTest<>();
        myTest.setText(false);
    }

}
