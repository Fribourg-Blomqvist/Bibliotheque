package menuBibliothecaire;

import dao.*;
import entity.Abonne;
import entity.Auteur;
import entity.Ouvrage;
import entity.TypeOuvrage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuBibliothecaire {
    static Scanner sc = new Scanner(System.in);

    public static void menuBibliothecaire(String pseudo){

        System.out.println("" +
                "             .--.           .---.        .-.\n" +
                "         .---|--|   .-.     | M |  .---. |~|    .--.\n" +
                "      .--|===|  |---|_|--.__| y |--|:::| |~|-==-|==|---.\n" +
                "      |%%|   |  |===| |~~|%%| S |--|   |_|~|JAVA|  |___|-.\n" +
                "      |  |   |  |===| |==|  | Q |  |:::|=| |    |  |---|=|\n" +
                "      |  |   |  |   |_|__|  | L |__|   | | |    |  |___| |\n" +
                "      |~~|===|--|===|~|~~|%%|~~~|--|:::|=|~|----|==|---|=|\n" +
                "      ^--^---'--^---^-^--^--^---'--^---^-^-^-==-^--^---^-'");
        System.out.println("               Bonjour, ["+pseudo+"]!");

        int choix;
        do{
            System.out.println("\n------------------------------------");
            System.out.println("     ### Menu principal ###");
            System.out.println("------------------------------------");
            System.out.println("1. Abonnés");
            System.out.println("2. Ouvrages");
            System.out.println("3. Type d'ouvrage");
            System.out.println("4. Auteur");
            System.out.println("5. Réserver");
            System.out.println("9. Information sur la bibliothèque");
            System.out.println("0. Sortir ");
            System.out.println("------------------------------------");

            System.out.print("CHOIX : ");
            choix = sc.nextInt();
            System.out.println("------------------------------------\n\n");

            switch(choix){
                case 1: //abonne
                    menuAbonne();
                    break;

                case 2: // Type d'ouvrage
                    menuOuvrages();
                    break;

                case 3: //Ouvrages
                    menuType();
                    break;

                case 4 :
                    menuAuteur();
                    break;

                case 5 :
                    menuReserver();
                    break;
                case 9:
                    System.out.println("La bibliothèque se situe à ........." +
                            "\nle numéro de téléphone  .........." +
                            "\nouvert à 24h / 24h" +
                            "\nLa régle d'emprunt est .......... ");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;
            }

        }while(choix != 0);

        //si choix == 0
        try {
            ConnexionBD.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("" +
                "     .oo o    o    .oPYo. .oPYo. o     o .oPYo. o  .oPYo. 88 \n" +
                "    .P 8 8    8    8   `8 8.     8     8 8    8 8  8   `8 88 \n" +
                "   .P  8 8    8   o8YooP' `boo   8     8 8    8 8 o8YooP' 88 \n" +
                "  oPooo8 8    8    8   `b .P     `b   d' 8    8 8  8   `b 88 \n" +
                " .P    8 8    8    8    8 8       `b d'  8    8 8  8    8 `' \n" +
                ".P     8 `YooP'    8    8 `YooP'   `8'   `YooP' 8  8    8 88 ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.exit(0);
    }//menuBibliothecaire()



    private static void menuAbonne() {
        AbonneDAO abonneDao = new AbonneDAO();
        int menuAbonne;
        do{
            System.out.println("" +
                    "      O      ~O\n" +
                    "     <|\\     /|\\\n" +
                    "      |   ~o/ | \\o    ~o/    _o\n" +
                    "      |\\  /|  |\\ |\\   /|      |\\\n" +
                    "     / |  / \\ |// >   / \\    / >");
            System.out.println("------------------------------------");
            System.out.println("Menu Abonné");
            System.out.println("------------------------------------");
            System.out.println("1. Consulter la liste des abonnés");
            System.out.println("2. Ajouter un abonné");
            System.out.println("3. Rechercher par son ID");
            System.out.println("4. Rechercher par son Nom");
            System.out.println("5. Modifier le nom d'un abonné");
            System.out.println("6. Supprimer un abonné");
            System.out.println("0. Aller au menu principal");
            System.out.println("------------------------------------");
            System.out.print("CHOIX : ");
            menuAbonne = sc.nextInt();

            switch(menuAbonne){
                case 1:
                    System.out.println("------------------------------------");
                    System.out.println("1. La liste des abonnés ");
                    System.out.println("------------------------------------");
                    List<Abonne> allAbonne = abonneDao.findAll();
                    allAbonne.forEach(System.out::println);
                    System.out.println("------------------------------------");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 2:
                    System.out.println("------------------------------------");
                    System.out.println("2. Ajouter un abonné");
                    System.out.println("EN CONSTRUCTION");
                    System.out.println("------------------------------------");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 3 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("Rechercher un abonné par ID");
                    System.out.println("------------------------------------");
                    System.out.print("Entrer id : ");
                    int idAbonneRechercher = sc.nextInt();sc.nextLine();
                    Abonne abonneTrouve = abonneDao.findById(idAbonneRechercher);
                    System.out.println(abonneTrouve);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();

                    break;

                case 4 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("Rechercher un abonné par son Nom");
                    System.out.println("------------------------------------");
                    System.out.print("Enter son nom : ");
                    String nomAbonne = sc.nextLine();
                    List<Abonne> listAbonneTrouve = abonneDao.findByName(nomAbonne);
                    listAbonneTrouve.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("5. Modifier le nom d'un abonné");
                    System.out.println("------------------------------------");

                    System.out.print("Le ID de l'abonné à modifier : ");
                    int idModifier = sc.nextInt();sc.nextLine();

                    System.out.println(abonneDao.findById(idModifier));
                    System.out.println("Vous voulez modifier son nom?  1: oui / 0. non");
                    int yesOrNo = sc.nextInt();sc.nextLine();
                    if(yesOrNo == 1){
                        System.out.print("Son nouveau nom : ");
                        String nouveauNom = sc.nextLine();
                        abonneDao.updateNomAbonne(idModifier, nouveauNom);
                        System.out.println("modifié comme : "+abonneDao.findById(idModifier));
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;


                case 6:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("6. Supprimer un abonné");
                    System.out.println("------------------------------------");

                    System.out.print("Le ID de type d'ouvrage à rechercher : ");
                    int idSupprimer = sc.nextInt();sc.nextLine();

                    System.out.println(abonneDao.findById(idSupprimer));
                    System.out.println("Vous voulez vraiment supprimer?  1: oui / 0. non");
                    int yesOrNo2 = sc.nextInt();sc.nextLine();
                    if(yesOrNo2 == 1){
                        abonneDao.deleteAbonne(idSupprimer);
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

            }

        }while(menuAbonne!=0);


    }



    private static void menuOuvrages() {
        OuvrageDAO ouvrageDAO = new OuvrageDAO();
        int choixMenuOuvrages;
        do{
            System.out.println("" +
                    "      ______ ______\n" +
                    "    _/      Y      \\_\n" +
                    "   // ~~ ~~ | ~~ ~  \\\\\n" +
                    "  // ~ ~ ~~ | ~~~ ~~ \\\\      \n" +
                    " //________.|.________\\\\     \n" +
                    "`----------`-'----------'");
            System.out.println("------------------------------------");
            System.out.println("Menu Ouvrage");
            System.out.println("------------------------------------");
            System.out.println("1. Consulter la liste des ouvrages");
            System.out.println("2. Ajouter un ouvrage (érreur à régler) ");
            System.out.println("3. Rechercher par ID");
            System.out.println("4. Rechercher par titre");
            System.out.println("5. Modifier le titre d'un ouvrage");
            System.out.println("6. Supprimer (érreur à régler)");
            System.out.println("0. Aller au menu principal");
            System.out.println("------------------------------------");
            System.out.print("CHOIX : ");
            choixMenuOuvrages = sc.nextInt();

            switch(choixMenuOuvrages){
                case 1:
                    System.out.println("------------------------------------");
                    System.out.println("1. Consulter la liste des ouvrages");
                    System.out.println("------------------------------------");
                    List all = ouvrageDAO.findAll();
                    all.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("2. Ajouter un ouvrage");
                    System.out.print("Le titre d'ouvrage à créer : ");
                    String titreOuvrage = sc.nextLine();
                    System.out.print("Sa durée de dates autorisées maximum : ");
                    int dureeMax = sc.nextInt(); sc.nextLine();
                    TypeOuvrageDAO typeOuvrageDAO = new TypeOuvrageDAO();
                    List all1 = typeOuvrageDAO.findAll();
                    all1.forEach(System.out::println);
                    System.out.print("Son type d'ouvrage (ID) : ");
                    int typeOuvrageCreate = sc.nextInt();sc.nextLine();

                    List<Auteur> listAuteur = new ArrayList<>();
                    AuteurDAO auteurDAO = new AuteurDAO();
                    List all2 = auteurDAO.findAll();
                    all2.forEach(System.out::println);
                    System.out.println("Enter un ou des ID des auteurs. Pour finir, tapez '0'.");
                    int idAuteur = -1;
                    do{
                        System.out.print("id d'auteur : ");
                        idAuteur = sc.nextInt();sc.nextLine();
                        listAuteur.add(auteurDAO.findById(idAuteur));
                    }while(idAuteur != 0);
                    System.out.println("Vous avez ajouté : ");
                    listAuteur.forEach(System.out::println);

                    Ouvrage ouvrage = new Ouvrage();
                    ouvrage.setTypeOuvrage(typeOuvrageDAO.findById(typeOuvrageCreate));
                    ouvrage.setTitre(titreOuvrage);
                    ouvrage.setDureeAutorisationMax(dureeMax);
                    ouvrage.setAuteurs(listAuteur);

                    Integer idOuvrageCree = ouvrageDAO.createOuvrage(ouvrage);


                    System.out.println("------------------------------------");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 3 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("3. Rechercher par ID");
                    System.out.println("------------------------------------");
                    System.out.print("ID d'ouvrage à rechercher : ");
                    int idOuvrage = sc.nextInt();
                    sc.nextLine();
                    System.out.println(ouvrageDAO.findById(idOuvrage));
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 4 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("4. Rechercher un ouvrage par le titre");
                    System.out.println("------------------------------------");
                    System.out.println("En construction");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("5. Modifier le titre d'un ouvrage");
                    System.out.println("------------------------------------");
                    System.out.print("Le ID d'ouvrage à modifier : ");
                    int idModifier = sc.nextInt();sc.nextLine();

                    System.out.println(ouvrageDAO.findById(idModifier));
                    System.out.println("Vous voulez vraiment modifier son titre?  1: oui / 0. non");
                    int yesOrNo = sc.nextInt();sc.nextLine();
                    if(yesOrNo == 1){
                        System.out.print("Son nouveau titre : ");
                        String nouveauTitre = sc.nextLine();
                        ouvrageDAO.updateTitre(idModifier, nouveauTitre);
                        System.out.println("modifié comme : "+ouvrageDAO.findById(idModifier));
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("6. Supprimer un ouvrage");
                    System.out.println("------------------------------------");
                    System.out.print("Le ID de type d'ouvrage à rechercher : ");
                    int idOuvrageSupprimer = sc.nextInt();sc.nextLine();

                    System.out.println(ouvrageDAO.findById(idOuvrageSupprimer));
                    System.out.println("Vous voulez vraiment supprimer?  1: oui / 0. non");
                    int yesOrNo2 = sc.nextInt();sc.nextLine();
                    if(yesOrNo2 == 1){
                        ouvrageDAO.delete(idOuvrageSupprimer);
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;


            }

        }while(choixMenuOuvrages !=0);

    }



    private static void menuType() {
        TypeOuvrageDAO typeOuvrageDAO = new TypeOuvrageDAO();
        int menuType;
        do{
            System.out.println("" +
                    " .-. | | |\n" +
                    " |R|_| | |\n" +
                    " |O|a| |B|<\\\n" +
                    " |M|r| |D| \\\\\n" +
                    " |A|t| | |  \\\\      \n" +
                    " |N|!| | |   \\>     \n" +
                    "\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"");
            System.out.println("------------------------------------");
            System.out.println("Menu Type d'ouvrage");
            System.out.println("------------------------------------");
            System.out.println("1. Consulter la liste des types d'ouvrage");
            System.out.println("2. Ajouter un type d'ouvrage");
            System.out.println("3. Rechercher par ID");
            System.out.println("4. Rechercher par nom");
            System.out.println("5. Modifier le nom");
            System.out.println("6. Supprimer un type d'ouvrage");
            System.out.println("0. Aller au menu principal");
            System.out.println("------------------------------------");
            System.out.print("CHOIX : ");
            menuType = sc.nextInt();

            switch(menuType){
                case 1:
                    System.out.println("------------------------------------");
                    System.out.println("1. Consulter la liste des types d'ouvrage");
                    System.out.println("------------------------------------");
                    List all = typeOuvrageDAO.findAll();
                    all.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("2. Ajouter un type d'ouvrage");
                    System.out.print("nom de type d'ouvrage à créer : ");
                    String nom = sc.nextLine();
                    typeOuvrageDAO.createType(nom);
                    System.out.println("------------------------------------");
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 3 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("3. Rechercher par ID");
                    System.out.println("------------------------------------");
                    System.out.print("id de type d'ouvrage à rechercher : ");
                    int idTypeOuvrage = sc.nextInt();
                    sc.nextLine();
                    System.out.println(typeOuvrageDAO.findById(idTypeOuvrage));
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 4 :
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("4. Rechercher un type d'ouvrage par le nom");
                    System.out.println("------------------------------------");
                    System.out.print("Le nom de type d'ouvrage à rechercher : ");
                    String nomTypeOuvrage = sc.nextLine();
                    List<TypeOuvrage> byName = typeOuvrageDAO.findByName(nomTypeOuvrage);
                    byName.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("5. Modifier le nom d'un type d'ouvrage");
                    System.out.println("------------------------------------");
                    System.out.print("Le ID de type d'ouvrage à modifier : ");
                    int idModifier = sc.nextInt();sc.nextLine();

                    System.out.println(typeOuvrageDAO.findById(idModifier));
                    System.out.println("Vous voulez modifier son nom?  1: oui / 0. non");
                    int yesOrNo = sc.nextInt();sc.nextLine();
                    if(yesOrNo == 1){
                        System.out.print("Son nouveau nom : ");
                        String nouveauNom = sc.nextLine();
                        typeOuvrageDAO.updateTypeNom(idModifier, nouveauNom);
                        System.out.println("modifié comme : "+typeOuvrageDAO.findById(idModifier));
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("6. Supprimer un type d'ouvrage");
                    System.out.println("------------------------------------");
                    System.out.print("Le ID de type d'ouvrage à rechercher : ");
                    int idSupprimer = sc.nextInt();sc.nextLine();

                    System.out.println(typeOuvrageDAO.findById(idSupprimer));
                    System.out.println("Vous voulez vraiment supprimer?  1: oui / 0. non");
                    int yesOrNo2 = sc.nextInt();sc.nextLine();
                    if(yesOrNo2 == 1){
                        typeOuvrageDAO.deleteType(idSupprimer);
                    }

                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();
                    break;

            }

        }while(menuType!=0);

    }


    private static void menuAuteur() {
        AuteurDAO auteurDAO = new AuteurDAO();
        int menuAuteur;

        do {
            System.out.println("(\\ \n" +
                    "\\'\\ \n" +
                    " \\'\\     __________  \n" +
                    " / '|   ()_________)\n" +
                    " \\ '/    \\ ~~~~~~~~ \\\n" +
                    "   \\       \\ ~~~~~~   \\\n" +
                    "   ==).      \\__________\\\n" +
                    "  (__)       ()__________)");
            System.out.println("------------------------------------");
            System.out.println("Auteur");
            System.out.println("------------------------------------");
            System.out.println("1. Consulter la liste des auteurs");
            System.out.println("2. Ajouter un Auteur");
            System.out.println("3. Rechercher par ID");
            System.out.println("4. Rechercher par nom");
            System.out.println("5. Modifier le nom");
            System.out.println("6. Supprimer un Auteur");
            //System.out.println("7. Consulter la liste des ouvrages d'un auteur");
            System.out.println("0. Aller au menu principal");
            System.out.println("------------------------------------");
            System.out.print("CHOIX : ");
            menuAuteur = sc.nextInt();

            switch (menuAuteur) {
                case 1:
                    System.out.println("------------------------------------");
                    System.out.println("1. Consulter la liste des auteurs");
                    System.out.println("------------------------------------");
                    List all = auteurDAO.findAll();
                    all.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 2:
                    System.out.println("------------------------------------");
                    System.out.println("2. Ajouter un Auteur");
                    System.out.println("------------------------------------");
                    System.out.print("Entrer le nom d'auteur : ");
                    String nomAuteur = sc.nextLine();
                    auteurDAO.createAuteur(nomAuteur);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 3:
                    System.out.println("------------------------------------");
                    System.out.println("3. Rechercher un auteur par ID");
                    System.out.println("------------------------------------");
                    System.out.print("Entrer ID : ");
                    int autuerId = sc.nextInt(); sc.nextLine();
                    Auteur auteurById = auteurDAO.findById(autuerId);
                    System.out.println(auteurById);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 4:
                    System.out.println("------------------------------------");
                    System.out.println("4. Rechercher un ou des auteurs par nom");
                    System.out.println("------------------------------------");
                    String auteurNom = sc.nextLine();
                    List<Auteur> auteursFoundByName = auteurDAO.findByName(auteurNom);
                    auteursFoundByName.forEach(System.out::println);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 5:
                    System.out.println("------------------------------------");
                    System.out.println("5. Modifier le nom d'un auteur");
                    System.out.println("------------------------------------");
                    System.out.print("Entrer ID : ");
                    int idAutuerModifier = sc.nextInt();sc.nextLine();
                    System.out.println(auteurDAO.findById(idAutuerModifier));
                    System.out.println("Voulez-vouz modifier son nom? (1. oui / 0. non)");
                    int ouiounon = sc.nextInt();sc.nextLine();
                    if(ouiounon != 1) break;
                    System.out.print("Entrer nouveau nom à modifier : ");
                    String nomAuteurModifier = sc.nextLine();
                    auteurDAO.updateNomAuteur(idAutuerModifier, nomAuteurModifier);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

                case 6:
                    System.out.println("------------------------------------");
                    System.out.println("6. Supprimer un Auteur");
                    System.out.println("------------------------------------");
                    System.out.print("Entrer ID : ");
                    int idAutuerSupprimer = sc.nextInt();sc.nextLine();
                    System.out.println(auteurDAO.findById(idAutuerSupprimer));
                    System.out.println("Voulez-vouz modifier son nom? (1. oui / 0. non)");
                    int ouiounonSupprimer = sc.nextInt();sc.nextLine();
                    if(idAutuerSupprimer != 1) break;
                    auteurDAO.deleteAuteur(idAutuerSupprimer);
                    System.out.println("\nAppuyez sur la touche Entrée");
                    sc.nextLine();sc.nextLine();
                    break;

            }

        } while (menuAuteur != 0);
    }




    private static void menuReserver() {
        System.out.println("------------------------------------");
        System.out.println("Réserver");
        System.out.println("------------------------------------");
        System.out.println("En construction");
        System.out.println("\nAppuyez sur la touche Entrée");
        sc.nextLine();sc.nextLine();
    }


}
