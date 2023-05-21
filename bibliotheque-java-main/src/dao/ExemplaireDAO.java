package dao;

import entity.Auteur;
import entity.Exemplaire;
import entity.Ouvrage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExemplaireDAO extends DAO{

    @Override
    public List<Exemplaire> findAll() {
        List<Exemplaire> listExemplaire = new ArrayList<>();
        String sql = "SELECT * FROM exemplaire";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Exemplaire exemplaire = new Exemplaire();
                exemplaire.setIdExemplaire(resultSet.getInt("id_exemplaire"));
                exemplaire.setDisponibilitee(resultSet.getBoolean("disponibilitee"));

                OuvrageDAO ouvrageDao = new OuvrageDAO();
                Ouvrage ouvrage = ouvrageDao.findById(resultSet.getInt("id_ouvrage"));
                exemplaire.setOuvrage(ouvrage);
                listExemplaire.add(exemplaire);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listExemplaire;
    }


    @Override
    public Exemplaire findById(int id) {
        Exemplaire exemplaire = new Exemplaire();
        String sql = "SELECT * FROM exemplaire WHERE id_exemplaire = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                exemplaire.setIdExemplaire(resultSet.getInt("id_exemplaire"));
                exemplaire.setDisponibilitee(resultSet.getBoolean("disponibilitee"));

                OuvrageDAO ouvrageDao = new OuvrageDAO();
                Ouvrage ouvrage = ouvrageDao.findById(resultSet.getInt("id_ouvrage"));
                exemplaire.setOuvrage(ouvrage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(exemplaire.getIdExemplaire() == null){
            return null;
        }else {
            return exemplaire;
        }
    }


    public int createExemplaire(boolean disponilitee, int idOuvrage){
        Integer generatedId = null;
        Connection connection = ConnexionBD.getConnection();
        String sql = "INSERT INTO exemplaire (disponibilitee, id_ouvrage) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, disponilitee);
            preparedStatement.setInt(2, idOuvrage);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
                System.out.println("id_exemplaire "+generatedId + " est créé.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //augmentation nb_exemplaire de l'Ouvrage
        String sql2 = "UPDATE ouvrage o SET o.nb_exemplaires = o.nb_exemplaires +1 WHERE o.id_ouvrage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, idOuvrage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }


    //update disponibilite.
    public void updateDisponibilite(int idExemplaire, boolean disponibilitee){
        Connection connection = ConnexionBD.getConnection();
        String sql = "UPDATE exemplaire SET exemplaire.disponibilitee = ? WHERE exemplaire.id_exemplaire = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, disponibilitee);
            preparedStatement.setInt(2, idExemplaire);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteExemplaire(int id){
        Exemplaire exemplaire = this.findById(id);
        int idOuvrage = exemplaire.getOuvrage().getIdOuvrage();

        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM exemplaire WHERE id_exemplaire = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //soustraire 1 de "nb_exemplaire de l'Ouvrage"
        String sql2 = "UPDATE ouvrage o SET o.nb_exemplaires = o.nb_exemplaires -1 WHERE o.id_ouvrage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, idOuvrage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(exemplaire + " est supprimée.");
    }

}