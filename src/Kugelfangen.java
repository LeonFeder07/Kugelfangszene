import GLOOP.*;
public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLTafel tafel, tafel2;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel [] kugel;
    private double speed,a,f;
    private int punkte= 0;

    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        tafel = new GLTafel(-350,250,0,250,150);
        tafel.setzeAutodrehung(true);
        tafel.setzeKamerafixierung(true);
        tafel.setzeBeleuchtung(true);
        tafel.setzeSelbstleuchten(1,1,1);
        tafel.setzeSichtbarkeit(false);
        tafel.setzeTextur("src/img/img_1.png");
        tafel2 = new GLTafel(-350,250,0,250,150);
        tafel2.setzeAutodrehung(true);
        tafel2.setzeKamerafixierung(true);
        tafel2.setzeBeleuchtung(true);
        tafel2.setzeSelbstleuchten(1,1,1);
        tafel2.setzeSichtbarkeit(false);
        tafel2.setzeTextur("src/img/img_1.png");
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
       for(int j = 0; j<5; j++) {
           for (int i = 0; i < kugel.length; i++) {
               kugel[i].startSpawn();
               kugel[i].setzeSichtbarkeit(false);
           }
       }
        dieBox.spawn();
        for(int j = 0; j<5; j++) {
        for (int i = 0; i < kugel.length; i++) {
            kugel[i].startSpawn();
            kugel[i].setzeSichtbarkeit(true);
        }}
        tafel.setzeText("Punktzahl:",30);
        tafel.setzeSichtbarkeit(true);
        while (!tastatur.esc()) {
            Sys.warte(10);
            for (int i = 0; i < kugel.length; i++) {
               kugel[i].istKugelGetroffen();

               if(kugel[i].istGetroffen()){
                   kugel[i].respawn(i*40);
                   punkte= punkte +1;
                   tafel.setzeText("Punktzahl:"+punkte+ " :)",30);
               }
               if(punkte==5){
                  kamera.setzeBlickpunkt(0,300,30000);
                  tafel2.setzeSichtbarkeit(true);
                  tafel.setzeSichtbarkeit(false);
                  tafel2.setzeText("Glueckwunsch hast du toll gemacht! Restart? Druecke Space!",30);
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

