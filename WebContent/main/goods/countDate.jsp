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
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript" src="js/customes.js"></script>
	<![endif]-->

<link rel="stylesheet" href="css/person.css">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>意见反馈</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div>
		<div id="" style="margin-top: 10%; margin-left: 5%; height: 600px; float: left; width: 10%;">
			<div class="row cl">

				<div class="formControls col-10">
					<label>开始时间：</label>
					<input type="text" id="start_time" onfocus="WdatePicker()" class="input-text Wdate"
						   placeholder="请填写开始时间" value="" class="input-text" >
				</div>
			</div>
			<br><br>

			<div class="row cl">

				<div class="formControls col-10">
					<label>截止时间：</label>
					<input type="text" id="end_time" onfocus="WdatePicker()" class="input-text Wdate"
						   placeholder="请填写截止时间" value="" class="input-text" >
				</div>
			</div>
			<br><br>


			<div class="row cl">

				<div class="formControls col-10">
					<input type="button" id="search" onclick="search()" value="查询" class="input-text">
				</div>
			</div>
			<br><br>

		</div>
		<p style="font-size: 25px; margin-top: 20px;">有效交易时间段分析</p>
		<div id="main" style="height:800px; float: left; width: 85%;"></div>
	</div>
	<%--<script type="text/javascript" src="js/echarts.min.js"></script>--%>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script src="js/customed.js"></script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function search() {
            var start_time = $('#start_time').val();
            var end_time = $('#end_time').val();
		    if (start_time != "" || end_time != "") {
                require.config({
                    paths: {
                        echarts: 'http://echarts.baidu.com/build/dist'
                    }
                });
                require(
                    [
                        'echarts',
                        'echarts/chart/pie'// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                    ],
                    function (ec) {
                        var url = location.search;
                        var i = url.indexOf("=");
                        var goods_id = url.substring(i+1);
                        // var goods_id = '105';
                        $.ajax({
                            url:'findcCountData.html',
                            type:'post',
                            async:'true',
                            data:'start_time=' + start_time + "&end_time=" + end_time,
                            success:function(rs){
                                var temp = JSON.parse(rs);
                                var res = new Array();
                                res = temp.countData;
                                var name = new Array();
                                var value = new Array();
                                for(var i = 0; i < res.length; i++){
                                    name.push(res[i]['name']);
                                    value.push(res[i]['value']);
                                }
                                // var dataViews=JSON.stringify(views);
                                // document.getElementById("views").value= dataViews;
                                // var dataCounts=JSON.stringify(counts);
                                // document.getElementById("count").value= dataCounts;
                                myChart.hideLoading();
                                myChart.setOption({
                                    tooltip : {
                                        trigger: 'item',
                                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                                    },
                                    legend: {
                                        orient: 'vertical',
                                        left: 'left',
                                        data: name
                                    },
                                    series : [
                                        {
                                            name: '单品名称',
                                            type: 'pie',
                                            radius : '40%',
                                            hoverAnimation: 'true',
                                            center: ['60%', '65%'],
                                            data: res,
                                            itemStyle: {
                                                emphasis: {
                                                    shadowBlur: 100,
                                                    shadowOffsetX: 0,
                                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                                }
                                            }
                                        }
                                    ]
                                });
                            }
                        })
                        var myChart = ec.init(document.getElementById('main'), 'customed');
                        myChart.showLoading();
                    }
                );
            }else{
                alert("请选择时间！");
			}
        }
	</script>
</body>
</html>