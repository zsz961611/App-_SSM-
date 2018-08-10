package com.zsz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * <p>Title: AppCategory</p>
 * <p>Description: 分类实体类--》app_category </p>
 * @author MuGuijun 
 * @date 2017年6月7日 上午8:24:09
 */
public class AppCategory {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 分类编码
	 */
	private String categoryCode;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 父级分类id
	 */
	private Integer parentId;
	/**
	 * 创建者
	 */
	private Integer createdBy;
	/**
	 * 创建时间
	 */
	private Date creationDate;
	/**
	 * 更新者
	 */
	private Integer modifyBy;
	/**
	 * 更新时间
	 */
	private Date modifyDate;
	
	/**
	 * 下一级分类：
	 */
	private List<AppCategory> twoList = new ArrayList<AppCategory>();//更新时间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public List<AppCategory> getTwoList() {
		return twoList;
	}
	public void setTwoList(List<AppCategory> twoList) {
		this.twoList = twoList;
	}
	/*public List<AppCategory> getThreeList() {
		return threeList;
	}
	public void setThreeList(List<AppCategory> threeList) {
		this.threeList = threeList;
	}*/
	
	
}
