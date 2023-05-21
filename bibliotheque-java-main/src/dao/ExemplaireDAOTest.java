package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entity.Exemplaire;
import entity.Ouvrage;

class ExemplaireDAOTest {


    ExemplaireDAO exemplaireDAO = new ExemplaireDAO();
    OuvrageDAO ouvrageDAO = new OuvrageDAO();

    @Test
    void findAll() {
        List<Exemplaire> all = exemplaireDAO.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void createExemplaire() {
        int exemplaireId = exemplaireDAO.createExemplaire(true, 40);
        Assertions.assertEquals(exemplaireDAO.findById(exemplaireId).getOuvrage().getIdOuvrage(),
                ouvrageDAO.findById(40).getIdOuvrage());
        System.out.println(exemplaireDAO.findById(exemplaireId).getOuvrage());
    }

    @Test
    void createExemplaireNbExemplaire() {
        //tester si chaque fois quand on crée Exemplaire, nb_exemplaire d'Ouvrage augmente aussi.
        int nbInitial = ouvrageDAO.findById(5).getNbExemplaires();

        int exemplaireId = exemplaireDAO.createExemplaire(true, 5);
        Assertions.assertEquals(exemplaireDAO.findById(exemplaireId).getOuvrage().getNbExemplaires(),
                nbInitial+1);
    }

    @Test
    void findById(){
        Exemplaire foundExemplaire = exemplaireDAO.findById(1);
        //System.out.println("foundExemplaire = " + foundExemplaire);
        Assertions.assertEquals(foundExemplaire.getIdExemplaire(), 1);
    }

    @Test
    void updateDisponibilite(){
        int id = 1;
        boolean disponibilitee = false;

        exemplaireDAO.updateDisponibilite(id, disponibilitee);
        assertEquals(exemplaireDAO.findById(id).isDisponibilitee(), disponibilitee);
        System.out.println(exemplaireDAO.findById(id).isDisponibilitee());
    }

    @Test
    void deleteExemplaire(){
        // verifier si findById == null &  nb exemplaire ouvrage est reduit
        int idExemplaire = 20;
        Ouvrage ouvrage = exemplaireDAO.findById(idExemplaire).getOuvrage();
        int nbExemplaireOuvrageAvant = ouvrage.getNbExemplaires();
        exemplaireDAO.deleteExemplaire(idExemplaire);
        int nbExemplaireOuvrageApres = ouvrage.getNbExemplaires();

        assertEquals(exemplaireDAO.findById(idExemplaire), null);
        assertEquals(nbExemplaireOuvrageAvant--, nbExemplaireOuvrageApres);
    }
    
    @Test
    void testTest() {
    	Assertions.assertEquals(1, 1);
    }

}