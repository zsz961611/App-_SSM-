package com.zsz.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.devuser.DevUserMapper;
import com.zsz.pojo.DevUser;
import com.zsz.service.developer.DevUserService;
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {

	@Autowired
	private DevUserMapper devUserMapper;
	
	//用户登录
	public DevUser devLogin(String devCode, String devPassword) {
		DevUser user=null;
		user=devUserMapper.devLogin(devCode, devPassword);
		if(user!=null){
			if(!user.getDevPassword().equals(devPassword)){				
				return null;
			}
		}
		return user;	
	}

	//根据用户id查询用户信息
	public DevUser getByDevUser(int id) {
		DevUser devUser=devUserMapper.getByDevUser(id);
		return devUser;
	}

	//修改个人信息
	public void updateDevUser(DevUser devUser) {
		devUserMapper.updateDevUser(devUser);
	}

	//查询所有用户
	public List<DevUser> findAlldevUser() {
		List<DevUser> list=devUserMapper.findAlldevUser();
		return list;
	}

	
}
