package es.nemamo.zoonerea.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Habitat extends RealmObject {
    @PrimaryKey
    private int id;

    private String habitatType;
    private String description;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getHabitatType() {
        return habitatType;
    }
    public void setHabitatType(String habitatType) {
        this.habitatType = habitatType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
