package com.zhao.mirrorfromgentleman.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/15.
 */
public class TestBean {

    /**
     * result : 1
     * msg :
     * data : {"has_more":"2","list":[{"goods_img":"","goods_id":"96Psa1455524521","goods_name":"GENTLE MONSTER","goods_price":"1300","discount_price":"1300","if_new":""},{"goods_img":"","goods_id":"bUmq31454389988","goods_name":"KAREN WALKER","goods_price":"1538","discount_price":"","if_new":""},{"goods_img":"","goods_id":"28JeX1452078872","goods_name":"SEE CONCEPT","goods_price":"450","discount_price":"","if_new":""}]}
     */

    private String result;
    private String msg;
    /**
     * has_more : 2
     * list : [{"goods_img":"","goods_id":"96Psa1455524521","goods_name":"GENTLE MONSTER","goods_price":"1300","discount_price":"1300","if_new":""},{"goods_img":"","goods_id":"bUmq31454389988","goods_name":"KAREN WALKER","goods_price":"1538","discount_price":"","if_new":""},{"goods_img":"","goods_id":"28JeX1452078872","goods_name":"SEE CONCEPT","goods_price":"450","discount_price":"","if_new":""}]
     */

    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String has_more;
        /**
         * goods_img :
         * goods_id : 96Psa1455524521
         * goods_name : GENTLE MONSTER
         * goods_price : 1300
         * discount_price : 1300
         * if_new :
         */

        private List<ListBean> list;

        public String getHas_more() {
            return has_more;
        }

        public void setHas_more(String has_more) {
            this.has_more = has_more;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String goods_img;
            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String discount_price;
            private String if_new;

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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

            public String getIf_new() {
                return if_new;
            }

            public void setIf_new(String if_new) {
                this.if_new = if_new;
            }
        }
    }
}
