<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title><c:if test="${item.orderItemId!=null}">Edit</c:if><c:if test="${item.orderItemId==null}">New</c:if> Order Item</title>
<style>
body{font-family:system-ui,Arial;margin:24px} form{max-width:720px}
label{display:block;margin-top:10px} input{width:100%;padding:6px}
.grid{display:grid;grid-template-columns:1fr 1fr;gap:10px} .actions{margin-top:14px}
a.button,button{border:1px solid #888;padding:6px 10px;border-radius:8px;text-decoration:none}

  .actions .btn{
    min-width:120px; padding:8px 16px;
    display:inline-block; text-align:center;
    border:1px solid #888; border-radius:8px;
    text-decoration:none;
  }
  .actions .btn:hover{ opacity:.9; }

  .actions .btn-primary{ background:#333; color:#fff; }
  .actions .btn-secondary{ background:#eee; color:#333; border-color:#bbb; }

  .actions a.btn-primary:link,
  .actions a.btn-primary:visited{ color:#fff; }
  .actions a.btn-secondary:link,
  .actions a.btn-secondary:visited{ color:#333; }


</style>
<script>
function calcTotal(){var p=document.getElementById('price').value|0;var q=document.getElementById('quantity').value|0;document.getElementById('totalPrice').value=p*q;}
</script>
</head>
<body>
<h2><c:choose><c:when test="${item.orderItemId!=null}">Edit</c:when><c:otherwise>New</c:otherwise></c:choose> Order Item</h2>
<form method="post" action="${pageContext.request.contextPath}/admin/orderitems">
  <c:if test="${item.orderItemId!=null}">
    <input type="hidden" name="orderItemId" value="${item.orderItemId}"/>
  </c:if>

  <div class="grid">
    <label>Order ID <input type="number" name="orderId" value="${item.orderId}"/></label>
    <label>Product ID <input type="number" name="productId" value="${item.productId}"/></label>
  </div>

  <label>Product Title <input type="text" name="productTitle" value="${item.productTitle}"/></label>
  <label>Description <input type="text" name="productDescription" value="${item.productDescription}"/></label>

  <div class="grid">
    <label>Product Code <input type="text" name="productCode" value="${item.productCode}"/></label>
    <label>Image URL <input type="text" name="productImg" value="${item.productImg}"/></label>
  </div>

  <label>Category <input type="text" name="productCategory" value="${item.productCategory}"/></label>

  <div class="grid">
    <label>Price <input id="price" type="number" name="price" value="${item.price}" oninput="calcTotal()"/></label>
    <label>Quantity <input id="quantity" type="number" name="quantity" value="${item.quantity}" oninput="calcTotal()"/></label>
  </div>

  <label>Total Price <input id="totalPrice" type="number" name="totalPrice" value="${item.totalPrice}"/></label>

  <div class="actions">
  <button type="submit" class="btn btn-dark" style="min-width:100px;">Save</button>
  <button type="button" class="btn btn-light border" style="min-width:100px;" onclick="location.href='${pageContext.request.contextPath}/admin/orderitems?action=list'">Cancel</button>
</div>

</form>
</body></html>
