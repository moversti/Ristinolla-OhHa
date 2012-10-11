package ristinolla.logiikka;

/**
 * Testaa pelilaudalta voittorivejä.
 *
 * @author moversti
 */
public class Voitontestaaja {

    private Pelilauta lauta;
    private int rivinPituus;

    /**
     *
     * @param lauta
     * @param rivinPituus voittorivin pituus.
     */
    public Voitontestaaja(Pelilauta lauta, int rivinPituus) {
        this.lauta = lauta;
        this.rivinPituus = rivinPituus;
    }

    /**
     * 
     * @return rivin pituus.
     */
    public int getRivinPituus() {
        return rivinPituus;
    }

    /**
     * 
     * @param rivinPituus 
     */
    public void setRivinPituus(int rivinPituus) {
        this.rivinPituus = rivinPituus;
    }

    /**
     * Testaa voittorivit laudalta.
     *
     * @return true jos voittorivi löytyi.
     */
    public boolean testaa() {
        return (testaaLaskevaVino() || testaaNousevaVino() || testaaPysty() || testaaVaaka());
    }

    /**
     * testaa vaakarivit.
     */
    private boolean testaaVaaka() {
        for (int k = 0; k < lauta.getKoko(); k++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int l = 0; l < lauta.getKoko(); l++) {
                Ruutu ruutu = lauta.getRuutu(l, k);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;
                }
            }
        }
        return false;
    }

    /**
     * testaa pystyrivit.
     */
    private boolean testaaPysty() {
        for (int leveys = 0; leveys < lauta.getKoko(); leveys++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int korkeus = 0; korkeus < lauta.getKoko(); korkeus++) {
                Ruutu ruutu = lauta.getRuutu(leveys, korkeus);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;
                }
            }
        }
        return false;
    }

    /**
     * testaa laskevaan vinoon menevät rivit.
     */
    private boolean testaaLaskevaVino() {
        for (int rivi = 0; rivi < lauta.getKoko(); rivi++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            int x = 0;
            int y = rivi;
            while (y >= 0) {
                Ruutu ruutu = lauta.getRuutu(x, y);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;

                }
                y--;
                x++;
            }
        }
        for (int sarake = 0; sarake < lauta.getKoko(); sarake++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            int x = sarake;
            int y = lauta.getKoko() - 1;
            while (x < lauta.getKoko()) {
                Ruutu ruutu = lauta.getRuutu(x, y);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;

                }
                y--;
                x++;
            }
        }
        return false;
    }

    /**
     * testaa nousevaan vinoon menevät rivit.
     */
    private boolean testaaNousevaVino() {
        for (int rivi = 0; rivi < lauta.getKoko(); rivi++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            int x = 0;
            int y = rivi;
            while (y < lauta.getKoko()) {
                Ruutu ruutu = lauta.getRuutu(x, y);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;

                }
                y++;
                x++;
            }
        }
        for (int sarake = 0; sarake < lauta.getKoko(); sarake++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            int x = sarake;
            int y = 0;
            while (x < lauta.getKoko()) {
                Ruutu ruutu = lauta.getRuutu(x, y);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus - 1) {
                        return true;
                    }
                } else {
                    laskuri = 0;
                    edellinen = ruutu;

                }
                y++;
                x++;
            }
        }
        return false;
    }
}
