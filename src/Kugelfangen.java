import GLOOP.*;
public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLTafel tafel, tafel2,tafel3, tafeltime;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel [] kugel, kugeltot;
    private double speed,a,f;
    private int punkte= 0;
    private int timer =0;
    private long timerSzeit=0,timerLzeit=0;
    private int timerLzeitRound=0;
    private boolean spielende=false;
        private boolean    spielende2=false;

    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        tafel = new GLTafel(-350,250,0,250,150);
        tafel.setzeAutodrehung(true);
        tafel.setzeKamerafixierung(true);
        tafel.setzeBeleuchtung(true);
        tafel.setzeSelbstleuchten(1,1,1);
        tafel.setzeSichtbarkeit(false);
        tafel.setzeTextur("src/img/img_1.png");

        tafeltime = new GLTafel(350,250,0,250,150);
        tafeltime.setzeAutodrehung(true);
        tafeltime.setzeKamerafixierung(true);
        tafeltime.setzeBeleuchtung(true);
        tafeltime.setzeSelbstleuchten(1,1,1);
        tafeltime.setzeSichtbarkeit(false);
        tafeltime.setzeTextur("src/img/img_1.png");

        tafel2 = new GLTafel(0,250,0,250,150);
        tafel2.setzeAutodrehung(true);
        tafel2.setzeKamerafixierung(true);
        tafel2.setzeBeleuchtung(true);
        tafel2.setzeSelbstleuchten(1,1,1);
        tafel2.setzeSichtbarkeit(false);
        tafel2.setzeTextur("src/img/img_1.png");

        tafel3 = new GLTafel(0,0,0,250,150);
        tafel3.setzeAutodrehung(true);
        tafel3.setzeKamerafixierung(true);
        tafel3.setzeBeleuchtung(true);
        tafel3.setzeSelbstleuchten(1,1,1);
        tafel3.setzeSichtbarkeit(false);
        tafel3.setzeTextur("src/img/img_1.png");
        kamera.setzePosition(0, 500, 800);

        spielfeld = new Spielfeld(1000, 1000);
        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        tastatur = new GLTastatur();
        kugel = new Kugel [5];
        kugeltot = new Kugel [5];
        dieBox= new Box();
        speed= Math.random();
        for(int i = 0; i< kugel.length;i++){
            kugel[i] = new Kugel(40,spielfeld,dieBox, (Math.random()*8+5),i,0.25);
        }
        for(int i = 0; i< kugeltot.length;i++){
            kugeltot[i] = new Kugel(40,spielfeld,dieBox, (Math.random()*2+5),i,0.25);
            kugeltot[i].setzeSichtbarkeit(false);
        }
        for(int i = 0; i< kugel.length;i++){
            kugel[i].uebergebeKugeln(kugel);
        }
        for(int i = 0; i< kugeltot.length;i++){
            kugeltot[i].uebergebeKugeln(kugel);
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

   public void fuehreAus(){
        while(2==2){
            initialiesiere();
        }
   }
    public void initialiesiere() {


       for(int j = 0; j<15; j++) {
           for (int i = 0; i < kugel.length; i++) {
               kugel[i].startSpawn();
               kugel[i].setzeSichtbarkeit(false);
           }
       }
        dieBox.spawn();
        for(int j = 0; j<15; j++) {
        for (int i = 0; i < kugel.length; i++) {
            kugel[i].startSpawn();
            kugel[i].setzeSichtbarkeit(true);
        }}
        tafel.setzeText("Punktzahl:"+punkte ,30);
        tafel.setzeSichtbarkeit(true);
        tafeltime.setzeText("Timer:" ,30);
        tafeltime.setzeSichtbarkeit(true);
        long startzeit=System.currentTimeMillis();
        while (!tastatur.esc()) {
            if (System.currentTimeMillis() - startzeit >= 500){
                for (int i = 0; i < kugel.length; i++) {
                 kugel[i].decreaseSpeed();
                }
                startzeit = System.currentTimeMillis();
            }
            Sys.warte(10);
            for (int i = 0; i < kugel.length; i++) {
               kugel[i].istKugelGetroffen();
                if(kugel[i].gibSpeed()==0 && !kugel[i].collected()){
                    timer=1;

                    kugel[i].setzeSelbstbeleuchtung(1,1,1);
                }
                if(timer==1) {
                    if (timerLzeit == 0) {
                        timerSzeit = System.currentTimeMillis();
                        timerLzeit++;
                    }


                    timerLzeit = timerLzeit + (System.currentTimeMillis() - timerSzeit);
                    timerLzeitRound = (int) timerLzeit / 1000;
                    System.out.println(timerLzeitRound);
                    if (timerLzeit <= 3500) {
                        tafeltime.setzeText("Timer: " +(3- timerLzeitRound), 30);
                        timer = 0;
                        if(timerLzeitRound==1){
                            kugel[i].setzeSelbstbeleuchtung(1,0,0);
                        }
                        if(timerLzeitRound==2){
                            kugel[i].setzeSelbstbeleuchtung(1,0,0);
                        }
                        if(timerLzeitRound==3){
                            kugel[i].setzeSelbstbeleuchtung(1,0,0);
                        }


                    } else {
                        timer= 0;
                        spielende2=true;
                    }
                    timerSzeit = System.currentTimeMillis();
                }
                if (kugel[i].istGetroffen() && kugel[i].gibSpeed()==0 && tastatur.tab()) {


                    timer = 0;
                    timerLzeit= 0;
                    kugel[i].setCollectedTrue();
                    tafeltime.setzeText("Timer: " + "0", 30);
                }
                    if (kugel[i].istGetroffen() && tastatur.tab()) {

                        kugel[i].setzePosition(1000, 1000, 100000);

                        kugeltot[i].setzeSichtbarkeit(true);

                        kugeltot[i].respawn(i * 40);
                        punkte = punkte + 1;
                        tafel.setzeText("Punktzahl: " + punkte , 30);


                    }
                    if (punkte == 5) {

                        spielende=true;
                    }
                    if(spielende==true){
                        kamera.setzeBlickpunkt(0, 300, 30000);
                        tafel2.setzeSichtbarkeit(true);
                        tafel3.setzeSichtbarkeit(true);
                        tafel.setzeSichtbarkeit(false);
                        tafeltime.setzeSichtbarkeit(false);

                        tafel2.setzeText("Glueckwunsch hast du toll gemacht! Restart? ", 30);
                        tafel3.setzeText("Druecke Enter!", 40);
                    }
                if(spielende2==true){
                    kamera.setzeBlickpunkt(0, 300, 30000);
                    tafel2.setzeSichtbarkeit(true);
                    tafel3.setzeSichtbarkeit(true);
                    tafel.setzeSichtbarkeit(false);
                    tafeltime.setzeSichtbarkeit(false);

                    tafel2.setzeText("Du hast leider verloren! Restart? ", 30);
                    tafel3.setzeText("Druecke Enter!", 40);
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
            if(tastatur.tab()){
                dieBox.setzeMaterial(GLMaterial.ROTGLAS);
            }else{
                dieBox.setzeMaterial(GLMaterial.GLAS);
            }


        }
    }
}

