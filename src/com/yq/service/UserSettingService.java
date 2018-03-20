package com.yq.service;

import com.yq.dao.UserDao;
import com.yq.dao.UserSettingDao;
import com.yq.entity.User;
import com.yq.entity.UserSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserSettingService {
	@Autowired
	private UserSettingDao userSettingDao;
	
	public int insert(Map<String,String> map ){
		return userSettingDao.insert(map);
	}
	
	public int update(Map<String,String> map ){
		return userSettingDao.update(map);
	}

	public List<UserSetting> list(){
		return userSettingDao.list();
	}


}
