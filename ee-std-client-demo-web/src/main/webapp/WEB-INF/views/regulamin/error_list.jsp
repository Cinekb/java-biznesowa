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
<center>
    
    <h1> Post został zgłoszony, administracja przyjrzy się temu </h1>
   <%-- <table>

        <tr>
            <th>Post id</th>
            <th>Author</th>
            <th>Tresc</th>

        </tr>
        <c:forEach items='${errorList}' var='error'>
            <tr>

                <td>${error.post_id}</td>
                <td>${error.author}</td>
                <td>${error.tekst}</td>
            </tr>
            </tr>
        </c:forEach>
    </table>
--%>
</center>
<p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>Powrót do strony głównej</a></p>

</body>
</html>
