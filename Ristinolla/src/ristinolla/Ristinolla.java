package ristinolla;

/**
 * Ristinollapeli.
 * @author moversti
 */
public class Ristinolla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta(7,7);
        Kayttoliittyma ui = new Tekstikayttoliittyma(pelilauta);
        ui.start();
    }
}
