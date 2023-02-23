package es.nemamo.zoonerea.Model;

import android.content.Context;

import java.time.LocalDate;

import es.nemamo.zoonerea.Model.Enum.Family;
import es.nemamo.zoonerea.Model.Enum.Frequency;
import es.nemamo.zoonerea.Model.Enum.HabitatType;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Database {
    private static Database instance = new Database();
    public static Database getInstance() {
        return instance;
    }

    private Realm realm;
    private SGBD sgbd;
    public Realm conect(Context context) {
        if (realm == null) {
            Realm.init(context);
            String name = "zoo.db";
            RealmConfiguration configuration = new RealmConfiguration.Builder().name(name).allowWritesOnUiThread(true).build();
            realm = Realm.getInstance(configuration);

            if (realm.isEmpty()) {
                sgbd = new SGBD(realm);
                sgbd.addCaretaker("11111111A", "admin", "admin", "admin", "admin", true);
                sgbd.addCaretaker("59410713B", "Nerea", "Mañez", "nerea", "1234", false);
                sgbd.addCaretaker("35584857Q", "Alba", "Serrat", "alba", "4321", false);
                sgbd.addCaretaker("99054947L", "Pablo", "Hernandez", "pablo", "1324", false);

                sgbd.addHabitat(0, HabitatType.SAVANNAH.getHabitat(), "-");
                sgbd.addHabitat(1, HabitatType.JUNGLE.getHabitat(), "-");
                sgbd.addHabitat(2, HabitatType.FOREST.getHabitat(), "birdcage");
                sgbd.addHabitat(3, HabitatType.DESERT.getHabitat(), "birdcage");
                sgbd.addHabitat(4, HabitatType.FOREST.getHabitat(), "birdcage");
                sgbd.addHabitat(5, HabitatType.FRESH_WATER.getHabitat(), "aquarium for amphibians");
                sgbd.addHabitat(6, HabitatType.FOREST.getHabitat(), "small mountain forest for amphibians");
                sgbd.addHabitat(7, HabitatType.RIVER.getHabitat(), "adapted to alligators");
                sgbd.addHabitat(8, HabitatType.FOREST.getHabitat(), "-");
                sgbd.addHabitat(9, HabitatType.SEA.getHabitat(), "aquarium with a beach");
                sgbd.addHabitat(10, HabitatType.SEA.getHabitat(), "aquarium");
                sgbd.addHabitat(11, HabitatType.FRESH_WATER.getHabitat(), "aquarium");
                sgbd.addHabitat(12, HabitatType.MOUNTAIN.getHabitat(), "delete");

                sgbd.addAnimal("Jirafa", Family.MAMMAL.getFamily(), 3, "Nasha", 0);
                sgbd.addAnimal("Jirafa", Family.MAMMAL.getFamily(), 3, "Joy", 0);
                sgbd.addAnimal("Cebra", Family.MAMMAL.getFamily(), 3, "Duna", 0);
                sgbd.addAnimal("Cebra", Family.MAMMAL.getFamily(), 3, "Also", 0);
                sgbd.addAnimal("Lémur", Family.MAMMAL.getFamily(), 2, "Fifi", 1);
                sgbd.addAnimal("Lémur", Family.MAMMAL.getFamily(), 2, "Lala", 1);
                sgbd.addAnimal("Elefante", Family.MAMMAL.getFamily(), 4, "Bibi", 1);
                sgbd.addAnimal("Elefante", Family.MAMMAL.getFamily(), 4, "Lewis", 1);
                sgbd.addAnimal("Guacamayo", Family.BIRD.getFamily(), 1, "Iris", 2);
                sgbd.addAnimal("Guacamayo", Family.BIRD.getFamily(), 1, "Black", 2);
                sgbd.addAnimal("Aguila", Family.BIRD.getFamily(), 3, "Zarpi", 3);
                sgbd.addAnimal("Aguila", Family.BIRD.getFamily(), 3, "Xena", 3);
                sgbd.addAnimal("Buho", Family.BIRD.getFamily(), 2, "Tellaa", 4);
                sgbd.addAnimal("Buho", Family.BIRD.getFamily(), 2, "Oby", 4);
                sgbd.addAnimal("Tucan", Family.BIRD.getFamily(), 3, "Miru", 2);
                sgbd.addAnimal("Tucan", Family.BIRD.getFamily(), 3, "Harsy", 2);
                sgbd.addAnimal("Tritón", Family.AMPHIBIAN.getFamily(), 4, "Perjax", 5);
                sgbd.addAnimal("Tritón", Family.AMPHIBIAN.getFamily(), 4, "Lyka", 5);
                sgbd.addAnimal("Ajolote", Family.AMPHIBIAN.getFamily(), 5, "Zarpi", 5);
                sgbd.addAnimal("Ajolote", Family.AMPHIBIAN.getFamily(), 5, "Attor", 5);
                sgbd.addAnimal("Rana arlequín", Family.AMPHIBIAN.getFamily(), 2, "Byla", 6);
                sgbd.addAnimal("Rana arlequín", Family.AMPHIBIAN.getFamily(), 2, "Vadu", 6);
                sgbd.addAnimal("Aligátor de China", Family.REPTILE.getFamily(), 3, "Cafus", 7);
                sgbd.addAnimal("Aligátor de China", Family.REPTILE.getFamily(), 3, "Detella", 7);
                sgbd.addAnimal("Dragón de Komodo", Family.REPTILE.getFamily(), 1, "Atis", 8);
                sgbd.addAnimal("Dragón de Komodo", Family.REPTILE.getFamily(), 1, "Shiowe", 8);
                sgbd.addAnimal("Iguana rinoceronte", Family.REPTILE.getFamily(), 2, "Dorgo", 8);
                sgbd.addAnimal("Iguana rinoceronte", Family.REPTILE.getFamily(), 2, "Eclia", 8);
                sgbd.addAnimal("Tortuga gigante de las Galápagos", Family.REPTILE.getFamily(), 2, "Cloen", 9);
                sgbd.addAnimal("Tortuga gigante de las Galápagos", Family.REPTILE.getFamily(), 2, "Slovo", 9);
                sgbd.addAnimal("Caballito de Mar Mediterráneo", Family.FISH.getFamily(), 1, "Mislo", 9);
                sgbd.addAnimal("Caballito de Mar Mediterráneo", Family.FISH.getFamily(), 1, "Oru", 9);
                sgbd.addAnimal("Raya de manchas azules", Family.FISH.getFamily(), 1, "Sesbar", 10);
                sgbd.addAnimal("Raya de manchas azules", Family.FISH.getFamily(), 1, "Grajia", 10);
                sgbd.addAnimal("Fish ángel", Family.FISH.getFamily(), 1, "Faook", 11);
                sgbd.addAnimal("Fish ángel", Family.FISH.getFamily(), 1, "Nuri", 11);

                sgbd.addTaskType(0,"Veterinario", "visita veterinaria");
                sgbd.addTaskType(1, "Alimentación", "alimentación");
                sgbd.addTaskType(2, "Desparasitación", "desparasitación");
                sgbd.addTaskType(3, "Exhibición", "exhibición de vuelo");
                sgbd.addTaskType(4, "Entrenamiento", "entrenamiento de vuelo");

                sgbd.addTask(0, 0, Frequency.MONTHLY.getFrec(), 1);
                sgbd.addTask(1, 0, Frequency.EXTRAORDINARY.getFrec(), 1);
                sgbd.addTask(2, 1, Frequency.DIARY.getFrec(), 1);
                sgbd.addTask(3, 2, Frequency.QUARTERLY.getFrec(), 1);
                sgbd.addTask(4, 3, Frequency.WEEKLY.getFrec(), 2);
                sgbd.addTask(5, 4, Frequency.DIARY.getFrec(), 1);

                sgbd.addDailyCare(LocalDate.now().toString(), "99054947L", "Black", 0);
                sgbd.addDailyCare(LocalDate.now().toString(), "99054947L", "Duna", 0);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Fifi", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Lala", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Tellaa", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Oby", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "35584857Q", "Faook", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "35584857Q", "Nuri", 2);
                sgbd.addDailyCare(LocalDate.now().toString(), "99054947L", "Nasha", 3);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Tellaa", 4);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Oby", 4);
                sgbd.addDailyCare(LocalDate.now().toString(), "59410713B", "Zarpi", 5);
                sgbd.addDailyCare(LocalDate.now().toString(), "35584857Q", "Xena", 5);
                sgbd.addDailyCare(LocalDate.now().toString(), "35584857Q", "Iris", 5);
                sgbd.addDailyCare(LocalDate.now().toString(), "35584857Q", "Black", 5);
            }
        }

        return realm;
    }
}
