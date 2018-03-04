package com.yq.controller;

import com.yq.entity.Coupons;
import com.yq.entity.User;
import com.yq.service.CartService;
import com.yq.service.CouponsService;
import com.yq.service.UserService;
import com.yq.util.PageUtil;
import com.yq.util.StringUtil;
import org.apache.commons.lang.StringUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class StoreCtrl extends StringUtil {
	@Autowired
	private CouponsService couponsService;
	private Coupons coupons = new Coupons();
	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/page/store.html")
	public ModelAndView listByOppen_id(String oppen_id,
									   HttpServletRequest request,HttpSession session) {
		ModelAndView ml = new ModelAndView();
		ml.setViewName("page/store");
		return ml;
	}

}
