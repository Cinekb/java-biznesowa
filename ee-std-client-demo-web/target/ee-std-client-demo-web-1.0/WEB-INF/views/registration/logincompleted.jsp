<%-- 
    Document   : logincompleted
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
       <h1>Logowanie powiodło się!</h1>
       Zalogowany jako : <% String name = (String) session.getAttribute("username");
				out.print( name);
                               %>
       <p><a href="http://localhost:8080/ee-std-client-demo-web/post/list"/>"Powrót do strony głównej</a></p>

    </body>
</html>
