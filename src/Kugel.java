import GLOOP.*;
public class Kugel{
    private GLKugel kugel;
    private Box dieBox;
    private Spielfeld feld;
    
    private double radius;
    private boolean istAktiv;
    private double a,b,d,f,g, a1,b1,d1;
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
    kugel = new GLKugel(Math.random()*500*a,40,Math.random()*500*f,pRadius);
    kugel.setzeFarbe(Math.random(),Math.random(),Math.random());
    radius=pRadius;
    }
   public boolean istGetroffen() {
        g= (kugel.gibX() - dieBox.gibX());

        b = (kugel.gibZ() - dieBox.gibZ());
        d = Math.sqrt(g * g + b * b);
       if (d <= 35) {
           return true;
       } else {
           return false;
       }
   }
    public void respawn() {
        kugel.setzePosition(20, 400, 300);
        kugel.setzeSelbstleuchten(1,1,1);
        kugel.skaliere(0.5,0.5,0.5);
        f=0;
    }



    public void bewegeHorizontal(){
      if(this.istGetroffen()){
         this.respawn();

        System.out.println("getroffen");
         }
        if(kugel.gibX()<= feld.getBreite()/2 && kugel.gibX()>=feld.getBreite()/-2){
           kugel.verschiebe(a * speed, 0, 0);

       }else {
            a = -a;
            kugel.verschiebe(8 * a, 0, 0);




        }

    }

    public void bewegeVertikal(){
        if(this.istGetroffen()){
        this.respawn();
         System.out.println("getroffen");
        }
       if(kugel.gibZ()<= feld.getTiefe()/2&&kugel.gibZ()>= feld.getTiefe()/-2){
            kugel.verschiebe(0, 0, f * speed);
        }else {
            f = -f;
            kugel.verschiebe(0, 0, radius* f);



        }

    }

    public double gibX() {
        return  kugel.gibX();
    }
    public double gibZ() {
        return  kugel.gibZ();
    }
}

