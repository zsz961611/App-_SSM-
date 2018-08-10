package com.zsz.dao.datadictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsz.pojo.DataDictionary;

public interface DataDictionaryMapper {

	// 查询所有app状态/所有平台
	List<DataDictionary> findAllStatus_Flatform(@Param("typeCode")String typeCode);
	
}
