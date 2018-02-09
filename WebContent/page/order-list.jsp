<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fn"
																		  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>

<div class="sjsc-title2">
	<h3 class="sjsc-t2l">我的订单</h3>
	<a href="center.html" class="sjsc-t2r"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px;"/></a>
</div>
<%--<div class="quanbu-title2" style="align-items: center">--%>
<%--<button id="openCard" onclick="demo()"style="margin: 0 auto; display: block;font-size: 15px;">打开我的卡包</button>--%>
<%--</div>--%>
<%--<div class="quanbu-title2" style="align-items: center">--%>
<%--<button id="openCard" onclick="demo1()"style="margin: 0 auto; display: block;font-size: 15px;">Demo</button>--%>
<%--</div>--%>
<%--<div>--%>
<%--<input id="cardCode" value="code">--%>
<%--</div>--%>
<ul class="quanbu-title2">
	<li class="current" style="display: inline;"><a href="JavaScript:;">全部</a></li>
	<li style="display: inline;"><a href="JavaScript:;">待支付</a></li>
	<li style="display: inline;"><a href="JavaScript:;">待发货</a></li>
	<li style="display: inline;"><a href="JavaScript:;">已发货</a></li>
	<div style="clear:both;"></div>
</ul>

<div class="my-dd">
	<div class="my-info">
		<c:forEach items="${map['list']}" var="list" varStatus="s">
			<c:set value="ord${s.index}" var="ord"></c:set>
			<div class="my-k1">
				<ul class="my-p1">
					<li class="my-spl f-l">${list.add_time}</li>
					<div>
						<input type="hidden" id="card_id" value="${list.goods_id }">
					</div>

					<li class="my-spr f-r">
						<c:if test="${list.status==0 }">待支付</c:if>
						<c:if test="${list.status==1 }">待发货</c:if>
						<c:if test="${list.status==2 }">已发货</c:if>
						<c:if test="${list.status==-5 }">退款中</c:if>
						<c:if test="${list.status==-6 }">已关闭</c:if>
					</li>
					<div style="clear:both;"></div>
				</ul>
				<c:forEach items="${map[ord]}" var="ordList">
					<dl class="my-dl1">
						<dt><a href="goodsListById.html?goods_id=${list.goods_id}"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
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
				<div class="my-p2">
					<span class="my-sp3 f-l">订单号：${list.order_id}</span>
					<c:if test="${list.status==0 }">
						<button class="my-btn1 f-r" onclick="window.location.href='payOrder.html?order_id=${list.order_id}'">支付：￥${list.goods_total}</button>
					</c:if>
					<c:if test="${list.status!=0}">
						<c:if test="${!empty list.express_hm}">
							<button class="my-btn1 f-r" onclick="window.location.href='order.html?order_id=${list.order_id}'">物流查询</button>
						</c:if>
						<button class="my-btn1 f-r" >￥${list.goods_total}</button>

						<c:if test="${list.status==1}">

							<button class="my-btn1 f-r" onclick="send('${list.order_id}')">退款</button>
							<c:if test="${list.card_status==0}">
								<c:if test="${!empty list.card_id}">
									<c:if test="${list.card_id != null}">
										<button style="background-color: #ff3c00; color:white; height:20px; width: 100%" class="my-btn1 f-r" onclick="card('${list.card_id}','${list.order_id}')">添加到我的微信卡包</button>
									</c:if>
								</c:if>
							</c:if>
							<%--<button class="my-btn1 f-r" onclick="demo('${list.order_id}')">demo</button>--%>
						</c:if>
					</c:if>
					<div style="clear:both;"></div>
				</div>
			</div>
		</c:forEach>

	</div>
	<div class="my-info" style="display:none;">
		<c:forEach items="${map['list0']}" var="list" varStatus="s">
			<c:set value="ord0${s.index}" var="ord"></c:set>
			<div class="my-k1">
				<ul class="my-p1">
					<li class="my-spl f-l">${list.add_time}</li>
					<li class="my-spr f-r">
						<c:if test="${list.status==0 }">待支付</c:if>
						<c:if test="${list.status==1 }">待发货</c:if>
						<c:if test="${list.status==2 }">已发货</c:if>
					</li>
					<div style="clear:both;"></div>
				</ul>
				<c:forEach items="${map[ord]}" var="ordList">
					<dl class="my-dl1">
						<dt><a href="goodsListById.html?goods_id=${list.goods_id}"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
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
				<div class="my-p2">
					<span class="my-sp3 f-l">订单号：${list.order_id}</span>
					<button class="my-btn1 f-r" onclick="window.location.href='payOrder.html?order_id=${list.order_id}'">支付：￥${list.goods_total}</button>
					<div style="clear:both;"></div>
				</div>
			</div>
		</c:forEach>


	</div>
	<div class="my-info" style="display:none;">
		<c:forEach items="${map['list1']}" var="list" varStatus="s">
			<c:set value="ord1${s.index}" var="ord"></c:set>
			<div class="my-k1">
				<ul class="my-p1">
					<li class="my-spl f-l">${list.add_time}</li>
					<li class="my-spr f-r">
						<c:if test="${list.status==0 }">待支付</c:if>
						<c:if test="${list.status==1 }">待发货</c:if>
						<c:if test="${list.status==2 }">已发货</c:if>
					</li>
					<div style="clear:both;"></div>
				</ul>
				<c:forEach items="${map[ord]}" var="ordList">
					<dl class="my-dl1">
						<dt><a href="goodsListById.html?goods_id=${list.goods_id}"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
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
				<div class="my-p2">
					<span class="my-sp3 f-l">订单号：${list.order_id}</span>

					<button class="my-btn1 f-r">￥${list.goods_total}</button>
					<c:if test="${list.status==1}">
						<button class="my-btn1 f-r" onclick="send('${list.order_id}')">退款</button>
					</c:if>
					<div style="clear:both;"></div>
				</div>
			</div>
		</c:forEach>

	</div>
	<div class="my-info" style="display:none;">
		<c:forEach items="${map['list2']}" var="list" varStatus="s">
			<c:set value="ord2${s.index}" var="ord"></c:set>
			<div class="my-k1">
				<ul class="my-p1">
					<li class="my-spl f-l">${list.add_time}</li>
					<li class="my-spr f-r">
						<c:if test="${list.status==0 }">待支付</c:if>
						<c:if test="${list.status==1 }">待发货</c:if>
						<c:if test="${list.status==2 }">已发货</c:if>
					</li>
					<div style="clear:both;"></div>
				</ul>
				<c:forEach items="${map[ord]}" var="ordList">
					<dl class="my-dl1">
						<dt><a href="goodsListById.html?goods_id=${list.goods_id}"><img src="${ordList.goods_img}" style="width: 68px"></a></dt>
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
				<div class="my-p2">
					<span class="my-sp3 f-l">订单号：${list.order_id}</span>
					<button class="my-btn1 f-r" onclick="window.location.href='order.html?order_id=${list.order_id}'">物流查询</button>
					<button class="my-btn1 f-r">￥${list.goods_total}</button>
					<div style="clear:both;"></div>
				</div>
			</div>
		</c:forEach>

	</div>
</div>



<jsp:include page="footer5.jsp"></jsp:include>

<script type="text/javascript">
	//				alert(location.href.split('#')[0])
	var targetUrl =location.href.split("#")[0];
	var urlTemp = '${map['list_wx'].url}';
	var timestamp1 = <%=request.getAttribute("timeStamp")%> ;

	var appId = '${map['list_wx'].appId}';
	var timestamp = '${map['list_wx'].timestamp}';
	var nonceStr = '${map['list_wx'].nonceStr}';
	var signature = '${map['list_wx'].signature}';
	var jsapi_ticket = '${map['list_wx'].jsapi_ticket}';
	var api_ticket = '${map['list_wx'].api_ticket}';

	//list_card_wx
	var signature_card = '${map['list_wx'].signature1}';
	var cardId = '${map['list_wx'].cardId}';
	var openId = '${map['list_wx'].openId}';


	wx.config({
		"debug": false,
		"appId": appId,
		"timestamp": timestamp,
		"nonceStr": nonceStr,
		"signature": signature,
		"jsApiList": [
			'addCard','chooseCard','openCard'
		]
	});
	function card(card_id, order_id){
		wx.ready(function(){
			//添加卡券
			$.ajax({
				url:'addCard.html',
				type:'post',
				data:'card_id=' + card_id + "&orderId=" + order_id,
				success:function(data){
					var data=JSON.parse(data);
					var card_nonce_str = data.nonceStr;
					var card_timestamp = data.timestamp;
					var card_signature = data.signature1;
					var cardVarText = '{"nonce_str":"' + card_nonce_str + '","code":"' + order_id + '","timestamp":"' + card_timestamp + '","signature":"' + card_signature + '"}';
					wx.addCard({
						cardList: [
							{
								cardId: card_id,
								cardExt: cardVarText,
							}
						],
						success: function (res){
//												alert('已添加卡券：' + JSON.stringify(res.cardList));
							var request = res.cardList
							var code = request[0].code;
							$('#cardCode').val(code);
							$.ajax({
								url:'cardStatus.html',
								type:'post',
								data:'order_id='+order_id
								+'&code='+code,
								success:function(){
									window.location.reload(true);
								}
							})
						},
						cancel: function (res) {
//												alert(JSON.stringify(res))
						}
					});
				},
			})
		});
	}
	function send(order_id){
		var  b = confirm('确定退款吗？');
		if(!b){
			return ;
		}
		$.ajax({
			url:'orderUpdate.html',
			type:'post',
			data:'order_id='+order_id+'&status=-5',
			success:function(rs){
				if(rs==1){
					alert("提交成功，我们将3个工作日内给您退款！");
					location.reload();
				}else{
					alert("失败，请联系客服！");
				}
			}
		})
	}
	function demo(){
		wx.ready(function(){
			//添加卡券
			$.ajax({
				url:'chooseCard.html',
				type:'post',
				data:'',
				success:function(data){
					var data=JSON.parse(data);
					var card_nonce_str = data.nonceStr;
					var card_timestamp = data.timestamp;
					var card_signature = data.signature2;

					wx.chooseCard({
//									cardType: 'GIFT', // 卡券类型
//									cardId: '', // 卡券Id
//									timestamp: card_timestamp, // 卡券签名时间戳
//									nonceStr: card_nonce_str, // 卡券签名随机串
//									signType: 'SHA1', // 签名方式，默认'SHA1'
//									cardSign: card_signature, // 卡券签名
						cardSign: card_signature,
						timestamp: card_timestamp,
						nonceStr: card_nonce_str,
						success: function (res) {
							res.cardList = JSON.parse(res.cardList);
							encrypt_code = res.cardList[0]['encrypt_code'];
							//alert('已选择卡券：' + JSON.stringify(res.cardList));
							decryptCode(encrypt_code, function (code) {
								codes.push(code);
							});
						},
						cancel: function (res) {
							//alert(JSON.stringify(res))
						}
					});
				}
			})
		});
	}
	function demo1() {
		var code = 'YS80Jt9mufFAqQjYziBQrZa6ULFiDcs45vUn3+069+g=';
		$.ajax({
			url:'cardDecode.html',
			type:'post',
			data:'code=' + code,
			success:function(data){
				var data=JSON.parse(data);
			}
		})
	}
</script>
</body>
</html>

