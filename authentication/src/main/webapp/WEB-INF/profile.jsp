<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dash board</title>
<link rel="stylesheet" type ="text/css" href="/css/style.css">
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
 <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="col=md-6 profile">
		<div class="h-100 p-5 text-bg-dark rounded-3">
			<h2>Welcome <c:out value="${user.userName}"></c:out>!</h2>
			<p>Here is your dashboard! Congratulations on making an account.</p>
			<p>Your preferred language is <c:out value="${user.language}"></c:out>!</p>
			<a href="/logout"><button>Logout</button></a>
		</div>
	</div>
</body>
</html>