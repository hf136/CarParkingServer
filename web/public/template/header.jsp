<%--
  Created by IntelliJ IDEA.
  User: wuyueqiu
  Date: 16-3-30
  Time: 上午10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <!-- 导航栏 -->
<header class="top-nav">
  <div class="top-nav-inner">
    <!-- 导航栏左边 -->
    <div class="nav-header">
      <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleSM">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

      <ul class="nav-notification pull-right">
        <li>
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cog fa-lg"></i></a>
          <span class="badge badge-danger bounceIn">1</span>
          <ul class="dropdown-menu dropdown-sm pull-right user-dropdown">
            <li class="user-avatar">
              <img src="images/profile/profile1.jpg" alt="" class="img-circle">
              <div class="user-content">
                <h5 class="no-m-bottom">Jane Doe</h5>
                <div class="m-top-xs">
                  <a href="profile.html" class="m-right-sm">Profile</a>
                  <a href="signin.html">Log out</a>
                </div>
              </div>
            </li>
            <li class="divider"></li>
            <li>
              <a href="#">Setting</a>
            </li>
          </ul>
        </li>
      </ul>

      <a href="index.html" class="brand">
        <i class="fa fa-database"></i><span class="brand-name">车位管家</span>
      </a>
    </div>
    <!-- 导航栏右边 -->
    <div class="nav-container">
      <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleLG">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <ul class="nav-notification">
        <li class="search-list">
          <div class="search-input-wrapper">
            <div class="search-input">
              <input type="text" class="form-control input-sm inline-block">
              <a href="#" class="input-icon text-normal"><i class="ion-ios7-search-strong"></i></a>
            </div>
          </div>
        </li>
      </ul>
      <div class="pull-right m-right-sm">
        <div class="user-block hidden-xs">
          <a href="#" id="userToggle" data-toggle="dropdown">
            <img src="images/profile/profile1.jpg" alt="" class="img-circle inline-block user-profile-pic">
            <div class="user-detail inline-block">
              Jane Doe
              <i class="fa fa-angle-down"></i>
            </div>
          </a>
          <div class="panel border dropdown-menu user-panel">
            <div class="panel-body paddingTB-sm">
              <ul>
                <li>
                  <a href="profile.html">
                    <i class="fa fa-edit fa-lg"></i><span class="m-left-xs">My Profile</span>
                  </a>
                </li>
                <li>
                  <a href="signin.html">
                    <i class="fa fa-power-off fa-lg"></i><span class="m-left-xs">Sign out</span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</header>