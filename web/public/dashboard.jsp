<%--
  Created by IntelliJ IDEA.
  User: wuyueqiu
  Date: 16-3-30
  Time: 上午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="template/head.jsp"></jsp:include>
</head>
<body class="overflow-hidden">
  <div class="wrapper preload">
    <jsp:include page="template/header.jsp"></jsp:include>
    <jsp:include page="template/menu.jsp"></jsp:include>

    <!-- 右边的内容 -->
    <div class="main-container">
      <div class="padding-md">
        <div class="row">
          <div class="col-sm-6">
            <div class="page-title">
              Dashboard
            </div>
            <%--<div class="page-sub-header">--%>
              <%--Welcome Back, John Doe , <i class="fa fa-map-marker text-danger"></i> London--%>
            <%--</div>--%>
          </div>
        </div>

        <div class="row m-top-md">
          <div class="col-lg-3 col-sm-6">
            <div class="statistic-box bg-danger m-bottom-md">
              <div class="statistic-title">
                总收入
              </div>

              <div class="statistic-value">
                ${total}
              </div>

              <%--<div class="m-top-md">7% Higher than last week</div>--%>

              <div class="statistic-icon-background">
                <i class="ion-stats-bars"></i>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-sm-6">
            <div class="statistic-box bg-info m-bottom-md">
              <div class="statistic-title">
                今日订单
              </div>

              <div class="statistic-value">
                ${requestScope.order}
              </div>

              <%--<div class="m-top-md">8% Higher than last week</div>--%>

              <div class="statistic-icon-background">
                <i class="ion-ios7-cart-outline"></i>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-sm-6">
            <div class="statistic-box bg-purple m-bottom-md">
              <div class="statistic-title">
                今日收入
              </div>

              <div class="statistic-value">
                ${requestScope.income}
              </div>

              <%--<div class="m-top-md">3% Higher than last week</div>--%>

              <div class="statistic-icon-background">
                <i class="ion-social-usd-outline"></i>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-sm-6">
            <div class="statistic-box bg-success m-bottom-md">
              <div class="statistic-title">
                剩余车位
              </div>

              <div class="statistic-value">
                ${requestScope.available}
              </div>

              <%--<div class="m-top-md">7% Higher than last week</div>--%>

              <div class="statistic-icon-background">
                <i class="ion-model-s"></i>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <%--<div class="col-lg-6">--%>
            <%--<div class="smart-widget widget-dark-blue">--%>
              <%--<div class="smart-widget-header">--%>
                <%--TOTAL VISITS--%>
									<%--<span class="smart-widget-option">--%>
										<%--<span class="refresh-icon-animated">--%>
											<%--<i class="fa fa-circle-o-notch fa-spin"></i>--%>
										<%--</span>--%>
			                            <%--<a href="#" class="widget-toggle-hidden-option">--%>
                                          <%--<i class="fa fa-cog"></i>--%>
                                        <%--</a>--%>
			                            <%--<a href="#" class="widget-collapse-option" data-toggle="collapse">--%>
                                          <%--<i class="fa fa-chevron-up"></i>--%>
                                        <%--</a>--%>
			                            <%--<a href="#" class="widget-refresh-option">--%>
                                          <%--<i class="fa fa-refresh"></i>--%>
                                        <%--</a>--%>
			                        <%--</span>--%>
              <%--</div>--%>
              <%--<div class="smart-widget-inner">--%>
                <%--<div class="smart-widget-hidden-section">--%>
                  <%--<ul class="widget-color-list clearfix">--%>
                    <%--<li style="background-color:#20232b;" data-color="widget-dark"></li>--%>
                    <%--<li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>--%>
                    <%--<li style="background-color:#23b7e5;" data-color="widget-blue"></li>--%>
                    <%--<li style="background-color:#2baab1;" data-color="widget-green"></li>--%>
                    <%--<li style="background-color:#edbc6c;" data-color="widget-yellow"></li>--%>
                    <%--<li style="background-color:#fbc852;" data-color="widget-orange"></li>--%>
                    <%--<li style="background-color:#e36159;" data-color="widget-red"></li>--%>
                    <%--<li style="background-color:#7266ba;" data-color="widget-purple"></li>--%>
                    <%--<li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>--%>
                    <%--<li style="background-color:#fff;" data-color="reset"></li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<div class="smart-widget-body no-padding">--%>
                  <%--<div class="padding-md">--%>
                    <%--<div id="totalSalesChart" class="morris-chart" style="height:250px;"></div>--%>
                  <%--</div>--%>

                  <%--<div class="bg-grey">--%>
                    <%--<div class="row">--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">999</h3>--%>
                        <%--<small class="m-bottom-sm block">Total Visits</small>--%>
                      <%--</div>--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">102</h3>--%>
                        <%--<small class="m-bottom-sm block">New Visits</small>--%>
                      <%--</div>--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">690</h3>--%>
                        <%--<small class="m-bottom-sm block">Bounce Rate</small>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>
              <%--</div><!-- ./smart-widget-inner -->--%>
            <%--</div><!-- ./smart-widget -->--%>
          <%--</div><!-- ./col -->--%>
          <div class="col-lg-12">
            <div class="smart-widget widget-dark-blue">
              <div class="smart-widget-header">
                总销售量
									<span class="smart-widget-option">
										<span class="refresh-icon-animated">
											<i class="fa fa-circle-o-notch fa-spin"></i>
										</span>
			                            <a href="#" class="widget-toggle-hidden-option">
                                          <i class="fa fa-cog"></i>
                                        </a>
			                            <a href="#" class="widget-collapse-option" data-toggle="collapse">
                                          <i class="fa fa-chevron-up"></i>
                                        </a>
			                            <a href="#" class="widget-refresh-option">
                                          <i class="fa fa-refresh"></i>
                                        </a>
			                        </span>
              </div>
              <div class="smart-widget-inner">
                <div class="smart-widget-hidden-section">
                  <ul class="widget-color-list clearfix">
                    <li style="background-color:#20232b;" data-color="widget-dark"></li>
                    <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                    <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                    <li style="background-color:#2baab1;" data-color="widget-green"></li>
                    <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                    <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                    <li style="background-color:#e36159;" data-color="widget-red"></li>
                    <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                    <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                    <li style="background-color:#fff;" data-color="reset"></li>
                  </ul>
                </div>
                <div class="smart-widget-body no-padding">
                  <div class="padding-md">
                    <div id="placeholder" style="height:250px;"></div>
                  </div>

                  <%--<div class="bg-grey">--%>
                    <%--<div class="row">--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">3491</h3>--%>
                        <%--<small class="m-bottom-sm block">Total Sales</small>--%>
                      <%--</div>--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">721</h3>--%>
                        <%--<small class="m-bottom-sm block">New Orders</small>--%>
                      <%--</div>--%>
                      <%--<div class="col-xs-4 text-center">--%>
                        <%--<h3 class="m-top-sm">$8103</h3>--%>
                        <%--<small class="m-bottom-sm block">Total Earnings</small>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                </div>
              </div><!-- ./smart-widget-inner -->
            </div><!-- ./smart-widget -->
          </div><!-- ./col -->
        </div>

      </div><!-- ./padding-md -->
    </div><!-- /main-container -->

  </div>

  <jsp:include page="template/footer.jsp"></jsp:include>

  <script src="js/simplify/simplify_dashboard.js"></script>

</body>
</html>
