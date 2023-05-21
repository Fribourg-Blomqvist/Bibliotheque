package dao;

import entity.Auteur;

import java.util.List;
import java.util.Scanner;

//AuthorDAO
public class AuteurDAOTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("findAll test");
        AuteurDAO auteurDao = new AuteurDAO();
        List all = auteurDao.findAll();
        all.forEach(System.out::println);


/*        System.out.println("\nfindById test");
        System.out.print("id ? : ");
        int id = sc.nextInt();
        Auteur byId = (Auteur) auteurDao.findById(id);
        System.out.println("byId = " + byId);

        System.out.println("\n createAuteur test");
        auteurDao.createAuteur("XXXXX");

        System.out.println("\nfindAll test");
        auteurDao = new AuteurDAO();
        List all2 = auteurDao.findAll();
        all2.forEach(System.out::println);

        System.out.println("\ndeleteAuteur TEST");
        System.out.print("id à supprimer : ");
        id = sc.nextInt();
        auteurDao.deleteAuteur(id);*/


        System.out.println("\n findByName Test");
        System.out.print("Le nom d'auteur à rechercher : ");
        String nom = sc.nextLine();
        List<Auteur> listAuteurs = auteurDao.findByName(nom);
        System.out.println(listAuteurs);

        System.out.println("\n updateNomAuteur TEST");
        System.out.print("id d'auteur à modifier : ");
        int idmodifier = sc.nextInt();sc.nextLine();
        System.out.print("nom d'autuer à modifier : ");
        String nomModifier = sc.nextLine();
        auteurDao.updateNomAuteur(idmodifier, nomModifier);
        List all2 = auteurDao.findAll();
        all2.forEach(System.out::println);

    }
}
