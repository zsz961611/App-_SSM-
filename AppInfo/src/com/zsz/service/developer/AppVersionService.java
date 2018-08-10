package com.zsz.service.developer;

import java.util.List;

import com.zsz.pojo.AppVersion;

public interface AppVersionService {

	//删除某app对应的版本信息
	void versionDel(int versionId);
	
	//根据appid查询版本信息
	List<AppVersion> versiongetById(int appid);
	
	//根据最新版本号查询版本信息
	AppVersion getByNewVersion(Integer id);
	
	//修改App最新版本信息
	int updateNewVersion(AppVersion appVersion);
	
	//新增版本信息
	void addAppVersion(AppVersion appVersion);
	
	//修改apk信息
	void deleteApkFile(int id);
}
