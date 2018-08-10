package com.zsz.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.AppInfo.AppInfoMapper;
import com.zsz.pojo.AppInfo;
import com.zsz.service.developer.AppInfoService;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoMapper appInfoMapper;
	
	
	/**
	 * 分页查询
	 * @param softwareName：软件名称
	 * @param status：APP状态
	 * @param flatformId：所属平台
	 * @param categoryLevel1：一级分类
	 * @param categoryLevel2：二级分类
	 * @param categoryLevel3：三级分类
	 * @return
	 */
	public List<AppInfo> findAllApp(int offset, int pageSize, String softwareName, Integer status, Integer flatformId,
			Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3) {
		
		List<AppInfo> applist=appInfoMapper.findAllApp(offset, pageSize, softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
		return applist;
	}

	// 查询总记录数
	public int findCount(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3) {
		
		int num=appInfoMapper.findCount(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
		
		return num;
	}

	//新增app基础信息
	public void appInfoAdd(AppInfo appInfo) {
		appInfoMapper.appInfoAdd(appInfo);		
	}

	//删除app信息
	public int appInfoDel(int appid) {
		int num=appInfoMapper.appInfoDel(appid);
		return num;
	
	}

	//根据appid查询
	public AppInfo appInfogetById(int appid){
		AppInfo appInfo=appInfoMapper.appInfogetById(appid);
		return appInfo;
	}

	//修改app状态//修改App状态  获取appid修改
	public int updateStatusgetByappId(AppInfo appInfo) {
		int num=appInfoMapper.updateStatusgetByappId(appInfo);
		return num;
	}

	//根据appid修改归属版本的编号
	public void updateapp_versionId(AppInfo appInfo) {
		appInfoMapper.updateapp_versionId(appInfo);
	}

	//根据appid更新logo图片路径
	public void deleteAppLogo(int id) {
		appInfoMapper.deleteAppLogo(id);
	}

	//根据appid修改
	public void updateInfo(AppInfo appInfo) {
		appInfoMapper.updateInfo(appInfo);
	}

	//总下载量
	public int count() {
		int num=appInfoMapper.count();
		return num;	
	}

	
}
