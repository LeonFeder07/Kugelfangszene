import GLOOP.*;
public class Kugel{
    private GLKugel kugel;
    private Box dieBox;
    private Spielfeld feld;
    
    private double radius;
    private boolean istAktiv;
    private double a,b,d,f, a1,b1,d1;
    private double speed;

    public Kugel(int pRadius,Spielfeld pFeld,Box pBox, double pspeed){
        speed = pspeed;
    if(Math.random()<0.5){
        a=1;
        f=1;
    }else{
        a=-1;
        f=-1;
    }
    feld=pFeld;
    dieBox=pBox;
    kugel = new GLKugel(Math.random()*400*a,40,Math.random()*400*f,pRadius);
    kugel.setzeFarbe(Math.random(),Math.random(),Math.random());
    radius=pRadius;
    }
   public boolean istGetroffen() {
        a = (kugel.gibX() - dieBox.gibX());

        b = (kugel.gibZ() - dieBox.gibZ());
        d = Math.sqrt(a * a + b * b);
       if (d <= 0) {
           return true;
       } else {
           return false;
       }
   }

    public void rolle() {
        this.bewegeHorizontal();
        this.bewegeVertikal();
        if(this.istGetroffen()){
            kugel.setzeSichtbarkeit(true);
        }
    }

    public void bewegeHorizontal(){
        if(kugel.gibX()<= feld.getBreite()/2 && kugel.gibX()>=feld.getBreite()/-2){
           kugel.verschiebe(a * speed, 0, 0);

       }else
           a = -a;
       kugel.verschiebe(radius*a,0,0);
        kugel.verschiebe(a*speed,0,0);

        //System.out.println("Inverted");


    }

    public void bewegeVertikal(){
        if(kugel.gibZ()<= feld.getTiefe()/2&&kugel.gibZ()>= feld.getTiefe()/-2){
            kugel.verschiebe(0, 0, f * speed);
        }else
            f = -f;
        kugel.verschiebe(0,0,radius*f);
        kugel.verschiebe(0,0,speed*f);

        //System.out.println("Inverted");
    }

    public double gibX() {
        return  kugel.gibX();
    }
    public double gibZ() {
        return  kugel.gibZ();
    }
}

