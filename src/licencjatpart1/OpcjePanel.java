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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author shpaq
 */
public class OpcjePanel extends JFrame
{

    public JButton ok;
    public JButton anuluj;
    private JPanel pom;
    private JTextField poleTekstowe[] = new JTextField[2];
    public JSlider suwak = new JSlider();
    private JLabel opis;

    public OpcjePanel(JLabel opis, JTextField poleTekstowe)
    {

        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();
        this.opis = opis;

        this.poleTekstowe[0] = poleTekstowe;

        this.pom.add(this.opis, BorderLayout.NORTH);
        this.pom.add(this.poleTekstowe[0], BorderLayout.CENTER);

        this.add(this.pom, BorderLayout.CENTER);

        this.pack();
        //  this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);

    }

    public OpcjePanel(JLabel opis)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();
        this.opis = opis;

        this.pom.add(this.opis, BorderLayout.NORTH);

        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JPanel obraz2, JLabel opis)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

        this.opis = opis;
        this.pom.add(this.opis, BorderLayout.NORTH);
        this.pom.add(obraz2, BorderLayout.CENTER);

        JLabel enter = new JLabel("");
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);

    }

    public OpcjePanel(JPanel obraz2, JLabel opis, JSlider pasek)
    {
        this.suwak = pasek;
        this.opis = opis;

        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

        JPanel pom2 = new JPanel();
        pom2.setLayout(new BorderLayout());
        pom2.add(this.opis, BorderLayout.NORTH);
        pom2.add(obraz2, BorderLayout.SOUTH);
        pom2.add(this.suwak, BorderLayout.CENTER);
        pom2.setBackground(new Color(64, 64, 64));

        //this.pom.add(opis, BorderLayout.NORTH);
        //this.pom.add(obraz2, BorderLayout.CENTER);
        this.pom.add(pom2, BorderLayout.CENTER);

        JLabel enter = new JLabel("");
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JLabel opis, JRadioButton wybor1, JRadioButton wybor2, JSlider pasek, JLabel wartosc)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();
        this.suwak = pasek;

        this.opis = wartosc;

        JPanel pom2 = new JPanel();
        pom2.setLayout(new GridLayout(0, 1));
        pom2.setBackground(new Color(64, 64, 64));
        pom2.add(this.opis);

        pom2.add(this.suwak);

        JPanel pom1 = new JPanel();
        pom1.setLayout(new GridLayout(0, 1));
        pom1.setBackground(new Color(64, 64, 64));

        pom1.add(wybor1);
        pom1.add(wybor2);

        pom1.add(this.opis);

        pom1.add(suwak);

        this.pom.add(opis, BorderLayout.NORTH);
        this.pom.add(pom1, BorderLayout.CENTER);
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JLabel opis0, JLabel opis1, JLabel opis2, JTextField poleTekstowe1, JTextField poleTekstowe2)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

        this.poleTekstowe[0] = poleTekstowe1;
        this.poleTekstowe[1] = poleTekstowe2;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.add(opis1);
        panel1.add(poleTekstowe1);
        panel1.setBackground(new Color(64, 64, 64));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.add(opis2);
        panel2.add(poleTekstowe2);
        panel2.setBackground(new Color(64, 64, 64));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel2, BorderLayout.NORTH);

        panel3.setBackground(new Color(64, 64, 64));

        this.pom.add(opis0, BorderLayout.NORTH);
        this.pom.add(panel3, BorderLayout.CENTER);

        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JRadioButton wybor1, JRadioButton wybor2, JTextField poleTekstowe1, JTextField poleTekstowe2, JLabel opis1, JLabel opis2, JLabel opis3)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

        this.poleTekstowe[0] = poleTekstowe1;
        this.poleTekstowe[1] = poleTekstowe2;

        JPanel pom0 = new JPanel();
        pom0.setLayout(new GridBagLayout());
        pom0.setBackground(new Color(64, 64, 64));

        pom0.add(opis3);
        pom0.add(poleTekstowe1);

        JPanel pom00 = new JPanel();
        pom00.setLayout(new GridBagLayout());
        pom00.setBackground(new Color(64, 64, 64));

        pom00.add(opis2);
        pom00.add(poleTekstowe2);

        JLabel Syx = new JLabel("", SwingConstants.LEFT);
        Syx.setForeground(new Color(255, 255, 255));
        Syx.setPreferredSize(new Dimension(30, 15));
        Syx.setText("<html><div align = left><font size = 3><i>Sy i Sx:  </i></html>");

        JPanel pom000 = new JPanel();
        pom000.setLayout(new GridBagLayout());
        pom000.setBackground(new Color(64, 64, 64));
        pom000.add(Syx);
        // pom000.add(poleTekstowe3);

        JPanel pom1 = new JPanel();
        pom1.setLayout(new GridLayout(0, 1));
        pom1.setBackground(new Color(64, 64, 64));
        pom1.add(wybor1);
        pom1.add(wybor2);

        pom1.add(pom0);
        pom1.add(pom00);

        this.pom.add(opis1, BorderLayout.NORTH);
        this.pom.add(pom1, BorderLayout.CENTER);
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JLabel opis, JRadioButton wybor1, JRadioButton wybor2)
    {
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

        JPanel enter[] = new JPanel[4];
        for (int a = 0; a < enter.length; a++)
        {
            enter[a] = new JPanel();
            enter[a].setBackground(new Color(64, 64, 64));
        }

        JPanel pom1 = new JPanel();
        pom1.setLayout(new GridLayout(0, 1));
        pom1.setBackground(new Color(64, 64, 64));

        pom1.add(wybor1);
        pom1.add(wybor2);
        pom1.add(enter[0]);
        pom1.add(enter[1]);

        this.pom.add(opis, BorderLayout.NORTH);
        this.pom.add(pom1, BorderLayout.CENTER);
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }

    public OpcjePanel(JLabel opis, JPanel pom1)
    {
        
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(250, 200));
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.uzupelnij();

       
    

        this.pom.add(opis, BorderLayout.NORTH);
        this.pom.add(pom1, BorderLayout.CENTER);
        
        this.add(this.pom, BorderLayout.CENTER);

        this.pack();

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setEnabled(true);
    }
   

    private void uzupelnij()
    {
        this.poleTekstowe[0] = new JTextField("null");
        this.poleTekstowe[1] = new JTextField("null");

        this.pom = new JPanel();
        this.pom.setLayout(new BorderLayout());
        this.pom.setBackground(new Color(64, 64, 64));

        ok = new JButton("OK");
        ok.setBackground(new Color(128, 128, 128));
        ok.setForeground(Color.WHITE);
        ok.setFocusPainted(false);
        ok.setFont(new Font("Tahoma", Font.BOLD, 12));
        ok.setPreferredSize(new Dimension(125, 25));

        anuluj = new JButton("ANULUJ");
        anuluj.setBackground(new Color(128, 128, 128));
        anuluj.setForeground(Color.WHITE);
        anuluj.setFocusPainted(false);
        anuluj.setFont(new Font("Tahoma", Font.BOLD, 12));
        anuluj.setPreferredSize(new Dimension(125, 25));

        JPanel pom1 = new JPanel();
        pom1 = new JPanel();
        pom1.setLayout(new GridBagLayout());
        pom1.setBackground(new Color(64, 64, 64));
        pom1.add(this.ok);
        pom1.add(this.anuluj);

        JPanel pom2 = new JPanel();
        pom2.setLayout(new BorderLayout());
        pom2.setBackground(new Color(64, 64, 64));
        pom2.add(pom1, BorderLayout.SOUTH);
       this.pom.add(pom2, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void wylacz()
    {
        this.setEnabled(false);
        this.setVisible(false);
        this.dispose();
    }

    public boolean sprawdz_tekst(String s)
    {
        boolean war = true;
        for (int a = 0; a < this.poleTekstowe.length; a++)
        {
            if (!this.poleTekstowe[a].getText().equals("null"))
            {
                if (s.equals("calkowite"))
                {

                    try
                    {
                        Integer.parseInt(this.poleTekstowe[a].getText());
                    } catch (Exception e)
                    {
                        PopUpWindow puw = new PopUpWindow(this.getLocation().x, this.getLocation().y);

                        puw.nazwa("<html><div align = center>BŁĘDNE DANE<br>WPISZ LICZBE CAŁKOWITĄ</html>");
                        puw.wlacz();

                        war = false;
                    }
                } else if (s.equals("zmiennoprzecinkowe"))
                {
                    try
                    {
                        Double.parseDouble(this.poleTekstowe[a].getText());
                    } catch (Exception e)
                    {
                        PopUpWindow puw = new PopUpWindow(this.getLocation().x, this.getLocation().y);

                        puw.nazwa("<html><div align = center>BŁĘDNE DANE<br>WPISZ LICZBE ZMIENNOPRZECINKOWĄ</html>");
                        puw.wlacz();
                        war = false;
                    }
                } else if (s.equals("dzielenie"))
                {

                    try
                    {
                        Double.parseDouble(this.poleTekstowe[a].getText());
                        if (Double.parseDouble(this.poleTekstowe[a].getText()) == 0.0)
                        {
                            PopUpWindow puw = new PopUpWindow(this.getLocation().x, this.getLocation().y);
                            puw.nazwa("<html><div align = center>BŁĘDNE DANE<br>WPISZ LICZBE RÓŻNĄ OD ZERA</html>");
                            puw.wlacz();
                            war = false;
                        }
                    } catch (Exception e)
                    {
                        PopUpWindow puw = new PopUpWindow(this.getLocation().x, this.getLocation().y);

                        puw.nazwa("<html><div align = center>BŁĘDNE DANE<br>WPISZ LICZBE ZMIENNOPRZECINKOWĄ</html>");
                        puw.wlacz();
                        war = false;
                    }

                }
            }
        }
        return war;
    }

    public void zmien_wartosc_suwaka(String operacja)
    {
        if (operacja.equals("pierwiastkowanie"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW POTĘGĘ<br>" + (double) this.suwak.getValue() / 100 + "</html>");
        }
        if (operacja.equals("mieszanie"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW WSP MIESZANIA<br>" + (double) this.suwak.getValue() / 100 + "<br></html>");
        }
        if (operacja.equals("obracanie"))
        {
            if (suwak.getMaximum() == 4)
            {
                this.opis.setText("<html><div align = center> <font size  = 4>" + (double) suwak.getValue() * 90 + "</html>");
            }
            if (suwak.getMaximum() == 360)
            {
                this.opis.setText("<html><div align = center> <font size  = 4>" + (double) suwak.getValue() + "</html>");
            }

        }
        if (operacja.equals("kontrastowanie"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW KONTRAST<br>" + (double) this.suwak.getValue() / 100 + "</html>");
        }
        if (operacja.equals("jasnosc"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW JASNOŚĆ<br>" + (double) this.suwak.getValue() + "</html>");
        }
        if (operacja.equals("ekspozycja"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW EKSPOZYCJE<br>" + (double) this.suwak.getValue() / 100 + "</html>");
        }
        if (operacja.equals("gamma"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW GAMME<br>" + (double) this.suwak.getValue() / 100 + "</html>");
        }
        if (operacja.equals("sepia"))
        {
            this.opis.setText("<html><div align = center> <font size  = 4><br>USTAW SEPIE<br>" + (double) this.suwak.getValue() + "</html>");
        }

        if (operacja.equals("binaryzacja"))
        {
            for (int a = 0; a < this.suwak.getMaximum(); a++)
            {
                if (suwak.getValue() == a + 1)
                {
                    int pom = (a + 1) * 2 + 1;
                    this.opis.setText("<html><div align = center> <font size  = 4>" + pom + "x" + pom + "</html>");

                }
            }
        }
        if(operacja.equals("pruning"))
        {
             this.opis.setText("<html><div align = center> <font size  = 4><br>PRUNING <br> USTAW ILOŚĆ POWTÓRZEŃ<br>" + suwak.getValue() + "</html>");
        }
        //System.out.println((double) this.suwak.getValue() / 100);
        this.repaint();
    }

}
