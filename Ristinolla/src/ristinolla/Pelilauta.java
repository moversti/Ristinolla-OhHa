
package ristinolla;

/**
 * Pelilaudalla on listoja joilla on tilana tyhjä, risti tai nolla.
 * @author moversti
 */
public class Pelilauta {
    private Ruutu[][] ruudut;

    /**
     * Täyttää ruudut tyhjällä.
     */
    public Pelilauta() {
        ruudut = new Ruutu[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ruudut[i][j]=Ruutu._;
            }
        }
    }
    /**
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param ruutu miksi muutetaan?
     */
    public void muutaRuutu(int x, int y, Ruutu ruutu){
        ruudut[x][y]=ruutu;
    }

    @Override
    public String toString() {
        String paluu ="";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                paluu+=ruudut[i][j]+" ";
            }
            paluu+="\n";
        }
        return paluu;
    }
    
}
