<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="css/showTip.css">
	<script type="text/javascript" src="js/showTip.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

</head>

<body id="wrap">

<div class="sjsc-title1" style="border-bottom:1px solid #ABD13E">
	<h3 class="sjsc-t1l f-l"><a href="JavaScript:history.go(-1);"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px"/></a></h3>
	<%--<button class="sjsc-btn1 f-r" onclick="add()">保存</button>--%>
	<div style="clear:both;"></div>
</div>


<ul class="xzdz-ul1">
	<li>
		<p class="xzdz-p1 f-l">收货人</p>
		<input type="text" placeholder="姓名" class="xzdz-ipt1 f-l" id="addr_user"/>
		<div style="clear:both;"></div>
	</li>
	<li>
		<p class="xzdz-p1 f-l">手机号</p>
		<input type="text" placeholder="11位手机号" class="xzdz-ipt1 f-l" id="addr_tel"/>
		<div style="clear:both;"></div>
	</li>

	<li>
		<p class="xzdz-p1 f-l">详细地址</p>
		<p>
			<select id="province" name="province"  lang="16" class="input-text" style="width: 20%"></select>
			<select id="city" name="city" lang="1"  class="input-text" style="width: 20%"></select>
			<select id="area" name="area" lang="0" class="input-text" style="width: 20%"></select></p>
		<input type="text" placeholder="具体到街道门牌信息" class="xzdz-ipt1 f-l" id="addr_name" />
		<div style="clear:both;"></div>
	</li>

</ul>
</br>
</br>

<div style="align-items: center; height: 50px">
	<button id="openCard" onclick="chooseCard()"style="margin: 0 auto; display: block;font-size: 20px;">打开我的卡包</button>
</div>
</br>
</br>

<div style="align-items: center">
	<input id="cardInfo" value="请选择卡券" disabled="disabled" style="text-align:center; vertical-align:middle; width: 100%; font-size: 30px; color: #FF3C00; height: 50px">
	<input id="orderId" type="hidden" value="" disabled="disabled"  style="text-align:center; vertical-align:middle; width: 100%; font-size: 15px">
	<input id="cardCode" type="hidden" value="" disabled="disabled" style="text-align:center; vertical-align:middle; width: 100%; font-size: 15px">
	<input id="cardId" type="hidden" value="" disabled="disabled" style="text-align:center; vertical-align:middle; width: 100%; font-size: 15px">
</div>
</br>
</br>
<div class="quanbu-title2" style="align-items: center">
	<button style="width: 100%; margin: 0 auto; display: block; font-size: 20px; color: white; background-color: #ff3c00" onclick="add()">提交地址并使用卡券</button>
</div>
<%--<div class="quanbu-title2" style="align-items: center">--%>
	<%--<button style="visibility: hidden; width: 100%; margin: 0 auto; display: block; font-size: 20px; color: white; background-color: #ff3c00" onclick="demo()">demo</button>--%>
<%--</div>--%>
<script type="text/javascript" src="js/area.js"></script>
<script type="text/javascript">
	$(function(){
		init("province","city","area");
	});
</script>
<script type="text/javascript">
	function add() {
		var orderId =  $('#orderId').val();
		if(orderId == ""){
			showTip("请选择卡券");return;
		}

		var addr_user =$('#addr_user').val()
		if(addr_user==""){
			showTip("请填写姓名");return;
		}
		var addr_tel =$('#addr_tel').val();
		if(addr_tel==""){
			showTip("请填写手机号码");return;
		}
		var province =$('#province option:selected').text();
		if(province==0){
			showTip("请选择省份");return;
		}
		var city =$('#city option:selected').text();
		if(city==0){
			showTip("请选择城市");return;
		}
		var area =$('#area option:selected').text();
		if(area==0||area==null){
			showTip("请选择区县");return;
		}

		var addr_name =$('#addr_name').val();

		var order_addr = addr_user+' '+addr_tel+' '+province+' '+city+' '+ area+' '+addr_name;

		var orderId = $('#orderId').val();

		if(addr_name==""){
			showTip("请填写具体收货地址");return;
		}
				$.ajax({
					url : 'cardAddress.html',
					type : 'post',
					data : 'orderId='+ orderId
					     + '&order_addr=' + order_addr,
						success:function(data){
							var data=JSON.parse(data);
							var status = data.result;
							var card_code = $('#cardCode').val();
							var card_id = $('#cardId').val();
							if (status == 1) {
								$.ajax({
									url:'consume.html',
									type:'post',
									data:'card_code=' + card_code
										+"&card_id=" + card_id,
									success:function(data){
										var data = JSON.parse(data);
										var errmsg = data.errmsg;
										if (errmsg == 'ok'){
											alert("卡券使用成功！");
											window.location  = "index.html";
										}else{
											alert("系统故障！");
										}
									}
								})

							} else {
								alert("系统故障！");
							}
					},
				})
	}
</script>
<%--拉起卡券内容--%>
<script type="text/javascript">
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
	function chooseCard(){
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
						cardSign: card_signature,
						timestamp: card_timestamp,
						nonceStr: card_nonce_str,
						success: function (res) {
							res.cardList = JSON.parse(res.cardList);
							encrypt_code = res.cardList[0]['encrypt_code'];
//							alert('已选择卡券：' + JSON.stringify(res.cardList));
							alert('已选择卡券');
//									decryptCode(encrypt_code, function (code) {
//										codes.push(code);
//									});
							$.ajax({
								url:'cardDecode.html',
								type:'post',
								data:'code=' + encrypt_code,
								success:function(data){
									var data=JSON.parse(data);
									var decode = data.code;
									$.ajax({
										url:'findOrder.html',
										type:'post',
										data:'card_code=' + decode,
										success:function(data){
											var data=JSON.parse(data);
											var goodsName = data.goods_name;
											var orderId = data.order_id;
											var cardId = data.card_id;

											$('#cardInfo').val(goodsName);
											$('#orderId').val(orderId);
											$('#cardCode').val(decode);
											$('#cardId').val(cardId);
										}
									})
								}
							})

						},
						cancel: function (res) {
//							alert(JSON.stringify(res))
						}
					});
				}
			})
		});
	}
	function demo(){
		var card_code = '608440642243';
		$.ajax({
			url:'findOrder.html',
			type:'post',
			data:'card_code=' + card_code,
			success:function(data){
				var data=JSON.parse(data);
				var goodsName = data.goods_name;
				var orderId = data.order_id;
				$('#cardInfo').val(goodsName);
				$('#orderId').val(orderId);
				$('#cardCode').val(card_code);
			}
		})
	}

</script>
</body>
</html>
