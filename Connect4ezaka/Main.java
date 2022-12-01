package jeu;

import java.util.Scanner;

import jeu.Fenetre;

public class Main{
    
    public static void main(String[] args) {
        Fenetre fen = new Fenetre();
        //fen.fin(Color.RED);

        while (true) {
            System.out.print("n : ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            fen.addPion("A", n);
        }
        

    }
}