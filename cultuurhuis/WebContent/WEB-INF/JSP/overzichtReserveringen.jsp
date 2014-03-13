<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>
<!DOCTYPE html >
<html>
<head>

<title>Het Cultuurhuis - overzicht</title>
</head>
<body>
<h1>Het Cultuurhuis: overzicht <img alt="bevestig.png" src="${contextPath}/images/bevestig.png" /></h1>
<vdab:menu/>
<h2>Gelukte reserveringen</h2>
<table class="zebra">
<thead><tr><td>Datum</td>
<td>Titel</td>
<td>Uitvoerders</td>
<td>Prijs(&euro;)</td>
<td>Plaatsen</td>
</tr>
</thead>
<tbody>
</tbody>
</table>
<h2>Mislukte reserveringen</h2>
<table class="zebra">
<thead><tr><td>Datum</td>
<td>Titel</td>
<td>Uitvoerders</td>
<td>Prijs(&euro;)</td>
<td>Plaatsen</td>
<td>Vrije Plaatsen</td>
</tr>
</thead>
<tbody>
</tbody>
</table>
</body>
</html>