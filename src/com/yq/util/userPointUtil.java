package com.yq.util;

import com.yq.entity.User;
import com.yq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Creat by John 2018/03/08
 */
public class userPointUtil {
    @Autowired
    private UserService userService;
    private User user = new User();
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

    public Integer updatePointShare (String open_id, int point){
        Integer status = 0;
        Map<String, String> map = new HashMap<>();
        /*分享出去后的页面被打开后加50 积分（待沟通）*/
        //TODO
        point = point + 50;
        String addPoint = String.valueOf(point);
        map.put("open_id", open_id);
        map.put("point", addPoint);
        status = userService.updatepoint(map);
        return status;
    }

    public Integer updatePointBuy (String open_id, String point, String sum){
        Integer status = 0;
        Map<String, String> map = new HashMap<>();
        point = point +
        map.put("open_id", open_id);
        map.put("point", point);
        status = userService.updatepoint(map);
        return status;
    }
}
