package ristinolla;

/**
 * Graaffinen käyttöliittymä
 *
 * @author moversti
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class Gui implements Kayttoliittyma, Runnable {

    private JFrame frame;
    private Pelilauta pl;
    private Voitontestaaja vt;

    public Gui(Pelilauta pelilauta, Voitontestaaja vt) {
        this.vt = vt;
        this.pl = pelilauta;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {

        frame = new JFrame("Ristinolla");
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        JPanel lauta = new JPanel();
        lauta.setLayout(new GridLayout(pl.getKoko(), pl.getKoko()));
        for (int i = 0; i < pl.getKoko(); i++) {
            for (int j = 0; j < pl.getKoko(); j++) {
                JButton jb = new JButton();
                lauta.add(jb);
            }
        }
        lauta.setVisible(true);
        container.add(lauta,BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }
}
