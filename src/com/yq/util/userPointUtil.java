package com.yq.util;
/**
 * Creat by John 2018/03/08
 */
public class userPointUtil {
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
}
