/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ristinolla.Kayttoliittyma;
import ristinolla.Pelilauta;
import ristinolla.Ruutu;
import ristinolla.Tekstikayttoliittyma;
import ristinolla.Voitontestaaja;

/**
 *
 * @author moversti
 */
public class Voittotesti {

    public Voittotesti() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void kokoToimii() {
        Pelilauta p = new Pelilauta(3);
        assertEquals(3, p.getKoko());
        Pelilauta p2 = new Pelilauta(8);
        assertEquals(8, p2.getKoko());
    }

    @Test
    public void voittorivitToimii() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja t = new Voitontestaaja(l, 3);
        assertFalse(t.testaa());
        l.muutaRuutu(0, 0, Ruutu.X);
        l.muutaRuutu(0, 1, Ruutu.X);
        l.muutaRuutu(0, 2, Ruutu.X);
        assertTrue(t.testaa());
        l.muutaRuutu(0, 2, Ruutu._);
        assertFalse(t.testaa());
        l.muutaRuutu(1, 0, Ruutu.X);
        l.muutaRuutu(2, 0, Ruutu.X);
        assertTrue(t.testaa());
        l.muutaRuutu(2, 0, Ruutu._);
        assertFalse(t.testaa());
        l.muutaRuutu(1, 1, Ruutu.X);
        l.muutaRuutu(2, 2, Ruutu.X);
        assertTrue(t.testaa());
        l.muutaRuutu(2, 2, Ruutu._);
        assertFalse(t.testaa());
        l.muutaRuutu(5, 5, Ruutu.X);
        l.muutaRuutu(6, 4, Ruutu.X);
        l.muutaRuutu(7, 3, Ruutu.X);
        assertTrue(t.testaa());
    }

    @Test
    public void undoToimii() {
        Pelilauta l = new Pelilauta();
        l.muutaRuutu(2, 3, Ruutu.X);
        assertEquals(Ruutu.X, l.getRuutu(2, 3));
        l.undo();
        assertEquals(Ruutu._, l.getRuutu(2, 3));
    }

    @Test
    public void tallennusJaLatausToimii() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja vt = new Voitontestaaja(l, 3);
        l.setVoitontestaaja(vt);
        l.muutaRuutu(2, 3, Ruutu.X);
        l.tallenna();
        Pelilauta k = new Pelilauta();
        Voitontestaaja vt2 = new Voitontestaaja(k, 10);
        k.setVoitontestaaja(vt2);
        k.lataa();
        assertEquals(Ruutu.X, k.getRuutu(2, 3));
        assertEquals(3, vt2.getRivinPituus());
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
