<%@ page import="wipb.jee.clientdemo.web.controller.AdminController" %>
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
				out.print("Zalogowany jako: " + name); }%>

   <p><a href="http://localhost:8080/ee-std-client-demo-web/post/myposts"/>Moje posty</p>
           <p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>"Powrót do strony głównej</a></p>
    <p><a href="http://localhost:8080/ee-std-client-demo-web/admin/regulamin/error_list"/>Lista zgłoszonych postów</a></p>



<center>
<table>
    
    <tr>
        <th>Title</th>
        <th>Author</th>
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
      
              <td>   <a href="<c:url value='/admin/posts/remove'/>/${post.id}">Remove</a></td>
               <td>  <a href="<c:url value='/admin/posts/edit'/>/${post.id}">Edit</a></td>
            <td><a href="<c:url value='/regulamin/error/add'/>/${post.id}">Raport</a></td>

        </tr>
    </c:forEach>
</table>
 <a href="<c:url value='/admin/posts/add'/>">Add</a> 

</center>
</body>
</html>
