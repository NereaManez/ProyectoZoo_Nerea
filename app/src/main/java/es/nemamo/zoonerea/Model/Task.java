package es.nemamo.zoonerea.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey
    private int id;

    private int idTaskType;
    private String frequency;
    private int times;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdTaskType() {
        return idTaskType;
    }
    public void setIdTaskType(int idTaskType) {
        this.idTaskType = idTaskType;
    }

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getTimes() {
        return times;
    }
    public void setTimes(int times) {
        this.times = times;
    }
}
