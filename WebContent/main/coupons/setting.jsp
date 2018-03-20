<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<title>基本设置</title>

</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		参数设置 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
    <c:forEach items="${list}" var="list">
	<div class="pd-20" style="width: 80%" align="center">
			
			
			<div class="row cl">
						<label class="form-label col-2">有效分享一次商城添加积分：</label>
						<div class="formControls col-10">
							<input type="text" id="share_point"  value="${list.share_point}" class="input-text" style="width:40%">
						</div>
			</div>
			<br>		
			<div class="row cl">
						<label class="form-label col-2">每购买1元商品获得积分：</label>
						<div class="formControls col-10">
							<input type="text" id="order_point"  value="${list.order_point}" class="input-text" style="width: 40%">
						</div>
					</div>		
			<br>
					
			<div class="row cl">
						<label class="form-label col-2">兑换1元代金券需要积分：</label>
						<div class="formControls col-10">
							<input type="text" id="money_point"  value="${list.money_point}" class="input-text" style="width: 40%">
						</div>
					</div>		
			<br>
			<div class="row cl">
						<label class="form-label col-2">1级会员经验区间：</label>
						<div class="formControls col-10">
							<input type="text" id="one_start"  value="${list.one_start}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">~</span>
							<input type="text" id="one_end"  value="${list.one_end}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">——折扣参数：</span>
							<input type="text" id="one_sale"  value="${list.one_sale}" class="input-text" style="width: 10%">
							<span style="font-size: 20px">折</span>
						</div>
					</div>		
			<br>	
			<div class="row cl">
						<label class="form-label col-2">2级会员经验区间：</label>
						<div class="formControls col-10">
							<input type="text" id="two_start"  value="${list.two_start}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">~</span>
							<input type="text" id="two_end"  value="${list.two_end}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">——折扣参数：</span>
							<input type="text" id="two_sale"  value="${list.two_sale}" class="input-text" style="width: 10%">
							<span style="font-size: 20px">折</span>
						</div>
					</div>	
			<br>
			<div class="row cl">
						<label class="form-label col-2">3级会员经验区间：</label>
						<div class="formControls col-10">
							<input type="text" id="three_start"  value="${list.three_start}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">~</span>
							<input type="text" id="three_end"  value="${list.three_end}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">——折扣参数：</span>
							<input type="text" id="three_sale"  value="${list.three_sale}" class="input-text" style="width: 10%">
							<span style="font-size: 20px">折</span>
						</div>
					</div>
			<br>
			<div class="row cl">
						<label class="form-label col-2">4级会员经验区间：</label>
						<div class="formControls col-10">
							<input type="text" id="four_start"  value="${list.four_start}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">~</span>
							<input type="text" id="four_end"  value="${list.four_end}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">——折扣参数：</span>
							<input type="text" id="four_sale"  value="${list.four_sale}" class="input-text" style="width: 10%">
							<span style="font-size: 20px">折</span>
						</div>
					</div>
			<br>
			<div class="row cl">
						<label class="form-label col-2">5级会员经验区间：</label>
						<div class="formControls col-10">
							<input type="text" id="five_start"  value="${list.five_start}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">~</span>
							<input type="text" id="five_end"  value="${list.five_end}" class="input-text" style="width: 19%">
							<span style="font-size: 20px">——折扣参数：</span>
							<input type="text" id="five_sale"  value="${list.five_sale}" class="input-text" style="width: 10%">
							<span style="font-size: 20px">折</span>
						</div>
					</div>
			<br>

				<div class="col-10 col-offset-2">
				
						<button onClick="update()" id="butt"
						class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 提交
					</button>
				</div>
			</div>
    </c:forEach>

	<script type="text/javascript">
	
	function update(){
		var share_point=$('#share_point').val();
		var order_point=$('#order_point').val();
		var money_point=$('#money_point').val();
		var one_start=$('#one_start').val();
		var one_end=$('#one_end').val();
        var two_start=$('#two_start').val();
        var two_end=$('#two_end').val();
        var three_start=$('#three_start').val();
        var three_end=$('#three_end').val();
        var four_start=$('#four_start').val();
        var four_end=$('#four_end').val();
        var five_start=$('#five_start').val();
        var five_end=$('#five_end').val();
        var one_sale=$('#one_sale').val();
        var two_sale=$('#two_sale').val();
        var three_sale=$('#three_sale').val();
        var four_sale=$('#four_sale').val();
        var five_sale=$('#five_sale').val();

		$.ajax({
			url:'updateSetting.html',
			type:'post',
			data:'share_point='+share_point+'&order_point='+order_point+'&money_point='+money_point+'&one_start='+one_start+'&one_end='+one_end+'&two_start='+two_start+
				 '&two_end='+two_end+'&three_start='+three_start+'&three_end='+three_end+'&four_start='+four_start+'&four_end='+four_end+'&five_start='+five_start+
           		 '&five_end='+five_end+'&one_sale='+one_sale+'&two_sale='+two_sale+'&three_sale='+three_sale+'&four_sale='+four_sale+'&five_sale='+five_sale,
			success:function(rs){
				var data = eval('('+rs+')');
				if(data.rs==1){
					alert("添加成功！");
					window.location.reload();
				}else{
					alert("添加失败！"+data.message);
				}
			}
		})
	}
	
	</script>
</body>
</html>