package com.zsz.service.developer;

import java.util.List;



import com.zsz.pojo.DataDictionary;

public interface DataDictionaryService {

	// 查询所有app状态/所有平台
	List<DataDictionary> findAllStatus_Flatform(String typeCode);
	
}
