package es.nemamo.zoonerea.Model.Enum;

public enum Family {
    MAMMAL("mammal"), BIRD("bird"), AMPHIBIAN("amphibial"), REPTILE("reptilie"), FISH("fish");

    private String family;

    Family(String family) {
        this.family = family;
    }

    public String getFamily() {
        return family;
    }
}