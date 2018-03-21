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
    <header class="user-info" style="background-image: url(images/head.png); background-size: 100%; background-repeat: no-repeat; height: 130px;">
        <section class="user-basic" style="height: 120px">
        <c:forEach items="${user}" var="list">
        <img src="${list.head_img}" style="">
            <%--<img src="${levelImg}" style="float: right;">--%>
        <div class="user-name">${list.realname}</div>
         </c:forEach>
        </section>
    </header>
    <%--<div style="background-color: #F3F3F3;height: 10px"></div>--%>

    <section style="line-height: 30px">
        <div style="background-color: #F3F3F3; padding: 10px 20px 0px;">
            <img src="images/card.png" style="width: 100%">
            <span style="position: absolute; top: 290px; left: 67px; color: white;">${name}</span>
        </div>
    </section>
    <%--<div style="background-color: #F3F3F3;height: 10px"></div>--%>

    <%--<section class="f-section" style="line-height: 30px">--%>
        <%--&lt;%&ndash;<c:forEach items="${user}" var="list">&ndash;%&gt;--%>
            <%--<div class="slide-link" style="background: white">--%>
                <%--<img src="images/wd-012-06.png">--%>
                <%--我的等级 : ${name}--%>
                <%--&lt;%&ndash;<img src="${levelImg}" style="float: right;">&ndash;%&gt;--%>
            <%--</div>--%>
        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
    <%--</section>--%>
    <%--<div style="background-color: #F3F3F3;height: 10px"></div>--%>

    <section class="f-section" style="line-height: 30px">
        <c:forEach items="${user}" var="list">
            <div class="slide-link" style="background: white">
             <img src="images/wd-012-04.png">
                我的经验值 :  ${list.point}
            </div>
        </c:forEach>
    </section>
    <div style="background-color: #F3F3F3;height: 10px"></div>

    <section class="f-section" style="line-height: 30px">
        <c:forEach items="${user}" var="list">
            <div class="slide-link" style="background: white">
                <img src="images/sjsc23-6.gif">
                优惠券积分 :  ${list.coupons}
            </div>
        </c:forEach>
    </section>
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
    <div style="background-color: #F3F3F3;height: 10px"></div>
        <section class="f-section">
            <a class="slide-link" href="store.html">
                <img src="images/sjsc23-3.gif">
                分享店铺 </a>
        </section>
    </section>
   
    <div style="height:80px"></div>
	<jsp:include page="footer4.jsp"></jsp:include>
</body></html>