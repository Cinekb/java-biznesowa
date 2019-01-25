<%@ page import="wipb.jee.clientdemo.ejb.UserDao" %>
<%@ page import="wipb.jee.clientdemo.model.Customer" %>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%
    String username=request.getParameter("username");
    String password=request.getParameter("password");
   UserDao userDao= new UserDao();
    List<Customer> users;
       users= userDao.findAll();
       for(Customer u :users)
    if((username.equals(u.getLogin()) && password.equals(u.getPassword())))
    {
        session.setAttribute("username",username);
        response.sendRedirect("index.jsp");
    }
    else
        response.sendRedirect("Error.jsp");

%>
</body>
</html>