package entity;

public class Exemplaire {
    private Integer idExemplaire;
    private boolean disponibilitee;
    private Ouvrage ouvrage;

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public boolean isDisponibilitee() {
        return disponibilitee;
    }

    public void setDisponibilitee(boolean disponibilitee) {
        this.disponibilitee = disponibilitee;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "idExemplaire=" + idExemplaire +
                ", disponibilitee=" + disponibilitee +
                ", ouvrage=" + ouvrage +
                '}';
    }
}
