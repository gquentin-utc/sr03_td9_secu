package fr.utc.sr03.td9_secu.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import fr.utc.sr03.td9_secu.model.User;
import fr.utc.sr03.td9_secu.model.UserDbDao;


@WebServlet("/connect")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ConnexionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pLogin = request.getParameter("login") ;
		String pMdp = request.getParameter("mdp") ;
		
		if (pLogin == null || pLogin.isBlank() || pMdp == null || pMdp.isBlank()) {
			// login ou mdp non saisi
			response.sendRedirect("connexion.jsp?nullvalue");
		} else {
			User connectedUser = UserDbDao.findByLoginPwd(pLogin, pMdp);
			if (connectedUser != null) {
				// authentification reussie
				HttpSession session = request.getSession();
				session.setAttribute("connected_user", connectedUser);
				session.setAttribute("all_users", UserDbDao.findAll());
				response.sendRedirect("accueil.jsp");
			} else {
	        	// erreur d'authentification
				response.sendRedirect("connexion.jsp?badvalue");
			} 
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
