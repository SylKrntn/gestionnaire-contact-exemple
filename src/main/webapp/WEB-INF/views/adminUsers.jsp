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
		<h1>Page d'administration des utilisateurs</h1>
		<p>Bienvenue <strong>${username}</strong> | <a class="btn blue-btn" href="${pageContext.request.contextPath}/">Se déconnecter</a></p>
		
		<p>
			<a class="btn green-btn" href="${pageContext.request.contextPath}/defaut/contacts">Espace Defaut</a> 
			<a class="btn blue-btn" href="${pageContext.request.contextPath}/admin/roles">Administrer les roles</a>
		</p>
		
		<!-- Formulaire permettant d'ajouter un utilisateur -->
		<h2>Ajouter un utilisateur</h2>
		<div class="cadre">
			<f:form modelAttribute="user" action="saveUser" method="post">
				<f:label path="login" cssClass="label">Login :</f:label>
				<f:input path="login" type="text" />
				<f:errors path="login" cssClass="errors"></f:errors><br />
				
				<f:label path="mdp" cssClass="label">Mot de passe :</f:label>
				<f:input path="mdp" type="password" />
				<f:errors path="mdp" cssClass="errors"></f:errors><br />
				
				<f:label path="actived" cssClass="label">Compte activé ?</f:label>
				<f:checkbox path="actived" />
				<f:errors path="actived" cssClass="errors"></f:errors><br />
				
				<!-- <f:label path="roles" cssClass="label">Rôles :</f:label>
				<label>ROLE_USER
					<input type="checkbox"
						value="ROLE_USER"
						checked="<c:forEach var="role" items="${utilisateur.roles}"><c:if test="${role.getNom() eq \"ROLE_USER\"}">checked</c:if></c:forEach>" />
				</label> 
				<label>ROLE_ADMIN
					<input type="checkbox"
						value="ROLE_ADMIN"
						checked="<c:forEach var="role" items="${utilisateur.roles}"><c:if test="${role.getNom() eq \"ROLE_ADMIN\"}">checked</c:if></c:forEach>" />
				</label> 
				
				<f:input path="roles" type="text" />
				<f:errors path="roles" cssClass="errors"></f:errors><br /> -->
				
				<f:input path="id" type="hidden" value="${user.id}" />
				<input type="submit" value="Enregistrer" />
			</f:form>
		</div>
		
		<!-- Tableau listant les contacts de l'utilisateur -->
		<h2>Liste des utilisateurs</h2>
		<div class="cadre">
			<table>
				<tr>
					<th>ID</th>
					<th>LOGIN</th>
					<th>MOT DE PASSE</th>
					<th>ACTIF</th>
					<th>ROLES</th>
				</tr>
				<c:forEach var="usr" items="${users}">
					<tr>
						<td>${usr.id}</td>
						<td>${usr.login}</td>
						<td>${usr.mdp}</td>
						<td>${usr.actived}</td>
						<td>[
							<c:forEach var="role" items="${usr.roles}">
								${role.getNom()}
							</c:forEach>
						]</td>
						<td><a class="btn blue-btn" href="${pageContext.request.contextPath}/admin/editUser?id=${usr.id}">Editer</a></td>
						<td><a class="btn red-btn" href="${pageContext.request.contextPath}/admin/delUser?userId=${usr.id}<c:forEach var="role" items="${usr.roles}" varStatus="sts">&r${sts.count}=${role.getId()}</c:forEach>">Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>