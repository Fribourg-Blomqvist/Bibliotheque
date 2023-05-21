package dao;

import entity.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurDAOTest {

    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Test
    void findAll() {
        List<Utilisateur> all = utilisateurDAO.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void findById() {
        Utilisateur utilisateurTrouve = utilisateurDAO.findById(1);
        assertEquals(1, utilisateurTrouve.getIdUtilisateur());
    }

    @Test
    void create(){
        int nouveauId = utilisateurDAO.create("pseudo99", "99", false);
        assertEquals(utilisateurDAO.findById(nouveauId).getPseudo(), "pseudo99");
    }

    @Test
    void findByPseudo(){
        String pseudo = "DOE";
        List<Utilisateur> byPseudo = utilisateurDAO.findByPseudo(pseudo);
        byPseudo.forEach(System.out::println);

        String pseudoViensDeUtilisateur = byPseudo.get(0).getPseudo().toLowerCase();
        String pseudoEnMiniscule = pseudo.toLowerCase();
        assertTrue(pseudoViensDeUtilisateur.contains(pseudoEnMiniscule));
    }

}