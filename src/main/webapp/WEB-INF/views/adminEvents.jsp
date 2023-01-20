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
<body>
<ul id="mydropdown" class="dropdown-content">
    <li><a href="/redirect">Home</a></li>
    <li><a href="/createEvent">Add New Event</a></li>
    <li><a href="/events">User Events</a></li>
    <li><a href="/chat">Chat</a></li>
    <li><a href="/readQrCode">Read QR Code</a></li>
    <li><a href="/history">History</a></li>
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
<h1 class="center-align">All events</h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Place</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${events}" var="event">
        <tr>
            <td><c:out value="${event.id}"/></td>
            <td><c:out value="${event.name}"/></td>
            <td><c:out value="${event.place}"/></td>
            <td>
                <form action="/updateEvent/${event.id}">
                    <input class="waves-effect waves-light btn" type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="/deleteEvent/${event.id}" method="post">
                    <input class="waves-effect waves-light btn" type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/materialize.min.js"></script>
<script type="text/javascript" src="/js/js.js"></script>
</body>
</html>