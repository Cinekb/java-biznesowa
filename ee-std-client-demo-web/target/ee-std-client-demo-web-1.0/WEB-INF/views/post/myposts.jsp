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
    <h1>Moje posty</h1> 
<form action="Logout" method="post">
		<input type="submit" value="logout">
	</form>
    <p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>"Powrót do strony głównej</a></p>

<center>
<table>
    
        <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Tekst</th>
        <th>Action</th>
    </tr>
    <c:forEach items='${postList}' var='post'>
        <tr>
            <c:set var="onepost" value="${post.author}"></c:set>
            <% String author =  pageContext.getAttribute("onepost").toString();
            if (session.getAttribute("username").equals(author)) { %>
            <td>${post.title}</td>
            <td>${post.author}</td>
            <td>
                    ${post.tekst}</td>
            <td>
                 <a href="<c:url value='/post/remove'/>/${post.id}">Remove</a>
                <a href="<c:url value='/post/edit'/>/${post.id}">Edit</a> 

            </td>
            <% } %>
        </tr>
    </c:forEach>
</table>

<a href="<c:url value='/post/add'/>">Add</a>
</center>
</body>
</html>
