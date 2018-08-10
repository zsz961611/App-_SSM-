package com.zsz.controller.backend;

import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.zsz.pojo.AppCategory;
import com.zsz.pojo.AppInfo;
import com.zsz.pojo.AppVersion;
import com.zsz.pojo.DataDictionary;
import com.zsz.pojo.DevUser;
import com.zsz.service.developer.AppCategoryService;
import com.zsz.service.developer.AppInfoService;
import com.zsz.service.developer.AppVersionService;
import com.zsz.service.developer.DataDictionaryService;
import com.zsz.service.developer.DevUserService;

@Controller
@RequestMapping("/backend")
public class BackendAppController {
	
	@Autowired
	@Qualifier("appInfoService")
	private AppInfoService appInfoService;
	
	@Autowired
	@Qualifier("dataDictionaryService")
	private DataDictionaryService dataDictionaryService;
	
	@Autowired
	@Qualifier("appCategoryService")
	private AppCategoryService appCategoryService;
	
	@Autowired
	@Qualifier("appVersionService")
	private AppVersionService appVersionService;
	
	@Autowired
	@Qualifier("devUserService")
	private DevUserService devUserService;
	
	//app审核首页
	@RequestMapping("/applist.html")
	public String appShenHe(String querySoftwareName,String queryStatus,String queryFlatformId,String queryCategoryLevel1,String queryCategoryLevel2,String queryCategoryLevel3,Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		//获取软件名称
				request.setCharacterEncoding("UTF-8");
				System.out.println("软件名称==="+querySoftwareName);
				
				//获取状态类型
				if(queryStatus==null || queryStatus=="" ){
					queryStatus="0";
				}
				System.out.println("状态类型==="+queryStatus);
			
				//获取所属平台
				if(queryFlatformId==null || queryFlatformId=="" ){
					queryFlatformId="0";
				}
				System.out.println("所属平台==="+queryFlatformId);
				
				// 获取一级分类
				if(queryCategoryLevel1==null || queryCategoryLevel1==""){
					queryCategoryLevel1="0";
				}
				System.out.println("一级分类==="+queryCategoryLevel1);
				
				//获取二级分类
				if(queryCategoryLevel2==null || queryCategoryLevel2==""){
					queryCategoryLevel2="0";
				}
				System.out.println("二级分类==="+queryCategoryLevel2);
				
				//获取三级分类
				if(queryCategoryLevel3==null || queryCategoryLevel3==""){
					queryCategoryLevel3="0";
				}
				System.out.println("三级分类==="+queryCategoryLevel3);
				
				//分页查询起始位置
				String offset=request.getParameter("pager.offset");
				if(offset==null){
					offset="0";
				}
				//每页显示条数
				int pageSize =5;
				//总记录数
				int totalCount = appInfoService.findCount(querySoftwareName, Integer.parseInt(queryStatus),Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3));
				System.out.println("总记录数======"+totalCount);
				//当前页
				int currentPageNo = (Integer.parseInt(offset)/5) +1;
				int currentPageNos=(currentPageNo-1)*pageSize;
				System.out.println("当前页======"+currentPageNo);
				//总页数
				int totalPageCount = (totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
				System.out.println("总页数======"+totalPageCount);
				
				//分页查询(高级查询)
				List<AppInfo> applist =appInfoService.findAllApp(currentPageNos, pageSize, querySoftwareName, Integer.parseInt(queryStatus), Integer.parseInt(queryFlatformId),Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3));
				if(applist.isEmpty()){
					System.err.println("1111111111");
				}else{
					System.err.println("2222222222222");
				}
				model.addAttribute("appInfoList",applist);
				
				//将数据传递到页面
				model.addAttribute("totalCount", totalCount);
				model.addAttribute("currentPageNo", currentPageNo);
				model.addAttribute("totalPageCount", totalPageCount);
				//查询条件回显
				model.addAttribute("querySoftwareName",querySoftwareName);
				model.addAttribute("queryStatus",queryStatus);
				model.addAttribute("queryFlatformId",queryFlatformId);
				model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
				model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
				model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
			
				//查询所有app状态
				List<DataDictionary> dataDictionarystatus =dataDictionaryService.findAllStatus_Flatform("APP_STATUS");
				model.addAttribute("statusList",dataDictionarystatus);
				//查询所有平台
				List<DataDictionary> dataDictionaryflateform =dataDictionaryService.findAllStatus_Flatform("APP_FLATFORM");
				model.addAttribute("flatFormList",dataDictionaryflateform);
				//查询所有一级分类
				List<AppCategory> categoryLevel1List=appCategoryService.findAllFenLei(100);
				model.addAttribute("categoryLevel1List", categoryLevel1List);
		
				return "backend/applist";
		
	}

	//查询二级分类
	@RequestMapping("one.json")
	@ResponseBody
	public String fenlei2(String pid,Model model){
			List<AppCategory> categoryLevel2List=appCategoryService.findAllFenLei(Integer.parseInt(pid));
			model.addAttribute("categoryLevel2List", categoryLevel2List);
			return JSONArray.toJSONString(categoryLevel2List);		
		}
		
	//查询三级分类
	@RequestMapping("two.json")
	@ResponseBody
	public String fenlei3(String pid,Model model){
			List<AppCategory> categoryLevel3List=appCategoryService.findAllFenLei(Integer.parseInt(pid));
			model.addAttribute("categoryLevel3List", categoryLevel3List);
			return JSONArray.toJSONString(categoryLevel3List);		
		}

	//查询app信息并审核--根据appid查看app信息，根据最新版本id查询版本信息 
	@RequestMapping("/appcheck.html")
	public String lookApp(String aid,String vid,Model model){
		//查询指定app信息
		AppInfo appInfo=appInfoService.appInfogetById(Integer.parseInt(aid));
		model.addAttribute("appInfo",appInfo);
		
		//查询最新版本信息
		AppVersion appVersion=appVersionService.getByNewVersion(Integer.parseInt(vid));
		model.addAttribute("appVersion",appVersion);
		
		return "backend/appcheck";	
	}
	
	//后台管理--审核状态修改
	@RequestMapping(value="/checksave")
	public String checkSave(AppInfo appInfo,String id,String vid){
		appInfoService.updateStatusgetByappId(appInfo);
		return "redirect:/backend/appcheck.html?aid="+id+"&vid="+vid;
	}
	
	//查询所有用户信息
	@RequestMapping("findAlldevUser.html")
	public String findAllDevUser(Model model){
		//查询所有用户信息
		List<DevUser> devUser=devUserService.findAlldevUser();
		model.addAttribute("devUser",devUser);
		return "backend/appuserlist";		
	}
		
	
}
