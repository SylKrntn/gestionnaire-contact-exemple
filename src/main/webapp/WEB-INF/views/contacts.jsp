<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${appName}</title>
	</head>
	<body>
		<h1>Bienvenue ${username} | <a href="${pageContext.request.contextPath}/">Se déconnecter</a></h1>
		<f:form modelAttribute="contact" action="saveContact" method="post">
			<f:label path="nom">Nom :</f:label>
			<f:input path="nom" type="text" />
			<f:errors path="nom" cssClass="errors"></f:errors><br />
			
			<f:label path="prenom">Prénom :</f:label>
			<f:input path="prenom" type="text" />
			<f:errors path="prenom" cssClass="errors"></f:errors><br />
			
			<f:label path="telephone">N° téléphone :</f:label>
			<f:input path="telephone" type="tel" />
			<f:errors path="telephone" cssClass="errors"></f:errors><br />
			
			<f:label path="mail">E-mail :</f:label>
			<f:input path="mail" type="email" />
			<f:errors path="mail" cssClass="errors"></f:errors><br />
			
			<input type="submit" value="Enregistrer" />
		</f:form>
		
		<div id="">
			<table>
				<tr>
					<th>ID</th>
					<th>NOM</th>
					<th>PRENOM</th>
					<th>N° TEL</th>
					<th>MAIL</th>
					<th>USER_ID</th>
				</tr>
				<c:forEach var="ctt" items="${contacts}">
					<tr>
						<td>${ctt.id}</td>
						<td>${ctt.nom}</td>
						<td>${ctt.prenom}</td>
						<td>${ctt.telephone}</td>
						<td>${ctt.mail}</td>
						<td>${ctt.user.getId()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>