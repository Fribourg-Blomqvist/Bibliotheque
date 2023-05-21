package dao;
import java.sql.*;
// import java.util.Scanner;

import entity.Utilisateur;

public class ConnexionUtilisateurDAO {
    // @Override
    public Object getUserConnexion(String pseudo, String motPasse, int bibliothecaire) {

        String sql = "SELECT * FROM Utilisateur WHERE pseudo = ? AND mot_passe = ? AND bibliothecaire = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, motPasse);
            preparedStatement.setLong(3, bibliothecaire);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                utilisateur.setPseudo(resultSet.getString("pseudo"));
                preparedStatement.close();
                //ne pas faire connection.close() svp.
                return utilisateur;
            }
            preparedStatement.close();
            //ne pas faire connection.close() svp.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    
}
