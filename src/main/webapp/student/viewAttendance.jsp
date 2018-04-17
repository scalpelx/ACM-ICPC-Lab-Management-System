<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Student" %>
<%@ page import="entity.Attendance" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>济南大学ACM实验室管理系统</title>
    <meta name="keywords" content="设置关键词..."/>
    <meta name="description" content="设置描述..."/>
    <meta name="author" content="Scalpel"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script src="/javascript/jquery.js"></script>
    <script src="/javascript/plug-ins/customScrollbar.min.js"></script>
    <script src="/javascript/plug-ins/echarts.min.js"></script>
    <script src="/javascript/plug-ins/layerUi/layer.js"></script>
    <script src="/editor/ueditor.config.js"></script>
    <script src="/editor/ueditor.all.js"></script>
    <script src="/javascript/plug-ins/pagination.js"></script>
    <script src="/javascript/public.js"></script>
</head>
<body>
<div class="main-wrap">
    <div class="side-nav">
        <div class="side-logo">
            <div class="logo">
                <span class="logo-ico">
                    <i class="i-l-1"></i>
                    <i class="i-l-2"></i>
                    <i class="i-l-3"></i>
                </span>
                <strong>济大ACM实验室管理系统</strong>
            </div>
        </div>

        <nav class="side-menu content mCustomScrollbar" data-mcs-theme="minimal-dark">
            <h2>
                <a href="/student/main.jsp" class="InitialPage"><i class="icon-dashboard"></i>系统首页</a>
            </h2>
            <ul>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-table"></i>信息管理<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/studentView">查看个人信息</a>
                        </dd>
                        <dd>
                            <a href="/student/modifyInfo.jsp">修改个人信息</a>
                        </dd>
                        <dd>
                            <a href="/student/modifyPassword.jsp">修改密码</a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-list-alt"></i>考勤管理<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/viewAttendances?startDate=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>&endDate=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
                                查看考勤
                            </a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-inbox"></i>训练计划<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/listStudentTrains?scholar=<%= ((Student)session.getAttribute("student")).getScholar() %>">查看训练计划</a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-columns"></i>统计及评价<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="">日常表现统计</a>
                        </dd>
                        <dd>
                            <a href="">我的评价</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </nav>
        <footer class="side-footer">© 济南大学ACM实验室 版权所有</footer>
    </div>
    <% Student student = (Student) session.getAttribute("student"); %>
    <div class="content-wrap">
        <header class="top-hd">
            <div class="hd-lt">
                <a class="icon-reorder"></a>
            </div>
            <div class="hd-rt">
                <ul>
                    <li>
                        <a><i class="icon-user"></i>用户:<em><%= student.getName() %>
                        </em></a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="JsSignOut"><i class="icon-signout"></i>退出</a>
                    </li>
                </ul>
            </div>
        </header>
        <main class="main-cont content mCustomScrollbar">
            <div class="page-wrap">
                <!--开始::内容-->
                <section class="page-hd">
                    <header>
                        <h2 class="title"><%= student.getName()  %>考勤情况</h2>
                    </header>
                    <hr>
                </section>
                <form action="viewAttendances" method="post">
                    <div class="form-group-col-2">
                        <div class="form-label">选择查询区间</div>
                        <div class="form-cont">
                            <input type="date" class="form-control form-boxed" name="startDate" value=<%= session.getAttribute("startDate") %> style="width:200px;" required="">
                            至
                            <input type="date" class="form-control form-boxed" name="endDate" value=<%= session.getAttribute("endDate") %> style="width:200px;" required="">
                            <input type="submit" class="btn btn-primary" value="提交" />
                        </div>
                    </div>
                </form>
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>日期</th>
                        <th>签到时间</th>
                        <th>签退时间</th>
                        <th>出勤时间</th>
                    </tr>
                    </thead>
                    <%
                        List<Attendance> attendances = (List<Attendance>) session.getAttribute("attendances");
                        int i = 0;
                        double total = 0;
                        for (Attendance attendance : attendances) {
                    %>
                    <tr class="cen">
                        <td><%= ++i %></td>
                        <td><%= attendance.getDate() %></td>
                        <td><%= attendance.getArriveTime() %></td>
                        <td><%= attendance.getLeaveTime() %></td>
                        <%
                            long millis = attendance.getLeaveTime().getTime() - attendance.getArriveTime().getTime();
                            total += millis;
                        %>
                        <td><%= String.format("%02d:%02d:%02d", millis / 3600000 % 24, millis / 60000  % 60, millis / 1000 % 60) %></td>
                    </tr>
                    <%
                        }
                        total /= attendances.size();
                    %>
                </table>
                <div class="panel panel-default">
                    <div class="panel-bd capitalize">
                        平均出勤时间<%= String.format("%02d:%02d:%02d", (int) total / 3600000 % 24, (int) total / 60000  % 60, (int) total / 1000 % 60) %>
                    </div>
                </div>
                <!--开始::结束-->
            </div>
        </main>
        <footer class="btm-ft">
            <p class="clear">
                <span class="fl">©Copyright 2018 <a href="">University of Jinan ACM-ICPC Laboratory</a></span>
                <span class="fr text-info">
                    <em class="uppercase">
                        <i class="icon-user"></i>
                        author:Yongpeng Xie
                    </em> |
                    <em class="uppercase"><i class="icon-envelope-alt"></i>
                        xieyongpeng@mail.ujn.edu.cn
                    </em>
                </span>
            </p>
        </footer>
    </div>
</div>
</body>
</html>
