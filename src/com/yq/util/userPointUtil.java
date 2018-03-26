package com.yq.util;

import com.yq.entity.User;
import com.yq.entity.UserSetting;
import com.yq.service.UserService;
import com.yq.service.UserSettingService;
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
import java.util.List;
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

    @Autowired
    private UserSettingService userSettingService;
    private UserSetting userSetting;
    public void setUserSettingService(UserSettingService userSettingService) {this.userSettingService = userSettingService; }

    @PostConstruct
    public void init() {
        userPointUtil = this;
        userPointUtil.userService = this.userService;
        userPointUtil.userSettingService = this.userSettingService;
    }
    /**
     * 用户等级区分
     * @param point
     * @return
     */
    public static Map<String, String> userLevel (int point){
        String level;
        String name;

        List<UserSetting> userSettings = userPointUtil.userSettingService.list();

        Integer oneStart = Integer.parseInt(userSettings.get(0).getOne_start());
        Integer oneEnd = Integer.parseInt(userSettings.get(0).getOne_end());
        Integer twoStart = Integer.parseInt(userSettings.get(0).getTwo_start());
        Integer twoEnd = Integer.parseInt(userSettings.get(0).getTwo_end());
        Integer threeStart = Integer.parseInt(userSettings.get(0).getThree_start());
        Integer threeEnd = Integer.parseInt(userSettings.get(0).getThree_end());
        Integer fourStart = Integer.parseInt(userSettings.get(0).getFour_start());
        Integer fourEnd = Integer.parseInt(userSettings.get(0).getFour_end());
        Integer fiveStart = Integer.parseInt(userSettings.get(0).getFive_start());
        Integer fiveEnd = Integer.parseInt(userSettings.get(0).getFive_end());

        Map<String, String> map = new HashMap<>();
        if(point >= oneStart && point <= oneEnd){
            level = "BRONZE";
            name = "铜卡会员";
        }else if(point > twoStart && point <= twoEnd){
            level = "SLIVER";
            name = "银卡会员";
        }else if(point > threeStart && point <= threeEnd){
            level = "GOLD";
            name = "金卡会员";
        }else if(point > fourStart && point <= fourEnd){
            level = "PLATINUM";
            name = "铂金会员";
        }else if(point > fiveStart && point <= fiveEnd){
            level = "DIAMOND";
            name = "钻石会员";
        }else{
            level = "KING";
            name = "至尊会员";
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

    /**
     * 分享积分经验添加工具类
     * @param open_id
     * @param sharePoint
     * @return
     */
    public static Integer updatePointShare (String open_id, String sharePoint){
        Integer addPointStatus;
        Integer addCouponsStatus;
        Integer status;
        Integer share = Integer.parseInt(sharePoint);

        //经验数据
        Map<String, String> mapPoint = new HashMap<>();
        /*分享出去后的页面被打开后加经验（后台自定义）*/
        //FIXED
        Integer point = userPointUtil.userService.findPointByOppenId(open_id);
        point = point + share;
        String addPoint = String.valueOf(point);
        mapPoint.put("open_id", open_id);
        mapPoint.put("point", addPoint);
        addPointStatus = userPointUtil.userService.updatepoint(mapPoint);

        //积分数据
        /*分享出去后的页面被打开后加积分（后台自定义）*/
        //FIXED
        Integer coupons = userPointUtil.userService.findCouponsByOppenId(open_id);
        coupons = coupons + share;
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

    /**
     * 购买积分经验更新工具类
     * @param open_id
     * @param sum
     * @return
     */
    public static Integer updateCouponsBuy (String open_id, Integer sum){
        Integer addPointStatus;
        Integer addCouponsStatus;
        Integer status;

        List<UserSetting> userSettings = userPointUtil.userSettingService.list();
        Integer orderPoint = Integer.parseInt(userSettings.get(0).getOrder_point());

        //经验数据
        Map<String, String> mapPoint = new HashMap<>();
        /*分购买商品加经验（后台自定义）*/
        //FIXED
        Integer point = userPointUtil.userService.findPointByOppenId(open_id);
        point = point + (sum * orderPoint);
        String addPoint = String.valueOf(point);
        mapPoint.put("open_id", open_id);
        mapPoint.put("point", addPoint);
        addPointStatus = userPointUtil.userService.updatepoint(mapPoint);

        //积分数据
        /*分购买商品加积分（后台自定义）*/
        //FIXED
        Integer coupons = userPointUtil.userService.findCouponsByOppenId(open_id);
        coupons = coupons + (sum * orderPoint);
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

    /**
     * 兑换优惠券工具类
     * @param open_id
     * @param use
     * @return
     */
    public static Integer useCouponsBuy (String open_id, Integer use){
        Integer addCouponsStatus;
        //积分数据
        /*兑换优惠券扣除积分（后台自定义）*/
        //FIXED
        Integer coupons = userPointUtil.userService.findCouponsByOppenId(open_id);
        if(coupons >= use){
            coupons = coupons - use;
            String addCoupons = String.valueOf(coupons);
            Map<String, String> mapCoupons = new HashMap<>();
            mapCoupons.put("open_id", open_id);
            mapCoupons.put("coupons", addCoupons);
            addCouponsStatus = userPointUtil.userService.updateCoupons(mapCoupons);
        }else{
            addCouponsStatus = 0;
        }
        return addCouponsStatus;
    }
}
