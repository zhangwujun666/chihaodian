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
    public static String userLevel (int point){
        String level;
        if(point >= 0 && point <= 500){
            level = "BRONZE";
        }else if(point > 500 && point <=1000){
            level = "SLIVER";
        }else if(point > 1000 && point <=5000){
            level = "GOLD";
        }else if(point > 5000 && point <=10000){
            level = "PLATINUM";
        }else if(point > 10000 && point <=50000){
            level = "DIAMOND";
        }else{
            level = "KING";
        }
        return level;
    }

    public Integer updatePointShare (String open_id, int point){
        Integer status = 0;
        Map<String, String> map = new HashMap<>();
        /*分享出去后的页面被打开后加50 积分（待沟通）*/
        //TODO
        point = point + 50;
//        point = point.to
        map.put("open_id", open_id);
        map.put("point", point);
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
