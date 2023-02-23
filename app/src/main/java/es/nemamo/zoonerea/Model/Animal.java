package es.nemamo.zoonerea.Model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Animal extends RealmObject {
    @PrimaryKey
    private int id;

    private String specie;
    private String family;
    private int frequency_al;
    private String name;
    private int idHabitat;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSpecie() {
        return specie;
    }
    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }

    public int getFrequency_al() {
        return frequency_al;
    }
    public void setFrequency_al(int frequency_al) {
        this.frequency_al = frequency_al;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIdHabitat() {
        return idHabitat;
    }
    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }
}
