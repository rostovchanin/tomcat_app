<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Все пользователи </title>
</head>
<body>

<c:if test="${!empty param.deleteOk}"><font color="red">Пользователь удален!</font></c:if>
<c:if test="${!empty param.deleteNotOk}"><font color="red">Неудача при попытке удалить пользователя!</font></c:if>
<c:if test="${!empty param.updateNotOk}"><font color="red">Неудача при попытке редактировать пользователя!</font></c:if>
<c:if test="${!empty param.updateOk}"><font color="green">Пользователь сохранен!</font></c:if>

<ol>
<li><a href="/editUser">Добавить пользователя</a>
<li><a href="/users">Все пользователи</a>
</ol>
<br />
<c:if test="${!empty users}">
<table width="800" border="1">
<tr align="center">
    <th>ID</th>
    <th>Имя</th>
    <th>Пароль</th>
    <th>Логин</th>
    <th>Изменить</th>
</tr>
</c:if>
<c:forEach var="user" items="${users}">
<tr align="center">
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.password}</td>
    <td>${user.login}</td>
    <td><a href="/deleteUser?id=${user.id}" title="Удалить">X</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/editUser?id=${user.id}" title="Редактировать">...</a></td>
</tr>
</c:forEach>
<c:if test="${!empty users}">
</table>
</c:if>
</body>
</html>