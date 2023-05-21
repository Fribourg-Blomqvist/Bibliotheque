package dao;

import entity.Auteur;
import entity.Ouvrage;
import entity.TypeOuvrage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//edited by Jun

public class AuteurDAO extends DAO{


    public void updateNomAuteur(int id, String nomAuteur){
        Connection conn = ConnexionBD.getConnection();
        String sql = "UPDATE auteur SET nom=? WHERE id_auteur=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nomAuteur);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            /////probleme ici!
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Auteur> findByName(String nom){
        List<Auteur> auteurs = new ArrayList<>();
        Connection connection = ConnexionBD.getConnection();
        String sql = "SELECT * FROM auteur WHERE LOWER(nom) LIKE LOWER(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            nom = "%"+nom.toLowerCase()+"%"; //lower
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Auteur auteur = new Auteur();
                auteur.setIdAuteur(resultSet.getInt("id_auteur"));
                auteur.setNom(resultSet.getString("nom"));
                auteurs.add(auteur);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auteurs;
    }


    @Override
    public List findAll() {
        List<Auteur> auteurs = new ArrayList<>();
        String sql = "" +
                "SELECT * FROM auteur";
        Connection connexion = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                Auteur auteur = new Auteur();
                auteur.setIdAuteur(resultSet.getInt("id_auteur"));
                auteur.setNom(resultSet.getString("nom"));
                auteurs.add(auteur);
            }
            preparedStatement.close();
            return auteurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auteurs;
    }

    @Override
    public Auteur findById(int id) {

        String sql = "SELECT * FROM auteur WHERE id_auteur = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Auteur auteur = new Auteur();
                auteur.setIdAuteur(resultSet.getInt("id_auteur"));
                auteur.setNom(resultSet.getString("nom"));
                preparedStatement.close();
                return auteur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createAuteur(String nom){
        Connection connection = ConnexionBD.getConnection();
        String sql = "INSERT INTO auteur (nom) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                System.out.println("Auteur id "+generatedKeys.getInt(1)+" est créé.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void deleteAuteur(int id){
        Auteur auteur = (Auteur) this.findById(id);
        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM auteur WHERE id_auteur = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println(auteur+ " est supprimé");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // méthode à réaliser
    public List<Ouvrage> findAllOuvrageByAuteur(int idAuteur){
        String sql = "SELECT o.id_ouvrage, o.titre, a.nom  FROM auteur a JOIN avoir_auteur aa ON a.id_auteur = aa.id_auteur " +
                "JOIN ouvrage o ON aa.id_ouvrage = o.id_ouvrage  WHERE a.id_auteur = ?";
        return null;
    }

}