package com.yq.controller;

import com.yq.dao.ShareDao;
import com.yq.entity.Share;
import com.yq.entity.User;
import com.yq.service.ShareService;
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

import com.yq.util.userPointUtil;

@Controller
@RequestMapping
public class StoreCtrl extends StringUtil {
	@Autowired
	private UserService userService;
	private User user = new User();
	@Autowired
	private ShareService shareService;
	private Share share = new Share();

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
	 * @param open_id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/page/storeShare.html")
	public ModelAndView goodsListById(String open_id,HttpServletRequest request, HttpSession session) {
		String oppen_id = getOppen_id(session);
		//打开页面的user
		user.setOppen_id(oppen_id);
		//分享页面的user的积分
//		Integer point = userService.findPointByOppenId(open_id);
//		List<User> list = userService.listById(user);
		//添加积分(重复浏览不生效)
		Map<String, String> map = new HashMap<>();
		map.put("share_oppen_id", open_id);
		map.put("user_oppen_id", oppen_id);

		Integer count = shareService.countByOppenID(map);
		if(count < 1){
			updatePointShare(open_id);
			shareService.insert(map);
		}
        //添加记录
		ModelAndView ml = new ModelAndView();
		ml.setViewName("page/index");
		return ml;
	}

    /**
     * 下载类
     * @param urlString
     * @param filename
     * @param realUrl
     * @throws Exception
     */
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

    /**
     * 分享加积分
     * @param open_id
     * @return
     */
    public void updatePointShare (String open_id){
    	Integer point = userService.findPointByOppenId(open_id);
		Map<String, String> map = new HashMap<>();
        /*分享出去后的页面被打开后加50 积分（待沟通）*/
        //TODO
        point = point + 50;
        String addPoint = String.valueOf(point);
        map.put("open_id", open_id);
        map.put("point", addPoint);
        userService.updatepoint(map);
    }

    /**
     * 购买加积分
     * @param open_id
     * @param sum
     * @return
     */
    public void updatePointBuy (String open_id, Integer sum){
		Integer point = userService.findPointByOppenId(open_id);
		Map<String, String> map = new HashMap<>();
        point = point + sum;
        map.put("open_id", open_id);
        map.put("point", String.valueOf(point));
        userService.updatepoint(map);
    }

}
