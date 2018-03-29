package com.yq.util;

import net.sf.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 18/01/2018.
 */
@SuppressWarnings("deprecation")
public class WeiXinUtil {
    public static Map main() throws UnsupportedEncodingException {
        String accessToken = getAccessToken();
        String api_ticket = getCardTicket(accessToken);
        String cardId = "plR6d0kE1Xr3T7WZcCuJ9IcHK8OQ";
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://www.mytpin.com/page/orderList.html";
//        String url = "http%3A%2F%2Fwww.mytpin.com%2Fpage%2ForderList.html";
//        String url = "http://loaclhost:8080/page/orderList.html";
        Map<String, String> ret = sign(api_ticket, cardId);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));
        System.out.println("=========================================================Debug End============================================================");
        return ret;
    }
    /**
     * 获取jsapi_ticket
     * @param
     * @return
     */
    public static String getCardTicket(String access_token) {
        String card_ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=wx_card";//这个url链接和参数不能变
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream isCard = http.getInputStream();
            int size = isCard.available();
            byte[] jsonBytes = new byte[size];
            isCard.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            card_ticket = demoJson.getString("ticket");
            isCard.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card_ticket;
    }

    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken() {
        String access_token = "";
        String grant_type = "client_credential";//获取access_token填写client_credential
        String AppId="wx81d1586561c7d12d";//第三方用户唯一凭证
        String secret="8cf96e67ad48b7444df12abaa7540414";//第三方用户唯一凭证密钥，即appsecret
        //这个url链接地址和参数皆不能变
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+AppId+"&secret="+secret;

        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }

    public static Map<String, String> sign(String api_ticket,String cardId) {
        String AppId="wx81d1586561c7d12d";//第三方用户唯一凭证
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        long timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = timestamp + nonce_str + api_ticket + cardId ;
        System.out.println("=========================================================Debug Start============================================================");
        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        ret.put("appId", AppId);
//        ret.put("openId", openId);
        ret.put("api_ticket", api_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", String.valueOf(timestamp));
        ret.put("cardId", cardId);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
    }

    private static Long create_timestamp() {
//        return Long.toString(System.currentTimeMillis() / 1000);
        return System.currentTimeMillis() / 1000;
    }
}




