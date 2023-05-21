package dao;

import java.util.List;
import java.util.Scanner;

import entity.Abonne;

public class AbonneDAOTest {
    public static void main(String[] args) {
/*        Scanner sc = new Scanner(System.in);
        AbonneDAO abonneDao = new AbonneDAO();
        List<Abonne> list = abonneDao.findAll();
        list.forEach(System.out::println);

        System.out.println("findById test");
        System.out.print("Entrer un id : ");
        int id = sc.nextInt();
        Abonne a = (Abonne) abonneDao.findById(id);
        System.out.println("byId = " + a);

        System.out.println("\n findByName Test");
        System.out.print("Entrer le nom d'abonne à rechercher : ");

        sc.nextLine();
        String nom = sc.nextLine();
        List<Abonne> listAbonne = abonneDao.findByName(nom);

        listAbonne.forEach(System.out::println);
        System.out.println("TESTER INSERTION ABONNE :");*/

        createAbonne();
    }

    static void createAbonne() {
        AbonneDAO aDAO = new AbonneDAO();

        Integer idNewAbonne = aDAO.createAbonne("stan", "Smith", "21 jump street chicago", 91100, 1);
        System.out.println(aDAO.findById(idNewAbonne));
        //Assertions.assertEquals(abonneDao.findById(idNewAbonne).getName(), "Stan");

    }
}