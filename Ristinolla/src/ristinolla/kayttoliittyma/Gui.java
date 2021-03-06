package ristinolla.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import ristinolla.logiikka.Pelilauta;
import ristinolla.logiikka.Voitontestaaja;

/**
 * Graaffinen käyttöliittymä
 * @author arvoitusmies
 */
public class Gui implements Kayttoliittyma, Runnable {

    private JFrame frame;
    private Pelilauta pelilauta;
    private Voitontestaaja voitontestaaja;

    /**
     * Ihan perus konstruktori joka laittaa parametrit omiin kenttiinsä
     * @param pelilauta
     * @param voitontestaaja
     */
    public Gui() {
        //Tähän kutsu metodiin joka kysyy asetukset
        //ponnahdusikkunalla ja tekee pelilauta ja voitontestaaja oliot
    }

    /**
     * Runnaa itsensä.
     */
    @Override
    public void start() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    /**
     * Luo ikkunan yms perusjutut.
     */
    public void run() {

        frame = new JFrame("Ristinolla");
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo frameen komponentit.
     * @param container
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        JPanel lauta = new JPanel();
        lauta.setLayout(new GridLayout(pelilauta.getKoko(), pelilauta.getKoko()));
        for (int i = 0; i < pelilauta.getKoko(); i++) {
            for (int j = 0; j < pelilauta.getKoko(); j++) {
                JButton jb = new JButton();
                lauta.add(jb);
            }
        }
        lauta.setVisible(true);
        container.add(lauta,BorderLayout.CENTER);
    }

    /**
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
