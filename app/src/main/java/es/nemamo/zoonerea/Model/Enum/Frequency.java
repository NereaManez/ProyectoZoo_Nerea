package es.nemamo.zoonerea.Model.Enum;

public enum Frequency {
    DIARY("diary"), WEEKLY("weekly"), MONTHLY("monthly"), QUARTERLY("quarterly"), EXTRAORDINARY("extraordinary");

    private String frec;

    Frequency(String frec) {
        this.frec = frec;
    }

    public String getFrec() {
        return frec;
    }
}
