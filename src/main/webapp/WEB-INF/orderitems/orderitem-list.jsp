<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Order Items</title>
<style>
body{font-family:system-ui,Arial;margin:24px}
table{border-collapse:collapse;width:100%} th,td{border:1px solid #ddd;padding:6px}
.toolbar{margin-bottom:10px} a.button{border:1px solid #888;padding:6px 10px;border-radius:8px;text-decoration:none}
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</style>
</head>
<body>
<h2>Order Items</h2>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.flash}">
  <div class="alert alert-success" style="margin:12px 0;">${sessionScope.flash}</div>
  <c:remove var="flash" scope="session"/>
</c:if>


<div class="d-flex align-items-center gap-2 mb-3">
  <a class="btn btn-secondary btn-sm"
     href="${pageContext.request.contextPath}/index.jsp">Home</a>

  <a class="btn btn-primary btn-sm"
     href="${pageContext.request.contextPath}/admin/orderitems?action=new">+ New Item</a>
</div>

<table>
  <thead>
  <tr>
    <th>ID</th><th>Order</th><th>Product</th><th>Title</th><th>Category</th>
    <th>Price</th><th>Qty</th><th>Total</th><th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${items}" var="i">
    <tr>
      <td>${i.orderItemId}</td>
      <td>${i.orderId}</td>
      <td>${i.productId}</td>
      <td>${i.productTitle}</td>
      <td>${i.productCategory}</td>
      <td>${i.price}</td>
      <td>${i.quantity}</td>
      <td>${i.totalPrice}</td>
      <td>
        <a href="${pageContext.request.contextPath}/admin/orderitems?action=edit&id=${i.orderItemId}">Edit</a> |
        <a href="${pageContext.request.contextPath}/admin/orderitems?action=delete&id=${i.orderItemId}"
           onclick="return confirm('Delete item #${i.orderItemId}?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body></html>
