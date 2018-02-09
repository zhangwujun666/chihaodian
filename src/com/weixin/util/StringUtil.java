package com.weixin.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weixin.entity.WxSetting;
import com.weixin.service.WxSettingService;

public class StringUtil {
//	final static  String appid = "wxe6473058b265185a"; wxffacb439e86f6377
//	final static  String appsecret = "2c1c6e31fbd8425c277e0d6279333dee"; cbcef8220cf134c1de794b81bafd52af
//	final static  String partner = "1306697601";
//	final static  String partnerkey = "yqkj1100yqkj1100yqkj1100yqkj1100";
	
	public WxSetting getSetting(){
		AbstractApplicationContext ctx   = new ClassPathXmlApplicationContext(new String []{"classpath:applicationContext.xml"});
		WxSettingService wxSettingService =(WxSettingService)ctx.getBean("wxSettingService") ;
		return wxSettingService.selectByPrimaryKey(1);
	}
	
	static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//创建菜单
	static String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"; // 网页授权获取用户信息接口
	static String token_url2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";// 全局accesstoken接口
	static String template_id_1 = "C6YRjqsvDLYFuVQbVHfAWKWPbXD8Ca_lSwiXG8cQQNY"; // 订单支付成功信息推送模板
	static String template_id_2 = "tjqPjlrB1vbXatR7_HhEefzjG1UNbacVTotD85J_ZR8	"; // 商品已发出通知
}
