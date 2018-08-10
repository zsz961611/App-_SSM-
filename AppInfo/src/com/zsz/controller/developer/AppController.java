package com.zsz.controller.developer;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.zsz.utils.Constants;


@Controller
@RequestMapping("/devapp")
public class AppController {

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
	@Qualifier("devUserService")
	private DevUserService devUserService;
	
	@Autowired
	@Qualifier("appVersionService")
	private AppVersionService appVersionService;
	
	//分页查询（高级查询app信息）
	@RequestMapping("/dev_applist.html")
	public String devapplist(String querySoftwareName,String queryStatus,String queryFlatformId,String queryCategoryLevel1,String queryCategoryLevel2,String queryCategoryLevel3,Model model,HttpServletRequest request) throws UnsupportedEncodingException{
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
		
		//下载量
		for (AppInfo appInfo : applist) {
			
			Double zongxiazai=((appInfo.getDownloads()/(double)appInfoService.count())*100);	
			;
			appInfo.setZongxiazai(zongxiazai.longValue());
		}
		for (AppInfo appInfo : applist) {
			System.err.println("下载量===="+appInfo.getZongxiazai());
			
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
		
		return "developer/appinfolist";	
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
	
	//跳转新增app基础信息页面
	@RequestMapping("/appinfoadd.html")
	public String appinfoadd(Model model){
		//查询所有平台
		List<DataDictionary> dataDictionaryflateform =dataDictionaryService.findAllStatus_Flatform("APP_FLATFORM");
		model.addAttribute("flatFormList",dataDictionaryflateform);
		//查询所有一级分类
		List<AppCategory> categoryLevel1List=appCategoryService.findAllFenLei(100);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		return "developer/appinfoadd";	
	}
	
	//新增操作app基础信息
	@RequestMapping("appinfoaddsave.html")
	public String appinfoaddsave(AppInfo appInfo,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException, ServletException{
			// 1、获取part对象
			Part part = request.getPart("a_logoPicPath");
			// 2、获取文件存储的路径
			String path = request.getServletContext().getRealPath("");// 系统目录
			String savePath = path + "/statics/images";
			/*System.out.println(savePath);*/
			// 3、获取文件名称:从请求头中截取文件名；
			//实际开发中：注意文件重名：时间戳+随机数+文件名
			long l = (long)(Math.random()*100000000);		
			String fileName =new Date().getTime()+"_"+l+"_"+ extractFileName(part);
			// 4、文件校验
			if (part.getSize() > 1024*1024) {
				session.setAttribute("fileUploadError", "文件太大！");
				return "developer/appinfoadd";// 跳转到新增页面
			}
			// 5、存储文件--》写到指定位置;File.separator:根据系统获取分割符号
			String lujing="D:/sd_work/AppInfo/WebContent/statics/uploadfiles" + File.separator + fileName;
			part.write("D:/sd_work/AppInfo/WebContent/statics/uploadfiles" + File.separator + fileName);
		
		// 6、存储到数据库--》set到对象中
		//查询当前登录用户的id
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int id=devUser.getId();
		
		appInfo.setCreationDate(new Date());
		appInfo.setLogoPicPath(fileName);
		appInfo.setLogoLocPath(lujing);
		
		appInfo.setCreatedBy(id);
		appInfo.setDevId(id);
		appInfo.setStatus(1);
		
		appInfoService.appInfoAdd(appInfo);
		return "redirect:/devapp/dev_applist.html";	
	}
	
    //上传截取字符串的方法
	 private String extractFileName(Part part) {  
	        String contentDisp = part.getHeader("content-disposition");  
	        String[] items = contentDisp.split(";");  
	        for (String s : items) {  
	            if (s.trim().startsWith("filename")) {  
	                return s.substring(s.indexOf("=") + 2, s.length()-1);  
	            }  
	        }  
	        return "";  
	    }  
	
	//删除app信息(同时删除对应版本信息)
	@RequestMapping("/delapp.json")
	@ResponseBody
	public String appDel(String id){
		HashMap<String,String> map=new HashMap<String,String>();
		if(null==id){
			map.put("delResult","notexist");
		}else{
			int num=appInfoService.appInfoDel(Integer.parseInt(id));
			appVersionService.versionDel(Integer.parseInt(id));
			if(num==0){
				map.put("delResult","false");			
			}else{
				map.put("delResult","true");	
			}
		}
		
		return JSONArray.toJSONString(map);		
	}
	
	//根据手机app查询
	@RequestMapping("/look")
	public String chaApp(String appid,Model model){
	
		AppInfo appInfo=appInfoService.appInfogetById(Integer.parseInt(appid));
		System.err.println("路径==="+appInfo.getLogoPicPath());
		model.addAttribute("appInfo",appInfo);
		
		List<AppVersion> appVersionList=appVersionService.versiongetById(Integer.parseInt(appid));
		
		model.addAttribute("appVersionList",appVersionList);
		
		return "developer/appinfoview";
	}
	
	//新增版本信息
	@RequestMapping("/appversionadd.html")
	public String appversionadd(String id,HttpSession session,Model model){
		//查询有关的所有历史版本
		List<AppVersion> appVersionList=appVersionService.versiongetById(Integer.parseInt(id));
		session.setAttribute("appVersionList",appVersionList);
		//将这一个appid放入model，用于传送页面，便于新增使用
		model.addAttribute("appId",id);
		return "developer/appversionadd";	
	}
	
	//新增最新版本信息操作
	@RequestMapping("/addversion.html")
	public String addVersion(AppVersion appVersion,String appId,HttpServletRequest request,HttpSession session,@RequestParam(value = "a_downloadLink", required = false) MultipartFile attach) throws IllegalStateException, IOException, ServletException{
		
		String downloadLink = null;
		String apkLocPath = null;
		String apkFileName = null;
		
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			System.out.println("上传指定路径  ============ " + path);
			
			String oldFileName = attach.getOriginalFilename();// 原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			if (prefix.equalsIgnoreCase("apk")){// apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				System.out.println("进入上传方法");
				// 使用从Session中拿到的id去查询APP信息 进而获取其APK名称
				apkName = appInfoService.appInfogetById(Integer.parseInt(appId)).getAPKName();
				System.out.println("获取到APKname"+apkName);
				apkFileName = apkName + "-" + appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path, apkFileName);
				// 判断路径是否存在
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 上传操作
				attach.transferTo(targetFile);
				downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
				apkLocPath = path + File.separator + apkFileName;
			}
		}
		
		//查询当前登录用户的id
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int id=devUser.getId();
		appVersion.setAppId(Integer.parseInt(appId));
		appVersion.setCreatedBy(id);
		appVersion.setCreationDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		
		appVersion.setApkFileName(apkFileName);
		appVersionService.addAppVersion(appVersion);
		
		//获取新增版本的主键
		int apid=appVersion.getId();
		
		AppInfo appInfo=new AppInfo(Integer.parseInt(appId), apid);
		appInfoService.updateapp_versionId(appInfo);
		return "redirect:/devapp/dev_applist.html";
	}
	
	//查看某App的所有历史版本以及最新版本（修改APP最新版本信息）--跳转到修改最新版本页面
	@RequestMapping("/appversionmodify.html")
	public String appversionupdate(String vid,String aid,Model model){
		System.out.println("-======="+vid);
		//查询app所属版本信息 历史版本
		List<AppVersion> appVersionList=appVersionService.versiongetById(Integer.parseInt(aid));
		
		model.addAttribute("appVersionList",appVersionList);

		//查询指定版本
		AppVersion appVersion=appVersionService.getByNewVersion(Integer.parseInt(vid));
		System.out.println("-======="+appVersion.getApkFileName());
		
		model.addAttribute("appVersion",appVersion);
		
		return "developer/appversionmodify";	
	}
	
	//修改最新版本操作 
	@RequestMapping("/updateVersion.html")
	public String updateversion(HttpServletRequest request,String appId,String id,AppVersion appVersion,HttpSession session,@RequestParam(value = "attach", required = false) MultipartFile attach) throws IllegalStateException, IOException, ServletException{
		System.out.println("版本id==="+id);
		
		String downloadLink = null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			System.out.println("进入上传方法");
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			System.out.println("上传指定路径  ============ " + path);
			String oldFileName = attach.getOriginalFilename();// 原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			if (prefix.equalsIgnoreCase("apk")) {// apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				// 使用从Session中拿到的id去查询APP信息 进而获取其APK名称
				apkName =appInfoService.appInfogetById(Integer.parseInt(appId)).getAPKName();
				System.out.println("APK名字");
				apkFileName = apkName + "-" + appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path, apkFileName);
				// 判断路径是否存在
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 上传操作
				attach.transferTo(targetFile);
				downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
				apkLocPath = path + File.separator + apkFileName;
			}
		}
		
		// 查询当前登录用户
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int ids=devUser.getId();
		
		appVersion.setModifyBy(ids);
		appVersion.setModifyDate(new Date());
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		appVersion.setDownloadLink(downloadLink);
		appVersionService.updateNewVersion(appVersion);
		
		return "redirect:/devapp/dev_applist.html";
	}
	
	//修改最新版本操作时，删除文件（logo图片/apk文件），并更新数据库（app_info/app_version）
	@RequestMapping("/delfile.json")
	@ResponseBody
	public String delAPK(String id,String flag){
		System.out.println("id==="+id);
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		String fileLocPath = null;
		
		//如果获取来的值都为空，那么返回一个失败操作
		if(flag == null || flag.equals("") ||
				id == null || id.equals("")){
				resultMap.put("result", "failed");
				
		}else if(flag.equals("logo")){//删除图片(操作App_Info)
			//根据appid查询logo路径，将其更新为空
			fileLocPath=appInfoService.appInfogetById(Integer.parseInt(id)).getLogoLocPath();
			File file = new File(fileLocPath);
			file.delete();
			//删除操作
			appInfoService.deleteAppLogo(Integer.parseInt(id));
			resultMap.put("result", "success");
		
		}else if(flag.equals("apk")){//（操作app_version）
			fileLocPath=appVersionService.getByNewVersion(Integer.parseInt(id)).getApkLocPath();		
			appVersionService.deleteApkFile(Integer.parseInt(id));
			// 创建流，删除物理文件
			File file = new File(fileLocPath);
			file.delete();
			resultMap.put("result", "success");
		}
		
		return JSONArray.toJSONString(resultMap);		
	}
	
	//修改app状态
	@RequestMapping(value="/updateStatus.json",method=RequestMethod.PUT)
	@ResponseBody
	public String updateStatus(String appId,String status,HttpSession session){
		System.out.println("进入状态修改的方法");
		System.out.println("app="+appId);
		System.out.println("状态id="+status);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		int id=Integer.parseInt(appId);
		//ajax，传到页面进入可操作,0为可进行正常操作
		resultMap.put("errorCode","0");
		
		// 查询当前登录用户
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int createdBy=devUser.getId();
		
		int statusid=Integer.parseInt(status);
		if(statusid==2 || statusid==4){
			
			AppInfo appInfo=new AppInfo(id,createdBy,5);
			appInfoService.updateStatusgetByappId(appInfo);

			resultMap.put("resultMsg","success");
			
		}else if(statusid==2 || statusid==5){
			
			AppInfo appInfo=new AppInfo(id,createdBy,4);
			appInfoService.updateStatusgetByappId(appInfo);
			
			resultMap.put("resultMsg","success");
			
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	//跳转到修改app页面
	@RequestMapping("/appinfomodify.html")
	public String appmodify(Model model,String id,HttpSession session){
		System.out.println("id==="+id);
		//查询所有平台
		List<DataDictionary> dataDictionaryflateform =dataDictionaryService.findAllStatus_Flatform("APP_FLATFORM");
		model.addAttribute("flatFormList",dataDictionaryflateform);
		//查询指定app信息
		AppInfo appInfo=appInfoService.appInfogetById(Integer.parseInt(id));
		model.addAttribute("appInfo",appInfo);
		
		return "developer/appinfomodify";
	}

	//修改app操作
	@RequestMapping("/updateInfo.html")
	public String updateInfo(HttpServletRequest request,AppInfo appInfo,HttpSession session) throws IllegalStateException, IOException, ServletException{
		// 1、获取part对象
		Part part = request.getPart("attach");
		// 2、获取文件存储的路径
		String path = request.getServletContext().getRealPath("");// 系统目录
		
		//实际开发中：注意文件重名：时间戳+随机数+文件名
		long l = (long)(Math.random()*100000000);		
		String fileName =new Date().getTime()+"_"+l+"_"+ extractFileName(part);
		// 4、文件校验
		if (part.getSize() > 1024*1024) {
			session.setAttribute("fileUploadError", "文件太大！");
			return "developer/appinfoadd";// 跳转到新增页面
		}
		// 5、存储文件--》写到指定位置;File.separator:根据系统获取分割符号
		String lujing="D:/sd_work/AppInfo/WebContent/statics/uploadfiles" + File.separator + fileName;
		part.write("D:/sd_work/AppInfo/WebContent/statics/uploadfiles" + File.separator + fileName);
		
		// 6、存储到数据库--》set到对象中
		//查询当前登录用户的id
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int id=devUser.getId();
				
		appInfo.setModifyDate(new Date());
		appInfo.setLogoPicPath(fileName);
		appInfo.setLogoLocPath(lujing);
				
		appInfo.setModifyBy(id);
		appInfo.setDevId(id);
		
		int appid=appInfo.getId();
		
		appInfoService.updateInfo(appInfo);
		
		return "redirect:/devapp/appinfomodify.html?id="+appid;		
	}
		
	
}
