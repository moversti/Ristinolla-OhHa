package ristinolla;

import java.util.Scanner;

/**
 * Ristinollapeli.
 * @author moversti
 */
public class Ristinolla {

    private static void kysyAsetukset() {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Leveys: ");
        leveys=Integer.parseInt(lukija.nextLine());
        System.out.print("Korkeus: ");
        korkeus=Integer.parseInt(lukija.nextLine());
        System.out.print("Rivin pituus: ");
        rivinPituus=Integer.parseInt(lukija.nextLine());
    }

    /**
     * @param args the command line arguments
     */
    public static int leveys;
    public static int korkeus;
    public static int rivinPituus;
    
    public static void main(String[] args) {
        kysyAsetukset();
        Pelilauta pelilauta = new Pelilauta(leveys,korkeus);
        Kayttoliittyma ui = new Tekstikayttoliittyma(pelilauta,rivinPituus);
        ui.start();
    }
}
