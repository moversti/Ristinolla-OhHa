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
        Pelilauta pelilauta = new Pelilauta();
        pelilauta.muutaRuutu(3, 4, Ruutu.X);
        System.out.println(pelilauta);
    }
}
