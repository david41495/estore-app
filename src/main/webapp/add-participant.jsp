<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Add Participant</title>
  <style>
    .center {
      margin: auto;
      width: 60%;
      border: 3px solid #2222FF;
      padding: 10px;
    }
  </style>
</head>
<body>
  <div class="center">
    <h3>Add new Participant</h3>

    <!-- Use context path so it works under /servlet-jsp or any app name -->
    <form action="${pageContext.request.contextPath}/ParticipantController" method="post">
      <label for="name">Full Name:</label><br/>
      <input type="text" id="name" name="name" placeholder="John Watson"/><br/><br/>

      <label for="phone">Phone:</label><br/>
      <input type="text" id="phone" name="phone" placeholder="+91 99999 11111"/><br/><br/>

      <label for="email">Email:</label><br/>
      <input type="text" id="email" name="email" placeholder="john@example.com"/><br/><br/>

      <label for="bid">Batch ID:</label><br/>
      <input type="text" id="bid" name="bid" placeholder="1"/><br/><br/>

      <label for="pid">Participant ID:</label><br/>
      <input type="text" id="pid" name="pid" placeholder="1"/><br/><br/>

      <input type="submit" value="Submit"/>
    </form>
  </div>
</body>
</html>
