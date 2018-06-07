/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

/**
 *
 * @author shpaq
 */
abstract public class OperacjeGeometryczne
{

    static public int[][][] translacja(int[][][] obrazGlowny, int pion, int poziom)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][obrazGlowny[0][0].length];
        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if ((a + b) % 2 == 0)
                    {
                        tab[a][b][k] = 0;
                    } else
                    {
                        tab[a][b][k] = 255;
                    }
                }
            }
        }
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (pion >= 0 && poziom >= 0)
                    {
                        if ((a + pion < tab.length && b + poziom < tab[a].length))
                        {
                            tab[a + pion][b + poziom][k] = obrazGlowny[a][b][k];
                        }
                    } else if (pion >= 0 && poziom < 0)
                    {
                        if (a + pion < tab.length && b + poziom >= 0)
                        {
                            tab[a + pion][b + poziom][k] = obrazGlowny[a][b][k];
                        }
                    } else if (pion < 0 && poziom >= 0)
                    {
                        if (a + pion >= 0 && b + poziom < tab[a].length)
                        {
                            tab[a + pion][b + poziom][k] = obrazGlowny[a][b][k];
                        }
                    } else if (pion < 0 && poziom < 0)
                    {
                        if (a + pion >= 0 && b + poziom >= 0)
                        {
                            tab[a + pion][b + poziom][k] = obrazGlowny[a][b][k];
                        }
                    }

                }
            }
        }

        System.out.println(pion);
        System.out.println(poziom);

        return tab;
    }

    static public int[][][] skalowanie_jednorodne(int[][][] obrazGlowny, double wspolczynnik)
    {
        if (wspolczynnik < 0.4)
        {
            wspolczynnik = 0.4;
        }
        if (wspolczynnik > 4)
        {
            wspolczynnik = 4;
        }

        int X = (int) Math.round((double) obrazGlowny.length * wspolczynnik);
        int Y = (int) Math.round((double) obrazGlowny[0].length * wspolczynnik);

        int tab[][][] = new int[X][Y][obrazGlowny[0][0].length];

        double ratioX = (double) obrazGlowny.length / (double) tab.length;
        double ratioY = (double) obrazGlowny[0].length / (double) tab[0].length;

        double x = 0.0;
        double y = 0.0;

        int xWejsciowe = 0;
        int yWejsciowe = 0;

        double wspPoziom = 0.0;
        double wspPion = 0.0;

        double interpolacjaPion[] = new double[3];
        double interpolacjaPoziom = 0.0;

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    x = (double) a * ratioX;
                    y = (double) b * ratioY;

                    xWejsciowe = (int) Math.floor(x);
                    yWejsciowe = (int) Math.floor(y);

                    wspPoziom = (x - xWejsciowe);
                    wspPion = (y - yWejsciowe);

//                    try
//                    {
//                        for (int aa = 0, bb = -1; bb <= 1; bb++, aa++)
//                        {
//                            interpolacjaPion[aa] = obrazGlowny[xWejsciowe][yWejsciowe+bb][k]
//                                    + (obrazGlowny[xWejsciowe +1][yWejsciowe + bb][k] - obrazGlowny[xWejsciowe -1][yWejsciowe +bb][k]) * wspPion
//                                    + (obrazGlowny[xWejsciowe -1][yWejsciowe +bb][k] - 2 * obrazGlowny[xWejsciowe ][yWejsciowe+bb][k] + obrazGlowny[xWejsciowe +1][yWejsciowe + bb][k]) * wspPion * wspPion;
//                        }
//                        interpolacjaPoziom = interpolacjaPion[1] + (interpolacjaPion[2] - interpolacjaPion[0])*wspPoziom +(interpolacjaPion[0] - 2*interpolacjaPion[1] +interpolacjaPion[2])*wspPoziom*wspPoziom;
//                        if(interpolacjaPoziom >=255)
//                        {
//                            interpolacjaPoziom = 255;
//                        }
//                        if(interpolacjaPoziom <=0)
//                        {
//                            interpolacjaPoziom =0;
//                        }
//
//                    } catch (Exception e)
//                    {
//                        System.out.println("Interpolacja error out of bounds");
//                    }
                    ///////////INTERPOLACJA DWULINIOWA////////////
                    try
                    {
                        if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                        } else if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k];
                        } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                        } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe + 1][k];
                        }

                        interpolacjaPoziom = (1 - wspPion) * interpolacjaPion[0] + (wspPion) * interpolacjaPion[1];

                    } catch (Exception e)
                    {
                        System.out.println("Interpolacja error out of bounds");
                    }
                    tab[a][b][k] = (int) Math.round(interpolacjaPoziom);

                }
            }
        }
        if (wspolczynnik < 1)
        {
            double suma = 0;

            for (int a = 1; a < tab.length - 1; a++)
            {
                for (int b = 1; b < tab[a].length - 1; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int xx = -1; xx <= 1; xx++)
                        {
                            for (int yy = -1; yy <= 1; yy++)
                            {
                                if (yy == 0 && xx == 0)
                                {
                                    suma = suma + (tab[a + xx][b + yy][k] * 32);

                                } else
                                {
                                    suma = suma + tab[a + xx][b + yy][k];
                                }

                            }
                        }
                        suma = Math.round(suma / 40.0);
                        tab[a][b][k] = (int) suma;
                        suma = 0;
                    }
                }
            }
        }

        return tab;
    }

    static public int[][][] skalowanie_niejednorodne(int[][][] obrazGlowny, double Sx, double Sy)
    {
        if (Sx < 0.4)
        {
            Sx = 0.4;
        }
        if (Sy < 0.4)
        {
            Sy = 0.4;
        }
        if (Sx > 4)
        {
            Sx = 4;
        }
        if (Sy > 4)
        {
            Sy = 4;
        }
        int X = (int) Math.round((double) obrazGlowny.length * Sx);
        int Y = (int) Math.round((double) obrazGlowny[0].length * Sy);

        int tab[][][] = new int[X][Y][obrazGlowny[0][0].length];

        double ratioX = (double) obrazGlowny.length / (double) tab.length;
        double ratioY = (double) obrazGlowny[0].length / (double) tab[0].length;

        double x = 0.0;
        double y = 0.0;

        int xWejsciowe = 0;
        int yWejsciowe = 0;

        double wspPoziom = 0.0;
        double wspPion = 0.0;

        double interpolacjaPion[] = new double[2];
        double interpolacjaPoziom = 0.0;

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    x = (double) a * ratioX;
                    y = (double) b * ratioY;

                    xWejsciowe = (int) Math.floor(x);
                    yWejsciowe = (int) Math.floor(y);

                    wspPoziom = x - xWejsciowe;
                    wspPion = y - yWejsciowe;

                    try
                    {
                        if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                        } else if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k];
                        } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                        } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                        {
                            interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                            interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe + 1][k];
                        }

                        interpolacjaPoziom = (1 - wspPion) * interpolacjaPion[0] + (wspPion) * interpolacjaPion[1];

                    } catch (Exception e)
                    {
                        System.out.println("Interpolacja error out of bounds");
                    }

                    tab[a][b][k] = (int) Math.round(interpolacjaPoziom);

                }
            }
        }
        if (Sx < 1 || Sy < 1)
        {
            double suma = 0;

            for (int a = 1; a < tab.length - 1; a++)
            {
                for (int b = 1; b < tab[a].length - 1; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int xx = -1; xx <= 1; xx++)
                        {
                            for (int yy = -1; yy <= 1; yy++)
                            {
                                if (yy == 0 && xx == 0)
                                {
                                    suma = suma + (tab[a + xx][b + yy][k] * 32);

                                } else
                                {
                                    suma = suma + tab[a + xx][b + yy][k];
                                }

                            }
                        }
                        suma = Math.round(suma / 40.0);
                        tab[a][b][k] = (int) suma;
                        suma = 0;
                    }
                }
            }
        }

        return tab;
    }

    static public int[][][] obracanie_o_dowolny_kat(int[][][] obrazGlowny, double kat)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if ((a + b) % 2 == 0)
                    {
                        tab[a][b][k] = 0;
                    } else
                    {
                        tab[a][b][k] = 255;
                    }
                }
            }
        }

        double x = 0;
        double y = 0;
        double xS = ((double) (tab.length - 1) / 2.0);
        double yS = ((double) (tab[0].length - 1) / 2.0);

        int xWejsciowe = 0;
        int yWejsciowe = 0;

        double wspPoziom = 0.0;
        double wspPion = 0.0;

        double interpolacjaPion[] = new double[2];
        double interpolacjaPoziom = 0.0;

        kat = kat * (-1);
        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    x = xS + (a - xS) * (Math.cos(Math.toRadians(kat))) - (b - yS) * (Math.sin(Math.toRadians(kat)));
                    y = yS + (a - xS) * (Math.sin(Math.toRadians(kat))) + (b - yS) * (Math.cos(Math.toRadians(kat)));
                    double cos = Math.sin(Math.toRadians(kat));
                    double co2 = Math.cos(Math.toRadians(kat));

                    if (((int) Math.floor(x) >= 0 && (int) Math.floor(x) < tab.length) && ((int) Math.floor(y) >= 0 && (int) Math.floor(y) < tab[0].length))
                    {
                        xWejsciowe = (int) Math.floor(x);
                        yWejsciowe = (int) Math.floor(y);

                        wspPoziom = x - xWejsciowe;
                        wspPion = y - yWejsciowe;

                        try
                        {

                            if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                            {
                                interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                                interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                            } else if (xWejsciowe + 1 >= obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                            {
                                interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k];
                                interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k];
                            } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 >= obrazGlowny[0].length)
                            {
                                interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                                interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                            } else if (xWejsciowe + 1 < obrazGlowny.length && yWejsciowe + 1 < obrazGlowny[0].length)
                            {
                                interpolacjaPion[0] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe][k];
                                interpolacjaPion[1] = (1 - wspPoziom) * obrazGlowny[xWejsciowe][yWejsciowe + 1][k] + (wspPoziom) * obrazGlowny[xWejsciowe + 1][yWejsciowe + 1][k];
                            }

                            interpolacjaPoziom = (1 - wspPion) * interpolacjaPion[0] + (wspPion) * interpolacjaPion[1];

                        } catch (Exception e)
                        {
                            System.out.println("Interpolacja error out of bounds");
                        }

                        tab[a][b][k] = (int) Math.round(interpolacjaPoziom);
                    }
                }
            }
        }

        return tab;
    }

    static public int[][][] obrazanie_o_wielokrotnosc_90(int[][][] obrazGlowny, double wielokrotnosc)
    {

        wielokrotnosc = wielokrotnosc * 90;
        int tab[][][] = obrazGlowny;
        int tab2[][][] = new int[0][0][0];

        if (wielokrotnosc == 0 || wielokrotnosc == 180 || wielokrotnosc == 360)
        {
            tab2 = new int[obrazGlowny.length][obrazGlowny[0].length][3];
        }
        if (wielokrotnosc == 90 || wielokrotnosc == 270)
        {
            tab2 = new int[obrazGlowny[0].length][obrazGlowny.length][3];
        }

        double x = 0;
        double y = 0;
        double xS = ((double) (tab.length - 1) / 2.0);
        double yS = ((double) (tab[0].length - 1) / 2.0);

        // wielokrotnosc = wielokrotnosc *-1;
        for (int a = 0; a < tab2.length; a++)
        {
            for (int b = 0; b < tab2[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (wielokrotnosc == 360 || wielokrotnosc == 0)
                    {
                        tab2 = obrazGlowny;
                    }
                    if (wielokrotnosc == 90)
                    {
                        tab2[a][b][k] = obrazGlowny[b][obrazGlowny[0].length - 1 - a][k];
                    }
                    if (wielokrotnosc == 180)
                    {
                        tab2[a][b][k] = obrazGlowny[a][obrazGlowny[0].length - 1 - b][k];
                    }
                    if (wielokrotnosc == 270)
                    {
                        try
                        {
                            tab2[a][b][k] = obrazGlowny[obrazGlowny.length - 1 - b][a][k];
                        } catch (Exception e)
                        {

                        }

                    }
                }
            }

        }
        tab = tab2;
        return tab;
    }

    static public int[][][] odbicie_lustrzane_pion(int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    tab[a][b][k] = obrazGlowny[a][obrazGlowny[0].length - 1 - b][k];
                }
            }
        }
        return tab;
    }

    static public int[][][] odbicie_lustrzane_poziom(int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    tab[a][b][k] = obrazGlowny[obrazGlowny.length - 1 - a][b][k];
                }
            }
        }
        return tab;
    }

    static public int[][][] kadrowanie(int[][][] obrazGlowny, int x, int y, int w, int h)
    {
        if (w == 0 || h == 0)
        {
            return obrazGlowny;
        }
        int tab[][][] = new int[w][h][3];
        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    tab[a][b][k] = obrazGlowny[x + a][y + b][k];
                }
            }
        }
        
        return tab;
    }

}
