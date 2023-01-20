<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    <meta charset="UTF-8">
    <title>FDM Events</title>
</head>
<body>
<form action="/readQrCode" method="POST">
    <table>
        <tr>
            <td><label for="code">Enter your code:</label></td>
            <td><input type="text" name="code"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit code"></td>
        </tr>
    </table>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/js.js"></script>
</body>
</html>
