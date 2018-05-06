package com.yq.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yq.dao.OrderDao;
import com.yq.entity.Order;

@Service
public class OrderService {
@Autowired
private OrderDao orderDao;
	public int insert(Order order){
		return orderDao.insert(order) ;
	}

	public int upstatus(Map<String, Object> map){
		return orderDao.upstatus(map) ;
	}

	public int upprice(Map<String, Object> map){
		return orderDao.upprice(map);
	}
	public int delete(Map<String, Object> map){
		return orderDao.delete(map) ;
	}

	public List<Order> list(Order order){
		return orderDao.list(order) ;
	}

	public List<Order> listById(Order order){
		return orderDao.listById(order) ;
	}
	
	public int count(Order order){
		return orderDao.count(order) ;
	}
	public List<Order> listJson(Order order){
		return orderDao.listJson(order) ;
	}
	

	public int listJsonCount(Order order){
		return orderDao.listJsonCount(order) ;
	}

	public void cardStatusUpdate(Map<String, String> map){
		orderDao.cardStatusUpdate(map);
	}

	public Map<String, String> findOrderByCode(String card_code){
		return orderDao.findOrderByCode(card_code);
	}

	public int cardAddressUpdate(Map<String, String> map){
		return orderDao.cardAddressUpdate(map);
	}

	public int conutOrderMain(){ return orderDao.conutOrderMain(); }

}
