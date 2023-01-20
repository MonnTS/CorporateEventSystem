<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    <meta charset="UTF-8">
    <title>FDM Events</title>
</head>
<body class="blue lighten-5">
<ul id="mydropdown" class="dropdown-content">
    <li><a href="/redirect">Home</a></li>
    <li><a href="/chat">Chat</a></li>
    <li><a href="/logout">Log Out</a></li>
</ul>
<nav class="light-blue">
    <div class="nav-wrapper">
        <a href="/redirect" class="brand-logo">FDM Corporate System</a>
        <ul class="right hide-on-med-and-down">
            <li><a class="dropdown-trigger" href="#!" data-target="mydropdown">Menu<i class="material-icons right">arrow_drop_down</i></a></li>
        </ul>
    </div>
</nav>
<h1 class="center-align">History of your participated events</h1>
<div class="row">
    <c:forEach items="${allParticipatedEvents}" var="event">
        <div class="col s2">
            <div class="card medium">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator" src="data:image/png;base64, ${event.image}">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">${event.name}<i class="material-icons right">more_vert</i></span>
                    <p>${event.date}</p>
                </div>
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4">${event.name}<i class="material-icons right">close</i></span>
                    <p>${event.description}</p>
                    <p>${event.place}</p>
                    <p>${event.date}</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/js.js"></script>
</body>
</html>