package dao;

import entity.Auteur;
import entity.TypeOuvrage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeOuvrageDAO extends DAO{


    @Override
    public List findAll() {
        List<TypeOuvrage> listTypeOuvrage = new ArrayList<>();
        String sql = "SELECT * FROM type_ouvrage ORDER BY type ASC";
        Connection connection = ConnexionBD.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                TypeOuvrage typeOuvrage = new TypeOuvrage();
                typeOuvrage.setIdTypeOuvrage(resultSet.getInt("id_type_ouvrage"));
                typeOuvrage.setType(resultSet.getString("type"));
                listTypeOuvrage.add(typeOuvrage);
            }
            //preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTypeOuvrage;
    }

    @Override
    public TypeOuvrage findById(int id) {

        String sql = "SELECT * FROM type_ouvrage WHERE id_type_ouvrage = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                TypeOuvrage typeOuvrage = new TypeOuvrage();
                typeOuvrage.setIdTypeOuvrage(resultSet.getInt("id_type_ouvrage"));
                typeOuvrage.setType(resultSet.getString("type"));
                preparedStatement.close();
                return typeOuvrage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TypeOuvrage> findByName(String type){
        List<TypeOuvrage> listTypeOuvrage = new ArrayList<>();
        Connection connection = ConnexionBD.getConnection();
        String sql = "SELECT * FROM type_ouvrage WHERE LOWER(type) LIKE LOWER(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            type = "%"+type.toLowerCase()+"%"; //lower
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                TypeOuvrage typeOuvrage = new TypeOuvrage();
                typeOuvrage.setIdTypeOuvrage(resultSet.getInt("id_type_ouvrage"));
                typeOuvrage.setType(resultSet.getString("type"));
                listTypeOuvrage.add(typeOuvrage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTypeOuvrage;
    }

    public void createType(String nom){
        Connection connection = ConnexionBD.getConnection();
        String sql = "INSERT INTO type_ouvrage (type) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                System.out.println(findById(id) + " est créé.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteType(int id){
        TypeOuvrage typeOuvrage = (TypeOuvrage) this.findById(id);
        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM type_ouvrage WHERE id_type_ouvrage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(typeOuvrage + " est supprimé.");
    }

    public void updateTypeNom(int id, String nomType){
        Connection connection = ConnexionBD.getConnection();
        String sql = "UPDATE type_ouvrage SET type=? WHERE id_type_ouvrage=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomType);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
