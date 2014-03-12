<%@ tag description="menu tonen" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="genreMenu">
<c:if test="${pagina != 'Voorstellingen' }" >
<li>
<c:url value="/voorstellingen" var="voorstellingenUrl"/>
<a href="${voorstellingenUrl}?genre=${genre}">Voorstellingen</a></li>
</c:if>
<c:if test="${pagina != 'Reservatiemandje' and not empty reservatiemandje }">
<c:url value="/reservatiemandje" var="reservatiemandjeUrl"/>
<a href="${reservatiemandjeUrl}">Reservatiemandje</a>
</c:if>
<c:if test="${pagina != 'Bevestiging reservatie' and not empty reservatiemandje }">
<li>
<c:url value="/reservatiemandje/bevestiging" var="bevestigingUrl"/>
<a href="${bevestigingUrl}">Bevestiging reservatie</a>
</li>
</c:if>

</ul>
