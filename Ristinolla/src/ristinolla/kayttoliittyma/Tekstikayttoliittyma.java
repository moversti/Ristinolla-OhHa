package ristinolla.kayttoliittyma;

import java.util.Scanner;
import ristinolla.logiikka.Pelilauta;
import ristinolla.logiikka.Ruutu;
import ristinolla.logiikka.Voitontestaaja;

/**
 * Tekstikäyttöliittymä.
 *
 * @author moversti
 */
public class Tekstikayttoliittyma implements Kayttoliittyma {

    /**
     * Kysyy pelin asetukset (laudan koko ja voittorivin pituus) ennen
     * aloittamista.
     *
     */
    private void kysyAsetukset() {
        String syote;
        while (true) {
            System.out.print("Koko: ");
            syote = lukija.nextLine();
            try {
                int kokoInt = Integer.parseInt(syote);
                if (kokoInt < 3 || kokoInt > 100) {
                    throw new IllegalArgumentException();
                }
                pelilauta = new Pelilauta(kokoInt);
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Kokonaisluku, kiitos!");
            } catch (IllegalArgumentException iae) {
                System.out.println("Luku v\u00e4lilt\u00e4 3-100, kiitos!");
            }
        }
        while (true) {
            System.out.print("Rivin pituus: ");
            syote = lukija.nextLine();
            try {
                int rivinPituusInt = Integer.parseInt(syote);
                if (rivinPituusInt < 3 || rivinPituusInt > pelilauta.getKoko()) {
                    throw new IllegalArgumentException();
                }
                voitontestaaja = new Voitontestaaja(pelilauta, rivinPituusInt);
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Kokonaisluku, kiitos!");
            } catch (IllegalArgumentException iae) {
                System.out.println("Luku v\u00e4lilt\u00e4 3 - laudan koko, kiitos!");
            }
        }
    }
    private Pelilauta pelilauta;
    private Voitontestaaja voitontestaaja;
    private Scanner lukija;
    private boolean pelataan;

    /**
     * Käynnistää lukijan ja kutsuu kysyAsetukset.
     */
    public Tekstikayttoliittyma() {
        lukija = new Scanner(System.in);
        kysyAsetukset();
        pelilauta.setVoitontestaaja(voitontestaaja);
        pelilauta.setVoittaja(Ruutu.O);
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
        System.out.println("Kiitos pelaamisesta!");
        if (pelilauta.isVoittajaSelvilla()) {
            System.out.println(pelilauta.getVoittaja() + " voittaa!");
        }

    }

    /**
     * printtaa laudan.
     */
    private void printtaaLauta() {
        System.out.println("\n" + pelilauta + "\n");
    }

    /**
     * Kysyy komennon ja kutsuu metodeita tekemään hommia.
     */
    private void kysyKomento() {
        System.out.print("Komento[Pelaa/X/O/Tallenna/Lataa/Undo]: ");
        String syote = lukija.nextLine().toUpperCase();
        if (syote.equals("X") || syote.equals("O") || syote.equals("P")) {
            do {
                System.out.print("Koordinaatti x: ");
                String koordinaattiSyote = lukija.nextLine();
                if (koordinaattiSyote.isEmpty()) {
                    break;
                }
                int xkoord;;
                try {
                    xkoord = Integer.parseInt(koordinaattiSyote);
                } catch (NumberFormatException nfe) {
                    System.out.println("!!!!! Koordinaatit ovat kokonaislukuja, ääliö !!!!!");
                    break;
                }
                System.out.print("Koordinaatti y: ");
                koordinaattiSyote = lukija.nextLine();
                if (koordinaattiSyote.isEmpty()) {
                    break;
                }
                int ykoord;
                try {
                    ykoord = Integer.parseInt(koordinaattiSyote);
                } catch (NumberFormatException nfe) {
                    System.out.println("!!!!! Koordinaatit ovat kokonaislukuja, ääliö !!!!!");
                    break;
                }
                Ruutu ruutu;
                if (koordinaattiSyote.equals("X")) {
                    ruutu = Ruutu.X;
                } else if (koordinaattiSyote.equals("O")) {
                    ruutu = Ruutu.O;
                } else if (pelilauta.getVoittaja() == Ruutu.O) {
                    ruutu = Ruutu.X;
                } else {
                    ruutu = Ruutu.O;
                }
                if (!pelilauta.muutaRuutu(xkoord, ykoord, ruutu)) {
                    System.out.println("!!!!! Koordinaatit laudan ulkopuolella !!!!!");
                    break;
                }

                pelilauta.setVoittaja(ruutu);
                boolean testi = voitontestaaja.testaa();
                pelataan = !testi;
                pelilauta.setVoittajaSelvilla(testi);
                printtaaLauta();
            } while (syote.equals("P") && pelataan);
        } else if (syote.equals("U")) {
            pelilauta.undo();
        } else if (syote.equals("T")) {
            pelilauta.tallenna();
            pelataan = false;
        } else if (syote.equals("L")) {
            pelilauta.lataa();
        } else {
            System.out.println("Lopetetaan peli?");
            String lopetus = lukija.nextLine().toUpperCase();
            if (lopetus.equals("K") || lopetus.equals("Y")) {
                pelataan = false;
            }
        }
    }
}
