package com.yq.controller;

import com.google.gson.Gson;
import com.yq.entity.*;
import com.yq.service.*;
import com.yq.util.PageUtil;
import com.yq.util.StringUtil;
import com.yq.util.userPointUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping
public class CountCtrl extends StringUtil {
	@Autowired
	private UserService userService;
	private User user = new User();
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	private Goods goods = new Goods();
	private Category category = new Category();
	@Autowired
	private CouponsService couponsService;
	private Coupons coupons = new Coupons();
	@Autowired
	private AddressService addressService;
	private Address address = new Address();
	@Autowired
	private FreightService freightService;
	private Freight freight = new Freight();
	@Autowired
	private CountService countService;
	private Count count = new Count();

	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/main/goodsCount.html")
	public ModelAndView goodscCount(Integer goods_id, HttpSession session) {
	    String oppen_id = getOppen_id(session);

		Count count = new Count();
		List<Map<String, String>> list = countService.countByGoodsId(goods_id);

		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("main/goods/count");
		return ml;
	}

    @RequestMapping(value = "/main/goodsCountAll.html")
    public ModelAndView goodsCountAll(HttpSession session) {
        String oppen_id = getOppen_id(session);

        Count count = new Count();
//        List<Map<String, String>> list = countService.countByGoodsId(goods_id);

        ModelAndView ml = new ModelAndView();
//        ml.addObject("list", list);
        ml.setViewName("main/goods/countAll");
        return ml;
    }

	@ResponseBody
	@RequestMapping(value = "/main/countData.html")
	public Object countData(Integer goods_id) {
		List<Map<String, String>> list = countService.countByGoodsId(goods_id);
		Map<String, String> data = new HashMap<>();
		List<Map<String, String>> res  = new ArrayList<>();
//		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
		for(int i = 0; i < list.size(); i++){
			data = list.get(i);
			String views = data.get("views");
//			String time = sdf.format(views);
			String count = String.valueOf(data.get("count"));
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("views", views);
			dataMap.put("count", count);
			res.add(dataMap);
  		}
//		for(int i=0; i < list.size(); i++){
//			String views = list.get(i).getViews();
//			String count = list.get(i).getCount();
//			map.put("countData", views);
//			map.put("count", count);
//		}
		Map<String, Object> map = new HashMap<>();
		map.put("countData", res);
		String json = new Gson().toJson(map);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String result = jsonObject.toString();
		return result;
	}

    @ResponseBody
    @RequestMapping(value = "/main/countDataAll.html")
    public Object countDataAll() {
        List<Map<String, String>> list = countService.countDataAll();
        Map<String, String> data = new HashMap<>();
        List<Map<String, String>> res  = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            data = list.get(i);
            String order_time = data.get("order_time");
            String count = String.valueOf(data.get("count"));
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("order_time", order_time);
            dataMap.put("count", count);
            res.add(dataMap);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("countData", res);
        String json = new Gson().toJson(map);
        JSONObject jsonObject = JSONObject.fromObject(json);
        String result = jsonObject.toString();
        return result;
    }
}
