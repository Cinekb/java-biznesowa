<%@ page import="wipb.jee.clientdemo.web.controller.PostController" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

   <!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Post</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    
    <% if (session.getAttribute("username") != null ) {
    String name = (String) session.getAttribute("username");
				out.print("Zalogowany jako: " + name); %>
<form action="Logout" method="post">
		<input type="submit" value="logout">
	</form>
   <p><a href="http://localhost:8080/ee-std-client-demo-web/post/myposts"/>Moje posty</p>
   <% if (session.getAttribute("issuperuser").equals(true)) { %>
           <p><a href="http://localhost:8080/ee-std-client-demo-web/admin/posts"/>Panel Admina</p>
           <% } %>
<% } %>


<% if (session.getAttribute("username") == null) { %>
<p><a href="<c:url value = "/login"/>" id="login">Logowanie</a></p>
<p><a href="<c:url value = "/registration"/>" id="registration">Rejestracja</a></p>
<p><a href="<c:url value = "/regulamin"/>" id="rules">Regulamin</a></p>
    <p><a href="<c:url value = "/mail/add"/>" id="subscribtion">Subskrybuj</a></p>

    <%--<p><a href="<c:url value = "mail/add"/>">Subskrybuj</a></p>--%>

    <% } %>
<center>
<table>
    
    <tr>
        <th>Title</th>
        <th>Author</th>
        <%--<th>Tekst</th>--%>
        <th>Go</th>
        <th>Remove</th>
        <th>Update</th>
        <th>Raport</th>
    </tr>
    <c:forEach items='${postList}' var='post'>
        <tr>

            <c:url var="tempLink" value="/post_nr/">
                <c:param name="PostID" value="${post.id}"/>
            </c:url>
            <td>${post.title}</td>
            <td>${post.author}</td>
            <td><a href=" ${tempLink}">Go</a></td>



            
           <td><%--<a href="<c:url value='/post/remove'/>/${post.id}">Remove </a> --%></td>
        <td ><%-- <a href="<c:url value='/post/edit'/>/${post.id}">Update</a> --%></td>
         
            <%--    <a< href="<c:url value='/post/remove'/>/${post.id}">Remove</a> --%>
             <%--   <a href="<c:url value='/post/edit'/>/${post.id}">Edit</a>--%>
            <td><a href="<c:url value='/regulamin/error/add'/>/${post.id}">Raport</a></td>

            </td>

        </tr>
    </c:forEach>
</table>
    <%--<% if (session.getAttribute("issuperuser").equals(true)) { %>  --%>
<%-- <a href="<c:url value='/post/add'/>">Add</a> --%>

</center>
</body>
</html>
