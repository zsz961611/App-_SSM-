package com.zsz.dao.appversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.AppVersion;

public interface AppVersionMapper {

	//删除某app对应的版本信息
	void versionDel(int versionId);
	
	//根据appid查询版本信息
	List<AppVersion> versiongetById(int appid );
	
	//修改App最新版本信息
	int updateNewVersion(AppVersion appVersion);
	
	//根据最新版本号查询版本信息
	AppVersion getByIdNewVersion(Integer id);
	
	//新增版本信息
	void addAppVersion(AppVersion appVersion);
	
	//修改apk信息
	void deleteApkFile(int id);

}
