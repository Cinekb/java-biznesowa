<%@ page import="wipb.jee.clientdemo.model.User" %>
<%@ page import="wipb.jee.clientdemo.ejb.UserDao" %>
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
    List<User> users;
    users= userDao.findAll();
    for(User us :users)
    if((username.equals(us.getUsername()) ))
    {
        out.println("Podany user juz istnieje");
       response.sendRedirect("rejestracja.jsp");
    }
    else
    {
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        userDao.save(user);
        response.sendRedirect("login.jsp");
    }

%>
</body>
</html>