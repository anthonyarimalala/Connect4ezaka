package server;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import jeu.Fenetre;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String marque;
    private Fenetre fenetre;
    private String tour = "A";

    private Client(Socket socket,String marque){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.marque = marque;
        } catch (Exception e) {
            //TODO: handle exception
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessage(){
        try {
            bufferedWriter.write(marque);
            bufferedWriter.newLine();
            bufferedWriter.flush();


            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                int messageToSend = 101;
            while (messageToSend>6 || messageToSend<0) {
                if (messageToSend==101) {
                    System.out.print("n entre [0;6] : ");
                    messageToSend = scanner.nextInt();
                }
                else{
                    System.out.println("Votre nombre n'est pas conforme!");
                    System.out.print("n entre [0;6] : ");
                    messageToSend = scanner.nextInt();
                }
            }

                bufferedWriter.write(marque+":"+messageToSend);
                
                    if(marque.equals("A")){
                        if (tour.equals("A")) {
                            fenetre.addPion("A", messageToSend);
                            setTour("B");
                        }
                        else{
                            System.out.println("Ce n'est pas votre tour!");
                        }
                        if (fenetre.isWinner("B", fenetre.getTable())) {
                            System.out.println("Vous avez gagnee");
                            fenetre.fin(Color.RED);
                        }
                       
                    }
                    else if(marque.equals("B")){
                        if (tour.equals("B")) {
                            fenetre.addPion("B", messageToSend);
                            setTour("A");
                        }
                        else{
                            System.out.println("Ce n'est pas votre tour!");
                        }
                        if (fenetre.isWinner("A", fenetre.getTable())) {
                            fenetre.fin(Color.RED);
                        }
                    }
                
                fenetre.showTable();
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (fenetre.isWinner("A", fenetre.getTable())) {
                    System.out.println("Vous avez gagnee");
                    fenetre.fin(Color.GREEN);
                }else if(fenetre.isWinner("B", fenetre.getTable())){
                    System.out.println("Vous avez gagnee");
                    fenetre.fin(Color.GREEN);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                fenetre = new Fenetre();
                String msgFromGroupChat;
                while(socket.isConnected()){
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        String[] msg = msgFromGroupChat.split(":");
                        if (fenetre.isWinner("A", fenetre.getTable())) {
                            System.out.println("A a gagnee");
                            // if(fenetre.getJpan()[0][0].getBackground()!=Color.GREEN){
                            //     fenetre.fin(Color.RED);
                            // }
                            socket.close();
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }else if(fenetre.isWinner("B", fenetre.getTable())){
                            System.out.println("B a gagnee");
                            // if(fenetre.getJpan()[0][0].getBackground()!=Color.GREEN){
                            //     fenetre.fin(Color.RED);
                            // }
                            socket.close();
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                        if(!msg[0].equals("SERVER ")){
                            System.out.println("mety");
                            int nbr = Integer.parseInt(msg[1]);
                            if(msg[0].equals("A")){
                                if(tour.equals("A")){
                                    fenetre.addPion("A", nbr);
                                   setTour("B");
                                }
                            }
                            else if(msg[0].equals("B")){
                                if(tour.equals("B")){
                                    fenetre.addPion("B", nbr);
                                  setTour("A");
                                }
                            }
                        }
                        System.out.println(msgFromGroupChat);
                    } catch (Exception e) {
                        //TODO: handle exception
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        try {
            if (bufferedReader!=null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket!=null){
                socket.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String marque = "Z";
        while (!marque.equals("A") && !marque.equals("B")) {
            System.out.print("A or B : ");
            marque = scanner.nextLine();    
        }
        Socket socket = new Socket("localhost",1234);
        Client client = new Client(socket, marque);
        client.listenForMessage();
        client.sendMessage();
    
    }
    public void setTour(String tour) {
        this.tour = tour;
    }
}
