package es.nemamo.zoonerea.RecyclerViewAnimal;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Habitat;

public class RecyclerViewAnimal {
    private Animal animal;
    private Habitat habitat;

    public RecyclerViewAnimal(Animal animal, Habitat habitat) {
        this.animal = animal;
        this.habitat = habitat;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}
