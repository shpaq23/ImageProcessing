/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author ZuzannaOlszewska
 */
abstract public class OperacjeArytmetyczne
{

    static public String typ;

    static public int[][][] normalizacja_int(int[][][] obrazGlowny)
    {

        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[2][0][2]
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
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
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
                    if (max[k] - min[k] > 0)
                    {
                        obrazGlowny[a][b][k] = (255 * (obrazGlowny[a][b][k] - min[k])) / (max[k] - min[k]);
                    }

                }
            }
        }

        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

    static public int[][][] sumowanie_ze_stala(int[][][] obrazGlowny, int wspolczynnik)
    {
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {

                    obrazGlowny[a][b][k] = obrazGlowny[a][b][k] + wspolczynnik;

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

    static public int[][][] sumowanie_z_drugim_obrazem(int[][][] obrazGlowny, int[][][] obrazWczytany)
    {

        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    obrazGlowny[a][b][k] = obrazGlowny[a][b][k] + obrazWczytany[a][b][k];

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;

    }

    static public int[][][] mnozenie_ze_stala(int[][][] obrazGlowny, double stala)
    {
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    obrazGlowny[a][b][k] = (int) Math.floor(obrazGlowny[a][b][k] * stala);

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;

    }

    static public int[][][] mnozenie_z_drugim_obrazem(int[][][] obrazGlowny, int[][][] obrazWczytany)
    {

        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    obrazGlowny[a][b][k] = obrazGlowny[a][b][k] * obrazWczytany[a][b][k];

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;

    }

    static public int[][][] mieszanie_z_drugim_obrazem(int[][][] obrazGlowny, int[][][] obrazWczytany, double wspolczynnik)
    {
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    obrazGlowny[a][b][k] = (int) Math.floor(wspolczynnik * obrazWczytany[a][b][k]) + (int) Math.floor((1 - wspolczynnik) * obrazGlowny[a][b][k]);

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;

    }

    static public int[][][] potegowanie_obrazu(int[][][] obrazGlowny, int potega)
    {
        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[2][0][2]
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
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
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
                  //  obrazGlowny[a][b][k] = (int) Math.pow(obrazGlowny[a][b][k], potega);
                    obrazGlowny[a][b][k] = (int) (255 * (Math.pow(obrazGlowny[a][b][k], potega)) / (Math.pow(max[k], potega)));

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;

    }

    static public int[][][] dzielenie_ze_stala(int[][][] obrazGlowny, double stala)
    {
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    obrazGlowny[a][b][k] = (int) Math.floor(obrazGlowny[a][b][k] / stala);

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

    static public int[][][] dzielenie_z_drugim_obrazem(int[][][] obrazGlowny, int[][][] obrazWczytany)
    {
        for (int a = 0; a < obrazGlowny.length; a++)
        {
            for (int b = 0; b < obrazGlowny[a].length; b++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (obrazWczytany[a][b][k] != 0)
                    {
                        obrazGlowny[a][b][k] = (int) Math.ceil(obrazGlowny[a][b][k] / obrazWczytany[a][b][k]);
                    } else
                    {
                        obrazGlowny[a][b][k] = obrazGlowny[a][b][k];
                    }
                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

    static public int[][][] pierwiastkowanie_obrazu(int[][][] obrazGlowny, double pierwiastek)
    {
        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[2][0][2]
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
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
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
                    //obrazGlowny[a][b][k] = (int) Math.pow(obrazGlowny[a][b][k], pierwiastek);
                    obrazGlowny[a][b][k] = (int) (255 * (Math.pow(obrazGlowny[a][b][k], pierwiastek)) / (Math.pow(max[k], pierwiastek)));

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

    static public int[][][] logarytmowanie_obrazu(int[][][] obrazGlowny)
    {
        int min[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[0][0][2]
        };
        int max[] =
        {
            obrazGlowny[0][0][0], obrazGlowny[0][0][1], obrazGlowny[2][0][2]
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
                    if (max[k] < obrazGlowny[a][b][k])
                    {
                        max[k] = obrazGlowny[a][b][k];
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

                    // obrazGlowny[a][b][k] = (int) Math.ceil(Math.log(obrazGlowny[a][b][k] + 1));
                    obrazGlowny[a][b][k] = (int) Math.ceil(255 * (Math.log(1 + obrazGlowny[a][b][k])) / Math.log(1 + max[k]));

                }
            }
        }
        if (typ.equals("GrayScale"))
        {
            for (int a = 0; a < obrazGlowny.length; a++)
            {
                for (int b = 0; b < obrazGlowny[a].length; b++)
                {
                    for (int k = 1; k < 3; k++)
                    {
                        obrazGlowny[a][b][k] = 0;

                    }
                }
            }
        }
        return obrazGlowny;
    }

}
