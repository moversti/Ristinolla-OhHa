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
        this.rivinPituus = rivinPituus-1;
    }

    public boolean testaa() {
        return (testaaLaskevaVino() || testaaNousevaVino() || testaaPysty() || testaaVaaka());
    }

    private boolean testaaVaaka() {
        for (int k = 0; k < lauta.getKorkeus(); k++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int l = 0; l < lauta.getLeveys(); l++) {
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
        for (int leveys = 0; leveys < lauta.getLeveys(); leveys++) {
            int laskuri = 0;
            Ruutu edellinen = Ruutu._;
            for (int korkeus = 0; korkeus < lauta.getKorkeus(); korkeus++) {
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
        return false;
    }

    private boolean testaaNousevaVino() {
        return false;
    }
}
