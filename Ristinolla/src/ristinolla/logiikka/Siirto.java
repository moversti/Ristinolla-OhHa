package ristinolla.logiikka;

/**
 * Tätä tarvitaan undo-ominaisuuteen.
 * @author moversti
 */
public class Siirto {
    /**
     * X-koordinaatti.
     */
    private  int x;
    /**
     * Y-koordinaatti.
     */
    private  int y;
    /**
     * Ruutu joka oli ennen kohdassa x,y.
     */
    private  Ruutu ruutu;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ruutu getRuutu() {
        return ruutu;
    }

    public void setRuutu(Ruutu ruutu) {
        this.ruutu = ruutu;
    }

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
