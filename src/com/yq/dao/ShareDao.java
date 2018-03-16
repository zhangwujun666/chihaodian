package com.yq.dao;

import com.yq.entity.User;

import java.util.List;
import java.util.Map;


public interface ShareDao {

	public int insert(Map<String, String> map);

	public int countByOppenID(Map<String, String> map);

}
