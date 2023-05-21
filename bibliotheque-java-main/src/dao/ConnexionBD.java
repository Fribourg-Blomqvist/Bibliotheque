package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

	//Jun & Sophie
	private static final String URL = "jdbc:mysql://localhost:3306/Bibliotheque?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true&autoReconnect=true";
	private static final String LOGIN = "groupe2";
	private static final String PSW = "abc";

	// connection pour Mathieu
/*	private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String LOGIN = "root";
	private static final String PSW = "";*/

	private static Connection connection;
	
	public static Connection getConnection() {
		//JDBC DRIVER inscription
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if(connection == null) {
			// Créer une connecion
			try {
				connection = DriverManager.getConnection(URL, LOGIN, PSW);

				//Transaction (rolled back) : le code suivant éteint "Commit automatique" pour la base de donnée.
				//ça veut dire, les changements de données sur les codes JAVA ne seront pas appliqués automatiquement
				// sur la base de donnée. Vous pouvez éteindre "commit automatique" pour tester les méthodes sans changement de base de donnée.
				//Si on a besoin de commit automatique (option par défaut), juste il faut ajouter "//" devant le code.
				//Si on n'a pas besoin de commit automatique pour testes les codes, enlever "//" devant le code
				//connection.setAutoCommit(false); // transaction - rolled back.

			} catch (SQLException e) {
				System.out.println("Erreur : "+ e.getMessage());
			}	
		}
		return connection;
	}
}
