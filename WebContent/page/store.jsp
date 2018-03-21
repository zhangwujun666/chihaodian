<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/shoujisc.css">
<%--<script type="text/javascript" src="js/jquery.js"></script>--%>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
    <script type="text/javascript" src="js/jquery.qrcode.js" ></script>
    <script type="text/javascript" src="js/qrcode.js" ></script>
    <script type="text/javascript" src="js/utf.js" ></script>
<link rel="stylesheet" type="text/css" href="css/showTip.css">
<script type="text/javascript" src="js/showTip.js"></script>
</head>

<body id="wrap">
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="js/jquery.qrcode.js" ></script>
<script type="text/javascript" src="js/qrcode.js" ></script>
<script type="text/javascript" src="js/utf.js" ></script>
<script type="text/javascript" src="js/html2canvas.min.js"></script>
    <div class="sjsc-title2">
    	<h3 class="sjsc-t2l">我的店铺</h3>
        <a href="javascript:history.back();" class="sjsc-t2r"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px"/></a>
    </div>

    <ul class="gwc-ul1"  style=" text-align:center;">
        <%--<li>--%>
            <%--<p class="gwc-p1">店铺掌柜</p>--%>
            <%--<c:forEach items="${user}" var="list">--%>
                <%--&lt;%&ndash;<p>${list.oppen_id}</p>&ndash;%&gt;--%>
                <%--<div style="text-align:center;">--%>
                    <%--<img id="icon" src="${list.head_img}" style="width: 80px; vertical-align:middle; display: inline-flex;">--%>
                <%--</div>--%>
                <%--<div class="user-name">${list.realname}</div>--%>
                <%--<input id="image" type="hidden" value="${list.head_img}">--%>
                <%--<input id="open_id" type="hidden" value="${list.oppen_id}">--%>

            <%--</c:forEach>--%>

                <%--<div>--%>
                    <%--<span>头像</span>--%>
                    <%--<img id="icon" src="${img.iconPath}">--%>
                <%--</div>--%>
                <%--<div>--%>
                    <%--<p>我的店铺二维码</p>--%>
                <%--</div>--%>
        <%--</li>--%>
        <c:forEach items="${img}" var="list">
            <div>
                <img id="code" src="${img.iconPath}" style="vertical-align:middle; display: none;">
                <img id="shareImg" src="images/shareImg.png" style="display: none">
            </div>
        </c:forEach>
        <%--<li>--%>
            <%--<button id="btn-merge" onclick="date()">合成</button>--%>
        <%--</li>--%>
        <li style="background-color: #b8061f">
            <div id="qrCode" style="vertical-align:middle; display: inline-flex;"></div>
        </li>
        <li>
            <p style="font-size: 20px">长按图片分享店铺</p>
        </li>

    </ul>



    <script type="text/javascript">
        $(document).ready(function(){
            date();
        })

//使用h5的canvas实现两张图片的合并
//         var icon = $("#icon").attr('src');
//         var imgs = document.getElementById("scanPayImg").getElementsByTagName("img");
//         var src = imgs[0].src;
        var code = $('#code').attr('src');
        var data=["images/shareImg.png",code],base64=[];
        function date(){
            var Mycanvas=document.createElement("canvas"),
                ct=Mycanvas.getContext("2d"),
                len=data.length;
            Mycanvas.width=300;
            Mycanvas.height=500;
            ct.rect(0,0,Mycanvas.width,Mycanvas.height);
            ct.fillStyle='#fff';
            ct.fill();
            function draw(n){
                if(n<len){
                    var img=new Image;
                    img.crossOrigin = 'Anonymous'; //解决跨域
                    img.src=data[n];
                    console.log(data[n]);
                    img.onload=function(){
                        ct.drawImage(this,0,0,300,500,0,0,300,500);
                        draw(n+1);
                    }
                }else{
                    base64.push(Mycanvas.toDataURL("image/png"));
                    var img = base64[0];
                    document.getElementById("qrCode").innerHTML='<img src="'+img+'">';
                }
            }
            draw(0)
        }
//js img转换base64
//         function getBase64Image(img) {
//             var canvas = document.createElement("canvas");
//             canvas.width = img.width;
//             canvas.height = img.height;
//             var ctx = canvas.getContext("2d");
//             ctx.drawImage(img, 0, 0, img.width, img.height);
//             var dataURL = canvas.toDataURL("image/png");
//             return dataURL
//             // return dataURL.replace("data:image/png;base64,", "");
//         }
//
//
//         function main() {
//             var img = document.createElement('img');
//             // var imgs = $("#icon");
//             var imgs = document.getElementById("qrcodeCanvas").getElementsByTagName("img");
//             var src = imgs[0].src;
//             img.src = src;  //此处自己替换本地图片的地址
//             img.onload =function() {
//                 var data = getBase64Image(img);
//                 var img1 = document.createElement('img');
//                 img1.src = data;
//                 document.body.appendChild(img1);
//                 console.log(data);
//             }
//         }
//         main()


//     //获取网页中的canvas对象
//     var mycans=$('canvas')[0];
//     //调用convertCanvasToImage函数将canvas转化为img形式
//     var img=convertCanvasToImage(mycans);
//     //将img插入容器
//     $('#qr').append(img);




    </script>
</body>
</html>
