<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<title>${appName}</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style1.css">
	</head>
	
	<body>
		<h1>Page de connexion</h1>
		
		<table>
			<tr>
				<td>
					<h1>Sign up</h1>
					<f:form modelAttribute="user" action="createAccount" method="post">
						<f:label path="login" cssClass="label">Login :</f:label>
						<f:input path="login" type="text"/>
						<f:errors cssClass="errors"></f:errors><br />
						
						<f:label path="mdp" cssClass="label">Mot de passe :</f:label>
						<f:input path="mdp" type="password"/>
						<f:errors cssClass="errors"></f:errors><br />
						
						<f:label path="mdp" cssClass="label">Confirmation du mot de passe :</f:label>
						<f:input path="mdp" type="password"/>
						<f:errors cssClass="errors"></f:errors><br />
						
						<input type="submit" value="Créer un compte" />
					</f:form>
				</td>
				<td>
					<h1>Sign in</h1>
					<form action="j_spring_security_check" method="post">
						<label class="label">Login :</label>
						<input name="j_username" type="text"/><br />
						
						<label class="label">Mot de passe :</label>
						<input name="j_password" type="password"/><br />
						
						<input type="submit" value="Se connecter" />
					</form>
				</td>
			</tr>
		</table>
		
	</body>
</html>
