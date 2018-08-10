package com.zsz.service.developer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.AppInfo;

public interface AppInfoService {

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
	List<AppInfo> findAllApp(int offset,int pageSize,String softwareName,Integer status,Integer flatformId,
			Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);

	// 查询总记录数
	int findCount(String softwareName,Integer status,Integer flatformId,
			Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);


	//新增app基础信息
	void appInfoAdd(AppInfo appInfo);
	
	//删除app信息
	int appInfoDel(int appid);
	
	//根据id查询(关联查)
	AppInfo appInfogetById(int appid);
	
	//修改App状态  获取appid修改
	int updateStatusgetByappId(AppInfo appInfo);
	
	//根据appid修改归属版本的编号
	void updateapp_versionId(AppInfo appInfo);
	
	//根据appid更新logo图片路径
	void deleteAppLogo(int id);
	
	//根据appid修改
	void updateInfo(AppInfo appInfo);
	
	//总下载量
	int count();
}
