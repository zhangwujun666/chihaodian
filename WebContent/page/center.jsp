<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html class="mdd-page">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <meta name="charset" content="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title></title>
	<meta http-equiv="Cache-Control" content="must-revalidate,no-cache">
	<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
	
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/app_user.css">
</head>
<body>
<section class="content">
    <header class="user-info" style="background-color: #ABD13E;">
        <section class="user-basic" style="height: 120px">
        <c:forEach items="${user}" var="list">
        <img src="${list.head_img}">
        <div class="user-name">${list.realname}</div>
         </c:forEach>
        </section>
    </header>
    <div style="background-color: #F3F3F3;height: 10px"></div>
    <section class="f-section" style="line-height: 30px">
        
       <a class="slide-link" href="orderList.html">
            <img src="images/wd-012-1.png">
            全部订单
        </a>
     </section>
      <div style="background-color: #F3F3F3;height: 10px"></div>
        <section class="f-section" style="line-height: 30px">
               
                <a class="slide-link" href="addressList.html">
            <img src="images/wd-012-03.png">
            收货地址        </a>
                
               
            </section>
             <div style="background-color: #F3F3F3;height: 10px"></div>
        <section class="f-section">
                <a class="slide-link" href="tel:请填写客服电话">
            <img src="images/wd-012-08.png">
            客服反馈        <em>请填写客服电话</em>  </a>
         </section>
          <div style="background-color: #F3F3F3;height: 10px"></div>
         <a class="slide-link" href="cpsList.html">
            <img src="images/wd-012-02.png">
            我的优惠券        </a> 
           
    </section>
   
    <div style="height:80px"></div>
	<jsp:include page="footer4.jsp"></jsp:include>
</body></html>