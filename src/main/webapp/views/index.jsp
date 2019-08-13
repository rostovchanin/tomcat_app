<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<c:if test="${!empty param.accessDeny}"><center><font color="red">Доступ запрещен!</font></center></c:if>
<c:if test="${!empty param.needAuth}"><center><font color="red">Необходима авторизация!</font></center></c:if>
<c:if test="${!empty requestScope.message}"><center><font color="red">${requestScope.message}</font></center></c:if>
<h2 align="center">Авторизация</h2>
<form action="/" method="POST" align="center" >
    Логин: <input type="text" name="login" /> <br /><br />
    Пароль: <input type="password" name="password" /> <br /><br />
    <input type="submit" value="Войти" />
</form>
</body>
</html>