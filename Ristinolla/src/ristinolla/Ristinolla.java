package ristinolla;

import ristinolla.kayttoliittyma.Kayttoliittyma;
import ristinolla.kayttoliittyma.Tekstikayttoliittyma;

/**
 * Ristinollapeli.
 *
 * @author moversti
 */
public class Ristinolla {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kayttoliittyma ui = new Tekstikayttoliittyma();
        ui.start();
    }
}
