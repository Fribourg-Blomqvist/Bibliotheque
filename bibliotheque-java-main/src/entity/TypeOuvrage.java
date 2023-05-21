package entity;

import java.util.ArrayList;
import java.util.List;

public class TypeOuvrage {
    private Integer idTypeOuvrage;
    private String type;

    public Integer getIdTypeOuvrage() {
        return idTypeOuvrage;
    }

    public void setIdTypeOuvrage(Integer idTypeOuvrage) {
        this.idTypeOuvrage = idTypeOuvrage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeOuvrage{" +
                "idTypeOuvrage=" + idTypeOuvrage +
                ", type='" + type + '\'' +
                '}';
    }
}