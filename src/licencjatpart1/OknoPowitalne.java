/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author shpaq
 */
public class OknoPowitalne extends JFrame
{

    private WczytajObr wo;
    private String sciezka;
    private int rozmiar;
    private DropTargetListener dtl;
    private PopUpWindow puw;

    private String typ = "";
    private boolean warunek = false;
    private JLabel napis[] = new JLabel[3];
    private JPanel pom;

    public OknoPowitalne()
    {
        super("WPO - Michał Szpak");

        wo = new WczytajObr();
        puw = new PopUpWindow(this.getLocation().x, this.getLocation().y);

        this.uzupelnij();
        this.setPreferredSize(new Dimension(700, 450));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.black);
        this.add(pom, BorderLayout.SOUTH);
        this.add(napis[0], BorderLayout.NORTH);
        this.add(napis[1], BorderLayout.CENTER);

        this.setAlwaysOnTop(true);
        this.wczytaj_obraz();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void wczytaj_obraz()
    {
        JLabel pom = new JLabel();
        DropTarget DT = new DropTarget();
        try
        {
            DT.setComponent(wo);
            DT.addDropTargetListener(this.dtl);

        } catch (Exception e)
        {
        }
    }

    private void uzupelnij()
    {
        this.dtl = new DropTargetAdapter()
        {

            @Override
            public void drop(DropTargetDropEvent event)
            {

                event.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transfer = event.getTransferable();
                DataFlavor[] flavors = transfer.getTransferDataFlavors();
                try
                {
                    List< File> files2 = (List) transfer.getTransferData(flavors[0]);
                    rozmiar = files2.size();

                    if (rozmiar == 1)
                    {
                        System.out.println("Sciezka pliku: " + files2.get(0).getPath());
                        sciezka = files2.get(0).getPath();
                        Pattern p = Pattern.compile(".jpg");
                        Matcher m = p.matcher(sciezka);

                        if (m.find())
                        {

                            ObrazekPanel sp = new ObrazekPanel(sciezka);
                            int rg[] = sp.pobierz_rozmiar();
                            puw.nazwa("<html><div align = center>OBRAZ TYPU: <font color=white>" + sp.get_type_wejsciowy() + "</font><br>O WYMIARACH: <font color=white>" + rg[0] + "x" + rg[1] + "</font><br>ZOSTAŁ POPRAWNIE WCZYTANY</div></html>");
                            typ = sp.get_type_wejsciowy();

                            warunek = true;
                            puw.setLocation(getLocation().x + (int) getSize().getWidth() / 2 - 125, getLocation().y + (int) getSize().getHeight() / 2 - 100);
                            puw.wlacz();

                        } else
                        {
                            puw.nazwa("<html><div align = center>ZŁE ROZSZERZENIE PLIKU<br>POPRAW</div></html>");
                            puw.setLocation(getLocation().x + (int) getSize().getWidth() / 2 - 125, getLocation().y + (int) getSize().getHeight() / 2 - 100);
                            puw.wlacz();
                        }

                    } else
                    {
                        puw.nazwa("<html><div align = center>WCZYTANO ZA DUŻO PLIKÓW<br>POPRAW</div></html>");
                        puw.setLocation(getLocation().x + (int) getSize().getWidth() / 2 - 125, getLocation().y + (int) getSize().getHeight() / 2 - 100);
                        puw.wlacz();

                    }
                    wylacz_ramke();
                } catch (Exception e)
                {
                    System.out.println(e);
                }

            }
        };
        this.napis[0] = new JLabel("<html><br><div align = center>WPROWADZENIE DO PRZETWARZANIA OBRAZU</div></html>", SwingConstants.CENTER);
        this.napis[0].setFont(new Font("Dialog", Font.ITALIC + Font.BOLD, 28));
        this.napis[0].setForeground(Color.red);

        this.napis[1] = new JLabel("<html><br><br><br><div align = center>Autor: Michał Szpak<br>Numer indekru: 95258<br>Prowadzący: dr Jakub Gąsior</html>", SwingConstants.CENTER);
        this.napis[1].setFont(new Font("ZapfDingbats", Font.PLAIN, 14));
        this.napis[1].setForeground(Color.blue);

        this.pom = new JPanel();
        this.pom.setLayout(new GridLayout(0, 1));
        this.pom.setBackground(Color.black);

        this.napis[2] = new JLabel("<html><div align = center>Przeciągnij obraz poniżej aby rozpocząć</html>", SwingConstants.CENTER);
        this.napis[2].setFont(new Font("ZapfDingbats", Font.PLAIN, 10));
        this.napis[2].setForeground(Color.green);

        this.pom.add(napis[2]);
        this.pom.add(wo);

        this.puw.guzik.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (!warunek)
                {
                    puw.wylacz();
                    wlacz_ramke();

                } else
                {
                    puw.wylacz();
                    setVisible(false);

                    dispose();
                    new OknoObraz(sciezka, typ);
                }

            }
        });
        this.puw.guzik.addKeyListener(new KeyAdapter()
        {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (!warunek)
                    {
                        puw.wylacz();
                        wlacz_ramke();

                    } else
                    {
                        puw.wylacz();
                        setVisible(false);

                        dispose();
                        new OknoObraz(sciezka, typ);
                    }
                }
            }

        });

    }

    private void wylacz_ramke()
    {
        this.setEnabled(false);
    }

    private void wlacz_ramke()
    {
        this.setEnabled(true);
        this.toFront();
    }
}
