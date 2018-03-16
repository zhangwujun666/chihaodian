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
		查看 <a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div id="main" style="height:400px;"></div>
	<%--<script type="text/javascript" src="js/echarts.min.js"></script>--%>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
        // function demo(goods_id){
        //     var goods_id = '105';
        //     $.ajax({
        //         url:'countData.html',
        //         type:'post',
        //         data:'goods_id='+ goods_id,
        //         success:function(rs){
        //             var temp = JSON.parse(rs);
        //             var res = temp.countData;
        //             var views = new Array();
        //             var counts = new Array();
        //             for(var i = 0; i < res.length; i++){
			// 			views.push(res[i]['views']);
        //                 counts.push(res[i]['count']);
			// 		}
        //             var dataViews=JSON.stringify(views);
        //             document.getElementById("views").value= dataViews;
        //             var dataCounts=JSON.stringify(counts);
        //             document.getElementById("count").value= dataCounts;
        //         }
        //     })
        //
        // }

        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/line',
				'echarts/chart/bar'   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            ],
            function (ec) {

                var goods_id = '105';
                $.ajax({
                    url:'countData.html',
                    type:'post',
                    async:'true',
                    data:'goods_id='+ goods_id,
                    success:function(rs){
                        var temp = JSON.parse(rs);
                        var res = temp.countData;
                        var views = new Array();
                        var counts = new Array();
                        for(var i = 0; i < res.length; i++){
                            views.push(res[i]['views']);
                            counts.push(res[i]['count']);
                        }
                        // var dataViews=JSON.stringify(views);
                        // document.getElementById("views").value= dataViews;
                        // var dataCounts=JSON.stringify(counts);
                        // document.getElementById("count").value= dataCounts;
                        myChart.hideLoading();
                        myChart.setOption({
                            tooltip : {
                                trigger: 'axis'
                            },
                            // legend: {
                            //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
                            // },
                            toolbox: {
                                show : true,
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            calculable : true,
                            xAxis : [
                                {
                                    type : 'category',
                                    boundaryGap : false,
                                    data : views,
									name : '浏览时间'
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value',
                                    name : '浏览量'
                                }
                            ],
                            series : [
                                {
                                    name:'浏览量',
                                    type:'line',
                                    stack: '总量',
                                    data: counts
                                }
                            ]
                        });
                    }
                })
                // var data1 = $("#views").val();
                // var data2 = $("#count").val();
                var myChart = ec.init(document.getElementById('main'));
                // var option = {
                //     tooltip : {
                //         trigger: 'axis'
                //     },
                //     // legend: {
                //     //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
                //     // },
                //     toolbox: {
                //         show : true,
                //         feature : {
                //             mark : {show: true},
                //             dataView : {show: true, readOnly: false},
                //             magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                //             restore : {show: true},
                //             saveAsImage : {show: true}
                //         }
                //     },
                //     calculable : true,
                //     xAxis : [
                //         {
                //             type : 'category',
                //             boundaryGap : false,
                //             data : $("#views").val()
                //         }
                //     ],
                //     yAxis : [
                //         {
                //             type : 'value'
                //         }
                //     ],
                //     series : [
                //         {
                //             name:'浏览量',
                //             type:'line',
                //             stack: '总量',
                //             data: $("#count").val()
                //         }
                //     ]
                // };
                myChart.showLoading();

            }
        );
	</script>
</body>
</html>