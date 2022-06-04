package co.com.poli.projectcloud.projectcloud.persistence.entity.enums;

public enum HoursRange {

    OCHO(8),
    SIETE(7),
    SEIS(6),
    CINCO(5),
    CUATRO(4),
    TRES(3),
    DOS(2),
    UNO(1);

    private int numVal;

    HoursRange(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
