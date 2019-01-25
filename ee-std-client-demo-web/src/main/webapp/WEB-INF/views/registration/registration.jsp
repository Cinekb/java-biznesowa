<%-- 
    Document   : registration
    Created on : 2018-06-11, 08:53:53
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
       <h3>Registration</h3>
        <form method="post">
            <table>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login"></input></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"></input></td>
                </tr>
                <tr>
                    <td>Surname:</td> 
                    <td><input type="text" name="surname"></input></td>
                </tr>
                <tr>
                    <td>email:</td> 
                    <td><input type="text" name="email"></input></td>
                </tr>
                <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Repeat password:</td> 
                    <td><input type="password" name="re-password"></input></td>
                </tr>
            </table>
            <input type="submit" value="Save"> 
        </form>
    </body>
</html>
