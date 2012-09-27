package ristinolla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pelilaudalla on listoja joilla on tilana tyhjä, risti tai nolla.
 *
 * @author moversti
 */
public class Pelilauta {

    public boolean isVoittajaSelvilla() {
        return voittajaSelvilla;
    }

    public void setVoittajaSelvilla(boolean voittajaSelvilla) {
        this.voittajaSelvilla = voittajaSelvilla;
    }
    /**
     * Pelilaudan 2d ruudukko ruuduille, laudan koko ja undoamisen mahdollistava
     * siirto-olio.
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
     * @param korkeus laudan korkeus
     */
    public Pelilauta(int koko) {
        ruudut = new Ruutu[koko][koko];
        this.koko = koko;
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                ruudut[i][j] = Ruutu._;
            }
        }
        undo = new Siirto(0, 0, Ruutu._);
        voittajaSelvilla = false;
    }

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
     */
    public boolean muutaRuutu(int x, int y, Ruutu ruutu) {
        if(x<0||y<0||x>=getKoko()||y>=getKoko()){
            return false;
        }
        asetetaanUndo(new Siirto(x, y, getRuutu(x, y)));
        ruudut[y][x] = ruutu;
        return true;
    }

    public void setVoittaja(Ruutu voittaja) {
        this.voittaja = voittaja;
    }

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
            for (int j = 0; j < koko; j++) {
                paluu += "  " + ruudut[i][j];
            }
            paluu += " " + i;
            paluu += "\n";
        }
        paluu += "   ";
        paluu = xKoordinaattiLuvut(paluu);
        return paluu;
    }

    /**
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
        ruudut[undo.y][undo.x] = undo.ruutu;
    }

    /**
     * Lisää x koordinaatit toStringgiin
     *
     * @param paluu
     * @return
     */
    private String xKoordinaattiLuvut(String paluu) {
        for (int i = 0; i < koko; i++) {
            String lisattava = i + "";
            lisattava = String.format("%3s", lisattava);
            paluu += lisattava;
        }
        return paluu;
    }

    /**
     * Tallentaa pelin.
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
            try (Scanner lukija = new Scanner(tallennus)) {
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
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pelilauta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            }
        }
    }
}
