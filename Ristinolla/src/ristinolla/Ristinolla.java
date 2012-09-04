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
        pelilauta.muutaRuutu(4, 5, Ruutu.X);
        pelilauta.muutaRuutu(5, 6, Ruutu.O);
        System.out.println(pelilauta);
    }
}
