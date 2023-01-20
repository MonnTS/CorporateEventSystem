<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Main page</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
    </head>
    <body class="blue lighten-5">
        <div class="row">
            <div class="col s4 offset-s4">
                <div class="card blue-grey lighten-3">
                    <h1 class="center-align">Login</h1>
                    <form name='f' action="/login" method='POST'>
                        <div class="row">
                            <div class="col s4 center-align valign-wrapper offset-s1" style="font-size: 20px">
                                <span class="material-icons left-align">
                                    person
                                </span>
                                Email
                            </div>
                            <div class="col s6">
                                <input type='email' name='username' value=''>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 center-align valign-wrapper offset-s1" style="font-size: 20px">
                                <span class="material-icons">
                                    password
                                </span>
                                Password
                            </div>
                            <div class="col s6">
                                <input type='password' name='password'/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s4 center-align valign-wrapper offset-s1" style="font-size: 20px">
                                <span class="material-icons">
                                    remember_me
                                </span>
                                Remember Me
                            </div>
                            <div class="col s6">
                                <label class="center-align valign-wrapper" style="align-items: center;">
                                    <input class="center-align" style="align-self: center;" classtype="checkbox" type="checkbox" name="remember-me"/>
                                    <span></span>
                                </label>
                            </div>
                        </div>
                        <div class="row center-align valign-wrapper">
                            <div class="col s4 offset-s4">
                                <input class="btn waves-effect waves-light" name="submit" type="submit" value="sign in"/>
                            </div>
                        </div>
                        <div class="row center-align valign-wrapper">
                            <div class="col s4 offset-s4">
                                <input class="btn waves-effect waves-light" name="register" type="submit" value="sign up" formmethod="get" formaction="/register"/>
                            </div>
                        </div>
                        <div class="row"></div>
                        <div class="row center-align valign-wrapper">
                            <div class="col s4 offset-s4">
                                <input class="btn waves-effect waves-light" name="register" type="submit" value="forgot password?" formmethod="get" formaction="/resetPassword"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>