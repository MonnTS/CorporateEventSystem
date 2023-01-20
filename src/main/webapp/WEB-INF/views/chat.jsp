<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"
            media="screen,projection"/>
        <meta charset="UTF-8">
        <title>FDM Events</title>
    </head>
    <body class="blue lighten-5">
        <div class="container blue-grey lighten-3">
            <ul id="mydropdown" class="dropdown-content">
                <li><a href="/redirect">Home</a></li>
                <li><a href="/history">History</a></li>
                <li><a href="/logout">Log Out</a></li>
            </ul>
            <nav class="light-blue">
                <div class="nav-wrapper">
                    <a href="/redirect" class="brand-logo">FDM Corporate System</a>
                    <ul class="right hide-on-med-and-down">
                        <li><a class="dropdown-trigger" href="/redirect" data-target="mydropdown">Menu<i class="material-icons right">arrow_drop_down</i></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col s3">
                    <ul class="collection">
                        <c:forEach items="${users}" var="user">
                            <li class="collection-item avatar user_chat" id="user_${user.id}">
                                <i class="material-icons circle"></i>
                                <span class="title usernames">${user.firstName}</span>
                                <p>${user.email}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="row">
                    <div class="col s9">
                        <div id="chat_container">
                        </div>
                        <div class="input-field valign-wrapper">
                            <textarea id="textarea2" class="materialize-textarea" data-length="1000"></textarea>
                            <label for="textarea2">Message</label>
                            <div class="valign-wrapper col s2">
                                <button class="btn waves-effect waves-light light-blue lighten-1" type="submit" name="action" onclick="sendMessage();socket.send(JSON.stringify({type:'aboba'}))">Send
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var userId = "${userId}"
        </script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script type="text/javascript" src="/js/materialize.min.js"></script>
        <script type="text/javascript" src="/js/js.js"></script>
        <script type="text/javascript" src="/js/chat.js"></script>
    </body>
</html>