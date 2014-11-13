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
		<h1>Page des contacts</h1>
		<p>Bienvenue <strong>${username}</strong> | <a class="btn blue-btn" href="${pageContext.request.contextPath}/">Se déconnecter</a></p>
		
		<!-- Formulaire permettant d'ajouter un contact -->
		<h2>Ajouter un contact</h2>
		<div class="cadre">
			<f:form modelAttribute="contact" action="saveContact" method="post">
				<f:label path="nom" cssClass="label">Nom :</f:label>
				<f:input path="nom" type="text" />
				<f:errors path="nom" cssClass="errors"></f:errors><br />
				
				<f:label path="prenom" cssClass="label">Prénom :</f:label>
				<f:input path="prenom" type="text" />
				<f:errors path="prenom" cssClass="errors"></f:errors><br />
				
				<f:label path="telephone" cssClass="label">N° téléphone :</f:label>
				<f:input path="telephone" type="tel" />
				<f:errors path="telephone" cssClass="errors"></f:errors><br />
				
				<f:label path="mail" cssClass="label">E-mail :</f:label>
				<f:input path="mail" type="email" />
				<f:errors path="mail" cssClass="errors"></f:errors><br />
				
				<f:input path="id" type="hidden" value="${contact.id}" />
				<input type="submit" value="Enregistrer" />
			</f:form>
		</div>
		
		<!-- Tableau listant les contacts de l'utilisateur -->
		<h2>Liste des contacts</h2>
		<div class="cadre">
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
						<td>${ctt.nom.toUpperCase()}</td>
						<td>${ctt.prenom}</td>
						<td>${ctt.telephone}</td>
						<td>${ctt.mail}</td>
						<td>${ctt.user.getId()}</td>
						<td><a class="btn blue-btn" href="${pageContext.request.contextPath}/defaut/editContact?id=${ctt.id}">Editer</a></td>
						<td><a class="btn red-btn" href="${pageContext.request.contextPath}/defaut/delContact?id=${ctt.id}">Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>