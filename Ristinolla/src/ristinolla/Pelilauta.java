package ristinolla;

/**
 * Pelilaudalla on listoja joilla on tilana tyhjä, risti tai nolla.
 *
 * @author moversti
 */
public class Pelilauta {

    /**
     * Pelilaudan 2d ruudukko ruuduille, laudan koko ja undoamisen mahdollistava
     * siirto-olio.
     */
    private Ruutu[][] ruudut;
    private int koko;
    private Siirto undo;

    /**
     * Täyttää ruudut tyhjällä.
     *
     * @param koko laudan leveys
     * @param korkeus laudan korkeus
     */
    public Pelilauta(int koko) {
        ruudut = new Ruutu[koko][koko];
        this.koko = koko;
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                ruudut[i][j] = Ruutu._;
            }
        }
        undo = new Siirto(0, 0, Ruutu._);
    }

    /**
     * 20x20 lauta jos parametriton konstruktori.
     */
    public Pelilauta() {
        this(20);
    }

    /**
     *
     * @return laudan koko.
     */
    public int getKoko() {
        return koko;
    }

    /**
     * Undo muistaa mitä jossain ruudussa oli ennen kun siihen päälle laitettiin
     * x tai o.
     *
     * @param siirto
     */
    public void asetetaanUndo(Siirto siirto) {
        undo = siirto;
    }

    /**
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param ruutu miksi muutetaan?
     */
    public void muutaRuutu(int x, int y, Ruutu ruutu) {
        ruudut[y][x] = ruutu;
    }

    /**
     * Printtaa laudan jossa koordinaatit alkavat nollasta ja vasemmasta
     * alakulmasta.
     *
     * @return
     */
    @Override
    public String toString() {
        String paluu = "   ";
        paluu = xKoordinaattiLuvut(paluu);
        paluu += "\n";
        for (int i = koko - 1; i >= 0; i--) {
            paluu += String.format("%3s", i);
            for (int j = 0; j < koko; j++) {
                paluu += "  " + ruudut[i][j];
            }
            paluu += " " + i;
            paluu += "\n";
        }
        paluu += "   ";
        paluu = xKoordinaattiLuvut(paluu);
        return paluu;
    }

    /**
     *
     * @param x
     * @param y
     * @return ruudussa x,y oleva tila
     */
    public Ruutu getRuutu(int x, int y) {
        return ruudut[y][x];
    }

    /**
     * Peruu edellisen siirron.
     */
    public void undo() {
        ruudut[undo.y][undo.x] = undo.ruutu;
    }

    /**
     * Lisää x koordinaatit toStringgiin
     *
     * @param paluu
     * @return
     */
    private String xKoordinaattiLuvut(String paluu) {
        for (int i = 0; i < koko; i++) {
            String lisattava = i + "";
            lisattava = String.format("%3s", lisattava);
            paluu += lisattava;
        }
        return paluu;
    }

    /**
     * Tallentaa pelin.
     */
    public void tallenna() {
    }

    /**
     * Lataa tallennetun pelin.
     */
    public void lataa() {
    }
}
