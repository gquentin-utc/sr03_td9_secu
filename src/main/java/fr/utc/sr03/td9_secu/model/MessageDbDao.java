package fr.utc.sr03.td9_secu.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MessageDbDao {

	private static Logger logger = Logger.getLogger(MessageDbDao.class.getPackage().getName());
	
	
	public static List<Message> findByIdDestinataire(int idDestinataire) {
		
		List<Message> foundMessages = new ArrayList<Message>();
		
		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT id_msg,sujet_msg,corps_msg,id_user_to,id_user_from FROM messages WHERE id_user_to="+idDestinataire+" ORDER BY id_msg");
		        while (rs.next()) {
		        	// identifants corrects
		        	Message oneMessage = new Message(rs.getInt("id_msg"),rs.getInt("id_user_to"),rs.getInt("id_user_from"),rs.getString("sujet_msg"),rs.getString("corps_msg"));
		        	oneMessage.setUserFrom(UserDbDao.findById(rs.getInt("id_user_from")));
		        	foundMessages.add(oneMessage);
		        }
			} catch (SQLException e) {
				logger.severe("SQLException :: "+e.getMessage());
			}
			
		}

		try {
			conn.close();
		} catch (SQLException e) {
			logger.severe("Connection not closed => SQLException :: "+e.getMessage());
		}

		return foundMessages;
		
	}
	
	
	public static boolean envoyerMessage(int idExpediteur, int idDestinataire, String sujet, String corps) {
		
		boolean envoiReussi = false;
		
		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        stmt.execute("INSERT INTO messages(id_user_to,id_user_from,sujet_msg,corps_msg) values ("+idDestinataire+","+idExpediteur+",'"+sujet+"','"+corps+"')");
		        envoiReussi = true;
			} catch (SQLException e) {
				logger.severe("SQLException :: "+e.getMessage());
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			logger.severe("Connection not closed => SQLException :: "+e.getMessage());
		}

		return envoiReussi;	
		
	}
	
}
	
	



