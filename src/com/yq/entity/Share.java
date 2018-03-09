package com.yq.entity;

public class Share extends Page{

    String share_oppen_id;//varchar(255) NOT NULL,
    String user_oppen_id;

    public String getShare_oppen_id() {
        return share_oppen_id;
    }

    public void setShare_oppen_id(String share_oppen_id) {
        this.share_oppen_id = share_oppen_id;
    }

    public String getUser_oppen_id() {
        return user_oppen_id;
    }

    public void setUser_oppen_id(String user_oppen_id) {
        this.user_oppen_id = user_oppen_id;
    }
}
