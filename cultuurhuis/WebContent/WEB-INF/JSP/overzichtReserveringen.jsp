<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.servletContext.contextPath}"
	var="contextPath" />
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
<title>Het Cultuurhuis - overzicht</title>
</head>
<body>
	<h1>
		Het Cultuurhuis: overzicht <img alt="bevestig.png"
			src="${contextPath}/images/bevestig.png" />
	</h1>
	<vdab:menu />
	<h2>Gelukte reserveringen</h2>
	<table class="zebra">
		<thead>
			<tr>
				<td>Datum</td>
				<td>Titel</td>
				<td>Uitvoerders</td>
				<td>Prijs(&euro;)</td>
				<td>Plaatsen</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${gelukteReservatieLijst}" var="reservering">
				<tr>
					<td><fmt:formatDate value="${reservering.voorstelling.datum}"
							dateStyle="short" timeStyle="short" type="both" pattern="dd/MM/yyyy HH:mm" /></td>
					<td>${reservering.voorstelling.titel}</td>
					<td><c:forEach items="${reservering.voorstelling.uitvoerders}"
							var="uitvoerder" varStatus="status">${uitvoerder.naam}<c:if
								test="${not status.last}">/</c:if>
						</c:forEach></td>
					<td>${reservering.voorstelling.prijs}</td>
					<td>${reservering.aantalPlaatsen}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>Mislukte reserveringen</h2>
	<table class="zebra">
		<thead>
			<tr>
				<td>Datum</td>
				<td>Titel</td>
				<td>Uitvoerders</td>
				<td>Prijs(&euro;)</td>
				<td>Plaatsen</td>
				<td>Vrije Plaatsen</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mislukteReservatieLijst}" var="reservering">
				<tr>
					<td><fmt:formatDate value="${reservering.voorstelling.datum}"
							dateStyle="short" timeStyle="short" type="both" pattern="dd/MM/yyyy HH:mm" /></td>
					<td>${reservering.voorstelling.titel}</td>
					<td><c:forEach items="${reservering.voorstelling.uitvoerders}"
							var="uitvoerder" varStatus="status">${uitvoerder.naam}<c:if
								test="${not status.last}">/</c:if>
						</c:forEach></td>
					<td>${reservering.voorstelling.prijs}</td>
					<td>${reservering.aantalPlaatsen}</td>
					<td>${reservering.voorstelling.aantalVrijePlaatsen}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>