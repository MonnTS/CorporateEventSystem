<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    <meta charset="UTF-8">
    <title>Change password</title>
</head>
<body class="blue lighten-5">
<form action="/changePassword" method="POST">
    <div class="row">
        <div class="col s4 offset-s4">
            <div class="card blue-grey lighten-3">
                <h1 class="center-align">Change Password</h1>
                <div class="row">
                    <div class="col s4 offset-s1"><label style="font-size: 20px; color: #000" for="password">New Password:</label></div>
                    <div class="col s6"><input style="color: #000" type="password" name="password" placeholder="Password"></div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s1"><label style="font-size: 20px; color: #000" for="confirmPassword">Confirm Password:</label></div>
                    <div class="col s6"><input style="color: #000" type="password" name="confirmPassword" placeholder="new Password"></div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s4"><input class="btn waves-effect waves-light" type="submit" value="Change Password"></div>
                </div>
                <div class="row"></div>
            </div>
        </div>
    </div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/js.js"></script>
</body>
</html>
