<%@ page import="java.util.List" %>
<%@ page import="pojo.Appointment" %>
<%--
  Created by IntelliJ IDEA.
  User: wuyueqiu
  Date: 16-4-12
  Time: 下午5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

      <div class="smart-widget">
        <div class="smart-widget-header">
          订单列表
        </div>
          <div class="smart-widget-body">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>#</th>
                <th>预定时间</th>
                <th>订单创建时间</th>
                <th>支付金额</th>
                <th>订单状态</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${appointments}" var="appoint">
                <tr>
                  <td><c:out value="${appoint.id}"></c:out></td>
                  <td><c:out value="${appoint.time}"></c:out></td>
                  <td><c:out value="${appoint.create_time}"></c:out></td>
                  <td><c:out value="${appoint.money}"></c:out></td>
                  <td><c:out value="${appoint.state}"></c:out></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div><!-- ./smart-widget-inner -->
      </div>

    </div><!-- ./padding-md -->
  </div><!-- /main-container -->

</div>

<jsp:include page="template/footer.jsp"></jsp:include>

</body>
</html>
