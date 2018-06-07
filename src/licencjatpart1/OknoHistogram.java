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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author shpaq
 */
public class OknoHistogram extends JFrame
{

    private histogram histogram;

    private JLabel opis;
    private JButton zamknij;
    private int x_y[] = new int[2];
    private String typ;

    public OknoHistogram(int x, int y, String typ)
    {
        super("Graficzna reprezentacja histogramu i LUT");
        this.setUndecorated(true);
        this.typ = typ;
        this.setLayout(new BorderLayout());

        this.setLocation(x, y);

        this.uzupelnij();

        this.add(histogram, BorderLayout.CENTER);

        this.add(opis, BorderLayout.NORTH);

        this.setPreferredSize(new Dimension(279, 235));

        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();

        this.setAlwaysOnTop(false);

        this.setResizable(false);

        this.setAlwaysOnTop(true);
        this.setVisible(true);

    }

    private void uzupelnij()
    {
        this.histogram = new histogram();

        this.zamknij = new JButton("ZAMKNIJ");
        this.zamknij.setBackground(new Color(128, 128, 128));
        this.zamknij.setForeground(Color.WHITE);
        this.zamknij.setFocusPainted(false);
        this.zamknij.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.zamknij.setPreferredSize(new Dimension(200, 37));

        this.zamknij.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                dispose();
            }
        });

        this.opis = new JLabel("", SwingConstants.CENTER);
        this.opis.setForeground(Color.white);
        this.opis.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        this.opis.setText("<html><div align = center>HISTOGRAM</html>");

        /////////PRZESUWANIE OKNA
//        this.addMouseMotionListener(new MouseAdapter()
//        {
//            @Override
//            public void mouseDragged(MouseEvent e)
//            {
//                int x = e.getXOnScreen();
//                int y = e.getYOnScreen();
//
//                setLocation(x - x_y[0], y - x_y[1]);
//            }
//        });
//        this.addMouseListener(new MouseAdapter()
//        {
//            @Override
//            public void mousePressed(MouseEvent e)
//            {
//
//                x_y[0] = e.getX();
//                x_y[1] = e.getY();
//            }
//        });
    }

    public void rysuj_hist(int tab[][])
    {
        this.histogram.rysuj_hist(tab);
    }

    public void zmien_lokacje(int x, int y)
    {
        this.setLocation(x, y);
    }

    private class histogram extends JPanel
    {

        int tab[][] = new int[256][3];
        double skala[] = new double[3];

        public histogram()
        {
            this.setPreferredSize(new Dimension(275, 200));

            this.setBackground(new Color(64, 64, 64));
        }

        public void rysuj_hist(int tab[][])
        {

            this.tab = tab;
            if (typ.equals("RGB"))
            {
                int max[] =
                {
                    0, 0, 0
                };
                for (int a = 0; a < tab.length; a++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        if (max[k] < tab[a][k])
                        {
                            max[k] = tab[a][k];
                        }
                    }

                }
                for (int a = 0; a < 3; a++)
                {
                    skala[a] = ((double) 56 / (double) max[a]) * 10.0;
                    skala[a] = skala[a] / 10.0;
                }
            }
            if (typ.equals("GrayScale"))
            {

                int max = 0;
                for (int a = 0; a < tab.length; a++)
                {

                    if (max < tab[a][0])
                    {
                        max = tab[a][0];
                    }

                }

                skala[0] = ((double) (56 + 60 + 60) / (double) max) * 10.0;
                skala[0] = skala[0] / 10.0;

            }

            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g
        )
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(168, 168, 168));
            g2d.fillRect(10 + 1, 10 + 1, 257, 180 - 1);
            g2d.setColor(Color.WHITE);
            if (typ.equals("RGB"))
            {
                g2d.drawRect(10, 10, 258, 60);
                g2d.drawRect(10, 10 + 60, 258, 60);
                g2d.drawRect(10, 10 + 60 + 60, 258, 60);
            }
            if (typ.equals("GrayScale"))
            {
                g2d.drawRect(10, 10, 258, 180);
            }
            g2d.setColor(Color.RED);

            if (typ.equals("RGB"))
            {
                for (int a = 10; a < 266; a++)
                {

                    
                    g2d.setColor(new Color(192, 0, 0));
                    if (tab[a - 10][0] != 0)
                    {
                        if (Math.round(tab[a - 10][0] * skala[0]) > 0 && Math.round(tab[a - 10][0] * skala[0]) < 1)
                        {
                            g2d.drawRect(a + 1, 68 - (int) Math.ceil(tab[a - 10][0] * skala[0]), 1, Math.abs(-(int) Math.ceil(tab[a - 10][0] * skala[0])) + 1);
                        } else
                        {
                            g2d.drawRect(a + 1, 68 - (int) Math.round(tab[a - 10][0] * skala[0]), 1, Math.abs(-(int) Math.round(tab[a - 10][0] * skala[0])) + 1);

                        }
                    }

                    g2d.setColor(new Color(0, 192, 0));
                    if (tab[a - 10][1] != 0)
                    {
                        if (Math.round(tab[a - 10][1] * skala[1]) > 0 && Math.round(tab[a - 10][1] * skala[1]) < 1)
                        {
                            g2d.drawRect(a + 1, 68 + 60 - (int) Math.ceil(tab[a - 10][1] * skala[1]), 1, Math.abs(-(int) Math.ceil(tab[a - 10][1] * skala[1])) + 1);
                        } else
                        {
                            g2d.drawRect(a + 1, 68 + 60 - (int) Math.round(tab[a - 10][1] * skala[1]), 1, Math.abs(-(int) Math.round(tab[a - 10][1] * skala[1])) + 1);

                        }
                    }
                    g2d.setColor(new Color(0, 0, 192));
                    if (tab[a - 10][2] != 0)
                    {
                        if (Math.round(tab[a - 10][2] * skala[2]) > 0 && Math.round(tab[a - 10][2] * skala[2]) < 1)
                        {
                            g2d.drawRect(a + 1, 68+ 60+60 - (int) Math.ceil(tab[a - 10][2] * skala[2]), 1, Math.abs(-(int) Math.ceil(tab[a - 10][2] * skala[2])) + 1);
                        } else
                        {
                            g2d.drawRect(a + 1, 68+60+60 - (int) Math.round(tab[a - 10][2] * skala[2]), 1, Math.abs(-(int) Math.round(tab[a - 10][2] * skala[2])) + 1);

                        }
                    }
                }
            }
            if (typ.equals("GrayScale"))
            {
                g2d.setColor(new Color(64, 64, 64));

                for (int a = 10; a < 266; a++)
                {
                   if (tab[a - 10][0] != 0)
                    {
                        if (Math.round(tab[a - 10][0] * skala[0]) > 0 && Math.round(tab[a - 10][0] * skala[0]) < 1)
                        {
                            g2d.drawRect(a + 1, 68 + 60+60 - (int) Math.ceil(tab[a - 10][0] * skala[0]), 1, Math.abs(-(int) Math.ceil(tab[a - 10][0] * skala[0])) + 1);
                        } else
                        {
                            g2d.drawRect(a + 1, 68+60+60 - (int) Math.round(tab[a - 10][0] * skala[0]), 1, Math.abs(-(int) Math.round(tab[a - 10][0] * skala[0])) + 1);

                        }
                    }
                }
            }
        }

    }
}
