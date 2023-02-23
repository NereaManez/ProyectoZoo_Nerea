package es.nemamo.zoonerea.Model.Enum;

public enum HabitatType {
    MOUNTAIN("mountain"), FRESH_WATER("fresh water"), FOREST("forest"), DESERT("desert"), SEA("sea"),SAVANNAH("savannah"), JUNGLE("jungle"), RIVER("river");

    private String habitat;

    HabitatType(String habitat) {
        this.habitat = habitat;
    }

    public String getHabitat() {
        return habitat;
    }
}
