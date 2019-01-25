<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Zgłoszenie naruszenia regulaminu</h1>
<form method="post">
    <table>
        <tr>
            <td>Author:</td>
            <% String user=(String)session.getAttribute("username"); %>
            <td><input type="text" name="author" style="width: 200px" value="<% out.print(user);%>" ></input></td> 
        </tr>
        <tr>
            <td>Powod:</td>
            <td><input type="text" name="tekst"></input></td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
</body>
</html>
