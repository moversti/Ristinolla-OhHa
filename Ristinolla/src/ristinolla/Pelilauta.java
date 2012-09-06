
package ristinolla;

/**
 * Pelilaudalla on listoja joilla on tilana tyhjä, risti tai nolla.
 * @author moversti
 */
public class Pelilauta {
    private Ruutu[][] ruudut;
    private int leveys;
    private int korkeus;
    private Siirto undo;

    /**
     * Täyttää ruudut tyhjällä.
     * @param leveys laudan leveys
     * @param korkeus laudan korkeus
     */
    public Pelilauta(int leveys, int korkeus) {
        ruudut = new Ruutu[leveys][korkeus];
        this.leveys = leveys;
        this.korkeus = korkeus;
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                ruudut[i][j]=Ruutu._;
            }
        }
        undo=new Siirto(0,0,Ruutu._);
    }
    public Pelilauta(){
        this(20,20);
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public void asetetaanUndo(Siirto siirto){
        undo=siirto;
    }
    /**
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param ruutu miksi muutetaan?
     */
    public void muutaRuutu(int x, int y, Ruutu ruutu){
        ruudut[y][x]=ruutu;
    }

    @Override
    public String toString() {
        String paluu ="   ";
        for (int i = 0; i < leveys; i++) {
            String lisattava=i+"";
            lisattava = String.format("%3s",lisattava);
            paluu+=lisattava;
        }
        paluu +="\n";
        for (int i = 0; i < leveys; i++) {
            paluu+=String.format("%3s", i);
            for (int j = 0; j < korkeus; j++) {
                paluu+="  "+ruudut[i][j];
            }
            paluu+="\n";
        }
        return paluu;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return ruudussa x,y oleva tila
     */
    public Ruutu getRuutu(int x, int y){
        return ruudut[x][y];
    }
    
    public void undo(){
        ruudut[undo.y][undo.x]=undo.ruutu;
    }
}
