package com.yq.dao;

import java.util.List;
import java.util.Map;

import com.yq.entity.Order;


public interface OrderDao {

	public int insert(Order order);

	public int upstatus(Map<String, Object> map);
	
	public int upprice(Map<String, Object> map);

	public int delete(Map<String, Object> map);

	public List<Order> list(Order order);

	public List<Order> listById(Order order);
	
	public List<Order> listJson(Order order);
	
	public int count(Order order);

	public int listJsonCount(Order order);

	public void cardStatusUpdate(Map<String, String> map);

	public Map<String, String> findOrderByCode(String card_code);

	public  int cardAddressUpdate(Map<String, String> map);
}
