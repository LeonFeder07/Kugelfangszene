import GLOOP.*;
public class Box {
    private GLQuader quader;
    private Spielfeld feld;
    private double vX, vZ;
    private double breite, tiefe;

    public Box() {
        quader = new GLQuader(0, 75, -200, 80, 150, 80);
        quader.setzeFarbe(0, 0, 0);
    }

    public void bewegeLinks() {
        if (quader.gibX() > -455) {
            quader.verschiebe(-2, 0, 0);
        }
    }
    public void bewegeRechts() {
        if (quader.gibX()<455) {
        quader.verschiebe(2,0,0);
    }}
    public void bewegeUnten() {
        if (quader.gibZ()<455) {
        quader.verschiebe(0,0,2);
    }}
    public void bewegeOben() {
        if (quader.gibZ() > -455) {
        quader.verschiebe(0,0,-2);
    }}
    public double gibX() {
        return  quader.gibX();
    }
    public double gibZ() {
        return quader.gibZ();
    }
}
