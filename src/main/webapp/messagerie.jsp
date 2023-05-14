<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.utc.sr03.td9_secu.model.Message"  %>
<%@ page import="java.util.ArrayList"  %>

<jsp:useBean id="connected_user" scope="session" class="fr.utc.sr03.td9_secu.model.User"></jsp:useBean>

<!doctype html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <title>Messagerie</title>
	  <link rel="stylesheet" type="text/css" media="all"  href="resources/css/mystyle.css" />
	</head>
	
	<body>
		<header>
	        <form method="POST" action="controller">
	            <input type="hidden" name="action" value="disconnect">
	            <input type="hidden" name="loginPage" value="connexion.jsp?disconnect">
	            <button class="btn-logout form-btn">Déconnexion</button>
	        </form>
	        
	        <h2><%= connected_user.getPrenom() %> <%= connected_user.getNom() %> - Ma messagerie</h2>
		</header>

    <section>
        <article>
        
          <div class="liste">
            <table>
              <tr><th>Expéditeur</th><th>Sujet</th><th>Message</th></tr>
<% 
// Cette façon de faire est déconseillée : utliser JSTL à la place
if (session.getAttribute("messagesRecus") != null) {
	for (Message message : (ArrayList<Message>)session.getAttribute("messagesRecus")) {
%>
	<tr>
		<td><%= message.getUserFrom().getNom() %> <%= message.getUserFrom().getPrenom() %></td>
		<td><%= message.getSujet_msg() %></td>
		<td><%= message.getCorps_msg() %></td>
	</tr>
<%
	}
}
%>
            </table>
          </div>
    
        </article>
    </section>



	</body>
</html>
