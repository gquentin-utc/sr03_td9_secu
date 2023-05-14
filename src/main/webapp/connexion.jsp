<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <title>Connexion</title>
	  <link rel="stylesheet" type="text/css" media="all"  href="resources/css/mystyle.css" />
	</head>
	<body>
		<header>
			<h1>Connexion</h1>
		</header>
		
		<section>
		    <div class="login-page">
				<div class="form">
				    <form method="POST" action="connect">
				        <input type="text" name="login" placeholder="login"/>
				        <input type="password" name="mdp" placeholder="mot de passe"/>
				        <button>login</button>
				    </form>
				</div>
		    </div>

<%-- Gestion de l'affichage d'un message  à l'utilisateur si paramètre présent dans l'URL --%>		    
<% if (request.getParameter("nullvalue") != null) { %>
			<p class="errmsg" >Merci de saisir votre login et votre mot de passe</p>
<% } else if (request.getParameter("bdderror") != null) { %>
			<p class="errmsg" >Problème technique : impossible de vérifier vos identifiants</p>
<% } else if (request.getParameter("badvalue") != null) { %>
			<p class="errmsg" >Votre login/mot de passe est incorrect</p>
<% } else if (request.getParameter("disconnect") != null) { %>
			<p class="errmsg" >Vous avez bien été déconnecté.</p>
<% } %>

		</section>

	</body>
</html>
