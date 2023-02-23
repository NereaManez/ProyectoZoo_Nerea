package es.nemamo.zoonerea.Model;

import io.realm.Realm;
import io.realm.RealmResults;

public class SGBD {
    private Realm realm;

    public SGBD(Realm realm) {
        this.realm = realm;
    }

    //INSERT
    public Caretaker addCaretaker(String DNI, String name, String surname, String user, String passwd, boolean admin) {
        Caretaker c = new Caretaker();
        c.setDNI(DNI);
        c.setName(name);
        c.setSurname(surname);
        c.setUser(user);
        c.setPasswd(passwd);
        c.setAdmin(admin);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(c);
        realm.commitTransaction();

        return c;
    }
    public Animal addAnimal(String specie, String family, int frequecy_al, String name, int idHabitat) {
        Animal a = new Animal();

        RealmResults<Animal> rr = realm.where(Animal.class).findAll();
        a.setId(rr.size());

        a.setSpecie(specie);
        a.setFamily(family);
        a.setFrequency_al(frequecy_al);
        a.setName(name);
        a.setIdHabitat(idHabitat);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(a);
        realm.commitTransaction();

        return a;
    }
    public Habitat addHabitat(int id, String habitatType, String desc) {
        Habitat h = new Habitat();

        h.setId(id);
        h.setHabitatType(habitatType);
        h.setDescription(desc);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(h);
        realm.commitTransaction();

        return h;
    }
    public TaskType addTaskType(int id, String title, String desc) {
        TaskType tt = new TaskType();

        tt.setId(id);
        tt.setTitle(title);
        tt.setDescription(desc);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(tt);
        realm.commitTransaction();

        return tt;
    }
    public Task addTask(int id, int idTaskType, String frequency, int times) {
        Task t = new Task();

        t.setId(id);
        t.setIdTaskType(idTaskType);
        t.setFrequency(frequency);
        t.setTimes(times);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(t);
        realm.commitTransaction();

        return t;
    }
    public DailyCare addDailyCare(String date, String idCaretaker, String nameAnimal, int idTask) {
        Animal a = realm.where(Animal.class).equalTo("name", nameAnimal).findFirst();
        DailyCare dc = new DailyCare();

        RealmResults<DailyCare> rr = realm.where(DailyCare.class).findAll();
        dc.setId(rr.size());

        dc.setDate(date);
        dc.setIdCaretaker(idCaretaker);
        dc.setIdAnimal(a.getId());
        dc.setIdTask(idTask);
        dc.setDone(false);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(dc);
        realm.commitTransaction();

        return dc;
    }
}
