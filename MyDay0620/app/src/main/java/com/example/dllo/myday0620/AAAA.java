package com.example.dllo.myday0620;

/**
 * Created by dllo on 16/6/20.
 */
public class AAAA {
    private  volatile  int num;

    //volatile 不稳定修饰符
    //它严格不是说这个num 不稳定, 可能会爆炸
    //而是通知编译器(系统)我这个哥们随时在变
    //多用于:多线程修改一个变量,该变量最好用bolatile修饰
    {
        num = 1;
        num = 2;
        num = 3;
        num = 4;
    }

}
