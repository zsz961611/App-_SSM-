package com.zsz.utils;
/**
 * 
 * <p>Title: Constants</p>
 * <p>Description: 工具类之常量值 </p>
 * @author MuGuijun 
 * @date 2017年6月7日 上午8:52:49
 */
public class Constants {
	/**
	 * 管理员存入session的key值
	 */
	public final static String USER_SESSION = "userSession";
	/**
	 * 开发者存入session的key值
	 */
	public final static String DEV_USER_SESSION = "devUserSession";
	/**
	 * 系统提示信息的key值
	 */
	public final static String SYS_MESSAGE = "message";
	/**
	 * 分页显示之每页显示的数据条数
	 */
	public final static int pageSize = 5;
	/**
	 * app上传错误提示信息一：APK信息不完整
	 */
	public final static String FILEUPLOAD_ERROR_1 = " * APK信息不完整！";
	/**
	 * app上传错误提示信息二：上传失败
	 */
	public final static String FILEUPLOAD_ERROR_2 = " * 上传失败！";
	/**
	 * app上传错误提示信息三：上传文件格式不正确
	 */
	public final static String FILEUPLOAD_ERROR_3 = " * 上传文件格式不正确！";
	/**
	 * app上传错误提示信息四：上传文件过大
	 */
	public final static String FILEUPLOAD_ERROR_4 = " * 上传文件过大！";
}
