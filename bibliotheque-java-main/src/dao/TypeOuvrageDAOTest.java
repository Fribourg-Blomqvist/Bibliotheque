package dao;

import entity.TypeOuvrage;

import java.util.List;
import java.util.Scanner;

public class TypeOuvrageDAOTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nfindAll test");
        TypeOuvrageDAO typeOuvrageDAO = new TypeOuvrageDAO();
        List all = typeOuvrageDAO.findAll();
        all.forEach(System.out::println);

        System.out.println("\nfindById test");
        TypeOuvrage byId = (TypeOuvrage) typeOuvrageDAO.findById(2);
        System.out.println("byId = " + byId);

        System.out.println("\nfindByName test");
        System.out.print("nom du type à rechercher : ");
        String type = sc.nextLine();
        List<TypeOuvrage> listByName = typeOuvrageDAO.findByName(type);
        listByName.forEach(System.out::println);

        System.out.println("\ncreateType() test ");
        System.out.print("nom du type à créer : ");
        String nomType = sc.nextLine();
        typeOuvrageDAO.createType(nomType);

        System.out.println("\nfindAll test");
        List all2 = typeOuvrageDAO.findAll();
        all2.forEach(System.out::println);

/*        System.out.println("\nupdateTypeNom() TEST");
        System.out.print("id du type pour modifier : ");
        int idForUpdate = sc.nextInt();sc.nextLine();
        System.out.print("Nouveau nom du type : ");
        String nomForUpdate = sc.nextLine();
        typeOuvrageDAO.updateTypeNom(idForUpdate, nomForUpdate);
        System.out.println("Resultat : "+typeOuvrageDAO.findById(idForUpdate));

        System.out.println("\ndeleteType() TEST");
        System.out.print("id du type ouvrage à supprimer : ");
        int idSupprimer = sc.nextInt();sc.nextLine();
        typeOuvrageDAO.deleteType(idSupprimer);

        System.out.println("\nfindAll test");
        List all3 = typeOuvrageDAO.findAll();
        all3.forEach(System.out::println);*/

    }//main
}
