package com.zsz.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsz.dao.datadictionary.DataDictionaryMapper;
import com.zsz.pojo.DataDictionary;
import com.zsz.service.developer.DataDictionaryService;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;

	// 查询所有app状态/所有平台
	public List<DataDictionary> findAllStatus_Flatform(String typeCode) {
		List<DataDictionary> lists=dataDictionaryMapper.findAllStatus_Flatform(typeCode);	
		return lists;
	}
	
	

	
}
