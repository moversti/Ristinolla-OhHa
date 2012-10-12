package ristinolla.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pelilaudalla on listoja joilla on tilana tyhjä, risti tai nolla.
 *
 * @author moversti
 */
public class Pelilauta {

    /**
     *
     * @return voittajaSelvilla
     */
    public boolean isVoittajaSelvilla() {
        return voittajaSelvilla;
    }

    /**
     *
     * @param voittajaSelvilla
     */
    public void setVoittajaSelvilla(boolean voittajaSelvilla) {
        this.voittajaSelvilla = voittajaSelvilla;
    }
    /**
     * Pelilaudan 2d ruudukko ruuduille, laudan koko, undoamisen mahdollistava
     * siirto-olio, Ruutu-olio josta pelin lopussa katsotaan voittaja,
     * voitontestaaja ja totuusarvo sille että onko voittaja selvillä.
     */
    private Ruutu[][] ruudut;
    private int koko;
    private Siirto undo;
    private Ruutu voittaja;
    private Voitontestaaja voitontestaaja;
    private boolean voittajaSelvilla;

    /**
     * Täyttää ruudut tyhjällä.
     *
     * @param koko laudan leveys
     */
    public Pelilauta(int koko) {
        setKoko(koko);
        undo = new Siirto(0, 0, Ruutu._);
        voittajaSelvilla = false;
    }

    /**
     * Voitontestaajan asetus koska konstruktorissa sitä ei voi tehdä koska
     * lauta luodaan ennen voitontestaajaa.
     *
     * @param voitontestaaja
     */
    public void setVoitontestaaja(Voitontestaaja voitontestaaja) {
        this.voitontestaaja = voitontestaaja;
    }

    /**
     * 20x20 lauta jos parametriton konstruktori.
     */
    public Pelilauta() {
        this(20);
    }

    /**
     *
     * @return laudan koko.
     */
    public int getKoko() {
        return koko;
    }

    /**
     * Muuttaa laudan koon ja tyhjentää sen.
     *
     * @param koko laudan uusi koko.
     */
    public void setKoko(int koko) {
        this.koko = koko;
        ruudut = new Ruutu[koko][koko];
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                ruudut[i][j] = Ruutu._;
            }
        }
    }

    /**
     * Undo muistaa mitä jossain ruudussa oli ennen kun siihen päälle laitettiin
     * x tai o.
     *
     * @param siirto
     */
    private void asetetaanUndo(Siirto siirto) {
        undo = siirto;
    }

    /**
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param ruutu miksi muutetaan?
     * @return true jos muutos onnistui, false jos tapahtui virhe.
     */
    public boolean muutaRuutu(int x, int y, Ruutu ruutu) {
        if (x < 0 || y < 0 || x >= getKoko() || y >= getKoko()) {
            return false;
        }
        asetetaanUndo(new Siirto(x, y, getRuutu(x, y)));
        ruudut[y][x] = ruutu;
        return true;
    }

    /**
     * Asettaa voittajan.
     *
     * @param voittaja
     */
    public void setVoittaja(Ruutu voittaja) {
        this.voittaja = voittaja;
    }

    /**
     *
     * @return voittaja.
     */
    public Ruutu getVoittaja() {
        return voittaja;
    }

    /**
     * Printtaa laudan jossa koordinaatit alkavat nollasta ja vasemmasta
     * alakulmasta.
     *
     * @return
     */
    @Override
    public String toString() {
        String paluu = "   ";
        paluu = xKoordinaattiLuvut(paluu);
        paluu += "\n";
        for (int i = koko - 1; i >= 0; i--) {
            paluu += String.format("%3s", i);
            StringBuilder buffer = new StringBuilder();
            for (int j = 0; j < koko; j++) {
                buffer.append("  ").append(ruudut[i][j]);
            }
            paluu+=buffer.toString();
            paluu += " " + i;
            paluu += "\n";
        }
        paluu += "   ";
        paluu = xKoordinaattiLuvut(paluu);
        return paluu;
    }

    /**
     * Palauttaa ruudun tilan.
     *
     * @param x
     * @param y
     * @return ruudussa x,y oleva tila
     */
    public Ruutu getRuutu(int x, int y) {
        return ruudut[y][x];
    }

    /**
     * Peruu edellisen siirron.
     */
    public void undo() {
        ruudut[undo.getY()][undo.getY()] = undo.getRuutu();
        if(voittaja.equals(Ruutu.X)){
            voittaja=Ruutu.O;
        } else {
            voittaja=Ruutu.X;
        }
    }

    /**
     * Lisää x koordinaatit toStringgiin
     *
     * @param paluu
     * @return
     */
    private String xKoordinaattiLuvut(String paluu) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < koko; i++) {
            String lisattava = i + "";
            lisattava = String.format("%3s", lisattava);
            builder.append(lisattava);
        }
        paluu+=builder.toString();
        return paluu;
    }

    /**
     * Tallentaa pelin tiedostoon RistinollaSave.
     */
    public void tallenna() {
        FileWriter kirjoittaja = null;
        if (voitontestaaja == null) {
            throw new IllegalStateException("Ei voitontestaajaa");
        }
        try {
            kirjoittaja = new FileWriter("RistinollaSave");
            kirjoittaja.write(koko + "," + voitontestaaja.getRivinPituus() + "\n");
            for (int y = 0; y < koko; y++) {
                for (int x = 0; x < koko; x++) {
                    kirjoittaja.append(getRuutu(x, y).toString().charAt(0));
                }
                kirjoittaja.append('\n');
                kirjoittaja.flush();
            }
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(Pelilauta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                kirjoittaja.close();
            } catch (IOException ex) {
                Logger.getLogger(Pelilauta.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    /**
     * Lataa tallennetun pelin.
     */
    public void lataa() {
        File tallennus = new File("RistinollaSave");
        if (voitontestaaja == null) {
            throw new IllegalStateException("Ei voitontestaajaa");
        }
        try {
            Scanner lukija = new Scanner(tallennus);
            String rivi = lukija.nextLine();
            String[] split = rivi.split(",");
            int rivinro = 0;
            setKoko(Integer.parseInt(split[0]));
            voitontestaaja.setRivinPituus(Integer.parseInt(split[1]));
            while (lukija.hasNextLine()) {
                rivi = lukija.nextLine();
                muutaRivi(rivi, rivinro);
                rivinro++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pelilauta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muuttaa laudan ruutuja tallennuksesta luetun rivin ja annetun rivinumeron
     * mukaan.
     *
     * @param rivi scannerilla otettu rivi tallennustiedostosta.
     * @param rivinro
     */
    private void muutaRivi(String rivi, int rivinro) {
        char[] toCharArray = rivi.toCharArray();
        for (int a = 0; a < toCharArray.length; a++) {
            switch (toCharArray[a]) {
                case ('X'):
                    muutaRuutu(a, rivinro, Ruutu.X);
                    break;
                case ('O'):
                    muutaRuutu(a, rivinro, Ruutu.O);
                    break;
                case ('_'):
                    muutaRuutu(a, rivinro, Ruutu._);
                    break;
                default:
                    System.out.println("Jotain vikaa tallennuksessa");
            }
        }
    }
}
