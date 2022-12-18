package server;

import java.awt.event.*;
import java.io.BufferedWriter;

import javax.swing.JPanel;

import server.Fenetre;

public class Ecouteur implements MouseListener{

    Fenetre fenetre;
    JPanel jPanel;

    int messageToSend;
    BufferedWriter bufferedWriter;
    String marque;
    
    public Ecouteur(Fenetre fenetre,JPanel jPanel){
        this.jPanel = jPanel;
        this.fenetre = fenetre;
    }
    



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        String[] position = jPanel.getName().split(",");
        int i = Integer.parseInt(position[0]);
        int j = Integer.parseInt(position[1]);
        this.getFenetre().getClient().sendMessage(j);
        

        // fenetre.setMessageToSend(j);
        // fenetre.addPion(this.getMarque(), j);

        System.out.println("fenetre.getMessageToSend(): "+j);
        
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



    public Fenetre getFenetre() {
        return fenetre;
    }



    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }



    public JPanel getjPanel() {
        return jPanel;
    }



    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }



    public int getMessageToSend() {
        return messageToSend;
    }



    public void setMessageToSend(int messageToSend) {
        this.messageToSend = messageToSend;
    }



    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }



    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }



    public String getMarque() {
        return marque;
    }



    public void setMarque(String marque) {
        this.marque = marque;
    }
    
    
}
