<%@ page import="wipb.jee.clientdemo.model.Post" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Post</title>
   <style>

   p.c {
       white-space: pre;}</style>


</head>
<body>

<% if (session.getAttribute("username") != null ) {
    String name = (String) session.getAttribute("username");
    out.print("Zalogowany jako: " + name); %>
    <form action="Logout" method="post">
    <input type="submit" value="logout">
</form>
<p><a href="http://localhost:8080/ee-std-client-demo-web/post/myposts"/>"Moje posty </p>
<% } %>
    <p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>"Powrót do strony głównej</a></p>


<% if (session.getAttribute("username") == null) { %>
<p><a href="<c:url value = "/login"/>">Logowanie</a></p>
<p><a href="<c:url value = "/registration"/>">Rejestracja</a></p>
<% } %>


<p></p>
<p></p>
<p></p>
<p></p>

<hr />

<h3><b>Tytul:</b></h3>
<%--<script type="text/javascript">--%>
<p class="c">
${PostID.title}
</p>


<p></p>
<p></p>
<hr />
<h3><b>Author:</b></h3>${PostID.author}

<p></p>
<p></p>
<hr />
<h3><b>Tresc:</b></h3>

<%--<script type="text/javascript">--%>
<p class="c">
${PostID.tekst}
</p>

<%--</script>--%>
            <%--<td>--%>
            <%-- if (session.getAttribute("issuperuser").equals(true)) { %>
            <a href="<c:url value='/post/remove'/>/${post.id}">Remove</a>
            <a href="<c:url value='/post/edit'/>/${post.id}">Edit</a>
            <% } --%>
            <%--</td>--%>


    <%--<% if (session.getAttribute("issuperuser").equals(true)) { %>  --%>

<%--</center>--%>
    <p> </p>
    <p> </p>
<hr/>
<c:url var="tempLink" value="/post_nr/add_answer">
    <c:param name="PostID" value="${PostID.id}"/>
</c:url>
    <h1>Odpowiedzi</h1>
    <% if (session.getAttribute("username") != null) { %>
<a href=" ${tempLink}">Add</a>
<% } %>

<c:forEach items='${listAnswer}' var='answer'>
    <p class="c">
    <p> <h3><b>Author:</b></h3>${answer.author}</p>
    </p>
    <p class="c">
    <p> <h3><b>Tresc:</b></h3>${answer.author}</p>
    </p>
    <hr/>
</c:forEach>
</body>
</html>

</body>
</html>
