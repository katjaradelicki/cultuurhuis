<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
    <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
<title>Het Cultuurhuis - Reserveren</title>
</head>
<body>
<h1>Het Cultuurhuis: reserveren <img alt="reserveren.png" src="${contextPath}/images/reserveren.png"/> </h1>
<vdab:menu/>
<dl>
<dd>
Voorstelling:</dd>
<dt>
${voorstelling.titel}
</dt>
<dd>
Uitvoerders:</dd>
<c:forEach items="${voorstelling.uitvoerders}" var="uitvoerder"><dt>${uitvoerder.naam} </dt></c:forEach>
<dd>
Datum:</dd>
<dt><fmt:formatDate value="${voorstelling.datum}"/></dt>
<dd>
Prijs:</dd>
<dt>&euro;<fmt:formatNumber value="${voorstelling.prijs}"/></dt>
<dd>
Vrije plaatsen:</dd>
<dt>${voorstelling.aantalVrijePlaatsen}</dt>
</dl>
<c:url value="/reserveren" var="reserverenUrl"/>
<form action="${reserverenUrl}?voorstelling=${voorstelling.nummer}" method="post"> 
<input type="number" min="1" max="${voorstelling.aantalVrijePlaatsen}" name="aantalTeReserveren" value="${plaatsen}"/>
<input type="submit" value="Reserveren"/><c:if test="${not empty fout}">${fout}</c:if>
</form>

</body>
</html>