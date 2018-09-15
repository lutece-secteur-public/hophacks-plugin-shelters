<jsp:useBean id="managesheltersBeds" scope="session" class="fr.paris.lutece.plugins.shelters.web.ManageBedsJspBean" />
<% String strContent = managesheltersBeds.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
