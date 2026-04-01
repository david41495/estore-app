<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><c:choose><c:when test="${shipment.shipmentId != null}">Edit</c:when><c:otherwise>New</c:otherwise></c:choose> Shipment</title>
    <style>
        body{font-family:system-ui,Arial;margin:24px}
        form{max-width:520px}
        label{display:block;margin-top:10px}
        input{width:100%;padding:6px}
        .row{display:grid;grid-template-columns:1fr 1fr;gap:10px}
        .actions{margin-top:14px}
        a.button, button{padding:6px 10px;text-decoration:none;border:1px solid #888;border-radius:6px}
    </style>
</head>
<body>
<h2>
    <c:choose><c:when test="${shipment.shipmentId != null}">Edit</c:when><c:otherwise>New</c:otherwise></c:choose>
    Shipment
</h2>

<form method="post" action="${pageContext.request.contextPath}/admin/shipments">
    <c:if test="${shipment.shipmentId != null}">
        <input type="hidden" name="shipmentId" value="${shipment.shipmentId}"/>
    </c:if>

    <label>Status (number)
        <input type="number" name="shipmentStatus" value="${shipment.shipmentStatus}"/>
    </label>

    <label>Title
        <input type="text" name="shipmentTitle" value="${shipment.shipmentTitle}"/>
    </label>

    <div class="row">
        <label>Date
            <input type="date" name="shipmentDate"
                   value="<fmt:formatDate value='${shipment.shipmentDate}' pattern='yyyy-MM-dd'/>"/>
        </label>
        <label>Method
            <input type="text" name="shipmentMethod" value="${shipment.shipmentMethod}"/>
        </label>
    </div>

    <label>Company
        <input type="text" name="shipmentCompany" value="${shipment.shipmentCompany}"/>
    </label>

<div class="actions">
  <button type="submit" class="btn btn-dark" style="min-width:100px;">Save</button>
  <button type="button" class="btn btn-light border" style="min-width:100px;" onclick="location.href='${pageContext.request.contextPath}/admin/orderitems?action=list'">Cancel</button>
</div>

</form>
</body>
</html>
