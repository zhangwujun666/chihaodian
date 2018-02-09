package com.weixin.pay.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weixin.entity.WxSetting;
import com.weixin.util.CommonUtil;
import com.weixin.pay.util.GetWxOrderno;
import com.weixin.pay.util.RequestHandler;
import com.weixin.pay.util.Sha1Util;
import com.weixin.pay.util.TenpayUtil;
import com.weixin.service.WxSettingService;
import com.yq.entity.Order;

import net.sf.json.JSONObject;

public class TopayServlet{
	private static  Logger log = Logger.getLogger(TopayServlet.class);
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public static String getPackage(List<Order> list,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
//		try {	
		AbstractApplicationContext ctx   = new ClassPathXmlApplicationContext(new String []{"classpath:applicationContext.xml"});
		WxSettingService wxSettingService =(WxSettingService)ctx.getBean("wxSettingService") ;
		WxSetting wxSetting  =  wxSettingService.selectByPrimaryKey(1);
		// 网页授权后获取传递的参数
		// String userId = request.getParameter("userId");
		// 商品描述根据情况修改
		String oppen_id = (String)session.getAttribute("oppen_id");
//		int ctg_id  = (int) map.get("ctg_id");
//		String addr_name =  (String) map.get("addr_name");
//		String goods_name = (String) map.get("goods_name");
//		String category = (String) map.get("category");
//		String body = addr_name+ category+ goods_name; //商品描述
//		String orderNo = (String) map.get("order_id");
//		String goods_price = (String) map.get("price");
//		String face =  (String) map.get("face");
//		String note =  (String) map.get("note");
//		float money = Float.parseFloat(goods_price);
//		int price = (int) (money*100);
//		String totalFee = price+"";
		
		// 商品描述根据情况修改
		String goods_name =list.get(0).getGoods_name();
		if(goods_name.contains("-=")){
			goods_name = goods_name.replace("-=", "");
		}
		String body = goods_name;
		String orderNo = list.get(0).getOrder_id()+"";
		float money = (float) list.get(0).getGoods_total();
		System.out.println("money=="+money);
		// String code = request.getParameter("code");
		// 金额转化为分为单位
//				float sessionmoney = Float.parseFloat(money);
//				String totalFee = String.format("%.2f", sessionmoney);
		int price = (int) (money*100);
		String totalFee = price+"";
		System.out.println("totalFee=="+totalFee);
		log.info("totalFee="+totalFee);
		String openId = (String) session.getAttribute("oppen_id");
		String notify_url = wxSetting.getLink()+"/page/noticeOrder.html";
		log.info("notify_url="+notify_url);
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 商户号
		String mch_id = wxSetting.getPartner();
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 附加数据
		String attach = "";
		// 商户订单号
		String out_trade_no = orderNo;

		String spbill_create_ip = "127.0.0.1";
//				request.getRemoteAddr();

		String trade_type = "JSAPI";
		String openid = openId;
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", wxSetting.getAppid());
		packageParams.put("mch_id", wxSetting.getPartner());
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(
				request,
				response);
		reqHandler.init(wxSetting.getAppid(), wxSetting.getAppsecret(), wxSetting.getPartnerkey());

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + wxSetting.getAppid() + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" + "<attach>" + attach
				+ "</attach>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ totalFee
				+ "</total_fee>"
				+
				// "<total_fee>"+finalmoney+"</total_fee>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openid + "</openid>" + "</xml>";
//		System.out.println(xml);
		// String allParameters = "";
		// try {
		// allParameters = reqHandler.genPackage(packageParams);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		
		log.info("packages="+packages);
		finalpackage.put("appId", wxSetting.getAppid());
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);

//		String packageStr = "\"appid\":\"" + wxSetting.getAppid() + "\","
//				+ "\"timeStamp\":\"" + timestamp + "\"," + "\"nonceStr\":\""
//				+ nonceStr2 + "\"," + "\"package\":\"" + packages + "\","
//				+ "\"signType\":\"" + "MD5\"" + "," + "\"paySign\":\""
//				+ finalsign + "\"";
		request.setAttribute("appId", wxSetting.getAppid());
		request.setAttribute("timeStamp", timestamp);
		request.setAttribute("nonceStr", nonceStr2);
		request.setAttribute("package", packages);
		request.setAttribute("signType", "MD5");
		request.setAttribute("paySign", finalsign);
		request.setAttribute("prepay_id", prepay_id);
		return "";

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	// public void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// doGet(request, response);
	// }

}
