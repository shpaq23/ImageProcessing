/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.java2d.loops.DrawRect;

/**
 *
 * @author shpaq
 */
public class OknoObraz extends JFrame implements ComponentListener
{

    private ObrazekPanel obrP;

    private OperacjeArytmetyczneOkno oa = new OperacjeArytmetyczneOkno();
    private OperacjeGeometryczneOkno og = new OperacjeGeometryczneOkno();
    private OperacjeHistogramOkno oh = new OperacjeHistogramOkno();
    private OperacjeFiltryOkno of = new OperacjeFiltryOkno();
    private OperacjeMorfologiczneOkno om = new OperacjeMorfologiczneOkno();
    private JScrollPane jsp;
    private int[] rozmiar = new int[2];
    private String typ;

    static private RamkaTyp rt;

    public OknoObraz(String sciezka, String typ)
    {
        super(sciezka);
        this.typ = typ;
        this.uzupelnij(sciezka);

        this.typ = obrP.get_type_operacyjny();

        OperacjeArytmetyczne.typ = this.typ;
        OperacjeHistogram.typ = this.typ;

        this.setPreferredSize(new Dimension(rozmiar[0] + 60, rozmiar[1] + 80));
        // this.setMaximumSize(new Dimension(rozmiar[0] + 60 - 20, rozmiar[1] + 80 - 20));

        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(jsp, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);

    }

    private void uzupelnij(String s)
    {
        this.obrP = new ObrazekPanel(s);

        this.rozmiar = this.obrP.pobierz_rozmiar();
        this.jsp = new JScrollPane(obrP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.rt = new RamkaTyp();
        this.addComponentListener(this);

        this.addWindowStateListener(new WindowAdapter()
        {

            @Override
            public void windowStateChanged(WindowEvent we)
            {
                if (we.getNewState() == Frame.ICONIFIED)
                {
                    rt.setState(Frame.ICONIFIED);

                    if (oa.isEnabled())
                    {
                        oa.setState(Frame.ICONIFIED);
                    }
                    if (og.isEnabled())
                    {
                        og.setState(Frame.ICONIFIED);
                    }
                    if (oh.isEnabled())
                    {
                        oh.setState(Frame.ICONIFIED);
                    }
                    if (of.isEnabled())
                    {
                        of.setState(Frame.ICONIFIED);
                    }
                    if (om.isEnabled())
                    {
                        om.setState(Frame.ICONIFIED);
                    }

                }
                if (we.getNewState() == Frame.NORMAL)
                {
                    rt.setState(Frame.NORMAL);
                    if (oa.isEnabled())
                    {
                        oa.setState(Frame.NORMAL);
                    }
                    if (og.isEnabled())
                    {
                        og.setState(Frame.NORMAL);
                    }
                    if (oh.isEnabled())
                    {
                        oh.setState(Frame.NORMAL);
                    }
                    if (of.isEnabled())
                    {
                        of.setState(Frame.NORMAL);
                    }
                    if (om.isEnabled())
                    {
                        om.setState(Frame.NORMAL);
                    }

                }

            }
        });

    }

    public void wygas()
    {
        this.setEnabled(false);
    }

    public void wlacz()
    {
        this.setEnabled(true);
        toFront();
    }

    public int[] wez_lokacje()
    {
        int tab[] = new int[2];

        tab[0] = this.getLocation().x + (int) this.getSize().getWidth() / 2 - 125;
        tab[1] = this.getLocation().y + (int) this.getSize().getHeight() / 2 - 100;
        return tab;
    }

    public void minimalizuj()
    {
        this.setState(Frame.ICONIFIED);
    }

    public void maksymalizuj()
    {
        this.setState(Frame.NORMAL);
    }

    public void zawsze_na_wierzchu_on()
    {
        this.setAlwaysOnTop(true);
    }

    public void zawsze_na_wierzchu_off()
    {
        this.setAlwaysOnTop(false);
    }

//    public void zmien_rozmiar()
//    {
//        this.rozmiar = obrP.pobierz_rozmiar();
//        this.setPreferredSize(new Dimension(rozmiar[0] + 60, rozmiar[1] + 80));
//        this.setMaximumSize(new Dimension(rozmiar[0] + 60 - 20, rozmiar[1] + 80 - 20));
//        this.repaint();
//    }
    @Override
    public void componentResized(ComponentEvent e)
    {

        rt.zmien_lokacje(this.getLocation().x + this.getSize().width, this.getLocation().y);
        //  toFront();
        // rt.setFocusable(true);

    }

    @Override
    public void componentMoved(ComponentEvent e)
    {

        rt.zmien_lokacje(this.getLocation().x + this.getSize().width, this.getLocation().y);
        //  toFront();
        // rt.setFocusable(true);

    }

    @Override
    public void componentShown(ComponentEvent e)
    {

    }

    @Override
    public void componentHidden(ComponentEvent e)
    {

    }

    private class RamkaTyp extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[6];
        private JPanel pom;
        private JButton zapisz;
        private JButton cofnij;

        public RamkaTyp()
        {

            this.uzupelnij();
            this.setUndecorated(true);

            this.setPreferredSize(new Dimension(200, 600));
            this.getContentPane().setBackground(new Color(64, 64, 64));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);

        }

        private void uzupelnij()
        {

            this.zapisz = new JButton("ZAPISZ");
            this.zapisz.setBackground(new Color(128, 128, 128));
            this.zapisz.setForeground(Color.WHITE);
            this.zapisz.setFocusPainted(false);
            this.zapisz.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.zapisz.setPreferredSize(new Dimension(100, 37));
            this.zapisz.addMouseListener(this);

            this.cofnij = new JButton("COFNIJ");
            this.cofnij.setBackground(new Color(128, 128, 128));
            this.cofnij.setForeground(Color.WHITE);
            this.cofnij.setFocusPainted(false);
            this.cofnij.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.cofnij.setPreferredSize(new Dimension(100, 37));
            this.cofnij.addMouseListener(this);

            this.pom = new JPanel();
            //  this.pom.setLayout(new BoxLayout(this.pom, BoxLayout.PAGE_AXIS));
            // this.pom.setLayout(new GridBagLayout());
            this.pom.setLayout(new GridLayout(0, 1));
            this.pom.setBackground(new Color(64, 64, 64));

            JLabel odstepy[] = new JLabel[14];
            for (int a = 0; a < odstepy.length; a++)
            {
                odstepy[a] = new JLabel("<html><br></html>");
            }

            JPanel pom1 = new JPanel();
            pom1 = new JPanel();
            pom1.setLayout(new GridBagLayout());
            pom1.setBackground(new Color(64, 64, 64));
            pom1.add(this.zapisz);
            pom1.add(this.cofnij);

            JPanel pom2 = new JPanel();
            pom2.setLayout(new BorderLayout());
            pom2.setBackground(new Color(64, 64, 64));
            pom2.add(pom1, BorderLayout.SOUTH);

            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.BOLD, 12));

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.CENTER);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
            }

            Matcher m = Pattern.compile("RGB").matcher(typ);
            if (m.find())
            {
                System.out.println("RGB");
                zadania[0].setText("Obraz typu: RGB");
                zadania[1].setText("<html><div align = center>OPERACJE ARYTMETYCZNE</html>");
                zadania[2].setText("<html><div align = center>OPERACJE GEOMETRYCZNE</html>");
                zadania[3].setText("<html><div align = center>OPERACJE NA HISTOGRAMACH</html>");
                zadania[4].setText("<html><div align = center>FILTROWANIE</html>");

                this.pom.add(zadania[0]);

                this.pom.add(zadania[1]);
                this.pom.add(odstepy[1]);

                this.pom.add(zadania[2]);
                this.pom.add(odstepy[2]);

                this.pom.add(zadania[3]);
                this.pom.add(odstepy[3]);

                this.pom.add(zadania[4]);
                this.pom.add(odstepy[0]);

                this.pom.add(pom2);

            }
            m = Pattern.compile("Binary").matcher(typ);
            if (m.find())
            {
                System.out.println("Binary");
                zadania[0].setText("Obraz typu: Binary");
                zadania[2].setText("<html><div align = center>OPERACJE GEOMETRYCZNE</html>");
                zadania[5].setText("<html><div align = center>OPERACJE MORFOLOGICZNE</html>");
                this.pom.add(zadania[0]);
                //this.pom.add(odstepy[8]);

                this.pom.add(zadania[2]);
                this.pom.add(odstepy[2]);

                this.pom.add(zadania[5]);
                this.pom.add(odstepy[4]);
                this.pom.add(odstepy[5]);
                this.pom.add(odstepy[6]);
                //   this.pom.add(odstepy[7]);

                this.pom.add(pom2);

            }

            m = Pattern.compile("GrayScale").matcher(typ);
            if (m.find())
            {
                System.out.println("GrayScale");
                zadania[0].setText("Obraz typu: GrayScale");
                zadania[1].setText("<html><div align = center>OPERACJE ARYTMETYCZNE</html>");
                zadania[2].setText("<html><div align = center>OPERACJE GEOMETRYCZNE</html>");
                zadania[3].setText("<html><div align = center>OPERACJE NA HISTOGRAMACH</html>");
                zadania[4].setText("<html><div align = center>FILTROWANIE</html>");

                this.pom.add(zadania[0]);

                this.pom.add(zadania[1]);
                this.pom.add(odstepy[1]);

                this.pom.add(zadania[2]);
                this.pom.add(odstepy[2]);

                this.pom.add(zadania[3]);
                this.pom.add(odstepy[3]);

                this.pom.add(zadania[4]);
                this.pom.add(odstepy[0]);

                this.pom.add(pom2);

            }
            this.add(pom, BorderLayout.CENTER);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                    }

                }
            });
            this.addComponentListener(new ComponentAdapter()
            {
                public void componentMoved(ComponentEvent e)
                {
                    if (oa.isEnabled())
                    {
                        oa.zmien_lokacje(getLocation().x - 44, getLocation().y);

                        oa.toFront();
                    }
                    if (og.isEnabled())
                    {
                        og.zmien_lokacje(getLocation().x - 44, getLocation().y);

                        og.toFront();
                    }
                    if (oh.isEnabled())
                    {
                        oh.zmien_lokacje(getLocation().x - 44, getLocation().y);

                        oh.toFront();
                    }
                    if (of.isEnabled())
                    {
                        of.zmien_lokacje(getLocation().x - 44, getLocation().y);

                        of.toFront();
                    }
                    if (om.isEnabled())
                    {
                        om.zmien_lokacje(getLocation().x - 44, getLocation().y);

                        om.toFront();
                    }

                }
            });

        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == this.zapisz)
            {
                zawsze_na_wierzchu_off();
                obrP.zapisz_obrazek();
                zawsze_na_wierzchu_on();

            }
            if (e.getSource() == this.zadania[1])
            {
                oa = new OperacjeArytmetyczneOkno(getLocation().x - 44, getLocation().y);
                this.setVisible(false);
                this.setEnabled(false);

            }
            if (e.getSource() == this.zadania[2])
            {
                og = new OperacjeGeometryczneOkno(getLocation().x - 44, getLocation().y);
                this.setVisible(false);
                this.setEnabled(false);

            }
            if (e.getSource() == this.zadania[3])
            {
                oh = new OperacjeHistogramOkno(getLocation().x - 44, getLocation().y);
                this.setVisible(false);
                this.setEnabled(false);
            }
            if (e.getSource() == this.zadania[4])
            {
                of = new OperacjeFiltryOkno(getLocation().x - 44, getLocation().y);
                this.setVisible(false);
                this.setEnabled(false);
            }
            if (e.getSource() == this.zadania[5])
            {
                om = new OperacjeMorfologiczneOkno(getLocation().x - 44, getLocation().y);
                this.setVisible(false);
                this.setEnabled(false);
            }
            if (e.getSource() == this.cofnij)
            {
                obrP.cofnij_zmiany();
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }

    }

    private class OperacjeArytmetyczneOkno extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[11];
        private JButton wroc;
        private JButton normalizuj;
        private String sciezka2;
        private ObrazekPanel obrazekWczytany;
        private boolean warunek_pom = false;
        private OpcjePanel opcjePanel;
        private int[] tabLokacja;
        private DropTarget DT;
        private JLabel mieszanie;
        private boolean mieszanieWar = false;

        public OperacjeArytmetyczneOkno(int x, int y)
        {
            this.uzupelnij();
            this.setUndecorated(true);

            OperacjeArytmetyczne.typ = typ;
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setPreferredSize(new Dimension(200, 650));
            this.getContentPane().setBackground(new Color(64, 64, 64));

            this.zmien_lokacje(x, y);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);

        }

        public OperacjeArytmetyczneOkno()
        {
            this.setEnabled(false);
            this.setVisible(false);
        }

        public void uzupelnij()
        {
            JPanel pom = new JPanel();
            pom = new JPanel();
            pom.setLayout(new GridLayout(0, 1));
            pom.setBackground(new Color(64, 64, 64));

            JPanel pom2 = new JPanel();
            pom2.setBackground(new Color(64, 64, 64));
            pom2.setLayout(new BorderLayout());

            JPanel pom3 = new JPanel();
            pom3.setBackground(new Color(64, 64, 64));
            pom3.setLayout(new BorderLayout());

            this.wroc = new JButton("WRÓĆ");
            this.wroc.setBackground(new Color(128, 128, 128));
            this.wroc.setForeground(Color.WHITE);
            this.wroc.setFocusPainted(false);
            this.wroc.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.wroc.setMaximumSize(new Dimension(200, 10));
            this.wroc.addMouseListener(this);

            this.normalizuj = new JButton("NORMALIZUJ");
            this.normalizuj.setBackground(new Color(128, 128, 128));
            this.normalizuj.setForeground(Color.WHITE);
            this.normalizuj.setFocusPainted(false);
            this.normalizuj.setFont(new Font("Tahoma", Font.BOLD, 12));

            this.normalizuj.setMaximumSize(new Dimension(200, 10));
            this.normalizuj.addMouseListener(this);

            pom2.add(this.normalizuj, BorderLayout.CENTER);
            pom3.add(this.wroc, BorderLayout.CENTER);
            JLabel enter[] = new JLabel[11];
            for (int a = 0; a < enter.length; a++)
            {
                enter[a] = new JLabel("<html></html>");
            }
            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 12));
            zadania[0].setText("<html><div align = center>OPERACJE ARYTMETYCZNE</html>");
            pom.add(zadania[0]);
            pom.add(enter[0]);

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.LEFT);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.PLAIN, 16));
                pom.add(zadania[a]);

                pom.add(enter[a]);
            }

            zadania[1].setText("<html><div align = left>- Sumowanie stałej z obrazem</html>");

            zadania[2].setText("<html><div align = left>- Sumowanie dwóch obrazów</html>");

            zadania[3].setText("<html><div align = left>- Mnożenie obrazu przez stałą</html>");

            zadania[4].setText("<html><div align = left>- Mnożenie dwóch obrazów</html>");

            zadania[5].setText("<html><div align = left>- Mieszanie dwóch obrazów</html>");

            zadania[6].setText("<html><div align = left>- Potęgowanie obrazu</html>");

            zadania[7].setText("<html><div align = left>- Dzielenie obrazu przez stałą</html>");

            zadania[8].setText("<html><div align = left>- Dzielenie dwóch obrazów</html>");

            zadania[9].setText("<html><div align = left>- Pierwiastkowanie obrazu</html>");

            zadania[10].setText("<html><div align = left>- Logarytmowanie obrazu</html>");

            pom.add(pom2);
            pom.add(pom3);
            this.add(pom, BorderLayout.CENTER);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                    }

                }
            });

        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        private DropTargetListener Drop_Target_Adapter(DropTargetListener dtl)
        {
            dtl = new DropTargetAdapter()
            {
                @Override
                public void drop(DropTargetDropEvent event)
                {

                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transfer = event.getTransferable();
                    DataFlavor[] flavors = transfer.getTransferDataFlavors();
                    try
                    {
                        int rozmiar = 0;
                        tabLokacja = wez_lokacje();

                        PopUpWindow puw = new PopUpWindow(tabLokacja[0], tabLokacja[1]);
                        List< File> files2 = (List) transfer.getTransferData(flavors[0]);
                        rozmiar = files2.size();

                        if (rozmiar == 1)
                        {
                            System.out.println("Sciezka pliku: " + files2.get(0).getPath());
                            sciezka2 = files2.get(0).getPath();
                            Pattern p = Pattern.compile(".jpg");
                            Matcher m = p.matcher(sciezka2);

                            if (m.find())
                            {

                                obrazekWczytany = new ObrazekPanel(sciezka2);
                                /////////rozmiar glownego obrazka
                                int rg[] = obrP.pobierz_rozmiar();
                                /////////////rozmiar wczytanego obrazka
                                int rw[] = obrazekWczytany.pobierz_rozmiar();
                                if (obrazekWczytany.get_type_operacyjny().equals(typ))
                                {
                                    if (rg[0] == rw[0] && rg[1] == rw[1])
                                    {
                                        puw.nazwa("<html><div align = center>OBRAZ TYPU: <font color=white>" + obrazekWczytany.get_type_operacyjny() + "</font><br>O WYMIARACH: <font color=white>" + rg[0] + "x" + rg[1] + "</font><br>ZOSTAŁ POPRAWNIE WCZYTANY</div></html>");
                                        if (mieszanieWar)
                                        {
                                            mieszanie.setText("<html><div align = center> <font size  = 4><br>USTAW WSP MIESZANIA<br>" + (double) 0 / 100 + "</html>");
                                            mieszanieWar = false;
                                        }
                                        warunek_pom = true;
                                        opcjePanel.ok.setEnabled(true);
                                        opcjePanel.suwak.setEnabled(true);
                                        opcjePanel.suwak.setVisible(true);
                                        DT.setActive(false);

                                    } else
                                    {
                                        puw.nazwa("<html><div align = center>WCZYTANO OBRAZ O WYMIARACH: <font color=white>" + rw[0] + "x" + rw[1] + "</font><br> OCZEKIWANO OBRAZ O WYMIARACH: <font color=white>" + rg[0] + "x" + rg[1] + "</font><br>POPRAW</div></html>");
                                    }
                                } else
                                {
                                    puw.nazwa("<html><div align = center>WCZYTANO OBRAZ TYPU: <font color=white>" + obrazekWczytany.get_type_operacyjny() + "</font><br> OCZEKIWANO OBRAZ TYPU: <font color=white>" + obrP.get_type_operacyjny() + "</font><br>POPRAW</div></html>");
                                }

                                puw.wlacz();

                            } else
                            {
                                puw.nazwa("<html><div align = center>ZŁE ROZSZERZENIE PLIKU<br>POPRAW</div></html>");
                                puw.wlacz();
                            }

                        } else
                        {
                            puw.nazwa("<html><div align = center>WCZYTANO ZA DUŻO PLIKÓW<br>POPRAW</div></html>");
                            puw.wlacz();

                        }

                    } catch (Exception e)
                    {
                        System.out.println(e);
                    }

                }
            };
            return dtl;

        }

        @Override
        public void mouseClicked(MouseEvent e)
        {

            if (e.getSource() == this.wroc)
            {
                this.setVisible(false);
                this.setEnabled(false);
                this.dispose();

                rt.setEnabled(true);
                rt.setVisible(true);

            }
            if (e.getSource() == this.normalizuj)
            {
                obrP.ustaw_tab_raster(OperacjeArytmetyczne.normalizacja_int(obrP.pobierz_tab_raster()));
            }

            //////////////////////////////////
            ////////////////////////////
            ////////SUMOWANIE ZE STALA
            //////////////////////////////////
            /////////////////////////////////
            ///////////////////////
            if (e.getSource() == this.zadania[1])
            {

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4>PODAJ STAŁĄ DO SUMOWANIA<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 155));

                JTextField poleTekstowe = new JTextField()
                {
                    public void processKeyEvent(KeyEvent ev)
                    {
                        char c = ev.getKeyChar();
                        if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || ev.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            super.processKeyEvent(ev);
                        }

                    }
                };
                poleTekstowe.setBackground(new Color(64, 64, 64));
                poleTekstowe.setHorizontalAlignment(SwingConstants.CENTER);
                poleTekstowe.setForeground(Color.white);
                poleTekstowe.setCaretColor(Color.white);

                this.opcjePanel = new OpcjePanel(opis, poleTekstowe);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                poleTekstowe.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("calkowite"))
                            {

                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.sumowanie_ze_stala(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe.getText())));

                                opcjePanel.wylacz();

                                setEnabled(true);

                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        String s = "" + poleTekstowe.getText();
                        System.out.println(s);
                        if (opcjePanel.sprawdz_tekst("calkowite"))
                        {

                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.sumowanie_ze_stala(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe.getText())));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });

                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });

            }

            //////////////////////////////////////
            ////////////////////////////////////////
            /////////////////////////////////////////
            ////////////SUMOWANIE Z DRUGIM OBRAZEM
            ////////////////////////////////////////
            ///////////////////////////////////////
            ////////////////////////////////////
            if (e.getSource() == this.zadania[2])
            {
                DropTargetListener dtl = null;
                this.DT = new DropTarget();
                dtl = Drop_Target_Adapter(dtl);

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>PRZECIĄGNIJ DRUGI OBRAZ<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 100));

                WczytajObr wo = new WczytajObr();
                wo.setBackground(new Color(64, 64, 64));
                this.opcjePanel = new OpcjePanel(wo, opis);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                try
                {
                    DT.setComponent(wo);
                    DT.addDropTargetListener(dtl);

                } catch (Exception ex)
                {

                }

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        if (opcjePanel.ok.isEnabled())
                        {
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.sumowanie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (warunek_pom)
                        {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            {

//                                System.out.println("dupa");
                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.sumowanie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                                warunek_pom = false;

                            }
                        }
                    }
                });

            }
            ///////////////////
            ///////////////////
            ///////////////////////
            ////MNOZENIE ZE STALA
            ///////////////////////////////
            ////////////////////////////////
            ////////////////////////////////
            if (e.getSource() == this.zadania[3])
            {
                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4>PODAJ STAŁĄ DO WYMNOŻENIA<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 155));

                JTextField poleTekstowe = new JTextField()
                {
                    public void processKeyEvent(KeyEvent ev)
                    {
                        char c = ev.getKeyChar();
                        if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || c == '.' || ev.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            super.processKeyEvent(ev);
                        }

                    }
                };
                poleTekstowe.setBackground(new Color(64, 64, 64));
                poleTekstowe.setHorizontalAlignment(SwingConstants.CENTER);
                poleTekstowe.setForeground(Color.white);
                poleTekstowe.setCaretColor(Color.white);

                this.opcjePanel = new OpcjePanel(opis, poleTekstowe);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                poleTekstowe.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("zmiennoprzecinkowe"))
                            {

                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.mnozenie_ze_stala(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe.getText())));

                                opcjePanel.wylacz();

                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        String s = "" + poleTekstowe.getText();
                        System.out.println(s);
                        if (opcjePanel.sprawdz_tekst("zmiennoprzecinkowe"))
                        {

                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.mnozenie_ze_stala(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe.getText())));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });

                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
            }
            ////////////mnożenie dwoch obrazow/////////
            ////////////mnożenie dwoch obrazow/////////
            ////////////mnożenie dwoch obrazow/////////
            ////////////mnożenie dwoch obrazow/////////

            if (e.getSource() == this.zadania[4])
            {
                DropTargetListener dtl = null;
                this.DT = new DropTarget();
                dtl = Drop_Target_Adapter(dtl);

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>PRZECIĄGNIJ DRUGI OBRAZ<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 100));

                WczytajObr wo = new WczytajObr();
                wo.setBackground(new Color(64, 64, 64));
                this.opcjePanel = new OpcjePanel(wo, opis);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                try
                {
                    DT.setComponent(wo);
                    DT.addDropTargetListener(dtl);
                } catch (Exception ex)
                {

                }

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        if (opcjePanel.ok.isEnabled())
                        {
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.mnozenie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (warunek_pom)
                        {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            {

//                                System.out.println("dupa");
                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.mnozenie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                                warunek_pom = false;

                            }
                        }
                    }
                });
            }
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////
            ///////////////MIESZANIE DWOCH OBRAZOW/////////////

            if (e.getSource() == this.zadania[5])
            {
                DropTargetListener dtl = null;
                this.DT = new DropTarget();
                dtl = Drop_Target_Adapter(dtl);

                this.mieszanieWar = true;
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(0, 100, 0);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                this.mieszanie = new JLabel("", SwingConstants.CENTER);

                this.mieszanie.setForeground(new Color(128, 128, 128));
                this.mieszanie.setText("<html><div align = center> <font size  = 4><br>PRZECIĄGNIJ DRUGI OBRAZ<br><br>" + (double) suwak.getValue() / 100 + "</html>");
                this.mieszanie.setPreferredSize(new Dimension(250, 80));

                WczytajObr wo = new WczytajObr();
                wo.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(wo, this.mieszanie, suwak);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);
                this.opcjePanel.suwak.setEnabled(false);
                this.opcjePanel.suwak.setVisible(false);

                try
                {
                    DT.setComponent(wo);
                    DT.addDropTargetListener(dtl);
                } catch (Exception ex)
                {

                }

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        if (opcjePanel.ok.isEnabled())
                        {
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.mieszanie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster(), (double) opcjePanel.suwak.getValue() / 100));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (warunek_pom)
                        {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            {

//                                System.out.println("dupa");
                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.mieszanie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster(), (double) opcjePanel.suwak.getValue() / 100));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                                warunek_pom = false;

                            }
                        }
                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("mieszanie");
                        }
                    }
                });

            }
            ////////////////////////////////////
            //////////////////////////////
            /////////////POTEGOWANIE OBRAZUY/
            /////////////////////////////////
            /////////////////////////////////
            /////////////////////////////////

            if (e.getSource() == this.zadania[6])
            {

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4>PODAJ POTĘGE DO POTĘGOWANIA<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 155));

                JTextField poleTekstowe = new JTextField()
                {
                    public void processKeyEvent(KeyEvent ev)
                    {
                        char c = ev.getKeyChar();
                        if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || ev.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            super.processKeyEvent(ev);
                        }

                    }
                };
                poleTekstowe.setBackground(new Color(64, 64, 64));
                poleTekstowe.setHorizontalAlignment(SwingConstants.CENTER);
                poleTekstowe.setForeground(Color.white);
                poleTekstowe.setCaretColor(Color.white);

                this.opcjePanel = new OpcjePanel(opis, poleTekstowe);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                poleTekstowe.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("calkowite"))
                            {

                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.potegowanie_obrazu(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe.getText())));

                                opcjePanel.wylacz();

                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        String s = "" + poleTekstowe.getText();
                        System.out.println(s);
                        if (opcjePanel.sprawdz_tekst("calkowite"))
                        {

                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.potegowanie_obrazu(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe.getText())));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });

                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });

            }
            //////////////////////
            //////////////////////
            //////////////////////
            //////////////////////
            //////////////////////
            /////////////DZIELENIE OBRAZU ZE STALA
            ///////////////////////////////////
            ///////////////////////////////////
            ///////////////////////////////////
            ///////////////////////////////////

            if (e.getSource() == this.zadania[7])
            {
                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4>PODAJ STAŁĄ DO PODZIELENIA<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 155));

                JTextField poleTekstowe = new JTextField()
                {
                    public void processKeyEvent(KeyEvent ev)
                    {
                        char c = ev.getKeyChar();
                        if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || c == '.' || ev.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            super.processKeyEvent(ev);
                        }

                    }
                };
                poleTekstowe.setBackground(new Color(64, 64, 64));
                poleTekstowe.setHorizontalAlignment(SwingConstants.CENTER);
                poleTekstowe.setForeground(Color.white);
                poleTekstowe.setCaretColor(Color.white);

                this.opcjePanel = new OpcjePanel(opis, poleTekstowe);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                poleTekstowe.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("dzielenie"))
                            {

                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.dzielenie_ze_stala(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe.getText())));

                                opcjePanel.wylacz();

                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        String s = "" + poleTekstowe.getText();
                        System.out.println(s);
                        if (opcjePanel.sprawdz_tekst("dzielenie"))
                        {

                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.dzielenie_ze_stala(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe.getText())));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });

                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
            }
            ////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////
            ///////////////////DZIELENIE OBRAZU PRZEZ INNY OBRAZ////////////
            ////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////
            if (e.getSource() == this.zadania[8])
            {
                DropTargetListener dtl = null;
                this.DT = new DropTarget();
                dtl = Drop_Target_Adapter(dtl);

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>PRZECIĄGNIJ DRUGI OBRAZ<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 100));

                WczytajObr wo = new WczytajObr();
                wo.setBackground(new Color(64, 64, 64));
                this.opcjePanel = new OpcjePanel(wo, opis);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                try
                {
                    DT.setComponent(wo);
                    DT.addDropTargetListener(dtl);
                } catch (Exception ex)
                {

                }

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        if (opcjePanel.ok.isEnabled())
                        {
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.dzielenie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (warunek_pom)
                        {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            {

//                                System.out.println("dupa");
                                obrP.ustaw_tab_raster(OperacjeArytmetyczne.dzielenie_z_drugim_obrazem(obrP.pobierz_tab_raster(), obrazekWczytany.pobierz_tab_raster()));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                                warunek_pom = false;

                            }
                        }
                    }
                });
            }
            ///////////////////////////////////
            //////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////
            /////////////PIERWIASTKOWANIE////////////////////////
            ///////////////////////////////////////////////////
            ////////////////////////////////////////////////
            if (e.getSource() == this.zadania[9])
            {

                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 100, 50);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW POTĘGĘ<br>" + (double) suwak.getValue() / 100 + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeArytmetyczne.pierwiastkowanie_obrazu(obrP.pobierz_tab_raster(), (double) opcjePanel.suwak.getValue() / 100));

                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {

//                                System.out.println("dupa");
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.pierwiastkowanie_obrazu(obrP.pobierz_tab_raster(), (double) opcjePanel.suwak.getValue() / 100));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                            warunek_pom = false;

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("pierwiastkowanie");
                        }
                    }
                });
            }
            ///////////////////////////////////////
            ///////////////////////////////////////
            ///////////////////////////////////////
            ///////////////////////////////////////LOGARYTMOWANIE
            ////////////////////////////////////
            ////////////////////////////////////
            ////////////////////////////////////
            if (e.getSource() == this.zadania[10])
            {

                this.setEnabled(false);
                wygas();
                JLabel opis = new JLabel("", SwingConstants.CENTER);
                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>LOGARYTMOWANIE<br><br><br></html>");
                opis.setPreferredSize(new Dimension(250, 100));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));
                this.opcjePanel = new OpcjePanel(jp, opis);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                        warunek_pom = false;

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeArytmetyczne.logarytmowanie_obrazu(obrP.pobierz_tab_raster()));

                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {

//                                System.out.println("dupa");
                            obrP.ustaw_tab_raster(OperacjeArytmetyczne.logarytmowanie_obrazu(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                            warunek_pom = false;

                        }

                    }
                });
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }

    }

    private class OperacjeGeometryczneOkno extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[6];
        private JButton wroc;
        private OpcjePanel opcjePanel;
        private int tabLokacja[] = new int[2];
        private int[] wycinanie = new int[4];

        public OperacjeGeometryczneOkno()
        {
            this.setEnabled(false);
            this.setVisible(false);
        }

        public OperacjeGeometryczneOkno(int x, int y)
        {

            this.uzupelnij();
            this.setUndecorated(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //OperacjeGeometryczne.ustaw_typ(typ);
            this.setPreferredSize(new Dimension(200, 650));
            this.getContentPane().setBackground(new Color(64, 64, 64));

            this.zmien_lokacje(x, y);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);
        }

        private void uzupelnij()
        {
            JPanel pom = new JPanel();
            pom = new JPanel();
            pom.setLayout(new GridLayout(0, 1));
            pom.setBackground(new Color(64, 64, 64));

            this.wroc = new JButton("WRÓĆ");
            this.wroc.setBackground(new Color(128, 128, 128));
            this.wroc.setForeground(Color.WHITE);
            this.wroc.setFocusPainted(false);
            this.wroc.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.wroc.setPreferredSize(new Dimension(200, 37));
            this.wroc.addMouseListener(this);

            JLabel enter[] = new JLabel[15];
            for (int a = 0; a < enter.length; a++)
            {
                enter[a] = new JLabel("<html></html>");
            }
            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 12));
            zadania[0].setText("<html><div align = center>OPERACJE GEOMETRYCZNE</html>");
            pom.add(zadania[0]);
            pom.add(enter[0]);

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.LEFT);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.PLAIN, 16));
                pom.add(zadania[a]);

                pom.add(enter[a]);
            }

            pom.add(enter[6]);
            pom.add(enter[7]);
            pom.add(enter[8]);
            pom.add(enter[9]);
            pom.add(enter[10]);
            pom.add(enter[11]);
            pom.add(enter[12]);
            // pom.add(enter[13]);
            // pom.add(enter[14]);

            zadania[1].setText("<html><div align = left>- Przemieszczanie obrazu</html>");

            zadania[2].setText("<html><div align = left>- Skalowanie obrazu</html>");

            zadania[3].setText("<html><div align = left>- Obracanie obrazu</html>");

            zadania[4].setText("<html><div align = left>- Symetria względem osi</html>");

            zadania[5].setText("<html><div align = left>- Wycinanie fragmentów obrazu</html>");

            this.add(pom, BorderLayout.CENTER);
            this.add(wroc, BorderLayout.SOUTH);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                    }

                }
            });
        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        private void wygas_panel()
        {
            for (int a = 0; a < zadania.length; a++)
            {
                zadania[a].setEnabled(false);
            }
            wroc.setEnabled(false);
        }

        private void wlacz_panel()
        {
            for (int a = 0; a < zadania.length; a++)
            {
                zadania[a].setEnabled(true);
            }
            wroc.setEnabled(true);
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == this.wroc)
            {
                this.setVisible(false);
                this.setEnabled(false);
                this.dispose();

                rt.setEnabled(true);
                rt.setVisible(true);

            }
            ///////////////////TRANSLACJA/////////////////
            ///////////////////TRANSLACJA/////////////////
            ///////////////////TRANSLACJA/////////////////
            ///////////////////TRANSLACJA/////////////////
            if (e.getSource() == this.zadania[1])
            {
                this.setEnabled(false);
                wygas();
                JLabel opis[] = new JLabel[2];
                JTextField poleTekstowe[] = new JTextField[2];
                for (int a = 0; a < opis.length; a++)
                {
                    opis[a] = new JLabel("", SwingConstants.LEFT);
                    opis[a].setForeground(new Color(128, 128, 128));
                    opis[a].setPreferredSize(new Dimension(125, 20));
                    poleTekstowe[a] = new JTextField()
                    {
                        public void processKeyEvent(KeyEvent ev)
                        {
                            char c = ev.getKeyChar();
                            if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || ev.getKeyCode() == KeyEvent.VK_ENTER || (int) c == 45)
                            {
                                super.processKeyEvent(ev);
                            }

                        }
                    };
                    poleTekstowe[a].setBackground(new Color(64, 64, 64));
                    poleTekstowe[a].setHorizontalAlignment(SwingConstants.CENTER);
                    poleTekstowe[a].setForeground(Color.WHITE);
                    poleTekstowe[a].setCaretColor(Color.WHITE);
                    poleTekstowe[a].setPreferredSize(new Dimension(100, 20));
                    poleTekstowe[a].setText("0");
                }

                opis[0].setText("<html><div align = left><font size = 3><i> M</i> w poziomie </html>");
                opis[1].setText("<html><div align = left><font size = 3><i> N</i> w pionie </html>");
                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>PODAJ WSPOLCZYNNIKI PRZESUNIĘCIA:<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                this.opcjePanel = new OpcjePanel(opis0, opis[0], opis[1], poleTekstowe[0], poleTekstowe[1]);
                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                poleTekstowe[0].addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("calkowite"))
                            {

                                obrP.ustaw_tab_raster(OperacjeGeometryczne.translacja(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe[0].getText()), Integer.parseInt(poleTekstowe[1].getText())));

                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                poleTekstowe[1].addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("calkowite"))
                            {

                                obrP.ustaw_tab_raster(OperacjeGeometryczne.translacja(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe[0].getText()), Integer.parseInt(poleTekstowe[1].getText())));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        if (opcjePanel.sprawdz_tekst("calkowite"))
                        {

                            obrP.ustaw_tab_raster(OperacjeGeometryczne.translacja(obrP.pobierz_tab_raster(), Integer.parseInt(poleTekstowe[0].getText()), Integer.parseInt(poleTekstowe[1].getText())));

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

            }
            //////////////////////SKALOWANIE
            //////////////////////SKALOWANIE
            //////////////////////SKALOWANIE
            //////////////////////SKALOWANIE
            if (e.getSource() == this.zadania[2])
            {
                this.setEnabled(false);
                wygas();
                JLabel opis1;
                JLabel opis2;
                JTextField poleTekstowe[] = new JTextField[2];
                JRadioButton wybor[] = new JRadioButton[2];
                int tab[] = obrP.pobierz_rozmiar();

                for (int a = 0; a < poleTekstowe.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    poleTekstowe[a] = new JTextField()
                    {
                        public void processKeyEvent(KeyEvent ev)
                        {
                            char c = ev.getKeyChar();
                            if (ev.getKeyCode() == KeyEvent.VK_BACK_SPACE || ((int) c >= 48 && (int) c <= 57) || c == '.' || ev.getKeyCode() == KeyEvent.VK_ENTER)
                            {
                                super.processKeyEvent(ev);
                            }

                        }
                    };
                    poleTekstowe[a].setBackground(new Color(64, 64, 64));
                    poleTekstowe[a].setHorizontalAlignment(SwingConstants.CENTER);
                    poleTekstowe[a].setForeground(Color.WHITE);
                    poleTekstowe[a].setCaretColor(Color.WHITE);
                    poleTekstowe[a].setPreferredSize(new Dimension(60, 15));

                    poleTekstowe[a].setEnabled(false);
                    poleTekstowe[a].setVisible(false);

                }

                poleTekstowe[0].setText("0.0");
                poleTekstowe[1].setText("0.0");

                wybor[0].setText("Skalowanie jednorodne");
                wybor[1].setText("Skalowanie niejednorodne");

                opis1 = new JLabel("", SwingConstants.LEFT);
                opis1.setForeground(new Color(255, 255, 255));
                opis1.setPreferredSize(new Dimension(30, 15));
                opis1.setText("<html><div align = left><font size = 3><i>Sx:  </i></html>");
                opis1.setVisible(false);

                opis2 = new JLabel("", SwingConstants.LEFT);
                opis2.setForeground(new Color(255, 255, 255));
                opis2.setPreferredSize(new Dimension(30, 15));
                opis2.setText("<html><div align = left><font size = 3><i>Sy:  </i></html>");
                opis2.setVisible(false);

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>WYBIERZ TRYB I WPROWADZ WSPÓŁCZYNNIKI SKALOWANIA:<br><font size = 2>(aktualna rozdzielczość: " + tab[0] + "x" + tab[1] + ")<br>(Sx i Sy od 0.4 do 4.0)<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                this.opcjePanel = new OpcjePanel(wybor[0], wybor[1], poleTekstowe[0], poleTekstowe[1], opis0, opis1, opis2);

                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                wybor[0].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[0].isEnabled())
                        {
                            if (wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(false);

                                poleTekstowe[0].setEnabled(true);
                                poleTekstowe[0].setVisible(true);
                                poleTekstowe[0].setPreferredSize(new Dimension(60, 17));

                                opis2.setVisible(true);

                                opis2.setText("<html><div align = left><font size = 3><i>Sy i Sx:  </i></html>");
                                opis2.setPreferredSize(new Dimension(60, 17));

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(true);

                                poleTekstowe[0].setEnabled(false);
                                poleTekstowe[0].setVisible(false);
                                poleTekstowe[0].setPreferredSize(new Dimension(60, 15));

                                opis2.setVisible(false);

                                opis2.setText("<html><div align = left><font size = 3><i>Sy:  </i></html>");
                                opis2.setPreferredSize(new Dimension(30, 15));

                                opcjePanel.ok.setEnabled(false);
                                poleTekstowe[0].setText("0.0");

                            }
                        }
                    }

                });
                wybor[1].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[1].isEnabled())
                        {
                            if (wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(false);

                                opcjePanel.ok.setEnabled(true);
                                opis1.setVisible(true);
                                opis2.setVisible(true);
                                poleTekstowe[0].setVisible(true);
                                poleTekstowe[1].setVisible(true);
                                poleTekstowe[0].setEnabled(true);
                                poleTekstowe[1].setEnabled(true);

                            }
                            if (!wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(true);

                                opis1.setVisible(false);
                                opis2.setVisible(false);

                                poleTekstowe[0].setVisible(false);
                                poleTekstowe[1].setVisible(false);

                                poleTekstowe[0].setEnabled(false);
                                poleTekstowe[1].setEnabled(false);

                                poleTekstowe[0].setText("0.0");
                                poleTekstowe[1].setText("0.0");
                                opcjePanel.ok.setEnabled(false);
                            }
                        }
                    }

                });

                poleTekstowe[0].addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("zmiennoprzecinkowe"))
                            {
                                if (wybor[0].isSelected())
                                {

                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_jednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText())));

                                } else if (wybor[1].isSelected())
                                {
                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_niejednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText()), Double.parseDouble(poleTekstowe[1].getText())));
                                }
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });
                poleTekstowe[1].addKeyListener(new KeyAdapter()
                {

                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.sprawdz_tekst("zmiennoprzecinkowe"))
                            {

                                if (wybor[0].isSelected())
                                {

                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_jednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText())));

                                } else if (wybor[1].isSelected())
                                {
                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_niejednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText()), Double.parseDouble(poleTekstowe[1].getText())));
                                }
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (opcjePanel.sprawdz_tekst("zmiennoprzecinkowe"))
                            {

                                if (wybor[0].isSelected())
                                {

                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_jednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText())));

                                } else if (wybor[1].isSelected())
                                {
                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.skalowanie_niejednorodne(obrP.pobierz_tab_raster(), Double.parseDouble(poleTekstowe[0].getText()), Double.parseDouble(poleTekstowe[1].getText())));
                                }
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }

                        }
                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            /////////////////////OBRACANIE O DOWOLNY KAT////////////////////
            /////////////////////OBRACANIE O DOWOLNY KAT////////////////////
            /////////////////////OBRACANIE O DOWOLNY KAT////////////////////
            /////////////////////OBRACANIE O DOWOLNY KAT////////////////////
            if (e.getSource() == this.zadania[3])
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(0, 360, 0);
                suwak.setBackground(new Color(64, 64, 64));

                JRadioButton wybor[] = new JRadioButton[2];
                int tab[] = obrP.pobierz_rozmiar();

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);

                }

                wybor[0].setText("Dowolny kąt");
                wybor[1].setText("Wielokrotność 90");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>WYBIERZ TRYB I USTAW KĄT<br><br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                JLabel wartosc = new JLabel("", SwingConstants.CENTER);
                wartosc.setText("<html><div align = center> <font size  = 4>" + (double) suwak.getValue() + "</html>");
                wartosc.setHorizontalAlignment(SwingConstants.CENTER);
                wartosc.setForeground(new Color(128, 128, 128));

                wartosc.setVisible(false);
                suwak.setEnabled(false);
                suwak.setVisible(false);

                this.opcjePanel = new OpcjePanel(opis0, wybor[0], wybor[1], suwak, wartosc);

                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                wybor[0].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[0].isEnabled())
                        {
                            if (wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(false);

                                wartosc.setVisible(true);
                                suwak.setEnabled(true);
                                suwak.setVisible(true);
                                suwak.setMaximum(360);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(true);

                                wartosc.setVisible(false);
                                suwak.setEnabled(false);
                                suwak.setVisible(false);
                                suwak.setValue(0);
                                opcjePanel.ok.setEnabled(false);

                            }
                        }
                    }

                });
                wybor[1].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[1].isEnabled())
                        {
                            if (wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(false);
                                wartosc.setVisible(true);
                                suwak.setEnabled(true);
                                suwak.setVisible(true);
                                suwak.setMaximum(4);
                                suwak.setMinorTickSpacing(1);
                                suwak.setMajorTickSpacing(1);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(true);

                                wartosc.setVisible(false);
                                suwak.setEnabled(false);
                                suwak.setVisible(false);
                                suwak.setValue(0);
                                opcjePanel.ok.setEnabled(false);
                            }
                        }
                    }

                });

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {

                                obrP.ustaw_tab_raster(OperacjeGeometryczne.obracanie_o_dowolny_kat(obrP.pobierz_tab_raster(), suwak.getValue()));

                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeGeometryczne.obrazanie_o_wielokrotnosc_90(obrP.pobierz_tab_raster(), suwak.getValue()));
                            }
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("obracanie");
                        }
                    }
                });

                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.suwak.isEnabled())
                            {
                                if (wybor[0].isSelected())
                                {

                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.obracanie_o_dowolny_kat(obrP.pobierz_tab_raster(), suwak.getValue()));
                                } else if (wybor[1].isSelected())
                                {
                                    obrP.ustaw_tab_raster(OperacjeGeometryczne.obrazanie_o_wielokrotnosc_90(obrP.pobierz_tab_raster(), suwak.getValue()));
                                }
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });

            }
            ///////////////////////////////////ODBICIA LUSTRZANE
            ///////////////////////////////////ODBICIA LUSTRZANE
            ///////////////////////////////////ODBICIA LUSTRZANE
            ///////////////////////////////////ODBICIA LUSTRZANE
            if (e.getSource() == zadania[4])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[2];
                int tab[] = obrP.pobierz_rozmiar();

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);

                }

                wybor[0].setText("Odbicie w pionie");
                wybor[1].setText("Odbicie w poziomie");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>WYBIERZ TRYB ODBICIA LUSTRZANEGO<br><br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                this.opcjePanel = new OpcjePanel(opis0, wybor[0], wybor[1]);

                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);
                this.opcjePanel.ok.setEnabled(false);

                wybor[0].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[0].isEnabled())
                        {
                            if (wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(false);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(true);

                                opcjePanel.ok.setEnabled(false);

                            }
                        }
                    }

                });
                wybor[1].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[1].isEnabled())
                        {
                            if (wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(false);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(true);

                                opcjePanel.ok.setEnabled(false);
                            }
                        }
                    }

                });

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {

                                obrP.ustaw_tab_raster(OperacjeGeometryczne.odbicie_lustrzane_pion(obrP.pobierz_tab_raster()));

                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeGeometryczne.odbicie_lustrzane_poziom(obrP.pobierz_tab_raster()));
                            }
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }
                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

            }
            //////////////WYCINANIE FRAGMENTOW OBRAZU////////////
            //////////////WYCINANIE FRAGMENTOW OBRAZU////////////
            //////////////WYCINANIE FRAGMENTOW OBRAZU////////////
            //////////////WYCINANIE FRAGMENTOW OBRAZU////////////
            if (e.getSource() == this.zadania[5])
            {

                this.setEnabled(false);
                wygas();

                for (int a = 0; a < this.wycinanie.length; a++)
                {
                    this.wycinanie[a] = 0;
                }

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);

                MouseAdapter wycinanie1 = new MouseAdapter()
                {

                    Point start;
                    Point end;
                    int tab[] = obrP.pobierz_rozmiar();
                    int szerokoscObrazu = obrP.getSize().width;
                    int wysokoscObrazu = obrP.getSize().height;
                    int xPoczatekObrazu;
                    int yPoczatekObrazu;
                    int xKoniecObrazu;
                    int yKoniecObrazu;

                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        tab = obrP.pobierz_rozmiar();

                        szerokoscObrazu = obrP.getSize().width;
                        wysokoscObrazu = obrP.getSize().height;

                        xPoczatekObrazu = (int) Math.round((double) szerokoscObrazu / 2.0 - (double) tab[0] / 2.0);
                        yPoczatekObrazu = (int) Math.round((double) wysokoscObrazu / 2.0 - (double) tab[1] / 2.0);

                        xKoniecObrazu = xPoczatekObrazu + tab[0];
                        yKoniecObrazu = yPoczatekObrazu + tab[1];

                        start = new Point(e.getX(), e.getY());
                    }

                    @Override
                    public void mouseReleased(MouseEvent e)
                    {

                        end = new Point(e.getX(), e.getY());

                        Rectangle rec = new Rectangle(start);
                        rec.add(end);

                        Graphics g = obrP.getGraphics();
                        g.setColor(Color.RED);
                        if ((rec.x >= xPoczatekObrazu && rec.y >= yPoczatekObrazu && rec.x <= xKoniecObrazu && rec.y <= yKoniecObrazu)
                                && (rec.x + rec.width >= xPoczatekObrazu && rec.y + rec.height >= yPoczatekObrazu && rec.x + rec.width <= xKoniecObrazu && rec.y + rec.height <= yKoniecObrazu))
                        {

                            wygas();
                            g.drawRect(rec.x, rec.y, rec.width, rec.height);
                            wycinanie[0] = rec.x - xPoczatekObrazu;
                            wycinanie[1] = rec.y - yPoczatekObrazu;
                            wycinanie[2] = rec.width;
                            wycinanie[3] = rec.height;

                            puw.nazwa("<html><div align = center>ZAZNACZONO OBSZAR ROZPOCZYNAJĄCY SIĘ W PUNKCIE O WSPÓŁRZĘDNYCH: <font color=white>" + "[" + wycinanie[0] + "," + wycinanie[1] + "]"
                                    + "</font><br>O SZEROKOŚCI I WYSOKOŚCI: <font color=white>" + wycinanie[2] + "x" + wycinanie[3] + "</font></div></html>");

                            puw.wlacz();

                        }
                        g.dispose();

                    }

                };
                puw.guzik.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        obrP.removeMouseListener(wycinanie1);
                        obrP.ustaw_tab_raster(OperacjeGeometryczne.kadrowanie(obrP.pobierz_tab_raster(), wycinanie[0], wycinanie[1], wycinanie[2], wycinanie[3]));
                        wlacz_panel();
                        wlacz();

                    }
                });

                int tab[] = obrP.pobierz_rozmiar();

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>NACISNIJ OK I ZAZNACZ MYSZKĄ NA OBRAZIE OBSZAR DO WYCIĘCIA<br><br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                this.opcjePanel = new OpcjePanel(opis0);

                this.tabLokacja = wez_lokacje();
                this.opcjePanel.setLocation(this.tabLokacja[0], this.tabLokacja[1]);

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            obrP.addMouseListener(wycinanie1);
                            opcjePanel.wylacz();
                            setEnabled(true);
                            wygas_panel();
                            toFront();
                            wlacz();

                        }
                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

            }

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }

    }

    private class OperacjeHistogramOkno extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[12];
        private JButton wroc;
        private OknoHistogram oknH;
        private OpcjePanel opcjePanel;

        public OperacjeHistogramOkno()
        {
            this.setEnabled(false);
            this.setVisible(false);
        }

        public OperacjeHistogramOkno(int x, int y)
        {

            this.uzupelnij();
            this.setUndecorated(true);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setPreferredSize(new Dimension(200, 650));
            this.getContentPane().setBackground(new Color(64, 64, 64));

            this.zmien_lokacje(x, y);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);
            oknH = new OknoHistogram(this.getLocationOnScreen().x + 250, this.getLocationOnScreen().y, typ);
            oknH.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();

                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();

                    }

                }
            });

        }

        private void uzupelnij()
        {
            JPanel pom = new JPanel();
            pom = new JPanel();
            pom.setLayout(new GridLayout(0, 1));
            pom.setBackground(new Color(64, 64, 64));

            OperacjeHistogram.typ = typ;
            this.wroc = new JButton("WRÓĆ");
            this.wroc.setBackground(new Color(128, 128, 128));
            this.wroc.setForeground(Color.WHITE);
            this.wroc.setFocusPainted(false);
            this.wroc.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.wroc.setPreferredSize(new Dimension(200, 37));
            this.wroc.addMouseListener(this);

            JLabel enter[] = new JLabel[15];
            for (int a = 0; a < enter.length; a++)
            {
                enter[a] = new JLabel("<html></html>");
            }
            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 12));
            zadania[0].setText("<html><div align = center>OPERACJE NA HISTOGRAMIE</html>");
            pom.add(zadania[0]);
            pom.add(enter[0]);

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.LEFT);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.PLAIN, 16));
                pom.add(zadania[a]);

                pom.add(enter[a]);
            }

            //pom.add(enter[12]);
            // pom.add(enter[13]);
            // pom.add(enter[13]);
            // pom.add(enter[14]);
            zadania[1].setText("<html><div align = left>- Obliczanie histogramu</html>");

            zadania[2].setText("<html><div align = left>- Rozciąganie histogramu</html>");

            zadania[3].setText("<html><div align = left>- Wyrównywanie histogramu</html>");

            zadania[4].setText("<html><div align = left>- Zmiana kontrastu</html>");

            zadania[5].setText("<html><div align = left>- Zmiana jasności</html>");

            zadania[6].setText("<html><div align = left>- Zmiana ekspozcji</html>");

            zadania[7].setText("<html><div align = left>- Korekcja gamma</html>");

            zadania[8].setText("<html><div align = left>- Negatyw</html>");

            zadania[9].setText("<html><div align = left>- Odcienie szarości</html>");

            zadania[10].setText("<html><div align = left>- Sepia</html>");

            zadania[11].setText("<html><div align = left>- Progowanie/binaryzacja</html>");

            this.add(pom, BorderLayout.CENTER);
            this.add(wroc, BorderLayout.SOUTH);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                        oknH.setState(Frame.ICONIFIED);
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                        oknH.setState(Frame.NORMAL);
                    }

                }
            });

            this.addComponentListener(new ComponentAdapter()
            {
                public void componentMoved(ComponentEvent e)
                {

                    oknH.zmien_lokacje(getLocationOnScreen().x + 250, getLocationOnScreen().y);
                }
            });
            this.wylacz_zadania();
        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        private void wylacz_zadania()
        {
            for (int a = 2; a < zadania.length; a++)
            {
                zadania[a].setEnabled(false);
            }
        }

        private void wlacz_zadania()
        {
            for (int a = 2; a < zadania.length; a++)
            {
                zadania[a].setEnabled(true);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == this.wroc)
            {
                this.setVisible(false);
                this.setEnabled(false);
                this.dispose();

                rt.setEnabled(true);
                rt.setVisible(true);

                oknH.dispose();

            }
            if (e.getSource() == this.zadania[1])
            {
                this.setEnabled(false);
                wygas();

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);

                puw.nazwa("<html><div align = center>NASTĄPI OBLICZENIE HISTOGRAU</font></div></html>");
                puw.guzik.addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        setEnabled(true);
                        wlacz();
                        wlacz_zadania();
                        if (typ.equals("GrayScale"))
                        {
                            zadania[9].setEnabled(false);
                            zadania[10].setEnabled(false);
                        }
                    }
                });
                puw.guzik.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            setEnabled(true);
                            wlacz();
                            wlacz_zadania();
                            if (typ.equals("GrayScale"))
                            {
                                zadania[9].setEnabled(false);
                                zadania[10].setEnabled(false);
                            }

                        }
                    }
                });

                puw.wlacz();

            }
            if (e.getSource() == this.zadania[2] && this.zadania[2].isEnabled())
            {

                this.setEnabled(false);
                wygas();

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);
                if (typ.equals("RGB"))
                {

                    int min[] = OperacjeHistogram.obliczMin(obrP.pobierz_tab_raster());
                    int max[] = OperacjeHistogram.obliczMax(obrP.pobierz_tab_raster());

                    puw.nazwa("<html><div align = center>NASTĄPI ROZCIĄGNIĘCIE HISTOGRAMU Z WARTOŚCI: <font color=white><br>" + "R [" + min[0] + "-" + max[0] + "] " + "G [" + min[1] + "-" + max[1] + "] " + "B [" + min[2] + "-" + max[2] + "]"
                            + "</font><br>DO WARTOŚCI <font color=white><br> R [0-255] G [0-255] B [0-255] </font></div></html>");
                }
                if (typ.equals("GrayScale"))
                {
                    int min[] = OperacjeHistogram.obliczMin(obrP.pobierz_tab_raster());
                    int max[] = OperacjeHistogram.obliczMax(obrP.pobierz_tab_raster());

                    puw.nazwa("<html><div align = center>NASTĄPI ROZCIĄGNIĘCIE HISTOGRAMU Z WARTOŚCI: <font color=white>" + "[" + min[0] + "," + max[0] + "]"
                            + "</font><br>DO WARTOŚCI <font color=white><br>[0,255]</font></div></html>");
                }

                puw.guzik.addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        obrP.ustaw_tab_raster(OperacjeHistogram.rozciagnijHistogram(obrP.pobierz_tab_raster()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        setEnabled(true);
                        wlacz();
                    }
                });
                puw.guzik.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.rozciagnijHistogram(obrP.pobierz_tab_raster()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            setEnabled(true);
                            wlacz();

                        }
                    }
                });
                puw.wlacz();
            }
            if (e.getSource() == this.zadania[3] && this.zadania[3].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);

                puw.nazwa("<html><div align = center>NASTĄPI WYRÓWNANIE HISTOGRAMU</font></div></html>");
                puw.guzik.addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        obrP.ustaw_tab_raster(OperacjeHistogram.wyrownajHistogram(obrP.pobierz_tab_raster()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));

                        setEnabled(true);
                        wlacz();

                    }
                });
                puw.guzik.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.wyrownajHistogram(obrP.pobierz_tab_raster()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));

                            setEnabled(true);
                            wlacz();

                        }
                    }
                });
                puw.wlacz();

            }
            if (e.getSource() == this.zadania[4] && this.zadania[4].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 200, 100);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW KONTRAST<br>" + (double) suwak.getValue() / 100 + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeHistogram.zmienKontrast(obrP.pobierz_tab_raster(), (double) suwak.getValue() / 100));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.zmienKontrast(obrP.pobierz_tab_raster(), (double) suwak.getValue() / 100));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("kontrastowanie");
                        }
                    }
                });

            }
            if (e.getSource() == this.zadania[5] && this.zadania[5].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(-255, 255, 0);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW JASNOŚĆ<br>" + (double) suwak.getValue() + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeHistogram.zmienJasnosc(obrP.pobierz_tab_raster(), suwak.getValue()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.zmienJasnosc(obrP.pobierz_tab_raster(), suwak.getValue()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("jasnosc");
                        }
                    }
                });
            }
            if (e.getSource() == this.zadania[6] && this.zadania[6].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 200, 100);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW EKSPOZYCJE<br>" + (double) suwak.getValue() / 100 + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeHistogram.zmienEkspozycje(obrP.pobierz_tab_raster(), suwak.getValue() / 100.0));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.zmienEkspozycje(obrP.pobierz_tab_raster(), suwak.getValue() / 100.0));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("ekspozycja");
                        }
                    }
                });
            }
            if (e.getSource() == this.zadania[7] && zadania[7].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 200, 100);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW GAMME<br>" + (double) suwak.getValue() / 100 + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeHistogram.zmienGamma(obrP.pobierz_tab_raster(), suwak.getValue() / 100.0));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.zmienGamma(obrP.pobierz_tab_raster(), suwak.getValue() / 100.0));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("gamma");
                        }
                    }
                });
            }
            if (e.getSource() == this.zadania[8] && this.zadania[8].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);

                puw.nazwa("<html><div align = center>NASTĄPI OBLICZENIE NEGATYWU OBRAZU</font></div></html>");
                puw.guzik.addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        obrP.ustaw_tab_raster(OperacjeHistogram.negatyw(obrP.pobierz_tab_raster()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        setEnabled(true);
                        wlacz();

                    }
                });
                puw.guzik.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.negatyw(obrP.pobierz_tab_raster()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            setEnabled(true);
                            wlacz();

                        }
                    }
                });

                puw.wlacz();
            }
            if (e.getSource() == this.zadania[9] && this.zadania[9].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                PopUpWindow puw = new PopUpWindow(wez_lokacje()[0], wez_lokacje()[1]);

                puw.nazwa("<html><div align = center>NASTĄPI OBLICZENIE SKAL SZAROŚCI OBRAZU</font></div></html>");
                puw.guzik.addMouseListener(new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        obrP.ustaw_tab_raster(OperacjeHistogram.skalaSzarosci(obrP.pobierz_tab_raster()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        setEnabled(true);
                        wlacz();

                    }
                });
                puw.guzik.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.skalaSzarosci(obrP.pobierz_tab_raster()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            setEnabled(true);
                            wlacz();

                        }
                    }
                });
                puw.wlacz();
            }
            if (e.getSource() == this.zadania[10] && this.zadania[10].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(20, 40, 30);
                suwak.setBackground(new Color(64, 64, 64));
                suwak.setMajorTickSpacing(10);
                suwak.setMinorTickSpacing(5);

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>USTAW SEPIE<br>" + (double) suwak.getValue() + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeHistogram.sepia(obrP.pobierz_tab_raster(), suwak.getValue()));
                        oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            obrP.ustaw_tab_raster(OperacjeHistogram.sepia(obrP.pobierz_tab_raster(), suwak.getValue()));
                            oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("sepia");
                        }
                    }
                });
            }
            if (e.getSource() == this.zadania[11] && this.zadania[11].isEnabled())
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 20, 1);
                suwak.setBackground(new Color(64, 64, 64));

                JRadioButton wybor[] = new JRadioButton[2];
                int tab[] = obrP.pobierz_rozmiar();

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);

                }

                wybor[0].setText("Progowanie Globalne");
                wybor[1].setText("Progowanie Lokalne");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4><br>WYBIERZ TRYB I USTAW MASKĘ PROGOWANIA LOKALNEGO<br><br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(250, 100));

                JLabel wartosc = new JLabel("", SwingConstants.CENTER);
                wartosc.setText("<html><div align = center> <font size  = 4>" + "3x3" + "</html>");
                wartosc.setHorizontalAlignment(SwingConstants.CENTER);
                wartosc.setForeground(new Color(128, 128, 128));

                wartosc.setVisible(false);
                suwak.setEnabled(false);
                suwak.setVisible(false);

                this.opcjePanel = new OpcjePanel(opis0, wybor[0], wybor[1], suwak, wartosc);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                wybor[0].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[0].isEnabled())
                        {
                            if (wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(false);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[0].isSelected())
                            {
                                wybor[1].setEnabled(true);
                                opcjePanel.ok.setEnabled(false);

                            }
                        }
                    }

                });
                wybor[1].addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        if (wybor[1].isEnabled())
                        {
                            if (wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(false);
                                wartosc.setVisible(true);
                                suwak.setEnabled(true);
                                suwak.setVisible(true);

                                opcjePanel.ok.setEnabled(true);

                            }
                            if (!wybor[1].isSelected())
                            {
                                wybor[0].setEnabled(true);

                                wartosc.setVisible(false);
                                suwak.setEnabled(false);
                                suwak.setVisible(false);
                                suwak.setValue(0);
                                opcjePanel.ok.setEnabled(false);
                            }
                        }
                    }

                });

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {

                                obrP.ustaw_tab_raster(OperacjeHistogram.progowanieGlobalne(obrP.pobierz_tab_raster()));
                                oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));

                            } else if (wybor[1].isSelected())
                            {

                                obrP.ustaw_tab_raster(OperacjeHistogram.progowanieLokalne(obrP.pobierz_tab_raster(), suwak.getValue()));
                                oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                            }
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("binaryzacja");
                        }
                    }
                });

                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (opcjePanel.suwak.isEnabled())
                            {
                                obrP.ustaw_tab_raster(OperacjeHistogram.progowanieLokalne(obrP.pobierz_tab_raster(), suwak.getValue()));
                                oknH.rysuj_hist(OperacjeHistogram.obliczHistogram(obrP.pobierz_tab_raster()));
                                opcjePanel.wylacz();
                                setEnabled(true);
                                toFront();
                                wlacz();

                            }
                        }
                    }
                });

            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }

    }

    private class OperacjeFiltryOkno extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[10];
        private JButton wroc;
        private OpcjePanel opcjePanel;

        public OperacjeFiltryOkno()
        {
            this.setEnabled(false);
            this.setVisible(false);
        }

        public OperacjeFiltryOkno(int x, int y)
        {

            this.uzupelnij();
            this.setUndecorated(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //OperacjeGeometryczne.ustaw_typ(typ);
            this.setPreferredSize(new Dimension(200, 650));
            this.getContentPane().setBackground(new Color(64, 64, 64));

            this.zmien_lokacje(x, y);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);
        }

        private void uzupelnij()
        {
            JPanel pom = new JPanel();
            pom = new JPanel();
            pom.setLayout(new GridLayout(0, 1));
            pom.setBackground(new Color(64, 64, 64));

            this.wroc = new JButton("WRÓĆ");
            this.wroc.setBackground(new Color(128, 128, 128));
            this.wroc.setForeground(Color.WHITE);
            this.wroc.setFocusPainted(false);
            this.wroc.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.wroc.setPreferredSize(new Dimension(200, 37));
            this.wroc.addMouseListener(this);

            JLabel enter[] = new JLabel[15];
            for (int a = 0; a < enter.length; a++)
            {
                enter[a] = new JLabel("<html></html>");
            }
            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
            zadania[0].setText("<html><div align = center>FILTRY</html>");
            pom.add(zadania[0]);
            pom.add(enter[0]);

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.LEFT);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.PLAIN, 16));
                pom.add(zadania[a]);

                pom.add(enter[a]);
            }

            //pom.add(enter[6]);
            //pom.add(enter[7]);
            //pom.add(enter[8]);
            //pom.add(enter[9]);
            pom.add(enter[10]);
            pom.add(enter[11]);
            pom.add(enter[12]);
            pom.add(enter[13]);
            // pom.add(enter[14]);

            zadania[1].setText("<html><div align = left>- Filtry dolnoprzepustowe</html>");

            zadania[2].setText("<html><div align = left>- Filtry górnoprzepustowe</html>");

            zadania[3].setText("<html><div align = left>- Filtry przesuń-odejmij</html>");

            zadania[4].setText("<html><div align = left>- Filtry gradientowe kierunkowe</html>");

            zadania[5].setText("<html><div align = left>- Filtry uwypuklające</html>");

            zadania[6].setText("<html><div align = left>- Filtry Laplace'a</html>");

            zadania[7].setText("<html><div align = left>- Filtry konturowe</html>");

            zadania[8].setText("<html><div align = left>- Filtry statyczne</html>");

            zadania[9].setText("<html><div align = left>- Filtry kombinowane</html>");

            this.add(pom, BorderLayout.CENTER);
            this.add(wroc, BorderLayout.SOUTH);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                    }

                }
            });
        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == this.wroc)
            {
                this.setVisible(false);
                this.setEnabled(false);
                this.dispose();

                rt.setEnabled(true);
                rt.setVisible(true);

            }
            if (e.getSource() == this.zadania[1])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[13];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Uśredniający");
                wybor[1].setText("Kwadratowy");
                wybor[2].setText("Kołowy");
                wybor[3].setText("LP1");
                wybor[4].setText("LP2");
                wybor[5].setText("LP3");
                wybor[6].setText("Piramidalny");
                wybor[7].setText("Stożkowy");
                wybor[8].setText("G1");
                wybor[9].setText("G2");
                wybor[10].setText("G3");
                wybor[11].setText("G4");
                wybor[12].setText("G5");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA DOLNOPRZEPUSTOWEGO<br></html>", SwingConstants.CENTER);

                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(40, 42));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[5];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);

                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[6]);

                pom2[2].add(wybor[3]);
                pom2[2].add(wybor[4]);
                pom2[2].add(wybor[5]);

                pom2[3].add(wybor[7]);

                pom2[4].add(wybor[8]);
                pom2[4].add(wybor[9]);
                pom2[4].add(wybor[10]);
                pom2[4].add(wybor[11]);
                pom2[4].add(wybor[12]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[3]);
                pom1.add(pom2[2]);
                pom1.add(pom2[4]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {

                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUsredniajacy()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrKwadratowy()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrKolowy()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LP1()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LP2()));
                            } else if (wybor[5].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LP3()));
                            } else if (wybor[6].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPiramidalny()));
                            } else if (wybor[7].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrStozkowy()));
                            } else if (wybor[8].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.G1()));
                            } else if (wybor[9].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.G2()));
                            } else if (wybor[10].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.G3()));
                            } else if (wybor[11].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.G4()));
                            } else if (wybor[12].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.G5()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

            }
            if (e.getSource() == this.zadania[2])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[4];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Usuń średnią");
                wybor[1].setText("HP1");
                wybor[2].setText("HP2");
                wybor[3].setText("HP3");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA GÓRNOPRZEPUSTOWEGO<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);

                pom2[1].add(wybor[1]);
                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[3]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUsunSrednia()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.HP1()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.HP2()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.HP3()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[3])
            {

                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[4];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Poziomy");
                wybor[1].setText("Pionowy");
                wybor[2].setText("Ukos-Prawo");
                wybor[3].setText("Ukos-Lewo");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA PRZESUŃ-ODEJMIJ<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);

                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[3]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPoziomy()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPionowy()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUkosPrawo()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUkosLewo()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[4])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[8];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("WS");
                wybor[1].setText("PD-WS");
                wybor[2].setText("PD");
                wybor[3].setText("PD-Z");
                wybor[4].setText("Z");
                wybor[5].setText("PŁ-Z");
                wybor[6].setText("PŁ");
                wybor[7].setText("PŁ-WS");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA GRADIENTOWEGO KIERUNKOWEGO<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[2]);
                pom2[0].add(wybor[4]);
                pom2[0].add(wybor[6]);

                pom2[1].add(wybor[1]);
                pom2[1].add(wybor[3]);
                pom2[1].add(wybor[5]);
                pom2[1].add(wybor[7]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrWschod()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPoludniowyWschod()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPoludnie()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPoludniowyZachod()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrZachod()));
                            } else if (wybor[5].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPolnocnyZachod()));
                            } else if (wybor[6].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPolnoc()));
                            } else if (wybor[7].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPolnocnyWschod()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[5])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[8];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("WS");
                wybor[1].setText("PD-WS");
                wybor[2].setText("PD");
                wybor[3].setText("PD-Z");
                wybor[4].setText("Z");
                wybor[5].setText("PŁ-Z");
                wybor[6].setText("PŁ");
                wybor[7].setText("PŁ-WS");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA UWYPUKLAJĄCEGO<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[2]);
                pom2[0].add(wybor[4]);
                pom2[0].add(wybor[6]);

                pom2[1].add(wybor[1]);
                pom2[1].add(wybor[3]);
                pom2[1].add(wybor[5]);
                pom2[1].add(wybor[7]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyWschod()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPoludniowyWschod()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPoludnie()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPoludniowyZachod()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyZachod()));
                            } else if (wybor[5].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPolnocnyZachod()));
                            } else if (wybor[6].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPolnoc()));
                            } else if (wybor[7].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrUwypuklajacyPolnocnyWschod()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[6])
            {

                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[6];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("LAPL1");
                wybor[1].setText("LAPL1");
                wybor[2].setText("LAPL3");
                wybor[3].setText("LAPL ukośny");
                wybor[4].setText("LAPL poziomy");
                wybor[5].setText("LAPL pionowy");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA LAPLACE'a<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 85));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);
                pom2[0].add(wybor[2]);

                pom2[1].add(wybor[3]);
                pom2[2].add(wybor[4]);
                pom2[2].add(wybor[5]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LAPL1()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LAPL2()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.LAPL3()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrLaplaceUkosny()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrLaplacePoziom()));
                            } else if (wybor[5].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrLaplacePion()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }

            if (e.getSource() == this.zadania[7])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[4];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Sobel-poziom");
                wybor[1].setText("Sobel-pion");
                wybor[2].setText("Prewitt-poziom");
                wybor[3].setText("Prewitt-pion");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA KONTUROWEGO<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);

                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[3]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrSobelPoziom()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrSobelPion()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPrewittPoziom()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPrewittPion()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[8])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[4];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Medianowy");
                wybor[1].setText("Minimalny");
                wybor[2].setText("Maksymalny");
                wybor[3].setText("Kuwahara");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA STATYCZNEGO<br><br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 100));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);

                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[3]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrMedianowy(obrP.pobierz_tab_raster()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrMinimalny(obrP.pobierz_tab_raster()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrMaksymalny(obrP.pobierz_tab_raster()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrKuwahara(obrP.pobierz_tab_raster()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[9])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[5];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Sobel: pion/poziom");
                wybor[1].setText("Prewitt: pion/poziom");
                wybor[2].setText("Laplace: pion/poziom");
                wybor[3].setText("Kierunkowy: PŁ-WS/PD-Z");
                wybor[4].setText("Kierunkowy: PŁ-Z/PD-WS");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>WYBIERZ RODZAJ FILTRA KOMBINOWANEGO<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(40, 72));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                pom1.add(wybor[0]);
                pom1.add(wybor[1]);
                pom1.add(wybor[2]);
                pom1.add(wybor[3]);
                pom1.add(wybor[4]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrNieLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrSobelPoziom(), OperacjeFiltrowanie.filtrSobelPion()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrNieLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPrewittPoziom(), OperacjeFiltrowanie.filtrPrewittPion()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrNieLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrLaplacePoziom(), OperacjeFiltrowanie.filtrLaplacePion()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrNieLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPolnocnyWschod(), OperacjeFiltrowanie.filtrPoludniowyZachod()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeFiltrowanie.filtrNieLiniowy(obrP.pobierz_tab_raster(), OperacjeFiltrowanie.filtrPolnocnyZachod(), OperacjeFiltrowanie.filtrPoludniowyWschod()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }

    }

    private class OperacjeMorfologiczneOkno extends JFrame implements MouseListener
    {

        private JLabel[] zadania = new JLabel[9];
        private JButton wroc;
        private OpcjePanel opcjePanel;

        public OperacjeMorfologiczneOkno()
        {
            this.setEnabled(false);
            this.setVisible(false);
        }

        public OperacjeMorfologiczneOkno(int x, int y)
        {

            this.uzupelnij();
            this.setUndecorated(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //OperacjeGeometryczne.ustaw_typ(typ);
            this.setPreferredSize(new Dimension(200, 650));
            this.getContentPane().setBackground(new Color(64, 64, 64));

            this.zmien_lokacje(x, y);
            this.pack();

            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setVisible(true);
        }

        private void uzupelnij()
        {
            JPanel pom = new JPanel();
            pom = new JPanel();
            pom.setLayout(new GridLayout(0, 1));
            pom.setBackground(new Color(64, 64, 64));

            this.wroc = new JButton("WRÓĆ");
            this.wroc.setBackground(new Color(128, 128, 128));
            this.wroc.setForeground(Color.WHITE);
            this.wroc.setFocusPainted(false);
            this.wroc.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.wroc.setPreferredSize(new Dimension(200, 37));
            this.wroc.addMouseListener(this);

            JLabel enter[] = new JLabel[15];
            for (int a = 0; a < enter.length; a++)
            {
                enter[a] = new JLabel("<html></html>");
            }
            zadania[0] = new JLabel("", SwingConstants.CENTER);
            zadania[0].setForeground(Color.white);
            zadania[0].setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 13));
            zadania[0].setText("<html><div align = center>OPERACJE MORFOLOGICZNE</html>");
            pom.add(zadania[0]);
            pom.add(enter[0]);

            for (int a = 1; a < zadania.length; a++)
            {
                zadania[a] = new JLabel("", SwingConstants.LEFT);
                zadania[a].setForeground(new Color(128, 128, 128));
                zadania[a].addMouseListener(this);
                zadania[a].setFont(new Font("Serif", Font.PLAIN, 16));
                pom.add(zadania[a]);

                pom.add(enter[a]);
            }

            //pom.add(enter[6]);
            //pom.add(enter[7]);
            //pom.add(enter[8]);
            //pom.add(enter[9]);
            pom.add(enter[10]);
            pom.add(enter[11]);
            pom.add(enter[12]);
            pom.add(enter[13]);
            // pom.add(enter[14]);

            zadania[1].setText("<html><div align = left>- Operacja dylacji</html>");

            zadania[2].setText("<html><div align = left>- Operacja erozji</html>");

            zadania[3].setText("<html><div align = left>- Operacja otwarcia</html>");

            zadania[4].setText("<html><div align = left>- Operacja zamknięcia</html>");

            zadania[5].setText("<html><div align = left>- Operacja \"Hit-or-Miss\"</html>");

            zadania[6].setText("<html><div align = left>- Operacja ścieniania</html>");

            zadania[7].setText("<html><div align = left>- Operacja pogrubiania</html>");

            zadania[8].setText("<html><div align = left>- Operacja pruningu</html>");

            this.add(pom, BorderLayout.CENTER);
            this.add(wroc, BorderLayout.SOUTH);
            this.addWindowStateListener(new WindowAdapter()
            {

                @Override
                public void windowStateChanged(WindowEvent we)
                {
                    if (we.getNewState() == Frame.ICONIFIED)
                    {
                        minimalizuj();
                    }
                    if (we.getNewState() == Frame.NORMAL)
                    {
                        maksymalizuj();
                    }

                }
            });
        }

        public void zmien_lokacje(int X, int Y)
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(X + 50, Y);

        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource() == this.wroc)
            {
                this.setVisible(false);
                this.setEnabled(false);
                this.dispose();

                rt.setEnabled(true);
                rt.setVisible(true);

            }
            if (e.getSource() == this.zadania[1])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[3];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("ES kwadrat 3x3");
                wybor[1].setText("ES krzyż 3x3");
                wybor[2].setText("ES koło 5x5");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>DYLACJA<br> WYBIERZ RODZAJ ELEMENTU STRUKTURALNEGO<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 90));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);

                pom2[1].add(wybor[1]);
                pom2[2].add(wybor[2]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaDylacji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kwadrat()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaDylacji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_krzyz()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaDylacji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kolo()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[2])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[3];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("ES kwadrat 3x3");
                wybor[1].setText("ES krzyż 3x3");
                wybor[2].setText("ES koło 5x5");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>EROZJA<br> WYBIERZ RODZAJ ELEMENTU STRUKTURALNEGO<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 90));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);

                pom2[1].add(wybor[1]);
                pom2[2].add(wybor[2]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaErozji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kwadrat()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaErozji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_krzyz()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaErozji(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kolo()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[3])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[3];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("ES kwadrat 3x3");
                wybor[1].setText("ES krzyż 3x3");
                wybor[2].setText("ES koło 5x5");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>OTWARCIE<br> WYBIERZ RODZAJ ELEMENTU STRUKTURALNEGO<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 90));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);

                pom2[1].add(wybor[1]);
                pom2[2].add(wybor[2]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaOtwarcia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kwadrat()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaOtwarcia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_krzyz()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaOtwarcia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kolo()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[4])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[3];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("ES kwadrat 3x3");
                wybor[1].setText("ES krzyż 3x3");
                wybor[2].setText("ES koło 5x5");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>ZAMKNIĘCIE<br> WYBIERZ RODZAJ ELEMENTU STRUKTURALNEGO<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 90));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);

                pom2[1].add(wybor[1]);
                pom2[2].add(wybor[2]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaZamkniecia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kwadrat()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaZamkniecia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_krzyz()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaZamkniecia(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_kolo()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[5])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[5];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Dolny lewy");
                wybor[1].setText("Dolny prawy");
                wybor[2].setText("Górny lewy");
                wybor[3].setText("Górny prawy");

                wybor[4].setText("Wszystkie");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>HIT & MISS(WYKRYWANIE ROGÓW)<br> WYBIERZ RODZAJ OPERACJI<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 85));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[3];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[0].add(wybor[1]);

                pom2[1].add(wybor[2]);
                pom2[1].add(wybor[3]);

                pom2[2].add(wybor[4]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);
                pom1.add(pom2[2]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaHitAndMissOne(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_dolnyLewy()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaHitAndMissOne(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_dolnyPrawy()));
                            } else if (wybor[2].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaHitAndMissOne(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_gornyLewy()));
                            } else if (wybor[3].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaHitAndMissOne(obrP.pobierz_tab_raster(), OperacjeMorfologiczne.ES_gornyPrawy()));
                            } else if (wybor[4].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaHitandMissAll(obrP.pobierz_tab_raster()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if (e.getSource() == this.zadania[6])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[2];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Znajdowanie krawędzi");
                wybor[1].setText("Znajdowanie szkieletu");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>ŚCIENIANIE <br> WYBIERZ RODZAJ OPERACJI<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 105));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[1].add(wybor[1]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPocienianieKrawedzie(obrP.pobierz_tab_raster()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPocienianieSzkielet(obrP.pobierz_tab_raster()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });

            }
            if (e.getSource() == this.zadania[7])
            {
                this.setEnabled(false);
                wygas();

                JRadioButton wybor[] = new JRadioButton[2];

                for (int a = 0; a < wybor.length; a++)
                {

                    wybor[a] = new JRadioButton();
                    wybor[a].setBackground(new Color(64, 64, 64));
                    wybor[a].setForeground(new Color(255, 255, 255));
                    wybor[a].setFocusable(false);
                    wybor[a].setFont(new Font("Tahoma", Font.BOLD, 11));

                }

                wybor[0].setText("Znajdowanie wypukłej otoczki obiektu");
                wybor[1].setText("Znajdowanie rozgraniczeń obiektów");

                JLabel opis0 = new JLabel("<html><div align = center> <font size  = 4>POGRUBIANIE <br> WYBIERZ RODZAJ OPERACJI<br><br></html>", SwingConstants.CENTER);
                opis0.setForeground(new Color(128, 128, 128));
                opis0.setPreferredSize(new Dimension(275, 105));

                JPanel enter[] = new JPanel[1];
                for (int a = 0; a < enter.length; a++)
                {
                    enter[a] = new JPanel();
                    enter[a].setBackground(new Color(64, 64, 64));
                }

                JPanel pom1 = new JPanel();
                pom1.setLayout(new GridLayout(0, 1));
                pom1.setBackground(new Color(64, 64, 64));

                JPanel pom2[] = new JPanel[2];

                for (int a = 0; a < pom2.length; a++)
                {
                    pom2[a] = new JPanel();
                    pom2[a].setLayout(new FlowLayout(FlowLayout.LEFT));
                    pom2[a].setBackground(new Color(64, 64, 64));
                }

                pom2[0].add(wybor[0]);
                pom2[1].add(wybor[1]);

                pom1.add(pom2[0]);
                pom1.add(pom2[1]);

                pom1.add(enter[0]);

                this.opcjePanel = new OpcjePanel(opis0, pom1);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);
                this.opcjePanel.ok.setEnabled(false);

                for (int a = 0; a < wybor.length; a++)
                {
                    wybor[a].addItemListener(new ItemListener()
                    {
                        @Override
                        public void itemStateChanged(ItemEvent e)
                        {
                            int ktory = 0;

                            for (int a = 0; a < wybor.length; a++)
                            {
                                if (e.getSource() == wybor[a])
                                {
                                    ktory = a;
                                    break;
                                }
                            }

                            if (wybor[ktory].isEnabled())
                            {
                                if (wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(false);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(true);
                                }
                                if (!wybor[ktory].isSelected())
                                {
                                    for (int a = 0; a < wybor.length; a++)
                                    {
                                        if (a != ktory)
                                        {
                                            wybor[a].setEnabled(true);
                                        }
                                    }
                                    opcjePanel.ok.setEnabled(false);

                                }
                            }
                        }

                    });
                }

                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if (opcjePanel.ok.isEnabled())
                        {

                            if (wybor[0].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPogrubianieWypukly(obrP.pobierz_tab_raster()));
                            } else if (wybor[1].isSelected())
                            {
                                obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPogrubianieRozgraniczenie(obrP.pobierz_tab_raster()));
                            }

                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
            }
            if(e.getSource() == this.zadania[8])
            {
                this.setEnabled(false);
                wygas();

                JSlider suwak = new JSlider(1, 80, 20);
                suwak.setBackground(new Color(64, 64, 64));
 

                JLabel opis = new JLabel("", SwingConstants.CENTER);

                opis.setForeground(new Color(128, 128, 128));
                opis.setText("<html><div align = center> <font size  = 4><br>PRUNING <br> USTAW ILOŚĆ POWTÓRZEŃ<br>" + suwak.getValue() + "</html>");
                opis.setPreferredSize(new Dimension(250, 80));

                JPanel jp = new JPanel();
                jp.setBackground(new Color(64, 64, 64));

                this.opcjePanel = new OpcjePanel(jp, opis, suwak);

                this.opcjePanel.setLocation(wez_lokacje()[0], wez_lokacje()[1]);

                this.opcjePanel.setFocusable(true);
                this.opcjePanel.anuluj.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.ok.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {

                        obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPruning(obrP.pobierz_tab_raster(), suwak.getValue()));
                   
                        opcjePanel.wylacz();
                        setEnabled(true);
                        toFront();
                        wlacz();

                    }
                });
                this.opcjePanel.suwak.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                           obrP.ustaw_tab_raster(OperacjeMorfologiczne.operacjaPruning(obrP.pobierz_tab_raster(), suwak.getValue()));
                         
                            opcjePanel.wylacz();
                            setEnabled(true);
                            toFront();
                            wlacz();

                        }

                    }
                });
                this.opcjePanel.suwak.addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        if (opcjePanel.suwak.isEnabled())
                        {
                            opcjePanel.zmien_wartosc_suwaka("pruning");
                        }
                    }
                });
            }

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(Color.white);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            for (int a = 1; a < this.zadania.length; a++)
            {
                if (e.getSource() == this.zadania[a])
                {
                    this.zadania[a].setForeground(new Color(128, 128, 128));
                }
            }
        }
    }
}
