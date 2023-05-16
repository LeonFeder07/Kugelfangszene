import GLOOP.*;
public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel [] kugel;
    private double speed,a,f;

    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        kamera.setzePosition(0, 500, 800);
        spielfeld = new Spielfeld(1000, 1000);
        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        tastatur = new GLTastatur();
        kugel = new Kugel [5];
        dieBox= new Box();
        speed= Math.random();
        for(int i = 0; i< kugel.length;i++){
            kugel[i] = new Kugel(40,spielfeld,dieBox, 5,i);
        }
        for(int i = 0; i< kugel.length;i++){
            kugel[i].uebergebeKugeln(kugel);
        }
        if(Math.random()<0.5){
            a=1;
            f=1;
        }else{
            a=-1;
            f=-1;
        }




        fuehreAus();
    }

    public void fuehreAus() {

        System.out.println("HI");
        for (int i = 0; i < kugel.length; i++) {
          kugel[i].startSpawn();
          kugel[i].setzeSichtbarkeit(false);
        }
        dieBox.spawn();
        for (int i = 0; i < kugel.length; i++) {
            kugel[i].startSpawn();
            kugel[i].setzeSichtbarkeit(true);
        }
        while (!tastatur.esc()) {
            Sys.warte(10);
            for (int i = 0; i < kugel.length; i++) {
               kugel[i].istKugelGetroffen();
               if(kugel[i].istGetroffen()){
                   kugel[i].respawn(i*40);
               }
                    kugel[i].bewegeHorizontal();
                    kugel[i].bewegeVertikal();


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

