package com.zsz.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.appcategory.AppCategoryMapper;
import com.zsz.pojo.AppCategory;
import com.zsz.service.developer.AppCategoryService;

@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {

	@Autowired
	private AppCategoryMapper appCategoryMapper;
	
	//查询分类信息
	public List<AppCategory> findAllFenLei(int parentId) {
		List<AppCategory> fenleiList=appCategoryMapper.findAllFenLei(parentId);
		return fenleiList;
	}

}
