/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licencjatpart1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author shpaq
 */
public class WczytajObr extends JPanel
{

    private BufferedImage ikonka;
    private JLabel pic;
    public String sciezka;
    private int rozmiar;
 

    public WczytajObr()
    {
       
        try
        {
            ikonka = ImageIO.read(getClass().getResourceAsStream("ikonka.jpg"));

        } catch (Exception e)
        {

        }
        pic = new JLabel(new ImageIcon(ikonka));
        this.setPreferredSize(new Dimension(100, 70));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        this.add(pic, BorderLayout.CENTER);

    }
    


}
