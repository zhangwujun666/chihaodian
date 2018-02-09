package com.yq.util;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 21/01/2018.
 */
public class wxsign {
//    public static String accessToken = getAccessToken();
//    public static String jsapi_ticket = getTicket(accessToken);
    public static Map main(String url, String orderId) throws UnsupportedEncodingException {
        String accessToken = getAccessToken();
        String jsapi_ticket = getTicket(accessToken);
        String api_ticket = getApiTicket(accessToken);
        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://www.mytpin.com/page/orderList.html";
//        String url = getBackUrl();
        String cardId = "plR6d0kE1Xr3T7WZcCuJ9IcHK8OQ";
//        String url = "http://loaclhost:8080/page/orderList.html";

        //签名算法@微信官方
        Map<String, String> ret = sign(jsapi_ticket, api_ticket, url, cardId, orderId);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("currentTime=" + sdf.format(d));
        System.out.println("=========================================================Debug End============================================================");
        return ret;
    }

    /**
     * 获取从卡券（addCard）签名
     * @param cardId
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map getCardSignature(String cardId, String orderId) throws UnsupportedEncodingException {
        String accessToken = getAccessToken();
        String jsapi_ticket = getTicket(accessToken);
        String api_ticket = getApiTicket(accessToken);
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://www.mytpin.com/page/orderList.html";
//        String cardId = "plR6d0kE1Xr3T7WZcCuJ9IcHK8OQ";
//        String url = "http://loaclhost:8080/page/orderList.html";

        //签名算法@微信官方
        Map<String, String> ret1 = sign(jsapi_ticket, api_ticket, url, cardId, orderId);
        for (Map.Entry entry : ret1.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return ret1;
    }

    /**
     * 获取cardSign（chooseCard）
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map getCardSign() throws UnsupportedEncodingException {
        String accessToken = getAccessToken();
        String jsapi_ticket = getTicket(accessToken);
        String api_ticket = getApiTicket(accessToken);
        String url = "http://www.mytpin.com/page/orderList.html";
        String cardId = "";
        String orderId = "";
        // 注意 URL 一定要动态获取，不能 hardcode
        Map<String, String> ret2 = sign(jsapi_ticket, api_ticket, url, cardId, orderId);
        for (Map.Entry entry : ret2.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return ret2;
    }

        /**
         * 获取jsapi_ticket
         * @param
         * @return
         */
    public static String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";//这个url链接和参数不能变
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
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
    /**
     * 获取api_ticket
     * @John
     */
    public static String getApiTicket(String access_token){
        String apiTicket = "";
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
            InputStream is_card = http.getInputStream();
            int size = is_card.available();
            byte[] jsonBytes = new byte[size];
            is_card.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            apiTicket = demoJson.getString("ticket");
            is_card.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiTicket;
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

    public static Map<String, String> sign(String jsapi_ticket, String api_ticket, String url, String cardId, String orderId) {
        String AppId="wx81d1586561c7d12d";//第三方用户唯一凭证
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        long timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("=========================================================Debug Start============================================================");
        System.out.println("jsapi_signature_oringnal="+string1);
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

//        wx.addCard签名算法
        //TODO
        // 卡券code Demo

        Map<String, String> wx_card = new HashMap<>();
        wx_card.put("nonceStr", nonce_str);
        wx_card.put("cardId", cardId);
        wx_card.put("timestamp", String.valueOf(timestamp));
        wx_card.put("api_ticket",api_ticket);
        wx_card.put("code", orderId);

        String[] str1 = {"timestamp", "cardId", "api_ticket", "nonceStr", "code"};
        String[] valStr = new String[str1.length];
        String api_signature_original = "";
        for (int i = 0; i < str1.length; i++){
            valStr[i] = wx_card.get(str1[i]);
        }
        Arrays.sort(valStr);
        for (int j = 0; j < valStr.length; j++){
            api_signature_original += valStr[j];
        }
        String signature1 = DigestUtils.shaHex(api_signature_original);
        System.out.println("api_signature_original="+api_signature_original);

//        wx.chooseCard签名算法
//        String location_id = "485502837";

        Map<String, String> wx_cardSign = new HashMap<>();
        wx_cardSign.put("nonceStr", nonce_str);
//        wx_cardSign.put("cardId", "");
        wx_cardSign.put("timestamp", String.valueOf(timestamp));
        wx_cardSign.put("api_ticket",api_ticket);
//        wx_cardSign.put("location_id", location_id);
//        wx_cardSign.put("card_type", card_type);
        wx_cardSign.put("app_id", AppId);

        String[] str2 = {"nonceStr", "timestamp", "api_ticket", "app_id"};
        String[] valStr1 = new String[str2.length];
        String cardSign = "";
        for (int i = 0; i < str2.length; i++){
            valStr1[i] = wx_cardSign.get(str2[i]);
        }
        Arrays.sort(valStr1);
        for (int j = 0; j < valStr1.length; j++){
            cardSign += valStr1[j];
        }
        String signature2 = DigestUtils.shaHex(cardSign);
        System.out.println("cardSign="+cardSign);



//        数据注入
        ret.put("appId", AppId);
        ret.put("url", url);
        ret.put("cardId", cardId);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("api_ticket", api_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", String.valueOf(timestamp));
        ret.put("signature", signature);
        ret.put("signature1", signature1);
        ret.put("signature2", signature2);

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
    /** *//**
     * 获取完整的Url
     * @param request
     * @return
     * @throws Exception
     */
    public String getBackUrl(javax.servlet.http.HttpServletRequest request) throws Exception {
        String strBackUrl = "";
        try {
            strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath() + "?" + this.codeToString(request.getQueryString());
            strBackUrl = java.net.URLEncoder.encode(strBackUrl,"gbk");
        } catch(Exception e) {
            throw e;
        }
        return strBackUrl;
    }
    /** *//**
     * 格式化中文字符，防止出现乱码
     * @param str
     * @return
     */
    public String codeToString(String str) {
        String strString = str;
        try {
            byte tempB[] = strString.getBytes("ISO-8859-1");
            strString = new String(tempB);
            return strString;
        } catch (Exception e) {
            return strString;
        }
    }

    /**
     * 卡券code解码
     * @param code
     * @return
     */
    public static String code(String code){
        String accessToken = getAccessToken();
        String URL = "https://api.weixin.qq.com/card/code/decrypt?access_token="+accessToken ;

//        String temp = "YS80Jt9mufFAqQjYziBQrZa6ULFiDcs45vUn3+069+g=";
        String decode = null;
        try {
            decode = URLEncoder.encode(code, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("code=" + code);
        System.out.println("decode=" + decode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("encrypt_code", decode);
        String json = new Gson().toJson(map);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(json, "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                System.out.println("请求服务器成功，做相应处理");

            } else {

                System.out.println("请求服务端失败");

            }


        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        String cardCode = jsonObject.getString("code");
        System.out.println("cardCode=" + cardCode);
        return cardCode;
    }

    /**
     * 卡券核销接口
     * @param code
     * @return
     */
    public static String consume(String code, String card_id){
        String accessToken = getAccessToken();
        String URL = "https://api.weixin.qq.com/card/code/consume?access_token="+accessToken ;
//        String decode = null;
//        try {
//            decode = URLEncoder.encode(code, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println("code=" + code);
//        System.out.println("decode=" + decode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("card_id", card_id);
        String json = new Gson().toJson(map);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(json, "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                System.out.println("请求服务器成功，正在核销卡券");

            } else {

                System.out.println("请求服务端失败");

            }


        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        String errmsg = jsonObject.getString("errmsg");
        System.out.println("errmsg=" + errmsg);
        return errmsg;
    }

    /**
     * 创建卡券工具类
     * @param json
     * @return
     */
    public static String creatCard(String json){
        String accessToken = getAccessToken();
        String URL = "https://api.weixin.qq.com/card/create?access_token="+accessToken ;

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("code", code);
//        String json = new Gson().toJson(map);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(json, "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                System.out.println("请求服务器成功，创建卡券成功！");

            } else {

                System.out.println("请求服务端失败");

            }


        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
//        JSONObject jsonObject = JSONObject.fromObject(result);
//        String card_id = jsonObject.getString("card_id");
//        System.out.println("card_id=" + card_id);
        return result;
    }
}
