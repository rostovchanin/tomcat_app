<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление/редактирование пользователя</title>
</head>
<body>
<ol>
<li><a href="/editUser">Добавить пользователя</a>
<li><a href="/users">Все пользователи</a>
</ol>
${message}
<h3>Добавление/редактирование пользователя</h3>
<form method="POST" action="/editUser">
<c:if test="${!empty editUser.id}"><input type="hidden" name="id" value="${editUser.id}"></c:if>
<table width="400">
<tr align="right"><td>Имя:</td><td><input type="text" name="name" value="${editUser.name}" /></td></tr>
<tr align="right"><td>Пароль:</td><td><input type="password" name="password" value="${editUser.password}"/></td></tr>
<tr align="right"><td>Логин:</td><td><input type="text" name="login" value="${editUser.login}"/></td></tr>
<tr align="right"><td>&nbsp;</td><td><input type="submit" name="Сохранить" value="Сохранить"></td></tr>

</form>
</body>
</html>