<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/styles.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    <meta charset="UTF-8">
    <title>Update Event</title>
</head>
<body class="blue lighten-5">
<form:form method="POST" action="/updateEvent" enctype="multipart/form-data">
    <form:input type="hidden" path="id"/>
    <div class="row">
        <div class="col s4 offset-s4">
            <div class="card blue-grey lighten-3">
                <h1 class="center-align">Update Event</h1>
                <div class="row">
                    <div class="col s4 offset-s1">
                        <form:label path="name" style="font-size: 20px; color: black;">Event Name</form:label>
                    </div>
                    <div class="col s6">
                        <form:input type="text" path="name" value="${event.name}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s1">
                        <form:label path="place" style="font-size: 20px; color: black;">Place</form:label>
                    </div>
                    <div class="col s6">
                        <form:input type="text" path="place" value="${event.place}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s1">
                        <form:label path="description" style="font-size: 20px; color: black;">Description</form:label>
                    </div>
                    <div class="col s6">
                        <form:input type="text" path="description" value="${event.description}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s1">
                        <form:label path="date" style="font-size: 20px; color: black;">Date</form:label>
                    </div>
                    <div class="col s6">
                        <div class="input-field">
                            <form:input type="text" path="date" name="bdate" class="datepicker" value="${event.date}"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s4 offset-s1">
                        <form:label path="picture" style="font-size: 20px; color: black;">Picture</form:label>
                    </div>
                    <div class="col s6">
                        <form:input type="file" path="file" />
                    </div>
                </div>
                <div class="row">
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
