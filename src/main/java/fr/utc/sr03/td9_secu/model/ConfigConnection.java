package fr.utc.sr03.td9_secu.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigConnection {

	public static Connection getConnection() {
		Properties props = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(ConfigConnection.class.getResource("/db_conf.properties").openStream());
			props.load(bis);
			Class.forName(props.getProperty("driver"));
			return DriverManager.getConnection(props.getProperty("url"),props.getProperty("utilisateur"),props.getProperty("mdp"));
		} catch (IOException ioe) {
			System.out.println("ERREUR lecture properties :: IOException :: "+ioe.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ERREUR Chargement driver JDBC :: ClassNotFoundException :: "+e.getMessage());
		}  catch (SQLException sqle) {
			System.out.println("ERREUR Connexion � MySQL :: SQLException :: "+sqle.getMessage());
		} finally {
				try {
					if (bis != null)
						bis.close();
				} catch (IOException e) {}
		}

		return null;
	}

}
