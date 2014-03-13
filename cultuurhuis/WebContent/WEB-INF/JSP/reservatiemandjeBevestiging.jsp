<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<c:set value="${pageContext.servletContext.contextPath}" var="contextPath"/>    
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
<title>Het Cultuurhuis - Bevestiging reservatie</title>
</head>
<body>
<h1>Het Cultuurhuis: bevestiging reservatie <img alt="bevestig.png" src="${contextPath}/images/bevestig.png"/> </h1>
<vdab:menu/>
<h2>Stap 1: wie ben je ?</h2>
<c:url value="/reservatiemandje/bevestiging" var="bevestigingUrl"/>
<form action="${bevestigingUrl}" method="post">
<dl>
<dt>Gebruikersnaam:</dt>
<dd><input type="text" name="gebruikersnaam" <c:if test="${not empty klant}">  disabled="disabled" </c:if>/></dd>
<dt>Paswoord:</dt>
<dd><input type="password" name="paswoord" <c:if test="${not empty klant}">  disabled="disabled" </c:if>   /></dd>
</dl>
<input type="submit" value="Zoek me op" <c:if test="${not empty klant}">  disabled="disabled" </c:if> >
</form>
<c:url value="/nieuweKlant" var="nieuweKlantUrl"/>
<form action="${nieuweKlantUrl}" method="get"> <%-- alternatief: link opmaken met css als knop -->Nadeel:knop kan wijzigen in uitzicht maar de css-knop niet (tenzij je verschillende css voorziet) --%>
<input type="submit" value="Ik ben nieuw"  <c:if test="${not empty klant}">  disabled="disabled" </c:if> >
</form>
</br>
</br>
<c:if test="${not empty klant}">${klant.toString()}</c:if>
<c:if test="${not empty fout}">${fout}</c:if>
<h2>Stap 2:Bevestigen</h2>
<c:url value="/reserveringen/toevoegen" var="toevoegenUrl"/>
<form action="${toevoegenUrl}" method="post">
<input type="submit" value="Bevestigen"  <c:if test="${empty klant}">  disabled="disabled" </c:if> > 
</form>
</body>
</html>