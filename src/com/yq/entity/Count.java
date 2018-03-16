package com.yq.entity;

public class Count extends Page{
    int goods_id;
    String view_time;
    String oppen_id;
    String views;
    String count;
    String id;


    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getView_time() {
        return view_time;
    }

    public void setView_time(String view_time) {
        this.view_time = view_time;
    }

    public String getOppen_id() {
        return oppen_id;
    }

    public void setOppen_id(String oppen_id) {
        this.oppen_id = oppen_id;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
