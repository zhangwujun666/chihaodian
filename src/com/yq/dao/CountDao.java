package com.yq.dao;

import com.yq.entity.Count;

import java.util.List;
import java.util.Map;


public interface CountDao {

	public int insert(Map<String, String> map);

	public List<Map<String, String>> countByGoodsId(Integer goods_id);

}
