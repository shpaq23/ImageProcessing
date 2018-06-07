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
public abstract class OperacjeHistogram
{

    static public String typ;

    static public int[][] obliczHistogram(int[][][] obrazGlowny)
    {
        int tab[][] = new int[256][3];
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (obrazGlowny[a][b][k] < tab.length)
                    {
                        tab[obrazGlowny[a][b][k]][k]++;
                    }
                }
            }
        }

        return tab;
    }

    static private int[][][] obliczTabzLUT(int[][] LUT, int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (obrazGlowny[a][b][k] < LUT.length)
                    {
                        tab[a][b][k] = LUT[obrazGlowny[a][b][k]][k];
                    }
                }
            }
        }

        return tab;
    }

    static public int[][][] rozciagnijHistogram(int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
                    }
                    if (min[k] > obrazGlowny[a][b][k])
                    {
                        min[k] = obrazGlowny[a][b][k];
                    }
                }
            }
        }

        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[0].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    tab[a][b][k] = (int) Math.round((255 * (obrazGlowny[a][b][k] - min[k])) / (double) (max[k] - min[k]));
                }
            }
        }

        return tab;

    }

    static public int[][][] wyrownajHistogram(int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];
        int hist[][] = OperacjeHistogram.obliczHistogram(obrazGlowny);
        double[][] D = new double[hist.length][3];
        double D0[] = new double[3];
        double sum[] = new double[3];
        int LUT[][] = new int[hist.length][3];
        double sumpom[] =
        {
            0, 0, 0
        };

        for (int a = 0; a < hist.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {
                sum[k] = sum[k] + hist[a][k];

            }
        }
        for (int a = 0; a < hist.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {
                for (int b = a; b >= 0; b--)
                {
                    sumpom[k] = sumpom[k] + (double) hist[b][k];

                }
                D[a][k] = (double) (sumpom[k] / sum[k]);
                sumpom[k] = 0;
            }
        }
        for (int a = 0; a < hist.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {
                if (D[a][k] != 0 && D0[k] == 0)
                {
                    D0[k] = D[a][k];

                }
                if (D0[0] != 0 && D0[1] != 0 && D0[2] != 0)
                {
                    a = hist.length - 1;
                    break;
                }
            }
        }

        for (int a = 0; a < hist.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {
                LUT[a][k] = (int) Math.round(((D[a][k] - D0[k]) / (1.0 - D0[k])) * (double) (hist.length - 1));
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] zmienKontrast(int[][][] obrazGlowny, double wspolczynnik)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        double war = 0.0;
        int LUT[][] = new int[256][3];

        for (int a = 0; a < LUT.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {

                war = wspolczynnik * ((double) a - 255.0 / 2.0) + 255.0 / 2.0;

                if (war < 0)
                {
                    LUT[a][k] = 0;
                } else if (war >= 0 && war <= 255)
                {
                    LUT[a][k] = (int) Math.round(war);
                } else if (war > 255.0)
                {
                    LUT[a][k] = 255;
                }
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] zmienJasnosc(int[][][] obrazGlowny, int wspolczynnik)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        int war = 0;
        int LUT[][] = new int[256][3];

        for (int a = 0; a < LUT.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {

                war = a + wspolczynnik;

                if (war < 0)
                {
                    LUT[a][k] = 0;
                } else if (war >= 0 && war <= 255)
                {
                    LUT[a][k] = war;
                } else if (war > 255.0)
                {
                    LUT[a][k] = 255;
                }
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] zmienEkspozycje(int[][][] obrazGlowny, double wspolczynnik)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        double war = 0.0;
        int LUT[][] = new int[256][3];

        for (int a = 0; a < LUT.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {

                war = a * wspolczynnik;

                if (war < 255.0)
                {
                    LUT[a][k] = (int) Math.round(war);
                } else if (war >= 255.0)
                {
                    LUT[a][k] = 255;
                }
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] zmienGamma(int[][][] obrazGlowny, double wspolczynnik)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        double war = 0.0;
        int LUT[][] = new int[256][3];

        for (int a = 0; a < LUT.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {

                war = 255.0 * Math.pow((double) a / 255.0, (1.0 / wspolczynnik));

                LUT[a][k] = (int) Math.round(war);
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] negatyw(int[][][] obrazGlowny)
    {
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];

        double war = 0.0;
        int LUT[][] = new int[256][3];

        for (int a = 0; a < LUT.length; a++)
        {
            for (int k = 0; k < 3; k++)
            {

                LUT[a][k] = 255 - a;
            }
        }

        tab = OperacjeHistogram.obliczTabzLUT(LUT, obrazGlowny);
        return tab;
    }

    static public int[][][] skalaSzarosci(int[][][] obrazGlowny)
    {
        if (typ.equals("RGB"))
        {
            ////////MODELEM YUV
            int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];
            double Rsz = 0.0;
            double Gsz = 0.0;
            double Bsz = 0.0;

            for (int a = 0; a < tab.length; a++)
            {
                for (int b = 0; b < tab[a].length; b++)
                {
                    Rsz = 0.299 * (double) obrazGlowny[a][b][0] + 0.587 * (double) obrazGlowny[a][b][1] + 0.114 * (double) obrazGlowny[a][b][2];
                    Gsz = 0.299 * (double) obrazGlowny[a][b][0] + 0.587 * (double) obrazGlowny[a][b][1] + 0.114 * (double) obrazGlowny[a][b][2];
                    Bsz = 0.299 * (double) obrazGlowny[a][b][0] + 0.587 * (double) obrazGlowny[a][b][1] + 0.114 * (double) obrazGlowny[a][b][2];

                    tab[a][b][0] = (int) Math.floor(Rsz);
                    tab[a][b][1] = (int) Math.floor(Gsz);
                    tab[a][b][2] = (int) Math.floor(Bsz);
                }
            }
            return tab;
        }
        return obrazGlowny;
    }

    static public int[][][] sepia(int[][][] obrazGlowny, int wspolczynnik)
    {
        int tab[][][] = OperacjeHistogram.skalaSzarosci(obrazGlowny);

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                if (tab[a][b][0] + 2 * wspolczynnik <= 255)
                {
                    tab[a][b][0] = tab[a][b][0] + 2 * wspolczynnik;
                } else
                {
                    tab[a][b][0] = 255;
                }
                if (tab[a][b][1] + wspolczynnik <= 255)
                {
                    tab[a][b][1] = tab[a][b][1] + wspolczynnik;
                } else
                {
                    tab[a][b][0] = 255;
                }
                tab[a][b][2] = tab[a][b][2];
            }
        }
        return tab;
    }

    static private int progOtsu(int[][][] obrazGlowny)
    {
        int otsu = 0;
        int hist[][] = OperacjeHistogram.obliczHistogram(obrazGlowny);

        double wagaTlo = 0.0;
        double sredniaTlo = 0.0;

        double wagaObiekt = 0.0;
        double sredniaObiekt = 0.0;

        long sumWszystkich = 0;
        long sumObiekt = 0;
        long sumTlo = 0;
        for (int a = 0; a < hist.length; a++)
        {
            sumWszystkich = sumWszystkich + hist[a][0];
        }

        double sumaWariancjiMiedzyKlasowej[] = new double[hist.length];
        for (int prog = 0; prog < hist.length; prog++)
        {

            ///////////OBLICZANIE SUM
            for (int a = 0; a < prog; a++)
            {
                sumObiekt = sumObiekt + hist[a][0];
            }
            for (int a = prog; a < hist.length; a++)
            {
                sumTlo = sumTlo + hist[a][0];
            }

            for (int a = 0; a < prog; a++)
            {
                //////OBIEKT
                wagaObiekt = wagaObiekt + hist[a][0];
                sredniaObiekt = sredniaObiekt + (a * hist[a][0]);

            }
            if (sumWszystkich == 0 || sumObiekt == 0)
            {
                wagaObiekt = 0;
                sredniaObiekt = 0;
            } else
            {
                wagaObiekt = wagaObiekt / (double) sumWszystkich;
                sredniaObiekt = sredniaObiekt / (double) sumObiekt;
            }
            for (int a = prog; a < hist.length; a++)
            {
                //////TLO
                wagaTlo = wagaTlo + hist[a][0];
                sredniaTlo = sredniaTlo + (a * hist[a][0]);
            }
            if (sumWszystkich == 0 || sumTlo == 0)
            {
                wagaTlo = 0;
                sredniaTlo = 0;
            } else
            {
                wagaTlo = wagaTlo / (double) sumWszystkich;
                sredniaTlo = sredniaTlo / (double) sumTlo;
            }

            sumaWariancjiMiedzyKlasowej[prog] = wagaObiekt * wagaTlo * Math.pow((sredniaObiekt - sredniaTlo), 2);
            wagaObiekt = 0.0;
            wagaTlo = 0.0;
            sredniaObiekt = 0.0;
            sredniaTlo = 0.0;
            sumObiekt = 0;
            sumTlo = 0;

        }
        double maxSWMK = sumaWariancjiMiedzyKlasowej[0];
        for (int a = 0; a < sumaWariancjiMiedzyKlasowej.length; a++)
        {
            if (maxSWMK < sumaWariancjiMiedzyKlasowej[a])
            {
                maxSWMK = sumaWariancjiMiedzyKlasowej[a];
            }
        }

        for (int a = 0; a < sumaWariancjiMiedzyKlasowej.length; a++)
        {
            if (maxSWMK == sumaWariancjiMiedzyKlasowej[a])
            {
                otsu = a;
                return otsu;
            }
        }

        return otsu;
    }

    static public int[][][] progowanieLokalne(int[][][] obrazGlowny, int maska)
    {
////////////METODA WHITE ROHRERA 1983, DLA K = 2.0
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];
        int tabPom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];
        int progGlobalny;
        if (typ.equals("RGB"))
        {
            tabPom = OperacjeHistogram.skalaSzarosci(obrazGlowny);
            progGlobalny = OperacjeHistogram.progOtsu(tabPom);
        }
        else
        {
            progGlobalny = OperacjeHistogram.progOtsu(obrazGlowny);
        }
        

        int progLokalny = 0;

        double k = 2.0;
        double srednia = 0.0;
        double ilosc = (maska * 2 + 1) * (maska * 2 + 1);

        int T = 0;

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {

                if ((a >= maska && a < (tab.length - maska)) && (b >= maska && b < (tab[0].length - maska)))
                {
                    for (int aa = -maska, x = 0; aa <= maska; aa++, x++)
                    {
                        for (int bb = -maska, y = 0; bb <= maska; bb++, y++)
                        {
                            if (typ.equals("RGB"))
                            {
                                srednia = srednia + tabPom[a + aa][b + bb][0];
                            }
                            else
                            {
                                 srednia = srednia + obrazGlowny[a + aa][b + bb][0];
                            }
                        }
                    }
                    srednia = srednia / ilosc;

                    T = (int) Math.round(srednia / k);
                    srednia = 0.0;
                } else
                {
                    T = 0;
                }

                if (typ.equals("RGB"))
                {
                    for (int kk = 0; kk < 3; kk++)
                    {
                        if (tabPom[a][b][kk] >= T)
                        {
                            tab[a][b][kk] = 255;
                        } else
                        {
                            tab[a][b][kk] = 0;
                        }
                    }
                } else
                {
                    if (obrazGlowny[a][b][0] >= T)
                    {
                        tab[a][b][0] = 255;
                    } else
                    {
                        tab[a][b][0] = 0;
                    }
                }

            }
        }

        return tab;

    }

    static public int[][][] progowanieGlobalne(int[][][] obrazGlowny)
    {
        /////////////METODA OTSU
        int tab[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][3];
        int otsu;
        if (typ.equals("RGB"))
        {
            tab = OperacjeHistogram.skalaSzarosci(obrazGlowny);
            otsu = OperacjeHistogram.progOtsu(tab);
            for (int a = 0; a < tab.length; a++)
            {
                for (int b = 0; b < tab[a].length; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        if (tab[a][b][k] >= otsu)
                        {
                            tab[a][b][k] = 255;
                        } else
                        {
                            tab[a][b][k] = 0;
                        }
                    }
                }
            }

        } else
        {
            otsu = OperacjeHistogram.progOtsu(obrazGlowny);
            for (int a = 0; a < tab.length; a++)
            {
                for (int b = 0; b < tab[a].length; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        if (obrazGlowny[a][b][k] >= otsu)
                        {
                            tab[a][b][k] = 255;
                        } else
                        {
                            tab[a][b][k] = 0;
                        }
                    }
                }
            }
        }

        return tab;
    }

    static public int[] obliczMax(int[][][] obrazGlowny)
    {
        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };

        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
                    }

                }
            }
        }
        return max;
    }

    static public int[] obliczMin(int[][][] obrazGlowny)
    {

        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    if (min[k] > obrazGlowny[a][b][k])
                    {
                        min[k] = obrazGlowny[a][b][k];
                    }
                }
            }
        }
        return min;
    }
}
