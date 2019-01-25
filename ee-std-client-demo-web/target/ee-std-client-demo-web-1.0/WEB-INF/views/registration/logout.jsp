<%-- 
    Document   : logout
    Created on : 2018-06-11, 08:53:53
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Wylogowanie przebiegło pomyślnie!</h1>
       <%   
		session.setAttribute("username", null);
		session.removeAttribute("username");
                session.invalidate();
		session.getMaxInactiveInterval();%>
       <p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>"Powrót do strony głównej</a></p>

    </body>
</html>
