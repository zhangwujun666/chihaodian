package com.yq.util;

import com.yq.entity.User;
import com.yq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Creat by John 2018/03/08
 */
@Component //此处注解不能省却（0）
public class userPointUtil {
//    private static Logger logger = (Logger) LoggerFactory.getLogger(userPointUtil.class);
    @Autowired
    private UserService userService;
    private User user = new User();
    private static userPointUtil userPointUtil;
    public void setUserService(UserService  userService) {
        this.userService = userService;
    }
    @PostConstruct
    public void init() {
        userPointUtil = this;
        userPointUtil.userService = this.userService;

    }
    /**
     * 用户等级区分
     * @param point
     * @return
     */
    public static Map<String, String> userLevel (int point){
        String level;
        String name;
        Map<String, String> map = new HashMap<>();
        if(point >= 0 && point <= 500){
            level = "BRONZE";
            name = "青铜会员";
        }else if(point > 500 && point <=1000){
            level = "SLIVER";
            name = "白银会员";
        }else if(point > 1000 && point <=5000){
            level = "GOLD";
            name = "黄金会员";
        }else if(point > 5000 && point <=10000){
            level = "PLATINUM";
            name = "铂金会员";
        }else if(point > 10000 && point <=50000){
            level = "DIAMOND";
            name = "钻石会员";
        }else{
            level = "KING";
            name = "王者会员";
        }
        map.put("level", level);
        map.put("name", name);
        return map;
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

    public static Integer updatePointShare (String open_id){
        Integer addPointStatus;
        Integer addCouponsStatus;
        Integer status;

        //经验数据
        Map<String, String> mapPoint = new HashMap<>();
        /*分享出去后的页面被打开后加50 经验（待沟通）*/
        //TODO
        Integer point = userPointUtil.userService.findPointByOppenId(open_id);
        point = point + 50;
        String addPoint = String.valueOf(point);
        mapPoint.put("open_id", open_id);
        mapPoint.put("point", addPoint);
        addPointStatus = userPointUtil.userService.updatepoint(mapPoint);

        //积分数据
        /*分享出去后的页面被打开后加50 积分（待沟通）*/
        //TODO
        Integer coupons = userPointUtil.userService.findPointByOppenId(open_id);
        coupons = coupons + 50;
        String addCoupons = String.valueOf(coupons);
        Map<String, String> mapCoupons = new HashMap<>();
        mapCoupons.put("open_id", open_id);
        mapCoupons.put("coupons", addCoupons);
        addCouponsStatus = userPointUtil.userService.updateCoupons(mapCoupons);

        if(addPointStatus == 1 && addCouponsStatus == 1) {
            status = 1;
        }else {
            status = 0;
        }

        return status;
    }

    public static Integer updateCouponsBuy (String open_id, Integer sum){
        Integer addPointStatus;
        Integer addCouponsStatus;
        Integer status;

        //经验数据
        Map<String, String> mapPoint = new HashMap<>();
        /*分购买商品加经验（待沟通）*/
        //TODO
        Integer point = userPointUtil.userService.findPointByOppenId(open_id);
        point = point + sum;
        String addPoint = String.valueOf(point);
        mapPoint.put("open_id", open_id);
        mapPoint.put("point", addPoint);
        addPointStatus = userPointUtil.userService.updatepoint(mapPoint);

        //积分数据
        /*分购买商品加积分（待沟通）*/
        //TODO
        Integer coupons = userPointUtil.userService.findPointByOppenId(open_id);
        coupons = coupons + sum;
        String addCoupons = String.valueOf(coupons);
        Map<String, String> mapCoupons = new HashMap<>();
        mapCoupons.put("open_id", open_id);
        mapCoupons.put("coupons", addCoupons);
        addCouponsStatus = userPointUtil.userService.updateCoupons(mapCoupons);

        if(addPointStatus == 1 && addCouponsStatus == 1) {
            status = 1;
        }else {
            status = 0;
        }

        return status;
    }
}
