<%@ page import="entity.Admin" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Train" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>济南大学ACM实验室管理系统</title>
    <meta name="keywords"  content="设置关键词..." />
    <meta name="description" content="设置描述..." />
    <meta name="author" content="Scalpel" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
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
                <a href="/admin/admin.jsp" class="InitialPage"><i class="icon-dashboard"></i>系统首页</a>
            </h2>
            <ul>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-table"></i>信息管理<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/admin/modifyInfo.jsp">修改个人信息</a>
                        </dd>
                        <dd>
                            <a href="/admin/modifyPassword.jsp">修改密码</a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-columns"></i><a href="/viewAllStudents">成员管理</a>
                        </dt>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-list-alt"></i>考勤管理<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/admin/addAttendance.jsp">录入考勤信息</a>
                        </dd>
                        <dd>
                            <a href="/listAttendance?startDate=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>&endDate=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
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
                            <a href="trainPre">分配训练计划</a>
                        </dd>
                        <dd>
                            <a href="/listTrains">查看训练计划</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </nav>
        <footer class="side-footer">© 济南大学ACM实验室 版权所有</footer>
    </div>
    <div class="content-wrap">
        <header class="top-hd">
            <div class="hd-lt">
                <a class="icon-reorder"></a>
            </div>
            <div class="hd-rt">
                <ul>
                    <li>
                        <a><i class="icon-user"></i>管理员:<em><%= ((Admin)session.getAttribute("admin")).getName() %></em></a>
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
                <%
                    List<Student> students = (List<Student>) session.getAttribute("students");
                    Train train = (Train) session.getAttribute("train");
                    List<Set<String>> dones = (List<Set<String>>) session.getAttribute("dones");
                    List<Set<String>> nones = (List<Set<String>>) session.getAttribute("nones");
                %>
                <section class="page-hd">
                    <header>
                        <h2 class="title"><%= train.getName()  %></h2>
                    </header>
                    <hr>
                </section>

                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <!-- th>已进行时间</th -->
                        <th>学生姓名</th>
                        <th>已完成</th>
                        <th>未完成</th>
                    </tr>
                    </thead>
                    <% for (int i = 0; i < students.size(); ++i) { %>
                    <tr class="cen">
                        <td><%= students.get(i).getName() %></td>
                        <td>
                        <% for (String p : dones.get(i)) { %>
                            <a href="http://acm.hdu.edu.cn/showproblem.php?pid=<%=p%>"><%=p%></a>
                        <% } %>
                        </td>
                        <td>
                        <% for (String p : nones.get(i)) { %>
                        <a href="http://acm.hdu.edu.cn/showproblem.php?pid=<%=p%>"><%=p%></a>
                        <% } %>
                        </td>
                    </tr>
                    <% } %>
                </table>
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
