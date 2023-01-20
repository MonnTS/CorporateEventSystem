<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    <meta charset="UTF-8">
    <title>Reset Password</title>
</head>

<body>
<h1>Reset Password</h1>

<form name='f' action="/resetPassword" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='email' value='' placeholder="Email"></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/js.js"></script>
</body>
</html>