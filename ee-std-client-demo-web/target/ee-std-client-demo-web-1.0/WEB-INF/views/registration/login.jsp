<%-- 
    Document   : login
    Created on : 2018-06-11, 22:21:02
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Log in</h1>
        <form method="post" >
            <table>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login"></input></td>
                </tr>
                <tr>
                    <td>Password:</td> 
                    <td><input type="password" name="password"></input></td>
                </tr>
            </table>
            <input type="submit" value="Save"> 
        </form>
    </body>
</html>
