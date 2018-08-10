package com.zsz.service.backend.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.backenduser.BackendUserMapper;
import com.zsz.pojo.BackendUser;
import com.zsz.service.backend.BackendUserService;

@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {

	@Autowired
	private BackendUserMapper backendUserMapper;
	
	// 后台管理登录
	public BackendUser backendLogin(String userCode, String userPassword) {
		BackendUser backendUser=null;
		
		backendUser=backendUserMapper.backendLogin(userCode, userPassword);
		
		if(null!=backendUser){
			return backendUser;
		}else{	
			return null;
		}
	}


	
}
