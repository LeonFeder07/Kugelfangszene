import GLOOP.*;
public class Spielfeld{
    private GLQuader untergrund,rechts,links,oben,unten;
    private double breite, tiefe;

    public Spielfeld(double pBreite, double pTiefe){
        breite = pBreite;
        tiefe = pTiefe;
        untergrund = new GLQuader(0,0,0, breite,20,tiefe);
        untergrund.setzeTextur("src/img/Marmor.jpg");
        rechts = new GLQuader(breite/2,20,0, 10,20,tiefe);
        links = new GLQuader(breite/(-2),20,0, 10,20,tiefe);
        unten = new GLQuader(0,20,tiefe/(-2), breite,20,10);
        oben = new GLQuader(0,20,tiefe/(2), breite,20,10);
    }

    public double getBreite() {
        return breite;
    } public double getTiefe() {
        return tiefe;
    }
}
