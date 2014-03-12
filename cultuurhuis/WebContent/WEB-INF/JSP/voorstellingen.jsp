<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath" />
<!DOCTYPE html >
<html>
<head>
<link href="${contextPath}/styles/default.css" rel="stylesheet"></link>
<title>Het Cultuurhuis: voorstellingen </title>
</head>
<body>
<header>
<div class="titel">
<h1>Het Cultuurhuis: voorstellingen<img alt="voorstellingen.png" src="${contextPath}/images/voorstellingen.png"></h1>
<vdab:menu></vdab:menu>
</div>
<h2>Genres</h2><nav>
<ul class="genreMenu">
<c:forEach var="genre" items="${requestScope.genres}">
<c:url value="/voorstellingen" var="genreUrl" /> 
<li><a href="${genreUrl}?genre=${genre.naam}">${genre.naam}</a>
</c:forEach>
</ul>
</nav>
</header>
<c:if test="${not empty voorstellingen}">
<h2>${applicationScope.genre} voorstellingen</h2>
	<table class="zebra">
	
		<thead>
			<tr class="titelRij">
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Vrije plaatsen</th>
				<th>Reserveren</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${voorstellingen}" var="voorstelling">
				<tr class="rijenTabel">
					<td><fmt:formatDate value="${voorstelling.datum}" type="both" dateStyle="short" timeStyle="short"/> </td>
					<td>${voorstelling.titel}</td>
					<td>
						<c:forEach items="${voorstelling.uitvoerders}" var="uitvoerder" varStatus="status">
						${uitvoerder.naam} <c:if test="${not status.last}"> / </c:if>
						</c:forEach>
					</td>
					<td>${voorstelling.prijs}</td>
					<td>${voorstelling.aantalVrijePlaatsen}</td>
					<td><c:if test="${not (voorstelling.aantalVrijePlaatsen==0)}">
					<c:url value="/reserveren" var="reserverenUrl"/>
					<a href="${reserverenUrl}?voorstelling=${voorstelling.nummer}">Reserveren</a></c:if></td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
</c:if>
</body>
</html>