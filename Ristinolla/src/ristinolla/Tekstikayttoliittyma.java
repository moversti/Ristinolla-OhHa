package ristinolla;

import java.util.Scanner;

/**
 * Tekstikäyttöliittymä.
 *
 * @author moversti
 */
public class Tekstikayttoliittyma implements Kayttoliittyma{

    private Pelilauta pelilauta;
    private Voitontestaaja voitontestaaja;
    private Scanner lukija;
    private boolean pelataan;

    public Tekstikayttoliittyma(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
        this.voitontestaaja = new Voitontestaaja(pelilauta);
        lukija = new Scanner(System.in);
    }

    /**
     * Aloittaa käyttöliittymän;
     */
    @Override
    public void start() {
        pelataan = true;
        while (pelataan) {
            printtaaLauta();
            kysyKomento();
        }
    }

    private void printtaaLauta() {
        System.out.println(pelilauta);
    }

    private void kysyKomento() {
        System.out.print("Komento[X/O/Tallenna/Lataa]: ");
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
            pelilauta.muutaRuutu(Integer.parseInt(xkoord), Integer.parseInt(ykoord), ruutu);
        } else {
            pelataan = false;
        }
    }
}
