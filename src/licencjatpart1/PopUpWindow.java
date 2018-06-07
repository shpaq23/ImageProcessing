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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

/**
 *
 * @author shpaq
 */
public class PopUpWindow extends JFrame implements MouseListener
{

    private JLabel pom;
    public JButton guzik;

    public PopUpWindow(int x, int y)
    {

        this.setUndecorated(true);
        // this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pom = new JLabel("", SwingConstants.CENTER);
        pom.setForeground(new Color(128, 128, 128));
        guzik = new JButton("OK");
        guzik.setBackground(new Color(128, 128, 128));
        guzik.setForeground(Color.WHITE);
        guzik.setFocusPainted(false);
        guzik.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.guzik.addMouseListener(this);
        this.guzik.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    wylacz();

                }
            }
        });

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 200));
        //this.setLayout(new BorderLayout());
        this.add(pom, BorderLayout.CENTER);
        this.add(guzik, BorderLayout.SOUTH);
        this.getContentPane().setBackground(new Color(64, 64, 64));

        this.pack();
        this.setLocation(x, y);
        //this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(false);
        this.setResizable(false);
        this.setEnabled(false);

    }

    public void nazwa(String s)
    {
        this.pom.setText(s);

    }

    public void wlacz()
    {
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void wylacz()
    {
        this.setVisible(false);
        this.setEnabled(false);
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == this.guzik)
        {
            this.wylacz();

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
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
