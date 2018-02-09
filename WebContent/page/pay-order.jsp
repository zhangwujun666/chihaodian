<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/woxiangyao.js"></script>
</head>

<body>

    <div class="sjsc-title2">
    	<h3 class="sjsc-t2l">确认付款</h3>
        <a href="javascript:window.location.href='orderList.html'" class="sjsc-t2r"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px"/></a>
    </div>
    
    <div class="my-dd">
    	<div class="my-info">
    	<c:forEach items="${map['list']}" var="list" varStatus="s">
    	<c:set value="ord${s.index}" var="ord"></c:set>
        	<div class="my-k1">
            	<ul class="my-p1">
                	<li class="my-spl f-l">订单号：${list.order_id}</li>
                	<li class="my-spr f-r">${list.add_time}</li>
                	
                    <div style="clear:both;"></div>
                </ul>
    
   
                <c:forEach items="${map[ord]}" var="ordList">
                <dl class="my-dl1">
                	<dt><a href="#"><img src="${ordList.goods_img}" style="width:70px"></a></dt>
                    <dd>
                    	<h3><a href="#">${ordList.goods_name}</a></h3>
                        <p class="my-dp1">价格：<span>￥${ordList.goods_price}</span></p>
                        <div class="my-jdt">
                        	<p class="jdt-p1 f-l">数量：</p>
                           
                            <p class="jdt-shuzi f-l">${ordList.goods_num}</p>
                    		<div style="clear:both;"></div>
                        </div>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                </c:forEach>
    <div class="drdd-info2">
    	<p class="p1 f-l">地址：<span >${list.addr_name}</span></p>
        <div style="clear:both;"></div>
    </div> 
    <div class="drdd-info2">
    <p class="p1 f-l">配送方式：<span>
    	<c:if test="${list.receive==''}">快递</c:if>
    	<c:if test="${list.receive!=''}">自提点：${list.receive}</c:if></span></p>
        <div style="clear:both;"></div>
    </div>
    <c:if test="${!empty list.note}">
    <div class="drdd-info2">
    	<p class="p1 f-l">备注：<span >${list.note}</span></p>
        <div style="clear:both;"></div>
    </div> 
    </c:if>
                <div class="my-p2">
                	<span class="my-sp3 f-l">共${list.goods_total_num}件商品</span>
                   <p class="my-sp3 f-r">总计：￥${list.goods_total}</p>
                    <div style="clear:both;"></div>
                </div>
            </div>
           </c:forEach> 
        </div>
        <c:forEach items="${map['list']}" var="list">
        <c:if test="${list.goods_total==0}">
        <button class="drdd-btn" onclick="window.location.href='orderUpdate.html?order_id=${list.order_id}'">确认下单</button>
        </c:if>
         <c:if test="${list.goods_total>0}">
        <button class="drdd-btn" onclick="callpay()">微信支付</button>
        </c:if>
        
        </c:forEach>
    </div>
    	<script type="text/javascript">
  	function callpay(){
  		
		 WeixinJSBridge.invoke('getBrandWCPayRequest',{
			 "appId" : "<%=request.getAttribute("appId")%>","timeStamp" : "<%=request.getAttribute("timeStamp")%>", "nonceStr" : "<%=request.getAttribute("nonceStr")%>", "package" : "<%=request.getAttribute("package")%>","signType" : "MD5", "paySign" : "<%=request.getAttribute("paySign")%>" 
   			},function(res){
				WeixinJSBridge.log(res.err_msg);
// 				alert(res.err_code + res.err_desc + res.err_msg);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	                alert("微信支付成功!");  
	                window.location.href='orderList.html';
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	                alert("用户取消支付!");  
	            }else{  
	               alert("支付失败!");  
	            }  
			})
		}
  </script>
</body>
</html>
