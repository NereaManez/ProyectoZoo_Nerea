package es.nemamo.zoonerea.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DailyCare extends RealmObject {
    @PrimaryKey
    private int id;

    private String date;
    private String idCaretaker;
    private int idAnimal;
    private int idTask;
    private boolean done;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getIdCaretaker() {
        return idCaretaker;
    }
    public void setIdCaretaker(String idCaretaker) {
        this.idCaretaker = idCaretaker;
    }

    public int getIdAnimal() {
        return idAnimal;
    }
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdTask() {
        return idTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
