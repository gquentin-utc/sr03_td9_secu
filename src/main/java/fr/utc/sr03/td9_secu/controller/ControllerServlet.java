package fr.utc.sr03.td9_secu.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import fr.utc.sr03.td9_secu.model.MessageDbDao;
import fr.utc.sr03.td9_secu.model.User;
import fr.utc.sr03.td9_secu.model.UserDbDao;


@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// URL de redirection par defaut (si pas d'action ou action non reconnue)
		String urlRedirect = "accueil.jsp";
		
		String pAction = request.getParameter("action") ;

		if (pAction.compareTo("disconnect") == 0)  {
			/* ======== DISCONNECT ======== */
			session.invalidate();
			urlRedirect = request.getParameter("loginPage")  ;
	          
	    } else if (pAction.compareTo("transfert") == 0) {
	    	/* ======== TRANSFERT ======== */
	    	try {
	            int montant = Integer.parseInt(request.getParameter("montant"));
	            User connectedUser = (User) session.getAttribute("connected_user");
	            
	            if (UserDbDao.transfererArgent(request.getParameter("destination"), connectedUser.getNumero_compte(), montant)) {
		            connectedUser.setSolde_compte(connectedUser.getSolde_compte() - montant); 
		            urlRedirect = "accueil.jsp?trf_ok";
	            } else {
		            urlRedirect = "accueil.jsp?trf_notok";
	            }
	            	            
	        } catch (NumberFormatException nfe) {
	        	// le montant saisi est incorrect
	        	urlRedirect = "accueil.jsp?bad_mt="+request.getParameter("montant");
	        }
	       
	    } else if (pAction.compareTo("sendmsg") == 0)  {
	    	/* ======== MESSAGE ======== */
            User connectedUser = (User) session.getAttribute("connected_user");
            int idDest = Integer.parseInt(request.getParameter("to"));
	    	MessageDbDao.envoyerMessage(connectedUser.getId_user(), idDest, request.getParameter("sujet"), request.getParameter("corps"));
	    	urlRedirect = "accueil.jsp?msg_ok";
	              
	    } else if (pAction.compareTo("msglist") == 0)  {
	    	/* ======== MESSAGE ======== */
	    	session.setAttribute("messagesRecus",MessageDbDao.findByIdDestinataire(Integer.parseInt(request.getParameter("userid"))));
	    	urlRedirect = "messagerie.jsp";
	              
	    } 
		
		// redirection vers l'URL visee
		response.sendRedirect(urlRedirect);
		
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
