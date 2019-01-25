<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Posts</title>
</head>
<body>
<h3>Update post</h3>
<form  method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><textarea name="title"  rows="1">${PostID.title} </textarea></td></tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" style="width: 200px" value="${PostID.author}" ></input></td>
        </tr>
        <tr>
            <td>Tekst:</td>
            <td><textarea name="tekst" cols="30" rows="4">${PostID.tekst}</textarea></td>
        </tr>
    </table>
    <input type="submit" value="Save">
</form>
</form>
</body>
</html>

