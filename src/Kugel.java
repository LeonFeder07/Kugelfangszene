import GLOOP.*;
public class Kugel{
    private GLKugel kugel;
    private Box dieBox;
    private Spielfeld feld;
    
    private double radius;
    private boolean istAktiv;
    private double a,b,d,f,g, rand1;
    private double aspeed,speed,speedlos;
    private int index, punkte;
    private boolean collected = false;
    Kugel[] kugeln;

    public Kugel(int pRadius,Spielfeld pFeld,Box pBox, double pspeed, int pindex, double pspeedlos){
        speed = pspeed;
        aspeed= pspeed;
        speedlos = pspeedlos;
        index= pindex;
        punkte = 0;
    if(Math.random()<0.5){
        a=1;
        f=1;
    }else{
        a=-1;
        f=-1;
    }
    feld=pFeld;
    dieBox=pBox;
    kugel = new GLKugel(Math.random()*500*a,55,Math.random()*500*f,pRadius);
    kugel.setzeFarbe(Math.random(),Math.random(),Math.random());
    radius=pRadius;
    }
   public boolean istGetroffen() {
        g= (kugel.gibX() - dieBox.gibX());

        b = (kugel.gibZ() - dieBox.gibZ());
        d = Math.sqrt(g * g + b * b);
       if (d <= radius + 10) {
           punkte = punkte +1;
           return true;

       } else {
           return false;
       }
   }
   public void istKugelGetroffen() {
       for (int i = 0; i < kugeln.length; i++) {
           g = (kugeln[i].gibX() - kugel.gibX());

           b = (kugeln[i].gibZ() - kugel.gibZ());
           d = Math.sqrt(g * g + b * b);
           if(i!=index){
           if (d <= radius * 2-10) {
               a=a*-1;
               f=f*-1;
           }
           }
       }
   }
    public void respawn(double pPos) {
        kugel.setzePosition(130-pPos, 480, 500);
        kugel.setzeSelbstleuchten(1,1,1);
        kugel.skaliere(0.2,0.2,0.2);
        f=0;
        a=0;
        rand1=150;
       // punkte = punkte +1;
    }
    public void startSpawn() {

        for (int i = 0; i < kugeln.length; i++) {
            g = (kugeln[i].gibX() - kugel.gibX());

            b = (kugeln[i].gibZ() - kugel.gibZ());
            d = Math.sqrt(g * g + b * b);
            if(i!=index)
            if (d <= radius * 2) {
                kugeln[i].setzePosition(Math.random()*400*a,40,Math.random()*400*f);
                kugel.verschiebe(5*speed*a*(Math.random()),0,5*speed*f*(Math.random()));

        }


        }
    }




    public void bewegeHorizontal(){


        if(kugel.gibX()<= feld.getBreite()/2 && kugel.gibX()>=feld.getBreite()/-2){


            kugel.verschiebe(a * speed, 0, 0);
            if(kugel.gibX()> feld.getBreite()/2-radius) {
                kugel.setzePosition(feld.getBreite() / 2-radius, kugel.gibY(), kugel.gibZ());
                a = -a;
                kugel.verschiebe(8 * a, 0, 0);
            }
            if(kugel.gibX()< feld.getBreite()/-2+radius) {
                kugel.setzePosition(feld.getBreite() / -2+radius, kugel.gibY(), kugel.gibZ());
                a = -a;
                kugel.verschiebe(8 * a, 0, 0);
            }
        }




    }



    public void bewegeVertikal(){

        if(kugel.gibZ()<= feld.getTiefe()/2+rand1*-1&&kugel.gibZ()>= feld.getTiefe()/-2+rand1*1){
            kugel.verschiebe(0, 0, f * speed);
            if(kugel.gibZ()> feld.getTiefe()/2-radius) {
                kugel.setzePosition(kugel.gibX(), kugel.gibY(), feld.getTiefe() / 2-radius);
                f = -f;
                kugel.verschiebe(0, 0, f*8);
            }
            if(kugel.gibZ()< feld.getTiefe()/-2+radius) {
                kugel.setzePosition(kugel.gibX(), kugel.gibY(), feld.getTiefe() / -2+radius);
                f = -f;
                kugel.verschiebe(0, 0, f*8);
            }



        }

    }

    public double gibX() {
        return  kugel.gibX();
    }
    public double gibZ() {
        return  kugel.gibZ();
    }
    public void uebergebeKugeln(Kugel[] pKugel){
        kugeln = pKugel;
    }
    public void setzePosition(double px,double py, double pz){
        kugel.setzePosition(px,py,pz);
    }
    public void setzeSichtbarkeit(boolean ptrue){
        kugel.setzeSichtbarkeit(ptrue);
    }

    public void decreaseSpeed(){if(speed>0){speed= speed-speedlos;}else{speed=0;}}
    public double gibSpeed(){return speed;}
    public boolean collected(){if(this.istGetroffen()){return true;}else{return false;}}
    public void setCollectedTrue(){collected = true;}
    public void setzeSelbstbeleuchtung(double pr,double pg,double pb){kugel.setzeSelbstleuchten(pr,pb,pg);}
    public void reset(){
        speed=0.05;
        if(Math.random()<0.5){
            a=1;
            f=1;
        }else{
            a=-1;
            f=-1;
        }
        kugel.setzePosition(Math.random()*500*a,55,Math.random()*500*f);
        for(int i=0; i<5;i++){
            this.startSpawn();

        }
        kugel.setzeSichtbarkeit(true);

    }
    public void skaliere(double x, double y, double z){kugel.skaliere(x,y,z);}
}

