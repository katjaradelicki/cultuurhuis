<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<c:set value="${pageContext.servletContext.contextPath}"
	var="contextPath" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
<title>Het Cultuurhuis - Nieuwe klant</title>
</head>
<body>
	<h1>
		Het Cultuurhuis: nieuwe klant<img alt="nieuweklant.png"
			src="${contextPath}/images/nieuweklant.png">
	</h1>
	<vdab:menu />
	<form action="" method="post">
		<dl>
			<dt>Voornaam:</dt>
			<dd>
				<input type="text" name="voornaam" required="required">
			</dd>
			<dt>Familienaam:</dt>
			<dd>
				<input type="text" name="familienaam" required="required">
			</dd>
			<dt>Straat:</dt>
			<dd>
				<input type="text" name="straat" required="required">
			</dd>
			<dt>Huisnr:</dt>
			<dd>
				<input type="text" name="huisnr" required="required">
			</dd>
			<dt>Postcode:</dt>
			<dd>
				<input type="text" name="postcode" required="required">
			</dd>
			<dt>Gemeente:</dt>
			<dd>
				<input type="text" name="gemeente" required="required">
			</dd>
			<dt>Gebruikersnaam:</dt>
			<dd>
				<input type="text" name="gebruikersnaam" required="required">
			</dd>
			<dt>Paswoord:</dt>
			<dd>
				<input type="password" name="paswoord" required="required">
			</dd>
			<dt>Herhaal paswoord:</dt>
			<dd>
				<input type="password" name="herhaaldPaswoord" required="required">
			</dd>
		</dl>
		<input type="submit" value="OK">
	</form>
	<c:if test="${not empty fouten}">
		<ul>
			<c:forEach items="${fouten}" var="fout">
				<li>${fout}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>