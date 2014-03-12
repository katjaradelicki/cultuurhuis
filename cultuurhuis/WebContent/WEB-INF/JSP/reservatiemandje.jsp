<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
    <c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
<title>Het Cultuurhuis - Reservatiemandje</title>
</head>
<body>
<h1>Het Cultuurhuis: reservatiemandje <img alt="mandje.png" src="${contextPath}/images/mandje.png"/></h1>
<vdab:menu/>
<c:url value="/reservatiemandje" var="reservatiemandjeUrl"/>
<form action="${reservatiemandjeUrl}" method="post">
<table class="zebra">
<thead><tr class="titelRij">
<td>Datum</td>
<td>Titel</td>
<td>Uitvoerders</td>
<td>Prijs</td>
<td>Plaatsen</td>
<td><input type="submit" value="Verwijderen"/>  </td>
</tr>
</thead>
<tbody>
<c:forEach items="${reservatieMandjeVolledig.reserveringen}" var="reservering">
<tr>
	<td>${reservering.voorstelling.datum}</td>
	<td>${reservering.voorstelling.titel}</td>
	<td><c:forEach items="${reservering.voorstelling.uitvoerders}" var="uitvoerder" >${uitvoerder.naam}</c:forEach></td>
	<td>&euro;${reservering.voorstelling.prijs}</td>
	<td>${reservering.aantalPlaatsen}</td>
	<td><input type="checkbox" name="verwijderenCheckbox" value="${reservering.voorstelling.nummer }"></td>
</tr>
</c:forEach>
</tbody>

</table>
</form>
Te betalen: &euro;${reservatieMandjeVolledig.berekenTotaal()}
</body>
</html>