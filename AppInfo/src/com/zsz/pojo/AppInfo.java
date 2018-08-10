package com.zsz.pojo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * <p>Title: AppInfo</p>
 * <p>Description: app信息类--》app_info</p>
 * @author MuGuijun 
 * @date 2017年6月7日 上午8:24:35
 */
public class AppInfo {
	private Integer id;//主键id
	private String softwareName;//软件名称
	private String APKName;//APK名称：安装包名称
	private String supportROM;//支持ROM
	private String interfaceLanguage;//界面语言
	private Date updateDate;//更新日期
	private BigDecimal softwareSize;//软件大小（单位：M）
	private String appInfo;//应用简介
	private Date onSaleDate;//上架时间
	private Date offSaleDate;//下架时间
	private Integer downloads;//下载量（单位：次）
	private Integer createdBy;//创建者
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者
	private Date modifyDate;//更新时间
	private String logoPicPath;//LOGO图片URL路径
	private String logoLocPath;//LOGO图片的服务器存储路径
	
	private Integer devId;//开发者id----》和开发者关联
	
	private Integer categoryLevel3;//所属三级分类id：---》三级分类关系
	private Integer categoryLevel1;//所属一级分类id  ---->一级分类
	private Integer categoryLevel2;//所属二级分类id  ---->二级分类
	
	private Integer status;//app状态id：-----》和状态表
	private Integer flatformId;//app所属平台id：--》所属平台：手机/平板、通用
	
	private Integer versionId;//最新的版本id  -----》最新版本

	
	
	
	
//=========================下面的属性主要用于页面数据的显示及mepper中数据封装方便=================================
	private String devName;//开发者名称	
	private String statusName;//app状态名称
	private String flatformName;//app所属平台名称
	private String categoryLevel3Name;//所属三级分类名称
	private String categoryLevel1Name;//所属一级分类名称
	private String categoryLevel2Name;//所属二级分类名称	
	private String versionNo;//最新的版本号--方便页数数据显示、dao层数据的封装
	private Long zongxiazai;//app的总下载量
		
	
	
	public Long getZongxiazai() {
		return zongxiazai;
	}
	public void setZongxiazai(Long zongxiazai) {
		this.zongxiazai = zongxiazai;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getLogoLocPath() {
		return logoLocPath;
	}
	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}
	public String getLogoPicPath() {
		return logoPicPath;
	}
	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}
	public Integer getCategoryLevel3() {
		return categoryLevel3;
	}
	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}
	public String getCategoryLevel3Name() {
		return categoryLevel3Name;
	}
	public void setCategoryLevel3Name(String categoryLevel3Name) {
		this.categoryLevel3Name = categoryLevel3Name;
	}
	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}
	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}
	public String getCategoryLevel2Name() {
		return categoryLevel2Name;
	}
	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.categoryLevel2Name = categoryLevel2Name;
	}
	public Integer getCategoryLevel1() {
		return categoryLevel1;
	}
	public void setCategoryLevel1(Integer categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}
	public Integer getCategoryLevel2() {
		return categoryLevel2;
	}
	public void setCategoryLevel2(Integer categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getFlatformName() {
		return flatformName;
	}
	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}
	
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public String getAPKName() {
		return APKName;
	}
	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}
	public String getSupportROM() {
		return supportROM;
	}
	public void setSupportROM(String supportROM) {
		this.supportROM = supportROM;
	}
	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}
	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public BigDecimal getSoftwareSize() {
		return softwareSize;
	}
	public void setSoftwareSize(BigDecimal softwareSize) {
		this.softwareSize = softwareSize;
	}
	public Integer getDevId() {
		return devId;
	}
	public void setDevId(Integer devId) {
		this.devId = devId;
	}
	public String getAppInfo() {
		return appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getOnSaleDate() {
		return onSaleDate;
	}
	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}
	public Date getOffSaleDate() {
		return offSaleDate;
	}
	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}
	
	public Integer getDownloads() {
		return downloads;
	}
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	public Integer getFlatformId() {
		return flatformId;
	}
	public void setFlatformId(Integer flatformId) {
		this.flatformId = flatformId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public AppInfo() {
		
	}
	
	public AppInfo(Integer id, String softwareName, String aPKName, String supportROM, String interfaceLanguage,
			Date updateDate, BigDecimal softwareSize, String appInfo, Date onSaleDate, Date offSaleDate,
			Integer downloads, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate,
			String logoPicPath, String logoLocPath, Integer devId, Integer categoryLevel3, Integer categoryLevel1,
			Integer categoryLevel2, Integer status, Integer flatformId, Integer versionId, String devName,
			String statusName, String flatformName, String categoryLevel3Name, String categoryLevel1Name,
			String categoryLevel2Name, String versionNo) {
		super();
		this.id = id;
		this.softwareName = softwareName;
		APKName = aPKName;
		this.supportROM = supportROM;
		this.interfaceLanguage = interfaceLanguage;
		this.updateDate = updateDate;
		this.softwareSize = softwareSize;
		this.appInfo = appInfo;
		this.onSaleDate = onSaleDate;
		this.offSaleDate = offSaleDate;
		this.downloads = downloads;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.logoPicPath = logoPicPath;
		this.logoLocPath = logoLocPath;
		this.devId = devId;
		this.categoryLevel3 = categoryLevel3;
		this.categoryLevel1 = categoryLevel1;
		this.categoryLevel2 = categoryLevel2;
		this.status = status;
		this.flatformId = flatformId;
		this.versionId = versionId;
		this.devName = devName;
		this.statusName = statusName;
		this.flatformName = flatformName;
		this.categoryLevel3Name = categoryLevel3Name;
		this.categoryLevel1Name = categoryLevel1Name;
		this.categoryLevel2Name = categoryLevel2Name;
		this.versionNo = versionNo;
	}
	
	//修改app上架下架状态
	public AppInfo(Integer id, Integer modifyBy, Integer status) {
		this.id = id;
		this.modifyBy = modifyBy;
		this.status = status;
	}
	public AppInfo(Integer id, Integer versionId) {
		this.id = id;
		this.versionId = versionId;
	}
	

	
	
	
	
}
