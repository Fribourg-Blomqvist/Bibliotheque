package dao;

import entity.Auteur;
import entity.Ouvrage;
import entity.TypeOuvrage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class OuvrageDAO extends DAO{


    @Override
    public List<Ouvrage> findAll() {
        List<Ouvrage> listOuvrage = new ArrayList<>();
        String sql = "SELECT * FROM ouvrage JOIN type_ouvrage \n" +
                "ON ouvrage.id_type_ouvrage = type_ouvrage.id_type_ouvrage ORDER BY titre ASC";
        try {
            PreparedStatement pStatement = ConnexionBD.getConnection().prepareStatement(sql);
            ResultSet resultat = pStatement.executeQuery();

            while(resultat.next()) {
                Ouvrage ouvrage = new Ouvrage();
                ouvrage.setIdOuvrage(resultat.getInt("id_ouvrage"));
                ouvrage.setTitre(resultat.getString("titre"));
                ouvrage.setDureeAutorisationMax(resultat.getInt("duree_autorisation_max"));
                ouvrage.setNbExemplaires(resultat.getInt("nb_exemplaires"));

                TypeOuvrage typeOuvrage = new TypeOuvrage();
                typeOuvrage.setIdTypeOuvrage(resultat.getInt("id_type_ouvrage"));
                typeOuvrage.setType(resultat.getString("type"));
                ouvrage.setTypeOuvrage(typeOuvrage);

                List<Auteur> allAuteursByIdOuvrage = findAllAuteursByIdOuvrage(ouvrage.getIdOuvrage());
                ouvrage.setAuteurs(allAuteursByIdOuvrage);

                listOuvrage.add(ouvrage);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOuvrage;
    }


    // Cette méthode retourne la liste des auteurs d'un ouvrage. ça sert pour findAll de la classe OuvrageDAO.java
    public List<Auteur> findAllAuteursByIdOuvrage(int idOuvrage){
        List<Auteur> listAuteur = new ArrayList<>();
        String sql = "SELECT a.id_auteur, a.nom FROM auteur a " +
                "JOIN avoir_auteur aa ON a.id_auteur = aa.id_auteur " +
                "WHERE aa.id_ouvrage = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOuvrage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Auteur auteur = new Auteur();
                auteur.setIdAuteur(resultSet.getInt("id_auteur"));
                auteur.setNom(resultSet.getString("nom"));
                listAuteur.add(auteur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAuteur;
    }



    @Override
    public Ouvrage findById(int id) {
        String sql = "SELECT * FROM ouvrage JOIN type_ouvrage \n" +
                "ON ouvrage.id_type_ouvrage = type_ouvrage.id_type_ouvrage WHERE id_ouvrage = ?;";
        try {
            PreparedStatement preparedStatement = ConnexionBD.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Ouvrage ouvrage = new Ouvrage();
                ouvrage.setIdOuvrage(resultSet.getInt("id_ouvrage"));
                ouvrage.setTitre(resultSet.getString("titre"));
                ouvrage.setDureeAutorisationMax(resultSet.getInt("duree_autorisation_max"));
                ouvrage.setNbExemplaires(resultSet.getInt("nb_exemplaires"));

                TypeOuvrage typeOuvrage = new TypeOuvrage();
                typeOuvrage.setIdTypeOuvrage(resultSet.getInt("id_type_ouvrage"));
                typeOuvrage.setType(resultSet.getString("type"));
                ouvrage.setTypeOuvrage(typeOuvrage);

                List<Auteur> allAuteursByIdOuvrage = findAllAuteursByIdOuvrage(ouvrage.getIdOuvrage());
                ouvrage.setAuteurs(allAuteursByIdOuvrage);

                preparedStatement.close();
                return ouvrage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //j'ai ajouté des fonctions pour que quand on crée un Ouvrage, on puisse ajouter
    //les informations sur ses auteurs .
    public Integer createOuvrage(Ouvrage ouvrage){
        Connection connection = ConnexionBD.getConnection();
        Integer idNouveauOuvrage = null;
        String sql1 = "INSERT INTO Ouvrage (titre, duree_autorisation_max, nb_exemplaires, id_type_ouvrage) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ouvrage.getTitre());
            preparedStatement.setInt(2, ouvrage.getDureeAutorisationMax());
            preparedStatement.setInt(3, 0); // Quand on crée un ouvrage, nb exemplaire est 0. Après quand on crée un exemplaire, nb_exemplaire d'ouvrage va augmenter.
            preparedStatement.setInt(4, ouvrage.getTypeOuvrage().getIdTypeOuvrage());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                idNouveauOuvrage = generatedKeys.getInt(1);
                ouvrage.setIdOuvrage(idNouveauOuvrage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "INSERT INTO avoir_auteur (id_ouvrage, id_auteur) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, idNouveauOuvrage);
            List<Auteur> auteurs = ouvrage.getAuteurs();

            for(Auteur auteur : auteurs){
                preparedStatement.setInt(1, idNouveauOuvrage);
                preparedStatement.setInt(2, auteur.getIdAuteur());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(ouvrage + " est créé.");
        return idNouveauOuvrage;
    }


    //il faut : deleteOuvrage
    public void delete(int idOuvrage){
        Ouvrage ouvrage = this.findById(idOuvrage);
        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM ouvrage WHERE id_ouvrage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOuvrage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ouvrage+ " est supprimé.");
    }


    //update titre d'ouvrage
    public void updateTitre(int id, String titre){
        Connection connection = ConnexionBD.getConnection();
        String sql = "UPDATE ouvrage SET titre = ? WHERE ouvrage.id_ouvrage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, titre);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
