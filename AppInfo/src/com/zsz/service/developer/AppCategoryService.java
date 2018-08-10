package com.zsz.service.developer;

import java.util.List;

import com.zsz.pojo.AppCategory;

public interface AppCategoryService {

	//查询所有一级分类
	List<AppCategory> findAllFenLei(int parentId);
}
