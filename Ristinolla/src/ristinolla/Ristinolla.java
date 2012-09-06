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
        Pelilauta pelilauta = new Pelilauta(20,20);
        Kayttoliittyma ui = new Tekstikayttoliittyma(pelilauta,3);
        ui.start();
    }
}
