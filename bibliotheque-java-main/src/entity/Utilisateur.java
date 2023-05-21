package entity;

public class Utilisateur {
    private int idUtilisateur;
    private String pseudo;
    private String motPasse;
    private boolean bibliothecaire; //bibliothecaire

    public boolean isBibliothecaire() {
        return bibliothecaire;
    }

    public void setBibliothecaire(boolean bibliothecaire) {
        this.bibliothecaire = bibliothecaire;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", bibliothecaire=" + bibliothecaire +
                '}';
    }
}