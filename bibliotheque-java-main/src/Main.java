

import menuBibliothecaire.MenuBibliothecaire;

import java.util.Scanner;

import dao.ConnexionUtilisateurDAO;
// import dao.ConnexionUtilisateurDAO;
import entity.Utilisateur;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Menu Login
        boolean etatLogin = false;
        do{
            System.out.println("         _._._                       _._._\n" +
                    "        _|   |_                     _|   |_\n" +
                    "        | ... |_._._._._._._._._._._| ... |\n" +
                    "        | ||| |  o BIBLIOTHEQUE o   | ||| |\n" +
                    "        | \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |\n" +
                    "   ())  |[-|-]| [-|-]  [-|-]  [-|-] |[-|-]|  ())\n" +
                    "  (())) |     |---------------------|     | (()))\n" +
                    " (())())| \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |(())())\n" +
                    " (()))()|[-|-]|  :::   .-\"-.   :::  |[-|-]|(()))()\n" +
                    " ()))(()|     | |~|~|  |_|_|  |~|~| |     |()))(()\n" +
                    "    ||  |_____|_|_|_|__|_|_|__|_|_|_|_____|  ||");
            System.out.println("B I E N V E N U E    A   L A    B I B L I O T H E Q U E " +
                    "\n                                       de Sophie, Mathieu, Jun\n");

            System.out.println("Pour tester l'interface, mettez 'admin' comme le pseudo et 'admin123' comme le mot de passe.");
            System.out.print("Pseudo : ");
            String pseudo = sc.nextLine();
            System.out.print("Mot de passe : ");
            String mp = sc.nextLine();
            int bibliothecaire = 1;

            // AJOUTER la vérification du pseudo et mot de passe d'un utilisateur
            //si pseudo == pseudo de db && mp == mp de db
            //et l'utilisateur est un bibliothécaire, on envoie à menuBibliothecaire()
            //si non exception ou répétition

            ConnexionUtilisateurDAO utilisateurDAO = new ConnexionUtilisateurDAO();

            Utilisateur utilisateur = (Utilisateur) utilisateurDAO.getUserConnexion(pseudo, mp, bibliothecaire);

            if (utilisateur != null) {
                etatLogin = true;
                System.out.println("L'utilisateur existe ");
                MenuBibliothecaire.menuBibliothecaire(pseudo);
            } else {
                System.out.println("L'utilisateur ou le mot de passe n'est pas correct.");
                sc.nextLine();
                System.exit(1);
            }

        }while(etatLogin==false);//menu login

    sc.close();

    }//main
}

/*
MENU 1 : LOGIN

MENU 2 (bibliothècaire) :
	1. abonne  / consulter, ajouter, modifier, supprimer
	2. types d'ouvrage / liste de type d'ouvrage, choisir une type
		- choisir une type / liste d'ouvrage par type
	3. ouvrages / listes d'ouvrage, choisir d'ouvrage
		-choisir d'ouvrage / ajouter, modifier, consulter, supprimer, emprunter(reserver à un abonné)

	4. rechercher / rechercher un abonné, rechercher un ouvrage (par nom ou auteur)
	
	5. information sur la bibliothèque
*/