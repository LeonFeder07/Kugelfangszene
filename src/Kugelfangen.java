import GLOOP.*;
public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel [] kugel;
    private double speed;

    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        kamera.setzePosition(0, 500, 800);
        spielfeld = new Spielfeld(1000, 1000);
        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        tastatur = new GLTastatur();
        kugel = new Kugel [12];
        dieBox= new Box();
        speed= Math.random();
        for(int i = 0; i< kugel.length;i++){
            kugel[i] = new Kugel(40,spielfeld,dieBox, 0.001);
        }



        fuehreAus();
    }

    public void fuehreAus() {
        System.out.println("HI");

        while (!tastatur.esc()) {
            Sys.warte(10);
            for (int i = 0; i < kugel.length; i++) {
                kugel[i].rolle();


            }
        if(tastatur.links()){
            dieBox.bewegeLinks();
        }
            if(tastatur.rechts()){
                dieBox.bewegeRechts();
            }
            if(tastatur.oben()){
                dieBox.bewegeOben();
            }
            if(tastatur.unten()){
                dieBox.bewegeUnten();
            }

        }
    }
}

