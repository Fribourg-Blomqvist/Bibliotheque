package entity;

import java.util.ArrayList;
import java.util.List;

public class Ouvrage {
    private Integer idOuvrage;
    private String titre;
    private Integer dureeAutorisationMax;
    private Integer nbExemplaires;
    private TypeOuvrage typeOuvrage;  // cle etrangere
    private List<Auteur> auteurs;

    public Ouvrage() {
        auteurs = new ArrayList<>();
        this.nbExemplaires = 0;
    }

    public Ouvrage( String titre, Integer dureeAutorisationMax, TypeOuvrage typeOuvrage, List<Auteur> auteurs) {
        this.titre = titre;
        this.dureeAutorisationMax = dureeAutorisationMax;
        this.nbExemplaires = 0;
        this.typeOuvrage = typeOuvrage;
        this.auteurs = auteurs;
    }

    public Integer getIdOuvrage() {
        return idOuvrage;
    }

    public void setIdOuvrage(Integer idOuvrage) {
        this.idOuvrage = idOuvrage;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getDureeAutorisationMax() {
        return dureeAutorisationMax;
    }

    public void setDureeAutorisationMax(Integer dureeAutorisationMax) {
        this.dureeAutorisationMax = dureeAutorisationMax;
    }

    public Integer getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(Integer nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }

    public TypeOuvrage getTypeOuvrage() {
        return typeOuvrage;
    }

    public void setTypeOuvrage(TypeOuvrage typeOuvrage) {
        this.typeOuvrage = typeOuvrage;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    @Override
    public String toString() {
        return "Ouvrage{" +
                "idOuvrage=" + idOuvrage +
                ", titre='" + titre + '\'' +
                ", dureeAutorisationMax=" + dureeAutorisationMax +
                ", nbExemplaires=" + nbExemplaires +
                ", typeOuvrage=" + typeOuvrage +
                ", auteurs=" + auteurs +
                '}';
    }
}