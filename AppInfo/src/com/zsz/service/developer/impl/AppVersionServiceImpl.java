package com.zsz.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.appversion.AppVersionMapper;
import com.zsz.pojo.AppInfo;
import com.zsz.pojo.AppVersion;
import com.zsz.service.developer.AppVersionService;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {

	@Autowired
	private AppVersionMapper appVersionMapper;
	
	//删除某app对应的版本信息
	public void versionDel(int versionId) {
		appVersionMapper.versionDel(versionId);
	}

	// 查询某app的版本信息
	public List<AppVersion> versiongetById(int appid) {
		List<AppVersion> appVersions=appVersionMapper.versiongetById(appid);
		return appVersions;
	}

	//根据最新版本号查询版本信息
	public AppVersion getByNewVersion(Integer id) {
		AppVersion appVersion=appVersionMapper.getByIdNewVersion(id);
		return appVersion;
	}

	// 修改app最新版本信息
	public int updateNewVersion(AppVersion appVersion) {
		int num=appVersionMapper.updateNewVersion(appVersion);
		return num;
	}

	//新增版本信息
	public void addAppVersion(AppVersion appVersion) {
		appVersionMapper.addAppVersion(appVersion);
	}

	//修改apk信息
	public void deleteApkFile(int id) {
		appVersionMapper.deleteApkFile(id);
	}

}
