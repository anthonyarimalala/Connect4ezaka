package jeu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

import jeu.Fenetre;

public class Ecouteur implements MouseListener{

    Fenetre fenetre;
    int numero;

    public Ecouteur(){

    }
    public Ecouteur(Fenetre fenetre,int numero){
        this.fenetre = fenetre;
        this.numero = numero;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getX()/42);
        this.setNumero(e.getX()/42);
        System.out.println("click");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public static void showTable(char[][] table){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
