<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
        <meta charset="UTF-8">
        <title>Registration</title>
    </head>
    <body class="blue lighten-5">
        <form:form method="POST" action="/register">
            <div class="row">
                <div class="col s4 offset-s4">
                    <div class="card blue-grey lighten-3">
                        <h1 class="center-align">Register</h1>
                        <div class="row">
                            <div class="col s4 offset-s1 center-align valign-wrapper">
                                <form:label path="firstName" style="font-size: 20px; color: #000">First Name</form:label>
                            </div>
                            <div class="col s6 center-align">
                                <form:input path="firstName"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 offset-s1 center-align valign-wrapper">
                                <form:label path="lastName" style="font-size: 20px; color: #000">Last Name</form:label>
                            </div>
                            <div class="col s6 center-align">
                                <form:input path="lastName"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 offset-s1 center-align valign-wrapper">
                                <form:label path="email" style="font-size: 20px; color: #000">Email</form:label>
                            </div>
                            <div class="col s6 center-align">
                                <form:input path="email"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 offset-s1 center-align valign-wrapper">
                                <form:label path="password" style="font-size: 20px; color: #000">Password</form:label>
                            </div>
                            <div class="col s6 center-align">
                                <form:password path="password" name="password" id="password"/>
                            </div>
                        </div>
                        <div class="row center-align valign-wrapper">
                            <div class="col s4 offset-s4">
                                <input type="submit" value="Submit" class="viewDetails btn waves-effect waves-light" id="button_confirm"/>
                            </div>
                        </div>
                        <div class="row"></div>
                    </div>
                </div>
            </div>
        </form:form>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script type="text/javascript" src="/js/materialize.min.js"></script>
        <script type="text/javascript" src="/js/js.js"></script>
    </body>
</html>
