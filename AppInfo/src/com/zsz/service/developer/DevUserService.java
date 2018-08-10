package com.zsz.service.developer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.DevUser;

public interface DevUserService {

	//用户登录
	DevUser devLogin(@Param("devCode")String devCode,@Param("devPassword")String devPassword);

	//根据用户id查询用户信息
	DevUser getByDevUser(int id);
	
	//修改个人信息
	void updateDevUser(DevUser devUser);
	
	//查询所有用户
	List<DevUser> findAlldevUser();
}
