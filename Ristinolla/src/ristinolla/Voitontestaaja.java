package ristinolla;

/**
 * Testaa pelilaudalta voittorivejä.
 *
 * @author moversti
 */
public class Voitontestaaja {

    private Pelilauta lauta;
    private int rivinPituus;

    public Voitontestaaja(Pelilauta lauta, int rivinPituus) {
        this.lauta = lauta;
        this.rivinPituus = rivinPituus - 1;
    }

    public boolean testaa() {
        return (testaaLaskevaVino() || testaaNousevaVino() || testaaPysty() || testaaVaaka());
    }

    private boolean testaaVaaka() {
        for (int k = 0; k < lauta.getKoko(); k++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int l = 0; l < lauta.getKoko(); l++) {
                Ruutu ruutu = lauta.getRuutu(l, k);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus) {
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

    private boolean testaaPysty() {
        for (int leveys = 0; leveys < lauta.getKoko(); leveys++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int korkeus = 0; korkeus < lauta.getKoko(); korkeus++) {
                Ruutu ruutu = lauta.getRuutu(leveys, korkeus);
                if (ruutu == edellinen && ruutu != Ruutu._) {
                    laskuri++;
                    if (laskuri >= rivinPituus) {
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
                    if (laskuri >= rivinPituus) {
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
                    if (laskuri >= rivinPituus) {
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
                    if (laskuri >= rivinPituus) {
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
                    if (laskuri >= rivinPituus) {
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
