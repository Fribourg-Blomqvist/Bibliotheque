package entity;

public class Auteur {
    private int idAuteur;
    private String nom;


    public Auteur() {
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "idAuteur=" + idAuteur +
                ", nom='" + nom + '\'' +
                '}';
    }
}
