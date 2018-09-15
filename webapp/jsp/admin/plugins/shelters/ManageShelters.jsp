<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="manageshelters" scope="session" class="fr.paris.lutece.plugins.shelters.web.ManageSheltersJspBean" />

<% manageshelters.init( request, manageshelters.RIGHT_MANAGESHELTERS ); %>
<%= manageshelters.getManageSheltersHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
