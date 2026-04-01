<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>eStore Admin</title>
  <style>
    :root { --bg:#f7f7fb; --card:#fff; --ink:#222; --muted:#6b7280; --line:#e5e7eb; --brand:#2563eb; }
    * { box-sizing: border-box; }
    body { margin:0; font-family: system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif; color:var(--ink); background:var(--bg); }
    /* Top nav */
    .topbar { position:sticky; top:0; background:#ffffffcc; backdrop-filter: blur(6px);
              border-bottom:1px solid var(--line); }
    .nav { max-width:1100px; margin:0 auto; display:flex; align-items:center; gap:14px; padding:12px 16px; }
    .brand { font-weight:700; color:var(--brand); text-decoration:none; margin-right:auto; }
    .nav a { color:var(--ink); text-decoration:none; padding:6px 10px; border-radius:8px; }
    .nav a:hover { background:var(--line); }
    /* Main */
    main { max-width:1100px; margin:32px auto; padding:0 16px; }
    h1 { margin:0 0 8px 0; font-size:1.6rem; }
    p.lead { color:var(--muted); margin:0 0 20px 0; }
    .cards { display:grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap:16px; margin-top:16px; }
    .card { background:var(--card); border:1px solid var(--line); border-radius:14px; padding:16px; text-decoration:none; color:inherit; box-shadow: 0 1px 2px rgba(0,0,0,.04); }
    .card h3 { margin:0 0 6px 0; font-size:1.1rem; }
    .card p { margin:0; color:var(--muted); }
    .card:hover { border-color: var(--brand); box-shadow: 0 4px 16px rgba(37,99,235,.08); }
    footer { max-width:1100px; margin:40px auto 24px; padding:0 16px; color:var(--muted); font-size:.9rem; }
  </style>
</head>
<body>

<header class="topbar">
  <nav class="nav">
    <a class="brand" href="<c:url value='/'/>">eStore Admin</a>
    <a href="<c:url value='/admin/shipments?action=list'/>">Shipments</a>
    <a href="<c:url value='/admin/orderitems?action=list'/>">Order Items</a>
    <!-- Add more menu items later -->
  </nav>
</header>

<main>
  <h1>Welcome</h1>
  <p class="lead">Choose a section to manage.</p>

  <div class="cards">
    <a class="card" href="<c:url value='/admin/shipments?action=list'/>">
      <h3>Shipments</h3>
      <p>Create, edit, and delete shipments.</p>
    </a>

    <a class="card" href="<c:url value='/admin/orderitems?action=list'/>">
      <h3>Order Items</h3>
      <p>Manage items within customer orders.</p>
    </a>
  </div>
</main>

<footer>
  <span>Tip:</span> use the navbar at the top to jump between admin sections.
</footer>

</body>
</html>


