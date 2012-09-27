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
import ristinolla.Pelilauta;
import ristinolla.Ruutu;
import ristinolla.Voitontestaaja;

/**
 *
 * @author moversti
 */
public class PelilautaTest {

    public PelilautaTest() {
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

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

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
