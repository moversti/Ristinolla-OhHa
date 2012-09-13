package ristinolla;

/**
 * Graaffinen käyttöliittymä
 *
 * @author moversti
 */
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Gui implements Kayttoliittyma, Runnable {

    private JFrame frame;
    private Pelilauta pl;
    private Voitontestaaja vt;

    public Gui(Pelilauta pelilauta, int rivinPituus) {
        vt = new Voitontestaaja(pelilauta, rivinPituus);
        this.pl = pelilauta;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {

        frame = new JFrame("Ristinolla");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
    }

    public JFrame getFrame() {
        return frame;
    }
}
