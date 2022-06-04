package co.com.poli.projectcloud.projectcloud.persistence.entity.enums;

public enum PriorityRange {

    Alto(5),
    Intermedio(4),
    Medio(3),
    Regular(2),
    Bajo(1);

    private int numVal;

    PriorityRange(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}