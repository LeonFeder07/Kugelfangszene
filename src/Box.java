import GLOOP.*;

public class Box {
    private GLQuader quader;

    private Spielfeld feld;
    private double vX, vZ;
    private double breite, punkte;

    public Box() {
        quader = new GLQuader(0, 300, 0, 80, 150, 80);
        //quader.setzeFarbe(0, 0, 0);

        quader.setzeTextur("src/img/box2.png");
        quader.setzeMaterial(GLMaterial.GLAS);

    }

    public void spawn() {

        quader.setzePosition(0, 300, 0);
        for (int i = 0; i < 200; i++) {
            quader.setzeMaterial(GLMaterial.GLAS);
            quader.setzeSichtbarkeit(true);
            quader.verschiebe(0, -1, 0);
            Sys.warte(15);
        }

    }

    public void reset() {
        quader.setzePosition(0, 300, 0);
        this.spawn();
    }

    public void bewegeLinks() {
        if (quader.gibX() > -455) {
            quader.verschiebe(-5, 0, 0);
        }
    }

    public void bewegeRechts() {
        if (quader.gibX() < 455) {
            quader.verschiebe(5, 0, 0);
        }
    }

    public void bewegeUnten() {
        if (quader.gibZ() < 455) {
            quader.verschiebe(0, 0, 5);
        }
    }

    public void bewegeOben() {

        if (quader.gibZ() > -455) {
            quader.verschiebe(0, 0, -5);
        }
    }

    public void setzeMaterial(float[][] pM) {
        quader.setzeMaterial(pM);
    }

    public double gibX() {
        return quader.gibX();
    }

    public double gibZ() {
        return quader.gibZ();
    }

}
