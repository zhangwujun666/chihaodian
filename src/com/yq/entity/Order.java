package com.yq.entity;

public class Order extends Page{
	
	String order_id ;
	String goods_id; // varchar(255) DEFAULT NULL,
	String goods_name; // text,
	String goods_img; // text,
	String goods_spe; // text,
	String goods_price; // varchar(255) DEFAULT NULL,
	String goods_num; // varchar(255) DEFAULT NULL,
	Float goods_total; // int(11) DEFAULT NULL,
	Integer goods_total_num; // float DEFAULT NULL,
	String addr_name;
	String receive;
	Integer cps_id; // int(11) DEFAULT NULL,
	String cps_name; // varchar(255) DEFAULT NULL,
	Float cps_price; // float DEFAULT NULL,
	String oppen_id; // varchar(255) DEFAULT NULL,
	String add_time ;
	String start_time ;
	String end_time ;
	String ctg_name ;
	String realname;
	String note ;
	int status ;
	String phone ;
	String express_dm ;
	String express_name ;
	String express_hm ;
	String card_id ;
	String card_status;
	String card_code;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCard_status() {
		return card_status;
	}
	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}
	public String getCard_code() {
		return card_code;
	}
	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public Integer getCps_id() {
		return cps_id;
	}
	public void setCps_id(Integer cps_id) {
		this.cps_id = cps_id;
	}
	public String getCps_name() {
		return cps_name;
	}
	public void setCps_name(String cps_name) {
		this.cps_name = cps_name;
	}
	public Float getCps_price() {
		return cps_price;
	}
	public void setCps_price(Float cps_price) {
		this.cps_price = cps_price;
	}
	public String getOppen_id() {
		return oppen_id;
	}
	public void setOppen_id(String oppen_id) {
		this.oppen_id = oppen_id;
	}
	public Float getGoods_total() {
		return goods_total;
	}
	public void setGoods_total(Float goods_total) {
		this.goods_total = goods_total;
	}
	public Integer getGoods_total_num() {
		return goods_total_num;
	}
	public void setGoods_total_num(Integer goods_total_num) {
		this.goods_total_num = goods_total_num;
	}
	public String getAddr_name() {
		return addr_name;
	}
	public void setAddr_name(String addr_name) {
		this.addr_name = addr_name;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getGoods_spe() {
		return goods_spe;
	}
	public void setGoods_spe(String goods_spe) {
		this.goods_spe = goods_spe;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCtg_name() {
		return ctg_name;
	}
	public void setCtg_name(String ctg_name) {
		this.ctg_name = ctg_name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExpress_dm() {
		return express_dm;
	}
	public void setExpress_dm(String express_dm) {
		this.express_dm = express_dm;
	}
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	public String getExpress_hm() {
		return express_hm;
	}
	public void setExpress_hm(String express_hm) {
		this.express_hm = express_hm;
	}
	
	
}
