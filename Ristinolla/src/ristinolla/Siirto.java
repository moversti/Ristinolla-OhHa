package ristinolla;

/**
 * Tätä tarvitaan undo-ominaisuuteen.
 * @author moversti
 */
public class Siirto {
    /**
     * X-koordinaatti.
     */
    public int x;
    /**
     * Y-koordinaatti.
     */
    public int y;
    /**
     * Ruutu joka oli ennen kohdassa x,y.
     */
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
