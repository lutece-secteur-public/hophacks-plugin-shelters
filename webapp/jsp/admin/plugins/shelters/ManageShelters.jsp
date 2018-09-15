<jsp:useBean id="managesheltersShelter" scope="session" class="fr.paris.lutece.plugins.shelters.web.ShelterJspBean" />
<% String strContent = managesheltersShelter.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
