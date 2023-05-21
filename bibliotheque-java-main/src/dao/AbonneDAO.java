package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Abonne;

public class AbonneDAO extends DAO {

    public List<Abonne> findAll() {
        List<Abonne> abonne = new ArrayList<>();

        String sql = "" + "SELECT * FROM abonne";
        Connection connexion = ConnexionBD.getConnection();

        try {
            PreparedStatement pStatement = connexion.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();
            // var Abonne= new ArrayList<Abonne>();
            while (resultSet.next()) {
                Abonne a = new Abonne();
                a.setIdAbonne(resultSet.getInt("id_abonne"));
                a.setAdresse(resultSet.getString("adresse"));
                a.setCodePostal(resultSet.getInt("code_postal"));
                a.setNom(resultSet.getString("nom"));
                a.setPrenom(resultSet.getString("prenom"));
                a.setTel(resultSet.getString("tel"));
                a.setEmail(resultSet.getString("email"));
                a.setNbInfractions(resultSet.getInt("nb_infractions"));
                a.setIdUtilisateur(resultSet.getInt("id_utilisateur"));

                // Ajouter l'objet Ã  la liste
                abonne.add(a);
            }
            return abonne;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Abonne findById(int id) {
        String sql = "SELECT * FROM abonne WHERE id_abonne = ?";
        Connection connection = ConnexionBD.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Abonne abonne = new Abonne();
                abonne.setIdAbonne(resultSet.getInt("id_abonne"));
                abonne.setNom(resultSet.getString("nom"));
                abonne.setPrenom(resultSet.getString("prenom"));
                abonne.setEmail(resultSet.getString("email"));
                abonne.setCodePostal(resultSet.getInt("code_postal"));
                abonne.setAdresse(resultSet.getString("adresse"));
                abonne.setTel(resultSet.getString("tel"));
                preparedStatement.close();
                return abonne;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    /*
     * try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
     */

    public List<Abonne> findByName(String nom) {
        List<Abonne> listAbonnes = new ArrayList<>();
        Connection connection = ConnexionBD.getConnection();
        String sql = "SELECT * FROM abonne WHERE LOWER(nom) LIKE LOWER(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            nom = "%" + nom.toLowerCase() + "%"; // lower
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Abonne abonne = new Abonne();
                abonne.setIdAbonne(resultSet.getInt("id_abonne"));
                abonne.setNom(resultSet.getString("nom"));
                abonne.setPrenom(resultSet.getString("prenom"));
                abonne.setEmail(resultSet.getString("email"));
                abonne.setCodePostal(resultSet.getInt("code_postal"));
                abonne.setAdresse(resultSet.getString("adresse"));
                abonne.setTel(resultSet.getString("tel"));
                listAbonnes.add(abonne);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAbonnes;

    }

    public int createAbonne(String nom, String prenom, String adresse, int codePostal,int idUtilisateur) {
        Integer idNewAbonne = null;
        Connection connection = ConnexionBD.getConnection();
        String sql = "INSERT INTO abonne (nom, prenom, adresse, code_Postal, id_utilisateur) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setInt(4, codePostal);
            preparedStatement.setInt(5, idUtilisateur);

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idNewAbonne = generatedKeys.getInt(1);
                // System.out.println("Abonne id "+generatedKeys.getInt(1)+" est créé.");
                return idNewAbonne;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateNomAbonne(int id, String nomAbonne) {
        Connection conn = ConnexionBD.getConnection();
        String sql = "UPDATE abonne SET nom=? WHERE id_abonne=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nomAbonne);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            ///// probleme ici!
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAbonne(int id) {
        Abonne abonne = (Abonne) this.findById(id);
        Connection connection = ConnexionBD.getConnection();
        String sql = "DELETE FROM abonne WHERE id_abonne = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println(abonne + " est supprimé");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
