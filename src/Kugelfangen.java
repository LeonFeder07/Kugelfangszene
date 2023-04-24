import GLOOP.*;
public class Kugelfangen {
    private GLKamera kamera;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;

    private Box dieBox;
    private Kugel kugel1, kugel2, kugel3;

    public Kugelfangen() {
        kamera = new GLKamera();
        kamera.setzePosition(0, 500, 800);

        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        tastatur = new GLTastatur();

        Spielfeld spielfeld = new Spielfeld(1000, 1000);

        fuehreAus();
    }

    public void fuehreAus() {

    }
}

