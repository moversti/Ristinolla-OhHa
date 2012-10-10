package ristinolla;

/**
 * Tätä tarvitaan undo-ominaisuuteen.
 * @author moversti
 */
public class Siirto {
    /**
     * X-koordinaatti.
     */
     int x;
    /**
     * Y-koordinaatti.
     */
     int y;
    /**
     * Ruutu joka oli ennen kohdassa x,y.
     */
     Ruutu ruutu;

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
