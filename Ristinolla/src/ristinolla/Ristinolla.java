package ristinolla;

import java.util.Scanner;
import ristinolla.kayttoliittyma.Kayttoliittyma;
import ristinolla.kayttoliittyma.Tekstikayttoliittyma;
import ristinolla.logiikka.Pelilauta;
import ristinolla.logiikka.Voitontestaaja;

/**
 * Ristinollapeli.
 *
 * @author moversti
 */
public class Ristinolla {

    /**
     * Kysyy pelin asetukset (laudan koko ja voittorivin pituus) ennen
     * aloittamista.
     */
    private static void kysyAsetukset() {
        Scanner lukija = new Scanner(System.in);
        String syote;

        while (true) {
            System.out.print("Koko: ");
            syote = lukija.nextLine();
            try {
                int kokoInt = Integer.parseInt(syote);
                if(kokoInt<3 || kokoInt > 100){
                    throw new IllegalArgumentException();
                }
                koko = kokoInt;
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Kokonaisluku, kiitos!");
            } catch (IllegalArgumentException iae){
                System.out.println("Luku väliltä 3-100, kiitos!");
            }
        }
        while (true) {
            System.out.print("Rivin pituus: ");
            syote = lukija.nextLine();
            try {
                int rivinPituusInt = Integer.parseInt(syote);
                if (rivinPituusInt<3 || rivinPituusInt>koko){
                    throw new IllegalArgumentException();
                }
                rivinPituus = rivinPituusInt;
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Kokonaisluku, kiitos!");
            } catch (IllegalArgumentException iae){
                System.out.println("Luku väliltä 3 - laudan koko, kiitos!");
            }
        }
    }    
    
    /**
     * Väliaikainen tallennuspaikka laudan koolle.
     */
     static int koko;
    /**
     * Väliaikainen tallennuspaikka rivin pituudelle.
     */
     static int rivinPituus;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        kysyAsetukset();
        Pelilauta pelilauta = new Pelilauta(koko);
        Voitontestaaja vt = new Voitontestaaja(pelilauta, rivinPituus);
        pelilauta.setVoitontestaaja(vt);
        Kayttoliittyma ui = new Tekstikayttoliittyma(pelilauta, vt);
        ui.start();
    }
}
