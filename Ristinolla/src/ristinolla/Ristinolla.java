package ristinolla;

import java.util.Scanner;

/**
 * Ristinollapeli.
 * @author moversti
 */
public class Ristinolla {

    /**
     * Kysyy pelin asetukset (laudan koko ja voittorivin pituus) ennen aloittamista.
     */
    private static void kysyAsetukset() {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Koko: ");
        koko=Integer.parseInt(lukija.nextLine());
        System.out.print("Rivin pituus: ");
        rivinPituus=Integer.parseInt(lukija.nextLine());
    }

    /**
     * @param args the command line arguments
     */
    public static int koko;
    public static int rivinPituus;
    
    public static void main(String[] args) {
        kysyAsetukset();
        Pelilauta pelilauta = new Pelilauta(koko);
        Kayttoliittyma ui = new Tekstikayttoliittyma(pelilauta,rivinPituus);
        ui.start();
    }
}
