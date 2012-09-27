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
public class VoitontestaajaTest {

    public VoitontestaajaTest() {
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
    public void pystyriviTest() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja t = new Voitontestaaja(l, 3);
        assertFalse(t.testaa());
        l.muutaRuutu(0, 0, Ruutu.X);
        l.muutaRuutu(0, 1, Ruutu.X);
        l.muutaRuutu(0, 2, Ruutu.X);
        assertTrue(t.testaa());
    }
    
    @Test
    public void vaakariviTest() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja t = new Voitontestaaja(l, 3);
        assertFalse(t.testaa());
        l.muutaRuutu(0, 0, Ruutu.X);
        l.muutaRuutu(1, 0, Ruutu.X);
        l.muutaRuutu(2, 0, Ruutu.X);
        assertTrue(t.testaa());
    }

    @Test
    public void nousevaVinoriviTest() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja t = new Voitontestaaja(l, 3);
        assertFalse(t.testaa());
        l.muutaRuutu(0, 0, Ruutu.X);
        l.muutaRuutu(1, 1, Ruutu.X);
        l.muutaRuutu(2, 2, Ruutu.X);
        assertTrue(t.testaa());
    }
    
    @Test
    public void laskevaVinoriviTest() {
        Pelilauta l = new Pelilauta();
        Voitontestaaja t = new Voitontestaaja(l, 3);
        assertFalse(t.testaa());
        l.muutaRuutu(5, 5, Ruutu.X);
        l.muutaRuutu(6, 4, Ruutu.X);
        l.muutaRuutu(7, 3, Ruutu.X);
        assertTrue(t.testaa());
    }
}
