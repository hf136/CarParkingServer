<%--
  Created by IntelliJ IDEA.
  User: wuyueqiu
  Date: 16-3-30
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 左边菜单栏 -->
<aside class="sidebar-menu fixed">
  <div class="sidebar-inner scrollable-sidebar">
    <div class="main-menu">
      <ul class="accordion">
        <li class="menu-header">
          Main Menu
        </li>
        <li class="bg-palette1 active">
          <a href="dashboard">
									<span class="menu-content block">
										<span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
										<span class="text m-left-sm">Dashboard</span>
									</span>
									<span class="menu-content-hover block">
										Home
									</span>
          </a>
        </li>

        <li class="openable bg-palette3">
          <a href="#">
									<span class="menu-content block">
										<span class="menu-icon"><i class="block fa fa-list fa-lg"></i></span>
										<span class="text m-left-sm">停车场管理</span>
										<span class="submenu-icon"></span>
									</span>
									<span class="menu-content-hover block">
										Form
									</span>
          </a>
          <ul class="submenu bg-palette4">
            <%--<li><a href="form_element.html"><span class="submenu-label">添加停车场</span></a></li>--%>
            <li><a href="appointments"><span class="submenu-label">预约列表</span></a></li>
          </ul>
        </li>

        <li class="menu-header">
          Others
        </li>

      </ul>
    </div>

  </div><!-- sidebar-inner -->
</aside>
