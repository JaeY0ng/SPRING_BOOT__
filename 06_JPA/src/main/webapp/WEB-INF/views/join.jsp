<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JOIN PAGE</h1>

    <form action="/join" method="post">
        <input type="text" name="username" /> <br>
        <input type="text" name="password" /> <br>
        <input type="submit" value="JOIN" />
    </form>

</body>
</html>