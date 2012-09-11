package ristinolla;

import java.util.Scanner;

/**
 * Tekstikäyttöliittymä.
 *
 * @author moversti
 */
public class Tekstikayttoliittyma implements Kayttoliittyma {

    private Pelilauta pelilauta;
    private Voitontestaaja voitontestaaja;
    private Scanner lukija;
    private boolean pelataan;
    private Ruutu voittaja;

    /**
     * Käynnistää lukijan.
     *
     * @param pelilauta
     * @param rivinPituus voittorivin pituus.
     */
    public Tekstikayttoliittyma(Pelilauta pelilauta, int rivinPituus) {
        this.pelilauta = pelilauta;
        this.voitontestaaja = new Voitontestaaja(pelilauta, rivinPituus);
        lukija = new Scanner(System.in);
    }

    /**
     * Aloittaa käyttöliittymän.
     */
    @Override
    public void start() {
        pelataan = true;
        while (pelataan) {
            printtaaLauta();
            kysyKomento();
            if (!pelataan) {
                break;
            }
        }
        printtaaLauta();
        System.out.println("Peli päättyi, " + voittaja + " voittaa!");
    }

    /*
     * printtaa laudan.
     */
    private void printtaaLauta() {
        System.out.println(pelilauta);
    }

    /*
     * Kysyy komennon ja kutsuu metodeita tekemään hommia.
     */
    private void kysyKomento() {
        System.out.print("Komento[X/O/Tallenna/Lataa/Undo]: ");
        String syote = lukija.nextLine().toUpperCase();
        if (syote.equals("X") || syote.equals("O")) {
            System.out.print("Koordinaatti x: ");
            String xkoord = lukija.nextLine();
            System.out.print("Koordinaatti y: ");
            String ykoord = lukija.nextLine();
            Ruutu ruutu;
            if (syote.equals("X")) {
                ruutu = Ruutu.X;
            } else {
                ruutu = Ruutu.O;
            }
            int xkoo = Integer.parseInt(xkoord);
            int ykoo = Integer.parseInt(ykoord);
            pelilauta.muutaRuutu(xkoo, ykoo, ruutu);
            voittaja = ruutu;
            pelataan = !voitontestaaja.testaa();
        } else if (syote.equals("U")) {
            pelilauta.undo();
        } else {
            pelataan = false;
        }
    }
}
