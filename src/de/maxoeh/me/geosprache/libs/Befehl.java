package de.maxoeh.me.geosprache.libs;

/**
 *  Vom Abitur gestellt
 */
public class Befehl
{
    String typ;
    int wert;

    public Befehl(String typ, int wert)
    {
        this.typ = typ;
        this.wert = wert;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }
}