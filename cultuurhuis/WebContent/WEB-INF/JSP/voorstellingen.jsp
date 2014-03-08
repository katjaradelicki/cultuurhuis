<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set value="${pageContext.servletContext.contextPath }" var="contextPath" />
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
</body>
</html>