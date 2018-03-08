package com.yq.controller;

import com.yq.entity.Category;
import com.yq.entity.Goods;
import com.yq.entity.User;
import com.yq.service.CategoryService;
import com.yq.service.GoodsService;
import com.yq.service.UserService;
import com.yq.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class StoreCtrl extends StringUtil {
	@Autowired
	private UserService userService;
	private User user = new User();

	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/page/store.html")
	public ModelAndView listByOppen_id(String oppen_id,HttpServletRequest request,HttpSession session) {
		String url = request.getSession().getServletContext().getRealPath("");
		oppen_id = getOppen_id(session);
		user.setOppen_id(oppen_id);
		List<User> list = userService.listById(user);
		String imgUrl = list.get(0).getHead_img();
		String qrCode = "http://qr.liantu.com/api.php?text="
				+ "http://www.mytpin.com/page/storeShare.html?open_id="+oppen_id
				+ "&w=300"
				+ "&logo="+imgUrl
				+ "&m=10"
				+ "&pt=ff3c00"
				;
		try {
			download(qrCode, oppen_id, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String iconPath = "img/"+oppen_id+".jpg";
		Map<String, String> img = new HashMap<>();
		img.put("iconPath", iconPath);
		img.put("qrCode", qrCode);
		ModelAndView ml = new ModelAndView();
		ml.addObject("user", list);
		ml.addObject("img", img);
		ml.setViewName("page/store");

		return ml;
	}

	/**
	 * 分享店铺
	 * @param oppen_id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/page/storeShare.html")
	public ModelAndView goodsListById(String oppen_id,HttpServletRequest request,HttpSession session) {
		oppen_id = getOppen_id(session);
		user.setOppen_id(oppen_id);
		List<User> list = userService.listById(user);

		ModelAndView ml = new ModelAndView();
		ml.setViewName("page/index");
		return ml;
	}


	public static void download(String urlString, String filename, String realUrl) throws Exception {
		String savePath = realUrl + "/page/img/";
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(5*1000);
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
	 	byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf=new File(savePath);
		if(!sf.exists()){
			sf.mkdirs();
			  }
		OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename+".jpg");
		// 开始读取
		while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
		}
		     // 完毕，关闭所有链接
			os.close();
			is.close();
	}

}
