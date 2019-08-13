<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Пользователь</title>
</head>
<body>
<c:if test="${!empty sessionScope.loggedUser}">
<h2>Привет, ${sessionScope.loggedUser.name}!  <a href="/?logout=1"><b>Выйти</b></a></h2></c:if>

</body>
</html>