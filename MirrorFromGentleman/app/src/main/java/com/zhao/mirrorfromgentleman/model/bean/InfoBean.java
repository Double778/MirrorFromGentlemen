package com.zhao.mirrorfromgentleman.model.bean;

import java.util.List;

/**
 * Created by 华哥哥 on 16/6/21.
 */
public class InfoBean {

    private String goods_id;
    private String goods_pic;
    private String model;
    private String goods_img;
    private String goods_name;
    private String last_storge;
    private String whole_storge;
    private String height;
    private String ordain;
    private String product_area;
    private String goods_price;
    private String discount_price;
    private String brand;
    private String info_des;
    private String goods_share;
    private List<DesignDesBean> design_des;
    private List<GoodsBean> goods_data;


    public List<DesignDesBean> getDesign_des() {
        return design_des;
    }

    public void setDesign_des(List<DesignDesBean> design_des) {
        this.design_des = design_des;
    }

    public List<GoodsBean> getGoods_data() {
        return goods_data;
    }

    public void setGoods_data(List<GoodsBean> goods_data) {
        this.goods_data = goods_data;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getLast_storge() {
        return last_storge;
    }

    public void setLast_storge(String last_storge) {
        this.last_storge = last_storge;
    }

    public String getWhole_storge() {
        return whole_storge;
    }

    public void setWhole_storge(String whole_storge) {
        this.whole_storge = whole_storge;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOrdain() {
        return ordain;
    }

    public void setOrdain(String ordain) {
        this.ordain = ordain;
    }

    public String getProduct_area() {
        return product_area;
    }

    public void setProduct_area(String product_area) {
        this.product_area = product_area;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInfo_des() {
        return info_des;
    }

    public void setInfo_des(String info_des) {
        this.info_des = info_des;
    }

    public String getGoods_share() {
        return goods_share;
    }

    public void setGoods_share(String goods_share) {
        this.goods_share = goods_share;
    }
}
