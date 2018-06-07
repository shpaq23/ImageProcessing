/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import java.util.Arrays;

/**
 *
 * @author shpaq
 */
abstract public class OperacjeFiltrowanie
{

    /////////////FILTRY DOLNOPRZEPUSTOWE//////////////
    /////////////FILTRY DOLNOPRZEPUSTOWE//////////////
    /////////////FILTRY DOLNOPRZEPUSTOWE//////////////
    /////////////FILTRY DOLNOPRZEPUSTOWE//////////////
    static public int[][] filtrUsredniajacy()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, 1, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrKwadratowy()
    {
        int tab[][] =
        {
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrKolowy()
    {
        int tab[][] =
        {
            {
                0, 1, 1, 1, 0
            },
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
            {
                1, 1, 1, 1, 1
            },
            {
                0, 1, 1, 1, 0
            },
        };
        return tab;
    }

    static public int[][] LP1()
    {

        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, 2, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] LP2()
    {

        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, 4, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] LP3()
    {

        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, 12, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPiramidalny()
    {
        int tab[][] =
        {
            {
                1, 2, 3, 2, 1
            },
            {
                2, 4, 6, 4, 2
            },
            {
                3, 6, 9, 6, 3
            },
            {
                2, 4, 6, 4, 2
            },
            {
                1, 2, 3, 2, 1
            },
        };
        return tab;
    }

    static public int[][] filtrStozkowy()
    {
        int tab[][] =
        {
            {
                0, 0, 1, 0, 0
            },
            {
                0, 2, 2, 2, 0
            },
            {
                1, 2, 5, 2, 1
            },
            {
                0, 2, 2, 2, 0
            },
            {
                0, 0, 1, 0, 0
            },
        };
        return tab;
    }

    static public int[][] G1()
    {
        int tab[][] =
        {
            {
                1, 2, 1
            },
            {
                2, 4, 2
            },
            {
                1, 2, 1
            },
        };
        return tab;
    }

    static public int[][] G2()
    {
        int tab[][] =
        {
            {
                1, 1, 2, 1, 1
            },
            {
                1, 2, 4, 2, 1
            },
            {
                2, 4, 8, 4, 2
            },
            {
                1, 2, 4, 2, 1
            },
            {
                1, 1, 2, 1, 1
            },
        };
        return tab;
    }

    static public int[][] G3()
    {
        int tab[][] =
        {
            {
                0, 1, 2, 1, 0
            },
            {
                1, 4, 8, 4, 1
            },
            {
                2, 8, 16, 8, 2
            },
            {
                1, 4, 8, 4, 1
            },
            {
                0, 1, 2, 1, 0
            },
        };
        return tab;
    }

    static public int[][] G4()
    {
        int tab[][] =
        {
            {
                1, 4, 7, 4, 1
            },
            {
                4, 16, 26, 16, 4
            },
            {
                7, 26, 41, 26, 7
            },
            {
                4, 16, 26, 16, 4
            },
            {
                1, 4, 7, 4, 1
            },
        };
        return tab;
    }

    static public int[][] G5()
    {
        int tab[][] =
        {
            {
                1, 1, 2, 2, 2, 1, 1
            },
            {
                1, 2, 2, 4, 2, 2, 1
            },
            {
                2, 2, 4, 8, 4, 2, 2
            },
            {
                2, 4, 8, 16, 8, 4, 2
            },
            {
                2, 2, 4, 8, 4, 2, 2
            },
            {
                1, 2, 2, 4, 2, 2, 1
            },
            {
                1, 1, 2, 2, 2, 1, 1
            },
        };
        return tab;
    }

    /////////////FILTRY GORNOPRZEPUSTOWE///////////
    /////////////FILTRY GORNOPRZEPUSTOWE///////////
    /////////////FILTRY GORNOPRZEPUSTOWE///////////
    /////////////FILTRY GORNOPRZEPUSTOWE///////////
    static public int[][] filtrUsunSrednia()
    {
        int tab[][] =
        {
            {
                -1, -1, -1
            },
            {
                -1, 9, -1
            },
            {
                -1, -1, -1
            },
        };
        return tab;
    }

    static public int[][] HP1()
    {
        int tab[][] =
        {
            {
                0, -1, 0
            },
            {
                -1, 5, -1
            },
            {
                0, -1, 0
            },
        };
        return tab;
    }

    static public int[][] HP2()
    {
        int tab[][] =
        {
            {
                1, -2, 1
            },
            {
                -2, 5, -2
            },
            {
                1, -2, 1
            },
        };
        return tab;
    }

    static public int[][] HP3()
    {
        int tab[][] =
        {
            {
                0, -1, 0
            },
            {
                -1, 20, -1
            },
            {
                0, -1, 0
            },
        };
        return tab;
    }

    //////////////FILTRY KONTUROWE SOBEL I PREWITT/////////
    //////////////FILTRY KONTUROWE SOBEL I PREWITT/////////
    //////////////FILTRY KONTUROWE SOBEL I PREWITT/////////
    //////////////FILTRY KONTUROWE SOBEL I PREWITT/////////
    static public int[][] filtrSobelPion()
    {
        int tab[][] =
        {
            {
                1, 2, 1
            },
            {
                0, 0, 0
            },
            {
                -1, -2, -1
            },
        };
        return tab;
    }

    static public int[][] filtrSobelPoziom()
    {
        int tab[][] =
        {
            {
                1, 0, -1
            },
            {
                2, 0, -2
            },
            {
                1, 0, -1
            },
        };
        return tab;
    }

    static public int[][] filtrPrewittPion()
    {
        int tab[][] =
        {
            {
                -1, -1, -1
            },
            {
                0, 0, 0
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPrewittPoziom()
    {
        int tab[][] =
        {
            {
                1, 0, -1
            },
            {
                1, 0, -1
            },
            {
                1, 0, -1
            },
        };
        return tab;
    }

    ////////////////FILTRY LAPLACA /////////////////
    ////////////////FILTRY LAPLACA /////////////////
    ////////////////FILTRY LAPLACA /////////////////
    ////////////////FILTRY LAPLACA /////////////////
    static public int[][] filtrLaplacePoziom()
    {
        int tab[][] =
        {
            {
                0, -1, 0
            },
            {
                0, 2, 0
            },
            {
                0, -1, 0
            },
        };
        return tab;
    }

    static public int[][] filtrLaplacePion()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                -1, 2, -1
            },
            {
                0, 0, 0
            },
        };
        return tab;
    }

    static public int[][] LAPL1()
    {
        int tab[][] =
        {
            {
                0, -1, 0
            },
            {
                -1, 4, -1
            },
            {
                0, -1, 0
            },
        };
        return tab;
    }

    static public int[][] LAPL2()
    {
        int tab[][] =
        {
            {
                -1, -1, -1
            },
            {
                -1, 8, -1
            },
            {
                -1, -1, -1
            },
        };
        return tab;
    }

    static public int[][] LAPL3()
    {
        int tab[][] =
        {
            {
                1, -2, 1
            },
            {
                -2, 4, -2
            },
            {
                1, -2, 1
            },
        };
        return tab;
    }

    static public int[][] filtrLaplaceUkosny()
    {
        int tab[][] =
        {
            {
                -1, 0, -1
            },
            {
                0, 4, 0
            },
            {
                -1, 0, -1
            },
        };
        return tab;
    }

    /////////////////FILTRY KIERUNKOWE //////////////////
    /////////////////FILTRY KIERUNKOWE //////////////////
    /////////////////FILTRY KIERUNKOWE //////////////////
    /////////////////FILTRY KIERUNKOWE //////////////////
    static public int[][] filtrPoludniowyWschod()
    {
        int tab[][] =
        {
            {
                -1, -1, 1
            },
            {
                -1, -2, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPoludniowyZachod()
    {
        int tab[][] =
        {
            {
                1, -1, -1
            },
            {
                1, -2, -1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPolnocnyWschod()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                -1, -2, 1
            },
            {
                -1, -1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPolnocnyZachod()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, -2, -1
            },
            {
                1, -1, -1
            },
        };
        return tab;
    }

    static public int[][] filtrWschod()
    {
        int tab[][] =
        {
            {
                -1, 1, 1
            },
            {
                -1, -2, 1
            },
            {
                -1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrPoludnie()
    {
        int tab[][] =
        {
            {
                -1, -1, -1
            },
            {
                1, -2, 1
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrZachod()
    {
        int tab[][] =
        {
            {
                1, 1, -1
            },
            {
                1, -2, -1
            },
            {
                1, 1, -1
            },
        };
        return tab;
    }

    static public int[][] filtrPolnoc()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, -2, 1
            },
            {
                -1, -1, -1
            },
        };
        return tab;
    }

    /////////////////FILTRY PRZESUN-ODEJMIJ//////////////
    /////////////////FILTRY PRZESUN-ODEJMIJ//////////////
    /////////////////FILTRY PRZESUN-ODEJMIJ//////////////
    /////////////////FILTRY PRZESUN-ODEJMIJ//////////////
    /////////////////FILTRY PRZESUN-ODEJMIJ//////////////
    static public int[][] filtrPoziomy()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                -1, 1, 0
            },
            {
                0, 0, 0
            },
        };
        return tab;
    }

    static public int[][] filtrPionowy()
    {
        int tab[][] =
        {
            {
                0, -1, 0
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            },
        };
        return tab;
    }

    static public int[][] filtrUkosPrawo()
    {
        int tab[][] =
        {
            {
                -1, 0, 0
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            },
        };
        return tab;
    }

    static public int[][] filtrUkosLewo()
    {
        int tab[][] =
        {
            {
                0, 0, -1
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            },
        };
        return tab;
    }

    //////////////////FILTR UWYPUKLAJACY//////////////////
    //////////////////FILTR UWYPUKLAJACY//////////////////
    //////////////////FILTR UWYPUKLAJACY//////////////////
    //////////////////FILTR UWYPUKLAJACY//////////////////
    static public int[][] filtrUwypuklajacyPoludniowyWschod()
    {
        int tab[][] =
        {
            {
                -1, -1, 0
            },
            {
                -1, 1, 1
            },
            {
                0, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyPoludniowyZachod()
    {
        int tab[][] =
        {
            {
                0, -1, -1
            },
            {
                1, 1, -1
            },
            {
                1, 1, 0
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyPolnocnyWschod()
    {
        int tab[][] =
        {
            {
                0, 1, 1
            },
            {
                -1, 1, 1
            },
            {
                -1, -1, 0
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyPolnocnyZachod()
    {
        int tab[][] =
        {
            {
                1, 1, 0
            },
            {
                1, 1, -1
            },
            {
                0, -1, -1
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyWschod()
    {
        int tab[][] =
        {
            {
                -1, 0, 1
            },
            {
                -1, 1, 1
            },
            {
                -1, 0, 1
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyPoludnie()
    {
        int tab[][] =
        {
            {
                -1, -1, -1
            },
            {
                0, 1, 0
            },
            {
                1, 1, 1
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyZachod()
    {
        int tab[][] =
        {
            {
                1, 0, -1
            },
            {
                1, 1, -1
            },
            {
                1, 0, -1
            },
        };
        return tab;
    }

    static public int[][] filtrUwypuklajacyPolnoc()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                0, 1, 0
            },
            {
                -1, -1, -1
            },
        };
        return tab;
    }

    ///////////////OPERACJE I FILTRY STATYCZNE///////////////
    ///////////////OPERACJE I FILTRY STATYCZNE///////////////
    ///////////////OPERACJE I FILTRY STATYCZNE///////////////
    ///////////////OPERACJE I FILTRY STATYCZNE///////////////
    ///////////////OPERACJE I FILTRY STATYCZNE///////////////
    static public int[][][] filtrMedianowy(int obrazGlowny[][][])
    {

        if (obrazGlowny.length - 2 > 0 && obrazGlowny[0].length - 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - 2][obrazGlowny[0].length - 2][3];
            int mediana[][] = new int[3][9];

            for (int a = 1; a < obrazGlowny.length - 1; a++)
            {
                for (int b = 1; b < obrazGlowny[0].length - 1; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int aa = -1, x = 0; aa <= 1; aa++)
                        {
                            for (int bb = -1; bb <= 1; bb++)
                            {
                                mediana[k][x] = obrazGlowny[a + aa][b + bb][k];
                                x++;
                            }
                        }
                        Arrays.sort(mediana[k]);
                        tab[a - 1][b - 1][k] = mediana[k][4];

                    }

                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] filtrMinimalny(int obrazGlowny[][][])
    {
        if (obrazGlowny.length - 2 > 0 && obrazGlowny[0].length - 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - 2][obrazGlowny[0].length - 2][3];
            int minimalny[][] = new int[3][9];

            for (int a = 1; a < obrazGlowny.length - 1; a++)
            {
                for (int b = 1; b < obrazGlowny[0].length - 1; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int aa = -1, x = 0; aa <= 1; aa++)
                        {
                            for (int bb = -1; bb <= 1; bb++)
                            {
                                minimalny[k][x] = obrazGlowny[a + aa][b + bb][k];
                                x++;
                            }
                        }
                        Arrays.sort(minimalny[k]);
                        tab[a - 1][b - 1][k] = minimalny[k][0];

                    }

                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] filtrMaksymalny(int obrazGlowny[][][])
    {
        if (obrazGlowny.length - 2 > 0 && obrazGlowny[0].length - 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - 2][obrazGlowny[0].length - 2][3];
            int maksymalny[][] = new int[3][9];

            for (int a = 1; a < obrazGlowny.length - 1; a++)
            {
                for (int b = 1; b < obrazGlowny[0].length - 1; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int aa = -1, x = 0; aa <= 1; aa++)
                        {
                            for (int bb = -1; bb <= 1; bb++)
                            {
                                maksymalny[k][x] = obrazGlowny[a + aa][b + bb][k];
                                x++;
                            }
                        }
                        Arrays.sort(maksymalny[k]);
                        tab[a - 1][b - 1][k] = maksymalny[k][8];

                    }

                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] filtrKuwahara(int obrazGlowny[][][])
    {
        if (obrazGlowny.length - 4 > 0 && obrazGlowny[0].length - 4 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - 4][obrazGlowny[0].length - 4][3];
            int region[][][] = new int[3][4][9];

            for (int a = 2; a < obrazGlowny.length - 2; a++)
            {
                for (int b = 2; b < obrazGlowny[0].length - 2; b++)
                {
                    double srednia[][] = new double[3][4];
                    double wariancja[][] = new double[3][4];

                    for (int k = 0; k < 3; k++)
                    {

                        ///////////USTALANIE REGIONOW////////////
                        for (int aa = -2, r1 = 0, r2 = 0, r3 = 0, r4 = 0; aa <= 2; aa++)
                        {
                            for (int bb = -2; bb <= 2; bb++)
                            {
                                if ((aa <= 0) && (bb <= 0))
                                {
                                    region[k][0][r1] = obrazGlowny[a + aa][b + bb][k];
                                    r1++;
                                }
                                if (aa >= 0 && bb <= 0)
                                {
                                    region[k][1][r2] = obrazGlowny[a + aa][b + bb][k];
                                    r2++;
                                }
                                if (aa <= 0 && bb >= 0)
                                {
                                    region[k][2][r3] = obrazGlowny[a + aa][b + bb][k];
                                    r3++;
                                }
                                if (aa >= 0 && bb >= 0)
                                {
                                    region[k][3][r4] = obrazGlowny[a + aa][b + bb][k];
                                    r4++;
                                }
                            }
                        }

                        //////LICZENIE SREDNIEJ///////
                        for (int r = 0; r < 4; r++)
                        {
                            for (int x = 0; x < 9; x++)
                            {
                                srednia[k][r] = srednia[k][r] + region[k][r][x];
                            }
                            srednia[k][r] = srednia[k][r] / 9.0;
                        }
                        ////////////LICZENIE WARIANCJI/////////
                        for (int r = 0; r < 4; r++)
                        {
                            for (int x = 0; x < 9; x++)
                            {
                                wariancja[k][r] = wariancja[k][r] + Math.pow((srednia[k][r] - region[k][r][x]), 2.0);
                            }
                            wariancja[k][r] = wariancja[k][r] / 9.0;

                        }
                        ///////WSKAZANIE REGIONU Z NAJMNIEJSZA WARTOSCIA///////////
                        double min = wariancja[k][0];
                        for (int r = 0; r < 4; r++)
                        {
                            if (min > wariancja[k][r])
                            {
                                min = wariancja[k][r];
                            }
                        }
                        int indeks = 0;
                        for (int r = 0; r < 4; r++)
                        {
                            if (min == wariancja[k][r])
                            {
                                indeks = r;
                            }
                        }
                        tab[a - 2][b - 2][k] = (int) Math.round(srednia[k][indeks]);

                        //System.out.println("");
                    }

                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    ////////////
    ////////////OPERACJE FILTROWANIE///////////////////
    ////////////OPERACJE FILTROWANIE///////////////////
    ////////////OPERACJE FILTROWANIE///////////////////
    ////////////OPERACJE FILTROWANIE///////////////////
    ////////////OPERACJE FILTROWANIE///////////////////
    static public int[][][] filtrLiniowy(int obrazGlowny[][][], int filtr[][])
    {

        int rozmiar = (int) Math.round(filtr.length - 1.0) / 2;
        if (obrazGlowny.length - rozmiar * 2 > 0 && obrazGlowny[0].length - rozmiar * 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - rozmiar * 2][obrazGlowny[0].length - rozmiar * 2][3];
            double suma[] =
            {
                0.0, 0.0, 0.0
            };
            double waga = 0.0;
            for (int a = 0; a < filtr.length; a++)
            {
                for (int b = 0; b < filtr[a].length; b++)
                {
                    waga = waga + filtr[a][b];
                }
            }

            for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
            {
                for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        for (int aa = -rozmiar; aa <= rozmiar; aa++)
                        {
                            for (int bb = -rozmiar; bb <= rozmiar; bb++)
                            {
                                suma[k] = suma[k] + filtr[aa + rozmiar][bb + rozmiar] * obrazGlowny[aa + a][bb + b][k];
                            }
                        }
                        if (waga != 0)
                        {
                            // System.out.println((int) Math.round(suma[k] / waga));
                            if ((int) Math.round(suma[k] / waga) <= 255 && (int) Math.round(suma[k] / waga) >= 0)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = (int) Math.round(suma[k] / waga);
                            } else if ((int) Math.round(suma[k] / waga) > 255)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = 255;
                            } else if ((int) Math.round(suma[k] / waga) < 0)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = 0;
                            }
                            //tab[a - rozmiar][b - rozmiar][k] = (int) Math.round(Math.abs(suma[k] / waga));

                        } else
                        {
                            if ((int) Math.round(suma[k] / 1) <= 255 && (int) Math.round(suma[k] / 1) >= 0)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = (int) Math.round(suma[k] / 1);
                            } else if ((int) Math.round(suma[k] / 1) > 255)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = 255;
                            } else if ((int) Math.round(suma[k] / 1) < 0)
                            {
                                tab[a - rozmiar][b - rozmiar][k] = 0;
                            }
                            //tab[a - rozmiar][b - rozmiar][k] = (int) Math.round(Math.abs(suma[k] / waga));

                        }

                        suma[k] = 0.0;
                    }
                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] filtrNieLiniowy(int obrazGlowny[][][], int filtr1[][], int filtr2[][])
    {
        int tab1[][][] = OperacjeFiltrowanie.filtrLiniowy(obrazGlowny, filtr1);
        int tab2[][][] = OperacjeFiltrowanie.filtrLiniowy(obrazGlowny, filtr2);

        int tab[][][] = new int[tab1.length][tab1[0].length][3];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    tab[a][b][k] = (int) Math.round(Math.sqrt(Math.pow(tab1[a][b][k], 2) + Math.pow(tab2[a][b][k], 2)));
                }
            }
        }

        return tab;

    }

}
