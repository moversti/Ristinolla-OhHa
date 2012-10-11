package ristinolla.logiikka;

import static org.junit.Assert.*;
import org.junit.Test;
import ristinolla.logiikka.Pelilauta;
import ristinolla.logiikka.Ruutu;
import ristinolla.logiikka.Voitontestaaja;

/**
 *
 * @author moversti
 */
public class PelilautaTest {
    
    /**
     * Testaa undoamisen toimivuuden.
     */
    @Test
    public void undoTest() {
        Pelilauta p = new Pelilauta();
        Voitontestaaja vt = new Voitontestaaja(p, 3);
        p.setVoitontestaaja(vt);
        assertEquals(Ruutu._, p.getRuutu(3, 5));
        p.muutaRuutu(3, 5, Ruutu.X);
        assertEquals(Ruutu.X, p.getRuutu(3, 5));
        p.undo();
        assertEquals(Ruutu._, p.getRuutu(3, 5));
    }

    /**
     * Testaa tallennuksen ja latauksen toimivuuden.
     */
    @Test
    public void tallennusJaLatausTest() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja vt = new Voitontestaaja(l, 3);
        l.setVoitontestaaja(vt);
        l.muutaRuutu(2, 3, Ruutu.X);
        l.tallenna();
        Pelilauta k = new Pelilauta(10);
        Voitontestaaja vt2 = new Voitontestaaja(k, 10);
        k.setVoitontestaaja(vt2);
        k.lataa();
        assertEquals(Ruutu.X, k.getRuutu(2, 3));
        assertEquals(3, vt2.getRivinPituus());
        assertEquals(20, l.getKoko());
    }
}
