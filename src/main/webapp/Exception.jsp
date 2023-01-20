<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String status = (String)request.getAttribute("status");
    String exception = (String)request.getAttribute("exception");
    String lien = (String)request.getAttribute("lien");
%>
<html>
<head>
    <title>BackOffice - Exception</title>
</head>
<body>
    <h3><% out.println(exception); %></h3>
    <a href="<% out.println(lien); %>"><< Revenir</a>
</body>
</html>
