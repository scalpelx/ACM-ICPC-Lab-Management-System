<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css">
    <!-- For-Mobile-Apps-and-Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Simple Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //For-Mobile-Apps-and-Meta-Tags -->
</head>
<body>
<h1>济南大学ACM实验室管理系统</h1>
<div class="container w3">
    <h2>欢迎使用</h2>
    <form action="adminLogin" method="post">
        <div class="username">
            <span class="username" style="height:19px">账号：</span>
            <input type="text" name="admin.id" class="name" placeholder="" required="">
            <div class="clear"></div>
        </div>
        <div class="password-agileits">
            <span class="username" style="height:19px">密码：</span>
            <input type="password" name="admin.password" class="password" placeholder="" required="">
            <div class="clear"></div>
        </div>
        <div class="login-w3">
            <input type="submit" value="登录">
        </div>
    </form>
</div>
<h3 align="center" style="color: red">${AdminLoginError}</h3>
<div class="footer-w3l">
    <p>Copyright © 2018 UJN ACM Lab. All Rights Reserved.</p>
</div>
</body>
</html>