package ristinolla;

/**
 * Tätä tarvitaan undo-ominaisuuteen.
 * @author moversti
 */
public class Siirto {
    public int x;
    public int y;
    public Ruutu ruutu;

    /**
     * Perus konstruktori.
     * @param x
     * @param y
     * @param ruutu
     */
    public Siirto(int x, int y, Ruutu ruutu) {
        this.x = x;
        this.y = y;
        this.ruutu = ruutu;
    }
    
}
