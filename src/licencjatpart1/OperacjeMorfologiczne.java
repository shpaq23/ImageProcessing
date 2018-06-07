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
abstract public class OperacjeMorfologiczne
{
//////////////////////ES/////////////////////////////
    /////////EROZJA DYLACJA ZAMKNIECIE OTWARCIE////////
    /////////EROZJA DYLACJA ZAMKNIECIE OTWARCIE////////
    /////////EROZJA DYLACJA ZAMKNIECIE OTWARCIE////////
    /////////EROZJA DYLACJA ZAMKNIECIE OTWARCIE////////

    static public int[][] ES_kwadrat()
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

    static public int[][] ES_krzyz()
    {
        int tab[][] =
        {

            {
                0, 1, 0
            },
            {
                1, 1, 1
            },
            {
                0, 1, 0
            },

        };
        return tab;
    }

    static public int[][] ES_kolo()
    {
        int tab[][] =
        {
            {
                0, 0, 1, 0, 0
            },
            {
                0, 1, 1, 1, 0
            },
            {
                1, 1, 1, 1, 1
            },
            {
                0, 1, 1, 1, 0
            },
            {
                0, 0, 1, 0, 0
            }
        };
        return tab;
    }

    /////////////////ES//////////////////////////////
    ///////////////ROGI H&M//////////////////////
    ///////////////ROGI H&M//////////////////////
    ///////////////ROGI H&M//////////////////////
    ///////////////ROGI H&M//////////////////////
    static public int[][] ES_gornyPrawy()
    {
        int tab[][] =
        {
            {
                -1, 1, -1
            },
            {
                0, 1, 1
            },
            {
                0, 0, -1
            }
        };
        return tab;
    }

    static public int[][] ES_gornyLewy()
    {
        int tab[][] =
        {
            {
                -1, 1, -1
            },
            {
                1, 1, 0
            },
            {
                -1, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_dolnyPrawy()
    {
        int tab[][] =
        {
            {
                0, 0, -1
            },
            {
                0, 1, 1
            },
            {
                -1, 1, -1
            }
        };
        return tab;
    }

    static public int[][] ES_dolnyLewy()
    {
        int tab[][] =
        {
            {
                -1, 0, 0
            },
            {
                1, 1, 0
            },
            {
                -1, 1, -1
            }
        };
        return tab;
    }

    ///////////////ES ///////////////
    //////POCIENIANIE///////////
    static private int[][] ES_krawedzie()
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
            }
        };
        return tab;
    }

    static private int[][] ES_szkielet1()
    {
        int tab[][]
                =
                {
                    {
                        0, 0, 0
                    },
                    {
                        -1, 1, -1
                    },
                    {
                        1, 1, 1
                    }
                };
        return tab;
    }

    static private int[][] ES_szkielet1b()
    {
        int tab[][] =
        {
            {
                0, -1, 1
            },
            {
                0, 1, 1
            },
            {
                0, -1, 1
            }
        };
        return tab;
    }

    static public int[][] ES_szkielet1c()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                -1, 1, -1
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_szkielet1d()
    {
        int tab[][] =
        {
            {
                1, -1, 0
            },
            {
                1, 1, 0
            },
            {
                1, -1, 0
            }
        };
        return tab;
    }

    static private int[][] ES_szkielet2()
    {
        int tab[][] =
        {
            {
                -1, 0, 0
            },
            {
                1, 1, 0
            },
            {
                -1, 1, -1
            }
        };
        return tab;
    }

    static private int[][] ES_szkielet2b()
    {
        int tab[][] =
        {
            {
                0, 0, -1
            },
            {
                0, 1, 1
            },
            {
                -1, 1, -1
            }
        };
        return tab;
    }

    static private int[][] ES_szkielet2c()
    {
        int tab[][] =
        {
            {
                -1, 1, -1
            },
            {
                0, 1, 1
            },
            {
                0, 0, -1
            }
        };
        return tab;
    }

    static private int[][] ES_szkielet2d()
    {
        int tab[][] =
        {
            {
                -1, 1, -1
            },
            {
                1, 1, 0
            },
            {
                -1, 0, 0
            }
        };
        return tab;
    }

    //////////////ES//////////////
    /////////POGRUBIANIE///////////
    //////////WYPUKLY KSZTALT///////
    static private int[][] ES_Pszkielet1()
    {
        int tab[][]
                =
                {
                    {
                        1, 1, -1
                    },
                    {
                        1, 0, -1
                    },
                    {
                        1, -1, 0
                    }
                };
        return tab;
    }

    static private int[][] ES_Pszkielet1b()
    {
        int tab[][] =
        {
            {
                -1, -1, 0
            },
            {
                1, 0, -1
            },
            {
                1, 1, 1
            }
        };
        return tab;
    }

    static public int[][] ES_Pszkielet1c()
    {
        int tab[][] =
        {
            {
                0, -1, 1
            },
            {
                -1, 0, 1
            },
            {
                -1, 1, 1
            }
        };
        return tab;
    }

    static public int[][] ES_Pszkielet1d()
    {
        int tab[][] =
        {
            {
                1, 1, 1,
            },
            {
                -1, 0, 1
            },
            {
                0, -1, -1
            }
        };
        return tab;
    }

    static private int[][] ES_Pszkielet2()
    {
        int tab[][] =
        {
            {
                -1, 1, 1
            },
            {
                -1, 0, 1
            },
            {
                0, -1, 1
            }
        };
        return tab;
    }

    static private int[][] ES_Pszkielet2b()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                1, 0, -1
            },
            {
                -1, -1, 0
            }
        };
        return tab;
    }

    static private int[][] ES_Pszkielet2c()
    {
        int tab[][] =
        {
            {
                1, -1, 0
            },
            {
                1, 0, -1
            },
            {
                1, 1, -1
            }
        };
        return tab;
    }

    static private int[][] ES_Pszkielet2d()
    {
        int tab[][] =
        {
            {
                0, -1, -1
            },
            {
                -1, 0, 1
            },
            {
                1, 1, 1
            }
        };
        return tab;
    }
    /////////////////////POGRUBIENIE////////////
    ///////////////SZKIELT STREFY WPLYWU////////

    static private int[][] ES_SSWszkielet1()
    {
        int tab[][]
                =
                {
                    {
                        0, 0, 0
                    },
                    {
                        -1, 0, -1
                    },
                    {
                        1, 1, 1
                    }
                };
        return tab;
    }

    static private int[][] ES_SSWszkielet1b()
    {
        int tab[][] =
        {
            {
                0, -1, 1
            },
            {
                0, 0, 1
            },
            {
                0, -1, 1
            }
        };
        return tab;
    }

    static public int[][] ES_SSWszkielet1c()
    {
        int tab[][] =
        {
            {
                1, 1, 1
            },
            {
                -1, 0, -1
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_SSWszkielet1d()
    {
        int tab[][] =
        {
            {
                1, -1, 0
            },
            {
                1, 0, 0
            },
            {
                1, -1, 0
            }
        };
        return tab;
    }

    static private int[][] ES_SSWszkielet2()
    {
        int tab[][] =
        {
            {
                -1, 0, -1
            },
            {
                1, 0, 0,
            },
            {
                1, 1, -1
            }
        };
        return tab;
    }

    static private int[][] ES_SSWszkielet2b()
    {
        int tab[][] =
        {
            {
                -1, 0, -1
            },
            {
                0, 0, 1
            },
            {
                -1, 1, 1
            }
        };
        return tab;
    }

    static private int[][] ES_SSWszkielet2c()
    {
        int tab[][] =
        {
            {
                -1, 1, 1
            },
            {
                0, 0, 1
            },
            {
                -1, 0, -1
            }
        };
        return tab;
    }

    static private int[][] ES_SSWszkielet2d()
    {
        int tab[][] =
        {
            {
                1, 1, -1
            },
            {
                1, 0, 0
            },
            {
                -1, 0, -1
            }
        };
        return tab;
    }

    //////////////////Pruning SE//////////////
    //////////////////Pruning SE//////////////
    //////////////////Pruning SE//////////////
    static public int[][] ES_pruning1()
    {
        int tab[][] =
        {
            {
                0, -1, -1
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_pruning1b()
    {
        int tab[][] =
        {
            {
                -1, 0, 0
            },
            {
                -1, 1, 0
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_pruning1c()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                0, 1, 0
            },
            {
                -1, -1, 0
            }
        };
        return tab;
    }

    static public int[][] ES_pruning1d()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                0, 1, -1
            },
            {
                0, 0, -1
            }

        };
        return tab;
    }

    static public int[][] ES_pruning2()
    {
        int tab[][] =
        {
            {
                -1, -1, 0
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_pruning2b()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                -1, 1, 0,
            },
            {
                -1, 0, 0
            }
        };
        return tab;
    }

    static public int[][] ES_pruning2c()
    {
        int tab[][] =
        {
            {
                0, 0, 0
            },
            {
                0, 1, 0
            },
            {
                0, -1, -1
            }

        };
        return tab;
    }

    static public int[][] ES_pruning2d()
    {
        int tab[][] =
        {

            {
                0, 0, -1
            },
            {
                0, 1, -1
            },
            {
                0, 0, 0
            }
        };
        return tab;
    }

    static public int[][][] operacjaDylacji(int obrazGlowny[][][], int ES[][])
    {
        int rozmiar = (int) Math.round(ES.length - 1.0) / 2;
        if (obrazGlowny.length - rozmiar * 2 > 0 && obrazGlowny[0].length - rozmiar * 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - rozmiar * 2][obrazGlowny[0].length - rozmiar * 2][1];

            int ile1 = 0;
            for (int a = 0; a < ES.length; a++)
            {
                for (int b = 0; b < ES[a].length; b++)
                {
                    if (ES[a][b] == 1)
                    {
                        ile1++;
                    }
                }
            }
            ile1 = ile1 - 1;

            for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
            {
                for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                {
                    int licznik = 0;

                    for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                    {
                        for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                        {
                            if (ES[x][y] == 1)
                            {
                                if (aa != bb || aa != 0)
                                {
                                    if (obrazGlowny[a + aa][b + bb][0] == 1)
                                    {
                                        tab[a - rozmiar][b - rozmiar][0] = 1;
                                        aa = rozmiar + 1;
                                        bb = rozmiar + 1;
                                        break;
                                    } else
                                    {
                                        licznik++;
                                    }

                                } else
                                {
                                    //  System.out.println("licencjatpart1.OperacjeMorfologiczne.operacjaDylacji()");
                                }

                            }
                        }
                    }
                    if (licznik == ile1)
                    {
                        tab[a - rozmiar][b - rozmiar][0] = 0;
                    }
                }

            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] operacjaErozji(int obrazGlowny[][][], int ES[][])
    {
        int rozmiar = (int) Math.round(ES.length - 1.0) / 2;
        if (obrazGlowny.length - rozmiar * 2 > 0 && obrazGlowny[0].length - rozmiar * 2 > 0)
        {
            int tab[][][] = new int[obrazGlowny.length - rozmiar * 2][obrazGlowny[0].length - rozmiar * 2][1];

            int ile1 = 0;
            for (int a = 0; a < ES.length; a++)
            {
                for (int b = 0; b < ES[a].length; b++)
                {
                    if (ES[a][b] == 1)
                    {
                        ile1++;
                    }
                }
            }
            ile1 = ile1 - 1;
            for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
            {
                for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                {
                    int licznik = 0;

                    for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                    {
                        for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                        {
                            if (ES[x][y] == 1)
                            {
                                if (aa != bb || aa != 0)
                                {
                                    if (obrazGlowny[a + aa][b + bb][0] == 0)
                                    {
                                        tab[a - rozmiar][b - rozmiar][0] = 0;
                                        aa = rozmiar + 1;
                                        bb = rozmiar + 1;
                                        break;
                                    } else
                                    {
                                        licznik++;
                                    }

                                }

                            }
                        }
                    }
                    if (licznik == ile1)
                    {
                        tab[a - rozmiar][b - rozmiar][0] = obrazGlowny[a][b][0];
                    }
                }

            }

            return tab;
        } else
        {
            return obrazGlowny;
        }
    }

    static public int[][][] operacjaOtwarcia(int obrazGlowny[][][], int ES[][])
    {

        int tab[][][] = OperacjeMorfologiczne.operacjaErozji(obrazGlowny, ES);
        tab = OperacjeMorfologiczne.operacjaDylacji(tab, ES);

        return tab;

    }

    static public int[][][] operacjaZamkniecia(int obrazGlowny[][][], int ES[][])
    {

        int tab[][][] = OperacjeMorfologiczne.operacjaDylacji(obrazGlowny, ES);
        tab = OperacjeMorfologiczne.operacjaErozji(tab, ES);

        return tab;

    }

    static public int[][][] operacjaHitAndMissOne(int obrazGlowny[][][], int ES[][])
    {
        int rozmiar = (int) Math.round(ES.length - 1.0) / 2;
        if (obrazGlowny.length - rozmiar * 2 > 0 && obrazGlowny[0].length - rozmiar * 2 > 0)
        {

            int tab[][][] = new int[obrazGlowny.length - rozmiar * 2][obrazGlowny[0].length - rozmiar * 2][1];
            int ileCzarnych = 0;
            int ileBialych = 0;

            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    if (obrazGlowny[a][b][0] == 0)
                    {
                        ileCzarnych++;
                    } else if (obrazGlowny[a][b][0] == 1)
                    {
                        ileBialych++;
                    }

                }
            }
            int kolor = -1;
            ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
            /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
            if (ileCzarnych > ileBialych)
            {
                kolor = 1;

            } else if (ileBialych >= ileCzarnych)
            {
                /////////////DLA OBIEKTU O WARTOSCI 0//////////////

                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        if (ES[x][y] != -1)
                        {
                            if (ES[x][y] == 1)
                            {
                                ES[x][y] = 0;
                            } else
                            {
                                ES[x][y] = 1;
                            }
                        }
                    }

                }
                kolor = 0;

            }

            int ile1 = 0;
            for (int a = 0; a < ES.length; a++)
            {
                for (int b = 0; b < ES[a].length; b++)
                {
                    if (ES[a][b] != 1)
                    {
                        ile1++;
                    }
                }
            }

            for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
            {
                for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                {
                    int liczba = 0;
                    for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                    {
                        for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                        {
                            if (ES[x][y] == obrazGlowny[a + aa][b + bb][0])
                            {
                                liczba++;
                            }

                        }
                    }
                    if (liczba == ile1)
                    {
                        tab[a - rozmiar][b - rozmiar][0] = kolor;
                    } else
                    {
                        if (kolor == 1)
                        {
                            tab[a - rozmiar][b - rozmiar][0] = 0;
                        } else
                        {
                            tab[a - rozmiar][b - rozmiar][0] = 1;
                        }

                    }

                }
            }

            return tab;
        } else
        {
            return obrazGlowny;
        }

    }

    static public int[][][] operacjaHitandMissAll(int obrazGlowny[][][])
    {
        int ileCzarnych = 0;
        int ileBialych = 0;

        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        int kolor = -1;
        ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {
            kolor = 1;
        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////
            kolor = 0;
        }

        int tab_pom[][][][] = new int[4][][][];
        tab_pom[0] = OperacjeMorfologiczne.operacjaHitAndMissOne(obrazGlowny, OperacjeMorfologiczne.ES_dolnyLewy());
        tab_pom[1] = OperacjeMorfologiczne.operacjaHitAndMissOne(obrazGlowny, OperacjeMorfologiczne.ES_dolnyPrawy());
        tab_pom[2] = OperacjeMorfologiczne.operacjaHitAndMissOne(obrazGlowny, OperacjeMorfologiczne.ES_gornyLewy());
        tab_pom[3] = OperacjeMorfologiczne.operacjaHitAndMissOne(obrazGlowny, OperacjeMorfologiczne.ES_gornyPrawy());
        int tab[][][] = new int[tab_pom[0].length][tab_pom[0][0].length][1];

        for (int a = 0; a < tab.length; a++)
        {
            for (int b = 0; b < tab[a].length; b++)
            {
                if (tab_pom[0][a][b][0] == kolor || tab_pom[1][a][b][0] == kolor || tab_pom[2][a][b][0] == kolor || tab_pom[3][a][b][0] == kolor)
                {
                    tab[a][b][0] = kolor;

                } else
                {
                    if (kolor == 1)
                    {
                        tab[a][b][0] = 0;
                    } else
                    {
                        tab[a][b][0] = 1;
                    }
                }
            }
        }

        return tab;

    }

    static public int[][][] operacjaPocienianieKrawedzie(int obrazGlowny[][][])
    {
        int ES[][] = OperacjeMorfologiczne.ES_krawedzie();

        int ileCzarnych = 0;
        int ileBialych = 0;

        int kolor = 1;
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {
            kolor = 0;

        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////

            for (int x = 0; x < 3; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                    if (ES[x][y] != -1)
                    {
                        if (ES[x][y] == 1)
                        {
                            ES[x][y] = 0;
                        } else
                        {
                            ES[x][y] = 1;
                        }
                    }
                }

            }
            kolor = 1;

        }

        int rozmiar = (int) Math.round(ES[0].length - 1.0) / 2;
        int tab_pom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][1];

        for (int a = 0; a < tab_pom.length; a++)
        {
            for (int b = 0; b < tab_pom[a].length; b++)
            {
                tab_pom[a][b][0] = obrazGlowny[a][b][0];
            }
        }

        int ile = 0;

        for (int a = 0; a < ES.length; a++)
        {
            for (int b = 0; b < ES[a].length; b++)
            {
                if (ES[a][b] != -1)
                {
                    ile++;
                }
            }

        }
        boolean warunek = true;
        while (warunek)
        {
            int wejscia = 0;

            for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
            {
                for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                {
                    int licznik = 0;

                    for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                    {
                        for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                        {
                            if (ES[x][y] == obrazGlowny[a + aa][b + bb][0])
                            {
                                licznik++;
                            }
                        }
                    }
                    if (licznik == ile)
                    {

                        tab_pom[a][b][0] = kolor;
                        wejscia++;

                    }
                }

            }
            /////////KOPIOWANIE Z POM D OBRAZ GLOWNY ///////
            for (int a = 0; a < tab_pom.length; a++)
            {
                for (int b = 0; b < tab_pom[a].length; b++)
                {
                    obrazGlowny[a][b][0] = tab_pom[a][b][0];
                }
            }

            if (wejscia == 0)
            {
                warunek = false;
            }

        }

        return obrazGlowny;
    }

    static public int[][][] operacjaPocienianieSzkielet(int obrazGlowny[][][])
    {

        int ES[][][] = new int[8][][];
        ES[0] = OperacjeMorfologiczne.ES_szkielet1();
        ES[1] = OperacjeMorfologiczne.ES_szkielet2();
        ES[2] = OperacjeMorfologiczne.ES_szkielet1b();
        ES[3] = OperacjeMorfologiczne.ES_szkielet2b();
        ES[4] = OperacjeMorfologiczne.ES_szkielet1c();
        ES[5] = OperacjeMorfologiczne.ES_szkielet2c();
        ES[6] = OperacjeMorfologiczne.ES_szkielet1d();
        ES[7] = OperacjeMorfologiczne.ES_szkielet2d();

        int ileCzarnych = 0;
        int ileBialych = 0;

        int kolor = -1;
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        ///////////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {

            kolor = 0;

        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////
            for (int a = 0; a < ES.length; a++)
            {
                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        if (ES[a][x][y] != -1)
                        {
                            if (ES[a][x][y] == 1)
                            {
                                ES[a][x][y] = 0;
                            } else
                            {
                                ES[a][x][y] = 1;
                            }
                        }
                    }
                }
            }
            kolor = 1;

        }

        int rozmiar = (int) Math.round(ES[0].length - 1.0) / 2;
        int tab_pom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][1];

        for (int a = 0; a < tab_pom.length; a++)
        {
            for (int b = 0; b < tab_pom[a].length; b++)
            {
                tab_pom[a][b][0] = obrazGlowny[a][b][0];
            }
        }

        int ile[] = new int[8];
        for (int ss = 0; ss < 8; ss++)
        {
            for (int a = 0; a < ES[0].length; a++)
            {
                for (int b = 0; b < ES[0][a].length; b++)
                {
                    if (ES[ss][a][b] != -1)
                    {
                        ile[ss]++;
                    }
                }
            }
        }
        boolean warunek = true;
        while (warunek)
        {
            int wejscia = 0;

            for (int ss = 0; ss < 8; ss++)
            {

                for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
                {
                    for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                    {
                        int licznik = 0;

                        for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                        {
                            for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                            {
                                if (ES[ss][x][y] == obrazGlowny[a + aa][b + bb][0])
                                {
                                    licznik++;
                                }
                            }
                        }
                        if (licznik == ile[ss])
                        {

                            tab_pom[a][b][0] = kolor;
                            wejscia++;

                        }
                    }

                }
                /////////KOPIOWANIE Z POM D OBRAZ GLOWNY ///////
                for (int a = 0; a < tab_pom.length; a++)
                {
                    for (int b = 0; b < tab_pom[a].length; b++)
                    {
                        obrazGlowny[a][b][0] = tab_pom[a][b][0];
                    }
                }

            }
            if (wejscia == 0)
            {
                warunek = false;
            }

        }

        return obrazGlowny;

    }

    static public int[][][] operacjaPogrubianieWypukly(int obrazGlowny[][][])
    {
        int ES[][][] = new int[8][][];
        ES[0] = OperacjeMorfologiczne.ES_Pszkielet1();
        ES[1] = OperacjeMorfologiczne.ES_Pszkielet2();
        ES[2] = OperacjeMorfologiczne.ES_Pszkielet1b();
        ES[3] = OperacjeMorfologiczne.ES_Pszkielet2b();
        ES[4] = OperacjeMorfologiczne.ES_Pszkielet1c();
        ES[5] = OperacjeMorfologiczne.ES_Pszkielet2c();
        ES[6] = OperacjeMorfologiczne.ES_Pszkielet1d();
        ES[7] = OperacjeMorfologiczne.ES_Pszkielet2d();

        int ileCzarnych = 0;
        int ileBialych = 0;

        int kolor = 1;
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {
            kolor = 1;

        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////
            for (int a = 0; a < ES.length; a++)
            {
                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        if (ES[a][x][y] != -1)
                        {
                            if (ES[a][x][y] == 1)
                            {
                                ES[a][x][y] = 0;
                            } else
                            {
                                ES[a][x][y] = 1;
                            }
                        }
                    }
                }

            }
            kolor = 0;

        }

        int rozmiar = (int) Math.round(ES[0].length - 1.0) / 2;
        int tab_pom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][1];

        for (int a = 0; a < tab_pom.length; a++)
        {
            for (int b = 0; b < tab_pom[a].length; b++)
            {
                tab_pom[a][b][0] = obrazGlowny[a][b][0];
            }
        }

        int ile[] = new int[8];
        for (int ss = 0; ss < 8; ss++)
        {
            for (int a = 0; a < ES[0].length; a++)
            {
                for (int b = 0; b < ES[0][a].length; b++)
                {
                    if (ES[ss][a][b] != -1)
                    {
                        ile[ss]++;
                    }
                }
            }
        }
        boolean warunek = true;
        while (warunek)
        {
            int wejscia = 0;

            for (int ss = 0; ss < 8; ss++)
            {

                for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
                {
                    for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                    {
                        int licznik = 0;

                        for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                        {
                            for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                            {
                                if (ES[ss][x][y] == obrazGlowny[a + aa][b + bb][0])
                                {
                                    licznik++;
                                }
                            }
                        }
                        if (licznik == ile[ss])
                        {

                            tab_pom[a][b][0] = kolor;
                            wejscia++;

                        }
                    }

                }
                /////////KOPIOWANIE Z POM D OBRAZ GLOWNY ///////
                for (int a = 0; a < tab_pom.length; a++)
                {
                    for (int b = 0; b < tab_pom[a].length; b++)
                    {
                        obrazGlowny[a][b][0] = tab_pom[a][b][0];
                    }
                }

            }
            if (wejscia == 0)
            {
                warunek = false;
            }

        }

        return obrazGlowny;
    }

    static public int[][][] operacjaPogrubianieRozgraniczenie(int obrazGlowny[][][])
    {
        int ES[][][] = new int[8][][];
        ES[0] = OperacjeMorfologiczne.ES_SSWszkielet1();
        ES[1] = OperacjeMorfologiczne.ES_SSWszkielet2();
        ES[2] = OperacjeMorfologiczne.ES_SSWszkielet1b();
        ES[3] = OperacjeMorfologiczne.ES_SSWszkielet2b();
        ES[4] = OperacjeMorfologiczne.ES_SSWszkielet1c();
        ES[5] = OperacjeMorfologiczne.ES_SSWszkielet2c();
        ES[6] = OperacjeMorfologiczne.ES_SSWszkielet1d();
        ES[7] = OperacjeMorfologiczne.ES_SSWszkielet2d();

        int ileCzarnych = 0;
        int ileBialych = 0;

        int kolor = 1;
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {
            kolor = 1;

        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////
            for (int a = 0; a < ES.length; a++)
            {
                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        if (ES[a][x][y] != -1)
                        {
                            if (ES[a][x][y] == 1)
                            {
                                ES[a][x][y] = 0;
                            } else
                            {
                                ES[a][x][y] = 1;
                            }
                        }
                    }
                }

            }
            kolor = 0;

        }

        int rozmiar = (int) Math.round(ES[0].length - 1.0) / 2;
        int tab_pom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][1];

        for (int a = 0; a < tab_pom.length; a++)
        {
            for (int b = 0; b < tab_pom[a].length; b++)
            {
                tab_pom[a][b][0] = obrazGlowny[a][b][0];
            }
        }

        int ile[] = new int[8];
        for (int ss = 0; ss < 8; ss++)
        {
            for (int a = 0; a < ES[0].length; a++)
            {
                for (int b = 0; b < ES[0][a].length; b++)
                {
                    if (ES[ss][a][b] != -1)
                    {
                        ile[ss]++;
                    }
                }
            }
        }
        boolean warunek = true;
        while (warunek)
        {
            int wejscia = 0;
            // for (int as = 0; as < 20; as++)
            {
                for (int ss = 0; ss < 8; ss++)
                {

                    for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
                    {
                        for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                        {
                            int licznik = 0;

                            for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                            {
                                for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                                {
                                    if (ES[ss][x][y] == obrazGlowny[a + aa][b + bb][0])
                                    {
                                        licznik++;
                                    }
                                }
                            }
                            if (licznik == ile[ss])
                            {

                                tab_pom[a][b][0] = kolor;
                                wejscia++;

                            }
                        }

                    }
                    /////////KOPIOWANIE Z POM D OBRAZ GLOWNY ///////
                    for (int a = 0; a < tab_pom.length; a++)
                    {
                        for (int b = 0; b < tab_pom[a].length; b++)
                        {
                            obrazGlowny[a][b][0] = tab_pom[a][b][0];
                        }
                    }

                }
                if (wejscia == 0)
                {
                    warunek = false;
                }
            }
        }

        return obrazGlowny;
    }

    static public int[][][] operacjaPruning(int obrazGlowny[][][], int ilosc)
    {
        int ES[][][] = new int[8][][];
        ES[0] = OperacjeMorfologiczne.ES_pruning1();
        ES[1] = OperacjeMorfologiczne.ES_pruning2();
        ES[2] = OperacjeMorfologiczne.ES_pruning1b();
        ES[3] = OperacjeMorfologiczne.ES_pruning2b();
        ES[4] = OperacjeMorfologiczne.ES_pruning1c();
        ES[5] = OperacjeMorfologiczne.ES_pruning2c();
        ES[6] = OperacjeMorfologiczne.ES_pruning1d();
        ES[7] = OperacjeMorfologiczne.ES_pruning2d();

        int ileCzarnych = 0;
        int ileBialych = 0;

        int kolor = -1;
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                if (obrazGlowny[a][b][0] == 0)
                {
                    ileCzarnych++;
                } else if (obrazGlowny[a][b][0] == 1)
                {
                    ileBialych++;
                }

            }
        }
        ///////////////W ZALEZNOSCI CO JEST TLEM A CO OBIEKTEM ///////////
        /////////DLA OBIEKTU O WARTOSCI 1 ////////////////
        if (ileCzarnych > ileBialych)
        {
            kolor = 0;

        } else if (ileBialych >= ileCzarnych)
        {
            /////////////DLA OBIEKTU O WARTOSCI 0//////////////
            for (int a = 0; a < ES.length; a++)
            {
                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        if (ES[a][x][y] != -1)
                        {
                            if (ES[a][x][y] == 1)
                            {
                                ES[a][x][y] = 0;
                            } else
                            {
                                ES[a][x][y] = 1;
                            }
                        }
                    }
                }

            }
            kolor = 1;

        }

        int rozmiar = (int) Math.round(ES[0].length - 1.0) / 2;
        int tab_pom[][][] = new int[obrazGlowny.length][obrazGlowny[0].length][1];

        for (int a = 0; a < tab_pom.length; a++)
        {
            for (int b = 0; b < tab_pom[a].length; b++)
            {
                tab_pom[a][b][0] = obrazGlowny[a][b][0];
            }
        }

        int ile[] = new int[8];
        for (int ss = 0; ss < 8; ss++)
        {
            for (int a = 0; a < ES[0].length; a++)
            {
                for (int b = 0; b < ES[0][a].length; b++)
                {
                    if (ES[ss][a][b] != -1)
                    {
                        ile[ss]++;
                    }
                }
            }
        }
        for(int xx =0; xx<ilosc; xx++)
        {

            {
                for (int ss = 0; ss < 8; ss++)
                {

                    for (int a = rozmiar; a < obrazGlowny.length - rozmiar; a++)
                    {
                        for (int b = rozmiar; b < obrazGlowny[a].length - rozmiar; b++)
                        {
                            int licznik = 0;

                            for (int aa = -rozmiar, x = 0; aa <= rozmiar; aa++, x++)
                            {
                                for (int bb = -rozmiar, y = 0; bb <= rozmiar; bb++, y++)
                                {
                                    if (ES[ss][x][y] == obrazGlowny[a + aa][b + bb][0])
                                    {
                                        licznik++;
                                    }
                                }
                            }
                            if (licznik == ile[ss])
                            {

                                tab_pom[a][b][0] = kolor;

                            }
                        }

                    }
                    /////////KOPIOWANIE Z POM D OBRAZ GLOWNY ///////
                    for (int a = 0; a < tab_pom.length; a++)
                    {
                        for (int b = 0; b < tab_pom[a].length; b++)
                        {
                            obrazGlowny[a][b][0] = tab_pom[a][b][0];
                        }
                    }

                }
                
            }
        }

        return obrazGlowny;

    }

}
