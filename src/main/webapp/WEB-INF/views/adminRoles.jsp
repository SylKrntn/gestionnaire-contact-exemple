<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${appName}" /></title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>Page d'administration des rôles</h1>
		<p>Bienvenue <strong>${username}</strong> | <a class="btn blue-btn" href="${pageContext.request.contextPath}/">Se déconnecter</a></p>
		
		<p>
			<a class="btn green-btn" href="${pageContext.request.contextPath}/defaut/contacts">Espace Defaut</a> 
			<a class="btn blue-btn" href="${pageContext.request.contextPath}/admin/users">Administrer les utilisateurs</a>
		</p>
	</body>
</html>