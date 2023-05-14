package fr.utc.sr03.td9_secu.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserDbDao {

	private static Logger logger = Logger.getLogger(UserDbDao.class.getPackage().getName());


	public static User findById(int idUser) {

		User foundUser = null;

		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT id_user,login,mot_de_passe,nom,prenom,numero_compte,profil_user, solde_compte"
		        								+ " FROM users"
		        								+ " WHERE id_user="+idUser);
		        if (rs.next()) {
		        	foundUser = new User(rs.getInt("id_user"),
        								  rs.getString("login"),
        								  rs.getString("mot_de_passe"),
        								  rs.getString("nom"),
        								  rs.getString("prenom"),
        								  rs.getString("numero_compte"),
        								  rs.getString("profil_user"),
        								  rs.getInt("solde_compte"));
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

		return foundUser;

	}


	public static User findByLoginPwd(String pLogin, String pMdp) {

		User foundUser = null;

		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT id_user,login,mot_de_passe,nom,prenom,numero_compte,profil_user, solde_compte"
		        								+ " FROM users"
		        								+ " WHERE login='"+pLogin+"' AND mot_de_passe='"+pMdp+"'");
		        if (rs.next()) {
		        	// identifants corrects
		        	foundUser = new User(rs.getInt("id_user"),
        								  rs.getString("login"),
        								  rs.getString("mot_de_passe"),
        								  rs.getString("nom"),
        								  rs.getString("prenom"),
        								  rs.getString("numero_compte"),
        								  rs.getString("profil_user"),
        								  rs.getInt("solde_compte"));
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

		return foundUser;

	}



	public static List<User> findAll() {

		List<User> foundUsers = new ArrayList<User>();

		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT id_user,login,mot_de_passe,nom,prenom,numero_compte,profil_user, solde_compte FROM users ORDER BY nom,prenom");
		        while (rs.next()) {
		        	// identifants corrects
		        	foundUsers.add(new User(rs.getInt("id_user"),
        								  rs.getString("login"),
        								  rs.getString("mot_de_passe"),
        								  rs.getString("nom"),
        								  rs.getString("prenom"),
        								  rs.getString("numero_compte"),
        								  rs.getString("profil_user"),
        								  rs.getInt("solde_compte")));
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

		return foundUsers;

	}



	public static boolean transfererArgent(String compteTO, String compteFrom, int montant) {

		boolean transfertReussi = false;

		Connection conn = ConfigConnection.getConnection();
		if (conn == null) {
			// dans le cas ou la connexion a MySQL a echoue
			logger.severe("Echec ConfigConnection.getConnection()");
		} else {
			try {
				Statement stmt = conn.createStatement();
		        stmt.execute("UPDATE users SET solde_compte=solde_compte+"+montant+" where numero_compte='"+compteTO+"'");
		        stmt.execute("UPDATE users SET solde_compte=solde_compte-"+montant+" where numero_compte='"+compteFrom+"'");
		        transfertReussi = true;
			} catch (SQLException e) {
				logger.severe("SQLException :: "+e.getMessage());
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			logger.severe("Connection not closed => SQLException :: "+e.getMessage());
		}

		return transfertReussi;

	}

}
