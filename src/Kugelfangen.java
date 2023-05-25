import GLOOP.*;

public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLTafel tafel, tafel2, tafel3, tafeltime;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel[] kugel, kugeltot;
    private double speed, a, f;
    private int punkte = 0;
    private int timer = 0;
    private long timerSzeit , timerLzeit ;
    long startzeit;
    private int timerLzeitRound = 0;
    private boolean spielende = false;
    private boolean spielende2 = false;

    //w
    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        tafel = new GLTafel(-350, 250, 0, 250, 150);
        tafel.setzeAutodrehung(true);
        tafel.setzeKamerafixierung(true);
        tafel.setzeBeleuchtung(true);
        tafel.setzeSelbstleuchten(1, 1, 1);
        tafel.setzeSichtbarkeit(false);
        tafel.setzeTextur("src/img/img_1.png");

        tafeltime = new GLTafel(350, 250, 0, 250, 150);
        tafeltime.setzeAutodrehung(true);
        tafeltime.setzeKamerafixierung(true);
        tafeltime.setzeBeleuchtung(true);
        tafeltime.setzeSelbstleuchten(1, 1, 1);
        tafeltime.setzeSichtbarkeit(false);
        tafeltime.setzeTextur("src/img/img_1.png");

        tafel2 = new GLTafel(0, 250, 0, 250, 150);
        tafel2.setzeAutodrehung(true);
        tafel2.setzeKamerafixierung(true);
        tafel2.setzeBeleuchtung(true);
        tafel2.setzeSelbstleuchten(1, 1, 1);
        tafel2.setzeSichtbarkeit(false);
        tafel2.setzeTextur("src/img/img_1.png");

        tafel3 = new GLTafel(0, 0, 0, 250, 150);
        tafel3.setzeAutodrehung(true);
        tafel3.setzeKamerafixierung(true);
        tafel3.setzeBeleuchtung(true);
        tafel3.setzeSelbstleuchten(1, 1, 1);
        tafel3.setzeSichtbarkeit(false);
        tafel3.setzeTextur("src/img/img_1.png");
        kamera.setzePosition(0, 500, 800);

        spielfeld = new Spielfeld(1000, 1000);
        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        tastatur = new GLTastatur();
        kugel = new Kugel[5];
        kugeltot = new Kugel[5];
        dieBox = new Box();
        for (int i = 0; i < kugeltot.length; i++) {
            kugeltot[i] = new Kugel(40, spielfeld, dieBox, (Math.random() * 2 + 5), i, 0.25, 0.2);
            kugeltot[i].setzeSichtbarkeit(true);
            kugeltot[i].setzePosition(1000000,10000000,10000000);
            kugeltot[i].setzeSelbstbeleuchtung(1, 1, 1);
        }

        fuereaus();
    }
public void fuereaus(){
        initialiesiere();
        hauptschleife();
}
    public void initialiesiere() {
        speed = Math.random();
        timerSzeit = 0;
        timerLzeit = 0;
        for (int i = 0; i < kugel.length; i++) {
            kugel[i] = null;
            kugel[i] = new Kugel(40, spielfeld, dieBox, (Math.random() * 8 + 5), i, 0.25, 1);

        }

        for (int i = 0; i < kugel.length; i++) {
            kugel[i].uebergebeKugeln(kugel);
        }


        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < kugel.length; i++) {
                kugel[i].startSpawn();

            }
        }
        dieBox.spawn();

        for (int i = 0; i < kugel.length; i++) {

        }
        tafel.setzeText("Punktzahl:" + punkte, 30);
        tafel.setzeSichtbarkeit(true);
        tafeltime.setzeText("Timer:", 30);
        tafeltime.setzeSichtbarkeit(true);
        startzeit = System.currentTimeMillis();


    }
        public void hauptschleife(){
        while (!tastatur.esc()) {
            tafel.setzeText("Punktzahl:" + punkte, 30);
            if (System.currentTimeMillis() - startzeit >= 500) {
                for (int i = 0; i < kugel.length; i++) {
                    kugel[i].decreaseSpeed();
                }
                startzeit = System.currentTimeMillis();
            }
            Sys.warte(10);
            for (int i = 0; i < kugel.length; i++) {
                kugel[i].istKugelGetroffen();
                if (kugel[i].gibSpeed() == 0 && !kugel[i].collected()) {
                    timer = 1;

                    kugel[i].setzeSelbstbeleuchtung(1, 1, 1);
                }
                if (timer == 1) {

                    if (timerLzeit == 0 && !kugel[i].collected() && kugel[i].getActiveState()) {
                        timerSzeit = System.currentTimeMillis();
                        timerLzeit++;
                    }


                    timerLzeit = timerLzeit + (System.currentTimeMillis() - timerSzeit);
                    timerLzeitRound = (int) timerLzeit / 1000;
                    System.out.println(timerLzeitRound);
                    if (timerLzeit <= 3500 && !kugel[i].collected() && kugel[i].getActiveState()) {
                        tafeltime.setzeText("Timer: " + (3 - timerLzeitRound), 30);

                        if (timerLzeitRound == 1) {
                            kugel[i].setzeSelbstbeleuchtung(1, 0, 0);
                        }
                        if (timerLzeitRound == 2) {
                            kugel[i].setzeSelbstbeleuchtung(1, 0, 0);
                        }
                        if (timerLzeitRound == 3) {
                            kugel[i].setzeSelbstbeleuchtung(1, 0, 0);
                        }


                    }
                    if(timerLzeit > 3500){
                        timer = 0;
                        timerLzeit = 0;
                        spielende2 = true;
                    }
                    timerSzeit = System.currentTimeMillis();
                }
                if (kugel[i].istGetroffen() && kugel[i].gibSpeed() == 0 && tastatur.tab()) {


                    timer = 0;
                    timerLzeit = 0;
                    timerSzeit = 0;
                    timerLzeitRound = 0;

                    tafeltime.setzeText("Timer: " + "0", 30);
                }
                if (kugel[i].istGetroffen() && tastatur.tab()) {
                    tafel.setzeText("Punktzahl:" + punkte, 30);
                    kugel[i].setzePosition(1000000,10000000,10000000);
                    kugel[i].setCollected(true);
                    kugel[i].setzeSelbstbeleuchtung(0, 0, 0);
                    kugeltot[i].setzePosition(130 - (i * 40), 480, 500);
                    kugeltot[i].setzeSichtbarkeit(true);
                    punkte = punkte + 1;
                }
                if (punkte == 5) {
                    spielende = true;
                }
                if (spielende || spielende2) {
                    kamera.setzeBlickpunkt(0, 300, 30000);
                    tafel2.setzeSichtbarkeit(true);
                    tafel3.setzeSichtbarkeit(true);
                    tafel.setzeSichtbarkeit(false);
                    tafeltime.setzeSichtbarkeit(false);
                    for (int j = 0; j < kugel.length; j++) {
                        kugel[i].setzePosition(1000000,10000000,10000000);
                        kugeltot[i].setzePosition(1000000,10000000,10000000);
                    }
                }
                if (spielende) {
                    tafel2.setzeText("Glueckwunsch hast du toll gemacht! Restart? ", 30);
                    tafel3.setzeText("Druecke Enter!", 40);
                }
                if (spielende2 && !spielende) {
                    tafel2.setzeText("Du hast leider verloren! Restart? ", 30);
                    tafel3.setzeText("Druecke Enter!", 40);
                }
                kugel[i].bewegeHorizontal();
                kugel[i].bewegeVertikal();

                if (tastatur.enter() && (spielende2 || spielende)) {
                    System.out.println("hi");

                    for (int j = 0; j < kugel.length; j++) {
                        kugel[i].reset();
                        kugel[i].setzeSelbstbeleuchtung(0, 0, 0);

                        kugeltot[i].setzeSichtbarkeit(false);
                    }

                    spielende = false;
                    spielende2 = false;
                    punkte = 0;
                    timerLzeit = 0;
                    timerSzeit = 0;
                    timer = 0;

                    kamera.setzeBlickpunkt(0, -25, -30);
                    tafel2.setzeSichtbarkeit(false);
                    tafel3.setzeSichtbarkeit(false);
                    tafel.setzeSichtbarkeit(true);
                    tafeltime.setzeSichtbarkeit(true);
                    initialiesiere();

                }
            }
            if (tastatur.links()) {
                dieBox.bewegeLinks();
            }
            if (tastatur.rechts()) {
                dieBox.bewegeRechts();
            }
            if (tastatur.oben()) {
                dieBox.bewegeOben();
            }
            if (tastatur.unten()) {
                dieBox.bewegeUnten();
            }
            if (tastatur.tab()) {
                dieBox.setzeMaterial(GLMaterial.ROTGLAS);
            } else {
                dieBox.setzeMaterial(GLMaterial.GLAS);
            }
            if (tastatur.backspace()) {
                dieBox.reset();
            }

        }
    }
}

