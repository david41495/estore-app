<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Shipments</title>
    <style>
        body{font-family:system-ui,Arial;margin:24px}
        table{border-collapse:collapse;width:100%}
        th,td{border:1px solid #ccc;padding:8px;text-align:left}
        .toolbar{margin-bottom:12px}
        a.button, button{padding:6px 10px;text-decoration:none;border:1px solid #888;border-radius:6px}
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    </style>
</head>
<body>
<h2>Shipments</h2>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.flash}">
  <div class="alert alert-success" style="margin:12px 0;">${sessionScope.flash}</div>
  <c:remove var="flash" scope="session"/>
</c:if>

<div class="d-flex align-items-center gap-2 mb-3">
  <a href="${pageContext.request.contextPath}/index.jsp"
     class="btn btn-secondary btn-sm">Home</a>

  <a href="${pageContext.request.contextPath}/admin/shipments?action=new"
     class="btn btn-primary btn-sm">+ New Shipment</a>
</div>



<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Status</th>
        <th>Title</th>
        <th>Date</th>
        <th>Method</th>
        <th>Company</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${shipments}" var="s">
        <tr>
            <td>${s.shipmentId}</td>
            <td>${s.shipmentStatus}</td>
            <td>${s.shipmentTitle}</td>
            <td><fmt:formatDate value="${s.shipmentDate}" pattern="yyyy-MM-dd"/></td>
            <td>${s.shipmentMethod}</td>
            <td>${s.shipmentCompany}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/shipments?action=edit&id=${s.shipmentId}">Edit</a>
                |
                <a href="${pageContext.request.contextPath}/admin/shipments?action=delete&id=${s.shipmentId}"
                   onclick="return confirm('Delete shipment #${s.shipmentId}?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
