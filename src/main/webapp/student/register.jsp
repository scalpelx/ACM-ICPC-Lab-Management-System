<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>济南大学ACM实验室管理系统注册</title>
    <link href="/css/register.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="Checkbox Background Signup Form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
</head>
<body>
<h2>济南大学ACM实验室管理系统</h2>
${registerError}
<form action="studentRegister" method="post">
    <div class="signup">
        <span class="ribben">注册用户</span>
        <p>学号 :<span class="dot"></span></p>
        <input type="text" name="student.scholar" placeholder="" required="">
        <p>密码 :<span class="dot"></span></p>
        <input type="password" name="student.passwd" placeholder="" required="">
        <p>姓名 :<span class="dot"></span></p>
        <input type="text" name="student.name" placeholder="" required="">
        <p>班级 :<span class="dot"></span></p>
        <input type="text" name="student.stdclass" placeholder="" required="">
        <p>性别 :<span class="dot"></span></p>
        <select name="student.sex">
            <option selected value="0">男</option>
            <option value="1">女</option>
        </select>
        <p>手机号 :<span class="dot"></span></p>
        <input type="text" name="student.phone" placeholder="" required="">
        <p>杭电账号 :<span class="dot"></span></p>
        <input type="text" name="student.hdu" placeholder="" required="">
        <input type="submit" value="注册">
    </div>
</form>
<div class="copyright">
    <p>Copyright © 2018 UJN ACM Lab. All Rights Reserved.</p>
</div>
</body>
</html>
