package com.zsz.dao.backenduser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.BackendUser;

public interface BackendUserMapper {

	//后台管理登录
	BackendUser backendLogin(@Param("userCode")String userCode,@Param("userPassword")String userPassword);

	//查询所有用户
	List<BackendUser> findAllBackendUser();
}
