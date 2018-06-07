/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author shpaq
 */
public class ObrazekPanel extends JPanel
{

    private BufferedImage obrazek_wejsciowy;
    private WritableRaster raster_obrazek_wejsciowy;

    private BufferedImage obraz_operacyjny;
    private WritableRaster raster_obraz_operacyjny;

    private int tab_raster[][][];

    private JFileChooser zapis;
    private final int pomiar = 300;
    private boolean rysuj = false;

    public ObrazekPanel(String s)
    {
        dodaj_obrazek(s);
        this.setPreferredSize(new Dimension(obrazek_wejsciowy.getWidth(), obrazek_wejsciowy.getHeight()));

        this.ustawObrazOperacyjny(this.get_type_wejsciowy());

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

    }

    public void ustawObrazOperacyjny(String typ)
    {

        ///////////////////SZARY 24 bit na SZARY 8 bit
        Matcher m = Pattern.compile("GrayScale_24bit").matcher(typ);
        if (m.find())
        {
            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    ww[1] = 0;
                    ww[2] = 0;
                    raster_obraz_operacyjny.setPixel(a, b, ww);
                    raster_obraz_operacyjny.getPixel(a, b, ww);

                }
            }
            this.repaint();
        }
        ///////KOLOROWY 24 bit
        m = Pattern.compile("RGB_24bit").matcher(typ);
        if (m.find())
        {
            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    raster_obraz_operacyjny.setPixel(a, b, ww);

                }
            }
            this.repaint();
        }
        ////////////SZARY 8 bit
        m = Pattern.compile("GrayScale_8bit").matcher(typ);
        if (m.find())
        {
            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    raster_obraz_operacyjny.setPixel(a, b, ww);

                }
            }
            this.repaint();
        }
        /////////////BINARNY 24 bit na BINARNY 1 bit
        m = Pattern.compile("Binary_24bit").matcher(typ);
        if (m.find())
        {

            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    ww[1] = 0;
                    ww[2] = 0;
                    if (ww[0] < 128)
                    {
                        ww[0] = 0;
                    } else
                    {
                        ww[0] = 255;
                    }
                    raster_obraz_operacyjny.setPixel(a, b, ww);

                }
            }
            this.repaint();

        }
        //////////////////////////SZARY 8 bit na BINARNY 1 bit
        m = Pattern.compile("Binary_8bit").matcher(typ);
        if (m.find())
        {

            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);

                    if (ww[0] < 128)
                    {
                        ww[0] = 0;
                    } else
                    {
                        ww[0] = 255;
                    }
                    raster_obraz_operacyjny.setPixel(a, b, ww);

                }
            }
            this.repaint();
        }
        //////////////BINARNY 1 bit
        m = Pattern.compile("Binary_1bit").matcher(typ);
        if (m.find())
        {

            this.obraz_operacyjny = new BufferedImage(this.obrazek_wejsciowy.getWidth(), this.obrazek_wejsciowy.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();
            int ww[] =
            {
                0, 0, 0
            };
            for (int a = 0; a < this.raster_obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < this.raster_obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);

                    raster_obraz_operacyjny.setPixel(a, b, ww);

                }
            }
            this.repaint();
        }
        ////////////WKLADAM RASTER D TABLICY
        this.tab_raster = new int[obraz_operacyjny.getWidth()][obraz_operacyjny.getHeight()][3];
        int ww[] = new int[3];
        for (int a = 0; a < this.obraz_operacyjny.getWidth(); a++)
        {
            for (int b = 0; b < this.obraz_operacyjny.getHeight(); b++)
            {
                this.raster_obraz_operacyjny.getPixel(a, b, ww);

                for (int k = 0; k < 3; k++)
                {
                    this.tab_raster[a][b][k] = ww[k];
                }
            }
        }
    }

    public void dodaj_obrazek(String s)
    {
        try
        {
            obrazek_wejsciowy = ImageIO.read(new File(s));
            raster_obrazek_wejsciowy = obrazek_wejsciowy.getRaster();

        } catch (Exception e)
        {
        }
        rysuj = true;

    }

    public void zapisz_obrazek()
    {
        String desktop = System.getProperty("user.home");
        zapis = new JFileChooser(desktop + "/Desktop");
        zapis.addChoosableFileFilter(new jpgSaveFilter());

        zapis.setAcceptAllFileFilterUsed(true);

        int saveValue = zapis.showSaveDialog(null);

        if (saveValue == JFileChooser.APPROVE_OPTION)
        {
            String rozszerzenie = "bmp";
            try
            {
                ImageIO.write(obraz_operacyjny, rozszerzenie, new File(zapis.getSelectedFile() + ".jpg"));
            } catch (Exception e)
            {
                System.out.println("blad zapisu");
            }

        }
    }

    public int[] pobierz_rozmiar()
    {
        int tab[] = new int[2];
        tab[0] = this.obraz_operacyjny.getWidth();
        tab[1] = this.obraz_operacyjny.getHeight();

        return tab;
    }

    public String get_type_wejsciowy()
    {
        String typ = "";
        ///////////////24 bitowe
        if (obrazek_wejsciowy.getType() == 5)
        {

            long tab[][][] = new long[obrazek_wejsciowy.getWidth()][obrazek_wejsciowy.getHeight()][3];
            boolean pom[][][] = new boolean[obrazek_wejsciowy.getWidth()][obrazek_wejsciowy.getHeight()][3];
            int ww[] = new int[3];
            for (int a = 0; a < obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    tab[a][b][0] = ww[0];
                    tab[a][b][1] = ww[1];
                    tab[a][b][2] = ww[2];
                }
            }
            for (int a = 0; a < tab.length; a++)
            {
                for (int b = 0; b < tab[a].length; b++)
                {
                    //////////KOLOROWY RGB 24bit////////////
                    if ((tab[a][b][0] != tab[a][b][1] && tab[a][b][1] != tab[a][b][2] && tab[a][b][2] != tab[a][b][0]) ///////3 kanaly rozne
                            || (tab[a][b][0] != tab[a][b][1]) //// lub 1 rozny od 2
                            || (tab[a][b][0] != tab[a][b][2]) //// lub 1 rozny od 3
                            || (tab[a][b][1] != tab[a][b][2])) //// lub 2 rozny od 3
                    {
                        pom[a][b][0] = true;
                        return "RGB_24bit";
                    } else
                    {
                        pom[a][b][0] = false;
                    }
                    /////////////ODCIENIE SZAROSCI 24 bit /////////////

                    if (tab[a][b][1] == tab[a][b][0] && tab[a][b][1] == tab[a][b][2] && tab[a][b][0] == tab[a][b][2])
                    {
                        if (tab[a][b][0] >= 10 && tab[a][b][0] < 245)
                        {
                            pom[a][b][1] = true;
                        } else
                        {
                            pom[a][b][1] = false;
                        }
                    } else
                    {
                        //////////////////////WYSTARCZY JEZELI JEDEN PIKSEL NIE MA 3 TAKICH SAMYCH WARTOSCI W 3 KANALACH I OBRAZ NIE BEDZIE GREYSCALE//////
                        return "RGB_24bit";
                    }

                    ///////////////////////BINARY 24bit//////////
                    if (tab[a][b][1] == tab[a][b][0] && tab[a][b][1] == tab[a][b][2] && tab[a][b][0] == tab[a][b][2])
                    {
                        if (tab[a][b][0] < 10 || tab[a][b][0] >= 245)
                        {
                            pom[a][b][2] = true;
                        } else
                        {
                            pom[a][b][2] = false;

                        }
                    } else
                    {
                        return "RGB_24bit";
                    }
                }
            }
            for (int a = 0, ileT[] = new int[3], ileF[] = new int[3]; a < pom.length; a++)
            {
                for (int b = 0; b < pom[a].length; b++)
                {
                    //////////ZLICZANIE WARUNKOW ZA I PRZECIW DLA RGB 24 bit
                    if (pom[a][b][0])
                    {
                        ileT[0]++;
                    } else
                    {
                        ileF[0]++;
                    }
                    ///////////ZLICZANIE WARUNKOW ZA I PRZECIW DLA GREYSCALE 24BIT
                    if (pom[a][b][1])
                    {
                        ileT[1]++;
                    } else
                    {
                        ileF[1]++;
                    }
                    ///////////////JAK WYZEJ DLA BINARY 24 bit
                    if (pom[a][b][2])
                    {
                        ileT[2]++;
                    } else
                    {
                        ileF[2]++;
                    }

                    if (a == pom.length - 1 && b == pom[a].length - 1)
                    {
                        if (ileT[0] * 0.2 > ileF[0] * 0.8)
                        {
                            typ = "RGB_24bit";
                        }

                        if (ileT[1] * 0.2 > ileF[1] * 0.8)
                        {
                            typ = "GrayScale_24bit";
                        }

                        if (ileT[2] * 0.2 > ileF[2] * 0.8)
                        {
                            typ = "Binary_24bit";
                        }

                    }
                }

            }
            /////////////////////////////8 bitowe////////////////
        } else if (obrazek_wejsciowy.getType() == 10)
        {

            int tab[][][] = new int[obrazek_wejsciowy.getWidth()][obrazek_wejsciowy.getHeight()][3];
            boolean pom[][][] = new boolean[obrazek_wejsciowy.getWidth()][obrazek_wejsciowy.getHeight()][2];
            int ww[] = new int[3];
            for (int a = 0; a < obrazek_wejsciowy.getWidth(); a++)
            {
                for (int b = 0; b < obrazek_wejsciowy.getHeight(); b++)
                {
                    raster_obrazek_wejsciowy.getPixel(a, b, ww);
                    tab[a][b][0] = ww[0];
                    tab[a][b][1] = ww[1];
                    tab[a][b][2] = ww[2];
                }
            }
            for (int a = 0; a < tab.length; a++)
            {
                for (int b = 0; b < tab[a].length; b++)
                {
                    /////////////////////8 bit greyscale///////
                    if (tab[a][b][0] >= 10 && tab[a][b][0] < 245)
                    {
                        pom[a][b][0] = true;
                    } else
                    {
                        pom[a][b][0] = false;
                    }
                    //////////////8bit binary
                    if (tab[a][b][0] < 10 || tab[a][b][0] >= 245)
                    {
                        pom[a][b][1] = true;
                    } else
                    {
                        pom[a][b][1] = false;
                    }

                }
            }
            for (int a = 0, ileT[] = new int[2], ileF[] = new int[2]; a < pom.length; a++)
            {
                for (int b = 0; b < pom[a].length; b++)
                {
                    if (pom[a][b][0])
                    {
                        ileT[0]++;
                    } else
                    {
                        ileF[0]++;
                    }
                    if (pom[a][b][1])
                    {
                        ileT[1]++;
                    } else
                    {
                        ileF[1]++;
                    }
                    if (a == pom.length - 1 && b == pom[a].length - 1)
                    {
                        if (ileT[0] * 0.2 > ileF[0] * 0.8)
                        {
                            typ = "GrayScale_8bit";
                        }

                        if (ileT[1] * 0.2 > ileF[1] * 0.8)
                        {
                            typ = "Binary_8bit";
                        }

                    }
                }

            }

        } else if (obrazek_wejsciowy.getType() == 12)
        {
            typ = "Binary_1bit";
        }

        return typ;
    }

    public String get_type_operacyjny()
    {
        String pom = "";
        if (this.obraz_operacyjny.getType() == 5)
        {
            pom = "RGB";
        } else if (this.obraz_operacyjny.getType() == 10)
        {
            pom = "GrayScale";
        } else if (this.obraz_operacyjny.getType() == 12)
        {
            pom = "Binary";
        }
        return pom;
    }

    public int[][][] pobierz_tab_raster()
    {
        return this.tab_raster;
    }

    public void cofnij_zmiany()
    {

        this.ustawObrazOperacyjny(this.get_type_wejsciowy());
        this.repaint();

    }

    public void ustaw_tab_raster(int tab[][][])
    {
        this.tab_raster = tab;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        boolean flaga = false;
        if (this.obraz_operacyjny.getWidth() != this.tab_raster.length || this.obraz_operacyjny.getHeight() != this.tab_raster[0].length)
        {
            flaga = true;
            int typ = this.obraz_operacyjny.getType();
            this.obraz_operacyjny = new BufferedImage(this.tab_raster.length, this.tab_raster[0].length, typ);
            this.raster_obraz_operacyjny = this.obraz_operacyjny.getRaster();

            this.setPreferredSize(new Dimension(this.tab_raster.length, this.tab_raster[0].length));

        }
        for (int a = 0; a < this.obraz_operacyjny.getWidth(); a++)
        {
            for (int b = 0; b < this.obraz_operacyjny.getHeight(); b++)
            {

                this.raster_obraz_operacyjny.setPixel(a, b, this.tab_raster[a][b]);
            }
        }

        Graphics2D g2d = (Graphics2D) g;

        if (rysuj)
        {
            int srodek_Y = this.getHeight() / 2 - this.obraz_operacyjny.getHeight() / 2;
            int srodek_X = this.getWidth() / 2 - this.obraz_operacyjny.getWidth() / 2;
            g2d.drawImage(obraz_operacyjny, srodek_X, srodek_Y, this);
        }
        if (flaga)
        {
            this.setSize(new Dimension(this.tab_raster.length, this.tab_raster[0].length));
            flaga = false;
        }

    }

    private class jpgSaveFilter extends FileFilter
    {

        @Override
        public boolean accept(File f)
        {
            if (f.isDirectory())
            {
                return false;
            }
            String s = f.getName();
            return s.endsWith(".jpg") || s.endsWith(".JPG");
        }

        @Override
        public String getDescription()
        {
            return "*.jpg,*.JPG";
        }

    }

}
