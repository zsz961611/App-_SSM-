package com.zsz.dao.appcategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.AppCategory;

public interface AppCategoryMapper {

	//查询所有一级分类
	List<AppCategory> findAllFenLei(@Param("parentId")int parentId);
}
