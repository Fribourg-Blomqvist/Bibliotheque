package dao;

import entity.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UtilisateurDAO extends DAO {
    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                utilisateur.setPseudo(resultSet.getString("pseudo"));
                utilisateur.setMotPasse(resultSet.getString("mot_passe"));
                utilisateur.setBibliothecaire(resultSet.getBoolean("bibliothecaire"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    @Override
    public Utilisateur findById(int id) {
        String sql = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                utilisateur.setPseudo(resultSet.getString("pseudo"));
                utilisateur.setMotPasse(resultSet.getString("mot_passe"));
                utilisateur.setBibliothecaire(resultSet.getBoolean("bibliothecaire"));
                return utilisateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Utilisateur> findByPseudo(String pseudo){
        String sql = "SELECT * FROM utilisateur WHERE LOWER(pseudo) LIKE LOWER(?)";
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            pseudo = "%" + pseudo.toLowerCase() + "%"; //modifier en miniscule
            preparedStatement.setString(1, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                utilisateur.setPseudo(resultSet.getString("pseudo"));
                utilisateur.setMotPasse(resultSet.getString("mot_passe"));
                utilisateur.setBibliothecaire(resultSet.getBoolean("bibliothecaire"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }


    //
    public int create(String pseudo, String motPasse, boolean bibliothecaire){
        String sql = "INSERT INTO utilisateur (pseudo, mot_passe, bibliothecaire) VALUES (?, ?, ?)";
        Integer nouveauId = null;
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, motPasse);
            preparedStatement.setBoolean(3, bibliothecaire);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                nouveauId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nouveauId;
    }

    public void updateMotPasse(int id){

    }

    public void updatePseudo(int id ){

        Connection connection = ConnexionBD.getConnection();
        String sql = "UPDATE utilisateur SET type=? WHERE idUutilisateur=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // preparedStatement.setString(1, pseudo);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilisateur(int id){

        Utilisateur utilisateur = (Utilisateur) this.findById(id);
        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM utilsateur WHERE idUtilisateur = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(utilisateur + " est supprimé.");
    }
}

