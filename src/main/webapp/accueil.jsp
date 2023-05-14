<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.utc.sr03.td9_secu.model.User"  %>
<%@ page import="java.util.ArrayList"  %>

<jsp:useBean id="connected_user" scope="session" class="fr.utc.sr03.td9_secu.model.User"></jsp:useBean>

<!doctype html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <title>Accueil</title>
	  <link rel="stylesheet" type="text/css" media="all"  href="resources/css/mystyle.css" />
	</head>

	<body>
		<header>
	        <form method="POST" action="controller">
	            <input type="hidden" name="action" value="disconnect">
	            <input type="hidden" name="loginPage" value="connexion.jsp?disconnect">
	            <button class="btn-logout form-btn">Déconnexion</button>
	        </form>
	        
	        <h2><%= connected_user.getPrenom() %> <%= connected_user.getNom() %> - Mon compte</h2>
		</header>


	    <section>
	      
	        <article>
	          <div class="fieldset">
	              <div class="fieldset_label">
	                  <span>Vos informations personnelles</span>
	              </div>
	              <div class="field">
	                  <label>Login : </label><span><%= connected_user.getLogin() %></span>
	              </div>
	              <div class="field">
	                  <label>Profil : </label><span><%= connected_user.getProfil_user() %></span>
	              </div>
	          </div>
	        </article>
	        
	        <article>
	          <div class="fieldset">
	              <div class="fieldset_label">
	                  <span>Votre compte</span>
	              </div>
	              <div class="field">
	                  <label>N° compte : </label><span><%= connected_user.getNumero_compte() %></span>
	              </div>
	              <div class="field">
	                  <label>Solde : </label><span><%= connected_user.getSolde_compte() %> &euro;</span>
	              </div>
	          </div>
	        </article>
	        
	        <article>
		        <form method="POST" action="controller">
		          <input type="hidden" name="action" value="transfert">
		          <div class="fieldset">
		              <div class="fieldset_label">
		                  <span>Transférer de l'argent</span>
		              </div>
		              <div class="field">
		                  <label>N° compte destinataire : </label><input type="text" size="20" name="destination">
		              </div>
		              <div class="field">
		                  <label>Montant à transférer : </label><input type="text" size="10" name="montant">
		              </div>
		              <button class="form-btn">Transférer</button>

<%-- Gestion de l'affichage d'un message à l'utilisateur suite à un transfert --%>		    
<% if (request.getParameter("trf_ok") != null) { %>
					<p>Virement effectué avec succès.</p>
<% } %>
<% if (request.getParameter("trf_notok") != null) { %>
					<p>Le virement a échoué.</p>
<% } %>
<% if (request.getParameter("bad_mt") != null) { %>
					<p>Le montant saisi est incorrect : <%= request.getParameter("bad_mt") %></p>
<% } %>
		          </div>
		        </form>
	        </article>
	        
	        <article>
		        <form method="POST" action="controller">
		          <input type="hidden" name="action" value="sendmsg">
		          <div class="fieldset">
		              <div class="fieldset_label">
		                  <span>Envoyer un message</span>
		              </div>
		              <div class="field">
		                  <label>Destinataire : </label>
		                  <select name="to">
<% 
// Cette façon de faire est déconseillée : utliser JSTL à la place
if (session.getAttribute("all_users") != null) {
	for (User user : (ArrayList<User>)session.getAttribute("all_users")) {
		if (user.getId_user() != connected_user.getId_user()) {
%>
	<option value="<%= user.getId_user() %>"> <%= user.getNom() %> <%= user.getPrenom() %> </option>		
<%
		}
	}
}
%>
		                  </select>
		              </div>
		              <div class="field">
		                  <label>Sujet : </label><input type="text" size="20" name="sujet">
		              </div>
		              <div class="field">
		                  <label>Message : </label><textarea name="corps" cols="25" rows="3"></textarea>
		              </div>
		              <button class="form-btn">Envoyer</button>
		              
<%-- Gestion de l'affichage d'un message à l'utilisateur suite à un envoi de message --%>		    
<% if (request.getParameter("msg_ok") != null) { %>
					<p>Message envoyé avec succès.</p>
<% } %>
		              <p><a href="controller?action=msglist&userid=<%= connected_user.getId_user() %>" target="_blank">Ma messagerie</a></p>
		          </div>
		        </form>
	        </article>
	        
	    </section>


	</body>
</html>
