package com.yq.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yq.dao.UserSettingDao;
import com.yq.service.UserService;
import com.yq.service.UserSettingService;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yq.service.CouponsService;
import com.yq.util.StringUtil;
import com.yq.util.PageUtil;
import com.yq.entity.Coupons;
import com.yq.util.userPointUtil;

@Controller
@RequestMapping
public class CouponsCtrl extends StringUtil {
	@Autowired
	private CouponsService couponsService;
	private Coupons coupons = new Coupons();
	@Autowired
	private UserSettingService userSettingService;
	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/main/cpsAddjsp.html")
	public ModelAndView addjsp() {
		ModelAndView ml = new ModelAndView();
		ml.setViewName("main/coupons/add");
		return ml;
	}

	@RequestMapping(value = "/main/setting.html")
	public ModelAndView setting() {
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", userSettingService.list());
		ml.setViewName("main/coupons/setting");
		return ml;
	}

	@ResponseBody
	@RequestMapping(value = "/main/updateSetting.html")
	public Object uodateSetting(String share_point, String order_point,String money_point,String one_start,String one_end,
                              String two_start,String two_end,String three_start,String three_end,
                              String four_start,String four_end,String five_start,String five_end,
                              String one_sale,String two_sale,String three_sale,String four_sale,
                              String five_sale) {
		Map<String, String> map = new HashMap<>();
		map.put("share_point", share_point);
		map.put("order_point", order_point);
		map.put("money_point", money_point);
		map.put("one_start", one_start);
		map.put("one_end", one_end);
		map.put("two_start", two_start);
		map.put("two_end", two_end);
		map.put("three_start", three_start);
		map.put("three_end", three_end);
		map.put("four_start", four_start);
		map.put("four_end", four_end);
		map.put("five_start", five_start);
		map.put("five_end", five_end);
		map.put("one_sale", one_sale);
		map.put("two_sale", two_sale);
		map.put("three_sale",three_sale);
		map.put("four_sale", four_sale);
		map.put("five_sale", five_sale);
        Integer rs =  userSettingService.update(map);
        Map<String, String> rsMap = new HashMap<>();
		rsMap.put("rs", String.valueOf(rs));
		JSONObject jsonObject = JSONObject.fromObject(rsMap);
		String result = jsonObject.toString();
		return result;
	}


	@ResponseBody
	@RequestMapping(value = "/main/cpsInsert.html")
	public String insert(String cps_name, String cps_code, Float cps_price,
			String cps_time, Integer cps_level,String cps_points, String oppen_id) throws UnsupportedEncodingException {
		String ranStr = (int) (Math.random() * 90000) + 1000 + "";
		cps_name = java.net.URLDecoder.decode(cps_name,"utf-8") ;
		map.put("cps_points", cps_points);
		map.put("cps_name", cps_name);
		map.put("cps_code", ranStr);
		map.put("cps_price", cps_price);
		map.put("cps_time", cps_time);
		map.put("cps_level", 0);
		map.put("oppen_id", 0);
		map.put("status", 1);
		String rs = couponsService.insert(map) + "" ;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+rs);
		return rs;
	}

	@ResponseBody
	@RequestMapping(value = "/page/cpsInsert.html")
	public String pageInsert(String cps_name, Integer cps_id, Float cps_price,
			Integer cps_level, String oppen_id,
			HttpSession session) {
		String add_time =sf.format(new Date());
		oppen_id = getOppen_id(session);
	//	coupons.setCps_code(cps_code);
		coupons.setCps_id(cps_id);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>cps_id="+cps_id);
		coupons.setCps_time(add_time);
		coupons.setCps_level(0);
		coupons.setOppen_id("0");
		
		List<Coupons> cps = couponsService.listByCode(coupons); // 系统优惠券

        if(cps.size() > 0 ){
            Integer userCoupons = userPointUtil.useCouponsBuy(oppen_id, Integer.parseInt(cps.get(0).getCps_points()));
            if(userCoupons == 1) {
                coupons.setCps_level(cps_id);
                coupons.setOppen_id(oppen_id);
                List<Coupons> cpsUser = couponsService.listByCode(coupons); // 用户优惠券
                System.err.println(cpsUser.size());
                System.err.println(1);
                map.put("cps_name", cps.get(0).getCps_name());
                map.put("cps_code", cps.get(0).getCps_code());
                map.put("cps_price", cps.get(0).getCps_price());
                map.put("cps_time", cps.get(0).getCps_time());
                map.put("cps_level", cps.get(0).getCps_id());
                map.put("oppen_id", oppen_id);
                map.put("status", 1);
                return couponsService.insert(map) + "";
            }else{
                return "-6";    //用户积分不够
            }
        }else {
            return "-5";    //优惠券不存在
        }
	}

	@ResponseBody
	@RequestMapping(value = "/main/cpsUpdate.html")
	public Object update(String cps_name, String cps_code, Float cps_price,
			String cps_time, Integer cps_level, Integer cps_id) throws UnsupportedEncodingException {
		cps_name = java.net.URLDecoder.decode(cps_name,"utf-8") ;
		map.put("cps_name", cps_name);
		// map.put("cps_code", cps_code);
		map.put("cps_price", cps_price);
		map.put("cps_time", cps_time);
		map.put("cps_id", cps_id);
		return couponsService.update(map) + "";
	}
	@ResponseBody
	@RequestMapping(value = "/main/cpsDelete.html")
	public Object delete(Integer cps_id) {
		map.put("cps_id", cps_id);
		return couponsService.delete(map) + "";
	}

	@RequestMapping(value = "/main/cpsList.html")
	public ModelAndView list(
			@RequestParam(defaultValue = "1") Integer currentPage,
			HttpServletRequest request) {
		String add_time =sf.format(new Date());
		coupons.setCps_time(add_time);
		coupons.setOppen_id("");
		coupons.setCps_level(0);
		
		int total = couponsService.count(coupons);
		PageUtil.pager(currentPage, pagesize_1, total, request);
		coupons.setPageSize(pagesize_1);
		coupons.setCurrentNum(PageUtil.currentNum(currentPage, pagesize_1));
		
		List<Coupons> list = couponsService.list(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("cps", list);
		ml.setViewName("main/coupons/list");
		return ml;
	}
	
	@RequestMapping(value = "/page/cpsList.html")
	public ModelAndView listByOppen_id(String oppen_id,
			HttpServletRequest request,HttpSession session) {
		String add_time =sf.format(new Date());
		oppen_id = getOppen_id(session);
		coupons.setOppen_id(oppen_id);
		coupons.setCps_level(-1);
		coupons.setCps_time(add_time);
		List<Coupons> list = couponsService.list(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("page/coupons-list");
		return ml;
	}
	
	@RequestMapping(value = "/page/cpsAll.html")
	public ModelAndView listAll(String oppen_id) {
		String add_time =sf.format(new Date());
		coupons.setCps_time(add_time);
		coupons.setCps_level(0);
		
		List<Coupons> list = couponsService.listAll(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("page/cpsAll-list");
		return ml;
	}
	
	@RequestMapping(value = "/page/cartCoupons.html")
	public ModelAndView cartCoupons(String oppen_id,Integer addr_id ,
			@RequestParam(defaultValue = "1") Integer currentPage,
			HttpServletRequest request,HttpSession session) {
		String add_time =sf.format(new Date());
		
		coupons.setCps_time(add_time);
		oppen_id = getOppen_id(session);
		coupons.setOppen_id(oppen_id);
		coupons.setCps_level(-1);
		List<Coupons> list = couponsService.list(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.addObject("addr_id", addr_id);
		ml.setViewName("page/cart-coupons");
		return ml;
	}
	@RequestMapping(value = "/page/goodsCoupons.html")
	public ModelAndView goodsCoupons(Integer goods_id,Integer goods_num,String oppen_id,Integer addr_id ,
			@RequestParam(defaultValue = "1") Integer currentPage,
			HttpServletRequest request,HttpSession session) {
		String add_time =sf.format(new Date());
		oppen_id = getOppen_id(session);
		coupons.setOppen_id(oppen_id);
		coupons.setCps_time(add_time);
		List<Coupons> list = couponsService.list(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.addObject("addr_id", addr_id);
		ml.addObject("goods_id", goods_id);
		ml.addObject("goods_num", goods_num);
		ml.setViewName("page/goods-coupons");
		return ml;
	}
	
	@RequestMapping(value = "/main/cpsListById.html")
	public ModelAndView listById(Integer cps_id) {
		coupons.setCps_id(cps_id);
		List<Coupons> list = couponsService.listById(coupons);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("main/coupons/info");
		return ml;
	}
	
//	@RequestMapping(value = "/main/cartCpsById.html")
//	public ModelAndView cartCpsById(Integer cps_id) {
//		coupons.setCps_id(cps_id);
//		List<Coupons> list = couponsService.listById(coupons);
//		ModelAndView ml = new ModelAndView();
//		ml.addObject("list", list);
//		ml.setViewName("main/coupons/info");
//		return ml;
//	}
}
