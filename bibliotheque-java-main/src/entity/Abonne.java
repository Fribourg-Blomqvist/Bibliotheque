package entity;



public class Abonne {



    private int idAbonne;
    private String adresse;
    private int codePostal;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private int nbInfractions;
    private int idUtilisateur;


    // code gÃ©nÃ©rÃ© automatiquement

    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public int getIdAbonne() {
        return idAbonne;
    }
    public void setIdAbonne(int idAbonne) {
        this.idAbonne = idAbonne;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public int getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNbInfractions() {
        return nbInfractions;
    }
    public void setNbInfractions(int nbInfractions) {
        this.nbInfractions = nbInfractions;
    }
    public Abonne() {
        super();
    }
    public int getIAbonne() {
        return idAbonne;
    }

    @Override
    public String toString() {
        return "Abonne [idAbonne=" + idAbonne + ", adresse=" + adresse + ", codePostal=" + codePostal + ", nom="
                + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email + ", nbInfractions="
                + nbInfractions + "]";
    }

}
