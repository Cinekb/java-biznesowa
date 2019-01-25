<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Posts</title>
</head>
<body>
<h3>Add post</h3>
<form method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><textarea name="title"  rows="1"></textarea></td>
        </tr>
        <tr>
            <td>Author:</td>
        <% String user=(String)session.getAttribute("username"); %>
            <td><input type="text" name="author" style="width: 200px" value="<% out.print(user);%>" ></input></td> 
        </tr>
        <tr>
            <td>Tekst:</td>
            <td><textarea name="tekst" cols="30" rows="4"></textarea>
    </table>
    <input type="submit" value="Save">
</form>
</form>
</body>
</html>

