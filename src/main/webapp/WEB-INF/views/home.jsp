<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${appName}" /></title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<h1>Page de connexion</h1>
		
		<p class="error"><strong>${errorMessage}</strong></p>
		
		<!-- Espace de connexion -->
		<div class="espace">
			<!-- Espace de création de compte -->
			<div class="espace-creation-compte">
				<h1>Sign up</h1>
				<f:form modelAttribute="user" action="createAccount" method="post">
					<f:label path="login" cssClass="label">Login :</f:label>
					<f:input path="login" type="text"/>
					<f:errors path="login" cssClass="errors"></f:errors><br />
					
					<f:label path="mdp" cssClass="label">Mot de passe :</f:label>
					<f:input path="mdp" type="password"/>
					<f:errors path="mdp" cssClass="errors"></f:errors><br />
					
					<f:label path="mdpConf" cssClass="label">Confirmation du mot de passe :</f:label>
					<f:input path="mdpConf" type="password"/>
					<f:errors path="mdpConf" cssClass="errors"></f:errors><br />
					
					<input type="submit" value="Créer un compte" />
				</f:form>
			</div><!-- END <div class="espace-creation-compte"> -->
			
			<!-- Espace de connexion au compte utilisateur -->
			<div class="espace-connexion-compte">
				<h1>Sign in</h1>
				<form action="j_spring_security_check" method="post">
					<label class="label">Login :</label>
					<input name="j_username" type="text"/><br />
					
					<label class="label">Mot de passe :</label>
					<input name="j_password" type="password"/><br />
					
					<input type="submit" value="Se connecter" />
				</form>
			</div><!-- END <div class="espace-connexion-compte"> -->
		</div><!-- END <div class="espace"> -->
		
	</body>
</html>
