package dao;

import entity.Auteur;
import entity.Ouvrage;
import entity.TypeOuvrage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Avant de faire les testes sur cette classe, il faut activer le code :
//connection.setAutoCommit(false); // transaction - rolled back.
dans la classe ConnexionBD.java
* */

class OuvrageDAOTest {

    OuvrageDAO ouvrageDao = new OuvrageDAO();

    @org.junit.jupiter.api.Test
    void findAll() {
        List<Ouvrage> all = ouvrageDao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void findById(){
        Ouvrage foundOuvrage = ouvrageDao.findById(5);
        System.out.println(foundOuvrage);
        assertEquals(foundOuvrage.getTitre(), "Harry Potter");
    }


    @Test
    void create(){
        String titre = "Asterix 6";

        TypeOuvrageDAO typeOuvrageDAO = new TypeOuvrageDAO();
        TypeOuvrage typeOuvrageBD = typeOuvrageDAO.findById(3);

        List<Auteur> listAuteur = new ArrayList<>();
        AuteurDAO auteurDAO = new AuteurDAO();
        listAuteur.add(auteurDAO.findById(2));
        listAuteur.add(auteurDAO.findById(3));
        listAuteur.add(auteurDAO.findById(4));

        Ouvrage ouvrage = new Ouvrage(titre, 10, typeOuvrageBD, listAuteur);
        Integer newOuvrageId = ouvrageDao.createOuvrage(ouvrage);
        assertEquals(ouvrageDao.findById(newOuvrageId).getTitre(), titre);
        assertEquals(ouvrageDao.findById(newOuvrageId).getDureeAutorisationMax(), 10);
    }

    @Test
    void updateOuvrageNom(){
        ouvrageDao.updateTitre(8, "ELLE");
        assertEquals(ouvrageDao.findById(8).getTitre(), "ELLE");
    }

}