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
    <div class="sjsc-title2">
    	<h3 class="sjsc-t2l">我的店铺</h3>
        <a href="javascript:history.back();" class="sjsc-t2r"><img src="images/back.png" alt="" style="width:20px;height: 20px;padding-top: 11px;padding-left: 5px"/></a>
    </div>

    <ul class="gwc-ul1"  style=" text-align:center;">
        <li onclick="">
            <p class="gwc-p1">店铺掌柜</p>
            <c:forEach items="${user}" var="list">
                <%--<p>${list.oppen_id}</p>--%>
                <div style="text-align:center;">
                    <%--<img id="icon" src="${list.head_img}" style="width: 80px; vertical-align:middle; display: inline-flex;">--%>
                </div>
                <div class="user-name">${list.realname}</div>
                <input id="image" type="hidden" value="${list.head_img}">
                <input id="open_id" type="hidden" value="${list.oppen_id}">

            </c:forEach>
            <c:forEach items="${img}" var="list">
                <div>
                    <span>头像</span>
                    <img id="icon" src="${img.iconPath}">
                </div>
            </c:forEach>
        </li>
        <%--<li>--%>
            <%--<p>Render in table</p>--%>
            <%--<div id="qrcodeTable"></div>--%>
        <%--</li>--%>
        <li>
            <p>保存二维码图片并分享我的店铺</p>
            <div hidden="hidden" id="qrcodeCanvas"></div>
            <div id="scanPayImg" style="vertical-align:middle; display: inline-flex;"></div>
        </li>

        <li>
            <button id="btn-merge" onclick="date()">合成</button>
        </li>
        <li>
            <img src="" style="width: 200px; vertical-align:middle; display: inline-flex;">
            <%--<img src="images/gray.jpg">--%>
            <%--<img src="images/11.png">--%>

        </li>
        <li>
            <div id="qrCode"></div>
        </li>
    </ul>



    <script type="text/javascript">
        var image = $('#image').val();
        var openId = $('#open_id').val();
        var shareUrl = "http://www.mytpin.com/page/storeShare.html?open_id="+openId;
        jQuery('#qrcodeTable').qrcode({
            render    : "table",                <!--二维码生成方式 -->
            text    : shareUrl , <!-- 二维码内容  -->
            width : "200",               //二维码的宽度
            height : "200",
        });
        jQuery('#qrcodeCanvas').qrcode({
            render    : "canvas",
            text    : shareUrl,
            width : "200",               //二维码的z宽度
            height : "200",              //二维码的高度
            background : "#ffffff",       //二维码的后景色
            foreground : "#000000",        //二维z码的前景色
            src: image            //二维码中间的图片
        });

        function convertCanvasToImage(canvas) {
            //新Image对象，可以理解为DOM
            var image = new Image();
            // canvas.toDataURL 返回的是一串Base64编码的URL，当然,浏览器自己肯定支持
            // 指定格式 PNG
            image.src = canvas.toDataURL("image/png");
            return image;
        }
        var mycanvas1=document.getElementsByTagName('canvas')[0]; //获取网页中的canvas对象
        var img=convertCanvasToImage(mycanvas1);
        $('#scanPayImg').html("");//移除已生成的避免重复生成
        $('#scanPayImg').append(img);//imagQrDiv表示你要插入的容器id


//使用h5的canvas实现两张图片的合并
//         $mergedImage = $('.img-boxs .merged')[0],
//         $mergedImage = $('.img-boxs .merged')[0],
        var icon = $("#icon").attr('src');
        var imgs = document.getElementById("scanPayImg").getElementsByTagName("img");
        var src = imgs[0].src;
        var data=["images/gray.jpg",src,icon],base64=[];
        function date(){
            var Mycanvas=document.createElement("canvas"),
                ct=Mycanvas.getContext("2d"),
                len=data.length;
            Mycanvas.width=300;
            Mycanvas.height=300;
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
                        ct.drawImage(this,0,0,300,300,0,0,300,300);
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
//             var imgs = document.getElementById("scanPayImg").getElementsByTagName("img");
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




    </script>
</body>
</html>
