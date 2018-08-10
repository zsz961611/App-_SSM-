package com.zsz.service.backend;

import java.util.List;

import com.zsz.pojo.BackendUser;

public interface BackendUserService {

	//后台管理登录
	BackendUser backendLogin(String userCode,String userPassword);

}
