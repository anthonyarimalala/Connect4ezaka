package server;

import javax.swing.*;



import java.awt.*;


public class Fenetre extends JFrame{
    Client client;
    int hauteur = 6;
    int largeur = 7;
    int tour = 0;
    JPanel[][] jpan = new JPanel[hauteur][largeur];
    String[][] table = new String[hauteur][largeur];
    String j1 = "A";
    String j2 = "B";

    public Fenetre(Client client){
        
        this.setClient(client);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if(table[i][j]==null){
                    table[i][j]=".";
                }
            }
        }
        this.setSize(largeur*50,hauteur*50);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(this.getHauteur(),this.getLargeur(),5,5));
        
            for (int i = 0; i < this.getHauteur(); i++) {
                for (int j = 0; j < this.getLargeur(); j++) {
                    this.getJpan()[i][j] = new JPanel();
                    this.getJpan()[i][j].setName(Integer.toString(i)+","+Integer.toString(j));
                    this.getJpan()[i][j].addMouseListener(new Ecouteur(this,this.getJpan()[i][j]));
                    if(this.getTable()[i][j]=="."){
                        this.getJpan()[i][j].setBackground(Color.WHITE);
                    }
                    if(this.getTable()[i][j]==this.getJ1()){
                        this.getJpan()[i][j].setBackground(Color.BLACK);
                    }
                    if(this.getTable()[i][j]==this.getJ2()){
                        this.getJpan()[i][j].setBackground(Color.GRAY);
                    }
                    
                    this.add(this.getJpan()[i][j]);
                }
            }

        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        //getTable().showTable();
        repaint();
    }
    public void setTable(String[][] table) {
        this.table = table;
    }
    public String[][] getTable() {
        return table;
    }
    public JPanel[][] getJpan() {
        return jpan;
    }
    public void setJpan(JPanel[][] jpan) {
        this.jpan = jpan;
    }
    public int getHauteur() {
        return hauteur;
    }
    public int getLargeur() {
        return largeur;
    }
    public String getJ1() {
        return j1;
    }
    public String getJ2() {
        return j2;
    }

    public void setCol(String player,int colonne){
        for (int i = this.getHauteur()-1; i >= 0; i--) {
            if(this.getTable()[i][colonne]=="."){
                if (player==this.getJ1()) {
                    this.getJpan()[i][colonne].setBackground(Color.BLACK);    
                }
                else if(player==this.getJ2()){
                    this.getJpan()[i][colonne].setBackground(Color.GRAY);  
                }
                i = -1;
            }
        }
    }
    public void setPoint(String player,int colonne){
        for (int i = hauteur-1; i >= 0; i--) {
            if(table[i][colonne]=="."){
                table[i][colonne]=player;
                if (this.getTour()==0) {
                    this.setTour(1);
                }
                else if (this.getTour()==1) {
                    this.setTour(0);
                }
                i = -1;
                
            }
        }
    }
    public void addPion (String player,int colonne){
        new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                setCol(player,colonne);
                setPoint(player,colonne);
            }
        }).start();
    }
    public int getTour() {
        return tour;
    }
    public void setTour(int tour) {
        this.tour = tour;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    public void setJ1(String j1) {
        this.j1 = j1;
    }
    public void setJ2(String j2) {
        this.j2 = j2;
    }
    
    public void showTable(){
        for (int i = 0; i < jpan.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public void fin(Color color){
        for (int i = 0; i < jpan.length; i++) {
            for (int j = 0; j < jpan[0].length; j++) {
                jpan[i][j].setBackground(color);
            }
        }
        
    }

    public boolean isWinner(String player, String[][] grid){
		//check for 4 across
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 3;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player &&
					grid[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player &&
					grid[row+3][col] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean isWinner2(Color color, JPanel[][] jpan){
    //check for 4 across
    for(int row = 0; row<jpan.length; row++){
        for (int col = 0;col < jpan[0].length - 3;col++){
            if (jpan[row][col].getBackground() == color   && 
                jpan[row][col+1].getBackground() == color &&
                jpan[row][col+2].getBackground() == color &&
                jpan[row][col+3].getBackground() == color){
                return true;
            }
        }			
    }
    //check for 4 up and down
    for(int row = 0; row < jpan.length - 3; row++){
        for(int col = 0; col < jpan[0].length; col++){
            if (jpan[row][col].getBackground() == color   && 
                jpan[row+1][col].getBackground() == color &&
                jpan[row+2][col].getBackground() == color &&
                jpan[row+3][col].getBackground() == color){
                return true;
            }
        }
    }
    //check upward diagonal
    for(int row = 3; row < jpan.length; row++){
        for(int col = 0; col < jpan[0].length - 3; col++){
            if (jpan[row][col].getBackground() == color   && 
                jpan[row-1][col+1].getBackground() == color &&
                jpan[row-2][col+2].getBackground() == color &&
                jpan[row-3][col+3].getBackground() == color){
                return true;
            }
        }
    }
    //check downward diagonal
    for(int row = 0; row < jpan.length - 3; row++){
        for(int col = 0; col < jpan[0].length - 3; col++){
            if (jpan[row][col].getBackground() == color   && 
                jpan[row+1][col+1].getBackground() == color &&
                jpan[row+2][col+2].getBackground() == color &&
                jpan[row+3][col+3].getBackground() == color){
                return true;
            }
        }
    }
    return false;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}