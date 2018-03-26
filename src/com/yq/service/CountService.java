package com.yq.service;

import com.yq.dao.CountDao;
import com.yq.dao.ShareDao;
import com.yq.entity.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CountService {
	@Autowired
	private CountDao countDao;

	public int insert(Map<String,String> map ){
		return countDao.insert(map);
	}

	public List<Map<String, String>> countByGoodsId(Integer goods_id){
		return countDao.countByGoodsId(goods_id);
	}

	public List<Map<String, String>> countDataAll(){
		return countDao.countDataAll();
	}

	public List<Map<String, String>> findCountByDate(Map<String, String> map) {
	    return countDao.findCountByDate(map);
	}

}
